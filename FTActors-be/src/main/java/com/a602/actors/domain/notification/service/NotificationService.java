package com.a602.actors.domain.notification.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.a602.actors.domain.notification.document.Notify;
import com.a602.actors.domain.notification.dto.NotifyDto;
import com.a602.actors.domain.notification.mapper.NotifyMapper;
import com.a602.actors.domain.notification.repository.EmitterRepositoryImpl;
import com.a602.actors.domain.notification.repository.NotifyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
	private static final Long DEFAULT_TIMEOUT = 60L * 60 * 1000;
	private final EmitterRepositoryImpl emitterRepository;
	// private final NoteRepository noteRepository;
	private final NotifyRepository notifyRepository;
	// private final ObjectMapper objectMapper;
	private final NotifyMapper notifyMapper;

	public SseEmitter subscribe(Long memberId, String lastEventId){
		log.info("NotificationService ============ start subscribe..");
		String id = memberId + "_" + System.currentTimeMillis();
		log.info("NotificationService ============ id : {}, lastEventId: {}", id, lastEventId);
		SseEmitter sseEmitter = emitterRepository.save(id, new SseEmitter(DEFAULT_TIMEOUT));
		// SseEmitter sseEmitter = new SseEmitter(DEFAULT_TIMEOUT);

		log.info("NotificationService ============ save emitter completed");

		sseEmitter.onCompletion(() -> emitterRepository.deleteById(id));
		sseEmitter.onTimeout(() -> emitterRepository.deleteById(id));
		// sseEmitter.onError();

		// sendToClient(sseEmitter, id, "EventStream Created. memberId = {" + memberId + "}");
		sendToClient(sseEmitter, id, "젭라 살려줏메");
		log.info("NotificationService ============ sendToClient completed");

		if(!lastEventId.isEmpty()){
			Map<String, Object> events = emitterRepository.findAllEventCacheStartWithId(String.valueOf(memberId));
			events.entrySet().stream()
				.filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
				.forEach(entry -> sendToClient(sseEmitter, entry.getKey(), entry.getValue()));
		}

		return sseEmitter;
	}

	// 알림이 필요한 곳에서 이 함수를 호출하면 됩니다.
	public void send(Long receiverId, Notify.NotificationType notificationType, String content){
		log.info("NotificationService ============= send() 시작");

		Notify notification = notifyRepository.save(createNotify(receiverId, notificationType, content));
		// Notify notification = createNotify(receiverId, notificationType, content);

		// receiver = 현재 로그인 한 유저 = 알림 받을 사람
		// String receiverId = receiver.getMemberId();

		Map<String, SseEmitter> sseEmitters = emitterRepository.findAllEmitterByIdStartWith(String.valueOf(receiverId));
		sseEmitters.forEach(
			(key, sseEmitter) -> {
				emitterRepository.saveEventCache(key, notification);
				sendToClient(sseEmitter, key, NotifyDto.Response.from(notification));
			}
		);

		log.info("NotificationService ============= send() 끝");
	}

	private Notify createNotify(Long receiverId, Notify.NotificationType notificationType, String content) {
		// Todo : sender 있는 경우, 없는 경우 나누기
		return Notify.builder()
			// .sender(sender)
			.receiverId(receiverId)
			.notificationType(notificationType)
			.content(content)
			.isRead('F')
			.build();
	}

	private void sendToClient(SseEmitter sseEmitter, String id, Object data){
		try{
			log.info("sendToClient ============ sendToClient start");
			sseEmitter.send(SseEmitter.event()
				.id(id)
				.name("sse")
				.data(data));
			log.info("sendToClient ============ sendToClient completed");
		} catch (IOException e){
			log.info("sendToClient ============ sendToClient failed");
			emitterRepository.deleteById(id);
			throw new RuntimeException("연결 오류");
		}
	}

	public List<NotifyDto.Response> getNotifyList(Long loginId){
		List<Notify> notifyList = notifyRepository.findByReceiverIdAndIsRead(loginId, 'F');
		return notifyMapper.NotifyToNotifyDtoResponse(notifyList);
	}
}
