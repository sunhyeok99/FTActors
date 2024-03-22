package com.a602.actors.domain.chat.mapper;

import com.a602.actors.domain.chat.dto.ParticipantsDto;
import com.a602.actors.domain.chat.entity.Participants;

// @Mapper
public interface ParticipantsMapper {
	Participants ParticipantsDtoToParticipants(ParticipantsDto participantsDto);
}
