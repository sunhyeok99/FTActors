package com.a602.actors.domain.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChatMessageDto {
	public enum MessageType {
		ENTER, TALK, EXIT
	}

	private String roomId;
	private Long sender;
	private String message;
	private char isRead;
	private MessageType type;
}
