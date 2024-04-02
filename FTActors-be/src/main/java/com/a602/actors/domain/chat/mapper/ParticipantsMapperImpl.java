package com.a602.actors.domain.chat.mapper;

import org.springframework.stereotype.Component;

import com.a602.actors.domain.chat.dto.ParticipantsDto;
import com.a602.actors.domain.chat.entity.ChatRoom;
import com.a602.actors.domain.chat.entity.Participants;

@Component
public class ParticipantsMapperImpl implements ParticipantsMapper {

	@Override
	public Participants ParticipantsDtoToParticipants(ParticipantsDto participantsDto) {
		return Participants.builder()
			.chatRoom(ParticipantsDtoToChatRoom(participantsDto))
			.memberId(participantsDto.getMemberId())
			.build();
	}

	protected ChatRoom ParticipantsDtoToChatRoom(ParticipantsDto participantsDto){
		return new ChatRoom(participantsDto.getChatRoomId());
	}
}
