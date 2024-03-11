package com.a602.actors.domain.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto {
	public enum MessageType {
		ENTER, TALK
	}

	private String roomId;
	private String sender;
	private String message;
	private MessageType type;
}
