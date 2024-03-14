package com.a602.actors.domain.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipantsDto {
	private Long chatRoomId;
	private Long memberId;
}
