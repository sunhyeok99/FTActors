package com.a602.actors.domain.chat.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;

import com.a602.actors.domain.chat.dto.ChatMessageDto;
import com.a602.actors.domain.chat.service.MessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {
	private final RabbitTemplate rabbitTemplate;
	private final MessageService messageService;
	// private final NotificationService notificationService;

	// 채팅방 입장
	@MessageMapping("chat.enter.{roomId}")
	public void enterUser(@DestinationVariable Long roomId, @Payload ChatMessageDto message){
		message.setMessage(message.getSender() + "님이 입장하셨습니다.");
		rabbitTemplate.convertAndSend("chat.exchange", "enter.room." + roomId, message);
		messageService.saveMessage(message);
	}

	// 채팅방 대화
	@MessageMapping("chat.talk.{roomId}")
	public void talkUser(@DestinationVariable Long roomId, @Payload ChatMessageDto message){
		rabbitTemplate.convertAndSend("chat.exchange", "*.room." + roomId, message);
		messageService.saveMessage(message);
	}

	// 채팅방 퇴장
	@MessageMapping("chat.exit.{roomId}")
	public void exitUser(@DestinationVariable Long roomId, @Payload ChatMessageDto message){
		message.setMessage(message.getSender() + "님이 퇴장하셨습니다.");
		rabbitTemplate.convertAndSend("chat.exchange", "exit.room." + roomId, message);
		messageService.saveMessage(message);
	}

	// receiver()는 단순히 큐에 들어온 메세지를 소비만 한다.
	@RabbitListener(queues = "chat.queue")
	public void receive(ChatMessageDto chatMessageDto) {
		log.info("receive ============ chatDto.getMessage() = {}",chatMessageDto.getMessage());
		// notificationService.send((long)chatMessageDto.getSender(), Notify.NotificationType.CHAT, "새로운 채팅 메시지가 있습니다.");
	}
}
