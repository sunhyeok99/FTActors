package com.a602.actors.domain.chat.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatRoomDto {
	private String roomId;
	private String title;

	public static ChatRoomDto createChatRoomDto(String title){
		return ChatRoomDto.builder()
			.roomId(UUID.randomUUID().toString())
			.title(title)
			.build();
	}
}
