package com.a602.actors.domain.notification.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.a602.actors.domain.notification.document.Notify;
import com.a602.actors.domain.notification.service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/notify")
public class NotificationController {
	private final NotificationService notificationService;

	// Todo : member 완성 후 로그인한 유저의 id값 가져오기 (uri까지 수정 필요)
	// id : member의 PK 값
	@GetMapping(value = "/subscribe/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public SseEmitter subscribe(@PathVariable Long id, @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId){
		log.info("subscribe !!! id : {}, Last-Event-ID : {}", id, lastEventId);
		SseEmitter sseEmitter = notificationService.subscribe(id, lastEventId);

		log.info("NotificationController ============ subscribe completed, sseEmitter : {}", sseEmitter);
		return sseEmitter;
	}

	// @PostMapping("/read")
	// public ApiResponse<String> markAsRead(){
	//
	// }

	// 디버깅 용 메서드
	@PostMapping("/sendtest/{senderId}/{receiverId}")
	public void sendTest(@PathVariable Long senderId, @PathVariable Long receiverId){
		// Member sender = Member.builder()
		// 	.memberId(String.valueOf(senderId))
		// 	.build();
		// Member receiver = Member.builder()
		// 	.memberId(String.valueOf(receiverId))
		// 	.build();

		notificationService.send(receiverId, Notify.NotificationType.CHAT, "test content");
	}
}
