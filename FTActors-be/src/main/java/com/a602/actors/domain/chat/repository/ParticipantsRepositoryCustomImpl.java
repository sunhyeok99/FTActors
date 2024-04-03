package com.a602.actors.domain.chat.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.a602.actors.domain.chat.entity.ChatRoom;
import com.a602.actors.domain.chat.entity.Participants;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ParticipantsRepositoryCustomImpl {
	private final ChatRoomRepository chatRoomRepository;
	private final ParticipantsRepository participantsRepository;

	public void saveAllInvitedMembers(Long chatRoomId, List<Long> memberIdList){
		ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
			.orElseThrow(() -> new RuntimeException("채팅방을 찾을 수 없습니다."));

		List<Participants> participants = memberIdList.stream()
			.map(memberId -> Participants.builder()
				.chatRoom(chatRoom)
				.memberId(memberId)
				.build())
			.collect(Collectors.toList());

		participantsRepository.saveAll(participants);
	}
}
