package com.a602.actors.domain.chat.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ParticipantsDto {
	private Long chatRoomId;
	private Long memberId;

	public static ParticipantsDto of(final long roomId, final long memberId) {
		return ParticipantsDto.builder()
			.chatRoomId(roomId)
			.memberId(memberId)
			.build();
	}
}
