package com.a602.actors.domain.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.a602.actors.domain.chat.dto.ChatMessageDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MessageController {
	private final SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/message")		// uri : pub/message
	public void message(ChatMessageDto chatMessageDto){
		if(chatMessageDto.getType().equals(ChatMessageDto.MessageType.ENTER)){
			chatMessageDto.setMessage(chatMessageDto.getSender() + "님이 입장하셨습니다.");
		}

		messagingTemplate.convertAndSend("/sub/room/" + chatMessageDto.getRoomId(), chatMessageDto.getMessage());
	}
}
