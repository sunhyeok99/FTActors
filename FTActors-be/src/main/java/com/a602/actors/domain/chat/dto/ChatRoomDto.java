package com.a602.actors.domain.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChatRoomDto {
	private String id;
	private String title;
}
