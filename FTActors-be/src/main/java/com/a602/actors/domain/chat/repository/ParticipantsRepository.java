package com.a602.actors.domain.chat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.a602.actors.domain.chat.entity.Participants;

public interface ParticipantsRepository extends JpaRepository<Participants, Long> {
	Optional<Participants> getParticipantsByChatRoomIdAndId(Long roomId, Long id);
}
