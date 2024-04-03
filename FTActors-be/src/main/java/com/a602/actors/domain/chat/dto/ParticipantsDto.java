package com.a602.actors.domain.chat.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ParticipantsDto {
	private Long chatRoomId;
	private Long memberId;

	public static ParticipantsDto of(final long chatRoomId, final long memberId) {
		return ParticipantsDto.builder()
			.chatRoomId(chatRoomId)
			.memberId(memberId)
			.build();
	}

	// public static List<ParticipantsDto> of(final Long chatRoomId, final List<Long> memberIdList){
	// 	return memberIdList.stream()
	// 		.map(memberId -> ParticipantsDto.builder()
	// 			.chatRoomId(chatRoomId)
	// 			.memberId(memberId)
	// 			.build())
	// 		.collect(Collectors.toList());
	// }
}
