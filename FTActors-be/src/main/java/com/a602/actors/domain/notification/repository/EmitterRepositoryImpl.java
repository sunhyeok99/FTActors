package com.a602.actors.domain.notification.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Repository
public class EmitterRepositoryImpl implements EmitterRepository {
	private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
	public final Map<String, Object> eventCache = new ConcurrentHashMap<>();

	@Override
	public SseEmitter save(String emitterId, SseEmitter sseEmitter) {
		emitters.put(emitterId, sseEmitter);
		return sseEmitter;
	}

	@Override
	public void saveEventCache(String eventCacheId, Object event){
		eventCache.put(eventCacheId, event);
	}

	@Override
	public void deleteById(String emitterId) {
		emitters.remove(emitterId);
	}

	@Override
	public Map<String, Object> findAllEventCacheStartWithId(String memberId) {
		return eventCache.entrySet().stream()
			.filter(entry -> entry.getKey().startsWith(memberId))
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	@Override
	public Map<String, SseEmitter> findAllEmitterByIdStartWith(String memberId) {
		return emitters.entrySet().stream()
			.filter(entry -> entry.getKey().startsWith(memberId))
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
}
