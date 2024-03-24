package com.a602.actors.domain.notification.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.notification.dto.NoteDto;
import com.a602.actors.domain.notification.entity.Note;
import com.a602.actors.domain.notification.repository.EmitterRepositoryImpl;
import com.a602.actors.domain.notification.repository.NoteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
	private static final Long DEFAULT_TIMEOUT = 60L * 60 * 1000;
	private final EmitterRepositoryImpl emitterRepository;
	private final NoteRepository noteRepository;

	public SseEmitter subscribe(Long memberId, String lastEventId){
		String id = memberId + "_" + System.currentTimeMillis();
		SseEmitter sseEmitter = emitterRepository.save(id, new SseEmitter(DEFAULT_TIMEOUT));

		sseEmitter.onCompletion(() -> emitterRepository.deleteById(id));
		sseEmitter.onTimeout(() -> emitterRepository.deleteById(id));
		// sseEmitter.onError();

		sendToClient(sseEmitter, id, "EventStream Created. memberId = {" + memberId + "}");

		if(!lastEventId.isEmpty()){
			Map<String, Object> events = emitterRepository.findAllEventCacheStartWithId(String.valueOf(memberId));
			events.entrySet().stream()
				.filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
				.forEach(entry -> sendToClient(sseEmitter, entry.getKey(), entry.getValue()));
		}

		return sseEmitter;
	}

	// 알림이 필요한 곳에서 이 함수를 호출하면 됩니다.
	public void send(Member sender, Member receiver, Note.NotificationType notificationType, String content){
		Note notification = noteRepository.save(createNote(sender, receiver, notificationType, content));
		// receiver = 현재 로그인 한 유저 = 알림 받을 사람
		String receiverId = receiver.getMemberId();

		Map<String, SseEmitter> sseEmitters = emitterRepository.findAllEmitterByIdStartWith(receiverId);
		sseEmitters.forEach(
			(key, sseEmitter) -> {
				emitterRepository.saveEventCache(key, notification);
				sendToClient(sseEmitter, key, NoteDto.Response.from(notification));
			}
		);
	}

	private Note createNote(Member sender, Member receiver, Note.NotificationType notificationType, String content) {
		// Todo : sender 있는 경우, 없는 경우 나누기
		return Note.builder()
			.sender(sender)
			.receiver(receiver)
			.notificationType(notificationType)
			.content(content)
			.isRead('F')
			.build();
	}

	private void sendToClient(SseEmitter sseEmitter, String id, Object data){
		try{
			sseEmitter.send(SseEmitter.event()
				.id(id)
				.name("sse")
				.data(data));
		} catch (IOException e){
			emitterRepository.deleteById(id);
			throw new RuntimeException("연결 오류");
		}
	}
}
