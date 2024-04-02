package com.a602.actors.domain.notification.repository;

import java.util.Map;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface EmitterRepository {
	SseEmitter save(String emitterId, SseEmitter sseEmitter);
	void saveEventCache(String eventCacheId, Object event);
	void deleteById(String emitterId);
	Map<String, Object> findAllEventCacheStartWithId(String memberId);
	Map<String, SseEmitter> findAllEmitterByIdStartWith(String memberId);
}
