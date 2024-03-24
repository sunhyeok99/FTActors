package com.a602.actors.domain.notification.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.a602.actors.domain.notification.service.NotificationService;
import com.a602.actors.global.common.dto.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/notify")
public class NotificationController {
	private final NotificationService notificationService;

	// Todo : member 완성 후 로그인한 유저의 id값 가져오기 (uri까지 수정 필요)
	// id : member의 PK 값
	@GetMapping(value = "/subscribe/{id}", produces = "text/event-stream")
	public ApiResponse<SseEmitter> subscribe(@PathVariable Long id, @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId){
		SseEmitter sseEmitter = notificationService.subscribe(id, lastEventId);
		return new ApiResponse<>(HttpStatus.OK.value(), "SSE 연결 성공", sseEmitter);
	}

}
