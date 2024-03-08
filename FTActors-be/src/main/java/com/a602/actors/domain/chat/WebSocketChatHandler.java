package com.a602.actors.domain.chat;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {
	private final ObjectMapper objectMapper;
	private final ChatService chatService;
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		log.info("WebSocketChatHandler ========== payload {}", payload);
		// TextMessage textMessage = new TextMessage("환영합니다~!!");
		// session.sendMessage(textMessage);

		// Todo : 여기 아래 코드 이해하기
		ChatMessageDto chatMessageDto = objectMapper.readValue(payload, ChatMessageDto.class);
		ChatRoomDto chatRoomDto = chatService.findRoomById(chatMessageDto.getRoomId());
		chatRoomDto.handleActions(session, chatMessageDto, chatService);
	}
}
