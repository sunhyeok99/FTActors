package com.a602.actors.domain.chat.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatRoomDto {
	private String roomId;
	private String name;

	public static ChatRoomDto createChatRoomDto(String name){
		return ChatRoomDto.builder()
			.roomId(UUID.randomUUID().toString())
			.name(name)
			.build();
	}
}
