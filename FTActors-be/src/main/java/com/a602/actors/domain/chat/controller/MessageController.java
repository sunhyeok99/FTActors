package com.a602.actors.domain.chat.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;

import com.a602.actors.domain.chat.dto.ChatMessageDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {
	private final RabbitTemplate rabbitTemplate;
	// private final MessageServie messageServie;

	// 채팅방 입장
	@MessageMapping("chat.enter.{roomId}")
	public void enterUser(@DestinationVariable Long roomId, @Payload ChatMessageDto message){
		message.setMessage(message.getSender() + "님이 입장하셨습니다.");
		rabbitTemplate.convertAndSend("chat.exchange", "enter.room." + roomId, message);
		// messageService.saveMessage(message);
	}

	// 채팅방 대화
	@MessageMapping("chat.talk.{roomId}")
	public void talkUser(@DestinationVariable Long roomId, @Payload ChatMessageDto message){
		rabbitTemplate.convertAndSend("chat.exchange", "*.room." + roomId, message);
		// messageService.saveMessage(message);
	}

	// 채팅방 퇴장
	@MessageMapping("chat.exit.{roomId}")
	public void exitUser(@DestinationVariable Long roomId, @Payload ChatMessageDto message){
		message.setMessage(message.getSender() + "님이 퇴장하셨습니다.");
		rabbitTemplate.convertAndSend("chat.exchange", "exit.room." + roomId, message);
	}



	// private final SimpMessagingTemplate messagingTemplate;
	//
	// @MessageMapping("/message")		// uri : /pub/message
	// public void message(ChatMessageDto chatMessageDto){
	// 	if(chatMessageDto.getType().equals(ChatMessageDto.MessageType.ENTER)){
	// 		chatMessageDto.setMessage(chatMessageDto.getSender() + "님이 입장하셨습니다.");
	// 	}
	//
	// 	log.info("convert to ========== {}", "/sub/room/" + chatMessageDto.getRoomId());
	// 	log.info("message    ========== {}", chatMessageDto.getMessage());
	//
	// 	messagingTemplate.convertAndSend("/sub/room/" + chatMessageDto.getRoomId(), chatMessageDto.getMessage());
	// }
}
