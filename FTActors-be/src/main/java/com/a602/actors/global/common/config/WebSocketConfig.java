package com.a602.actors.global.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry){
		registry.setPathMatcher(new AntPathMatcher("."));	// url을 chat/room/3 -> chat.room.3으로 참조하기 위한 설정
		registry.setApplicationDestinationPrefixes("/pub");		// 메시지 발행 요청 prefix (메시지 전송)
		// registry.enableSimpleBroker("/sub");	// 메시지 구독 요청 prefix
		registry.enableStompBrokerRelay("/queue", "/topic", "/exchange", "/amq/queue");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry){
		registry.addEndpoint("/ws-stomp").setAllowedOrigins("*");
			// .withSockJS();	// 이거 있으면 ws://localhost:8080/api/ws-stomp 접속 안됨.. 왜지?
	}
}
