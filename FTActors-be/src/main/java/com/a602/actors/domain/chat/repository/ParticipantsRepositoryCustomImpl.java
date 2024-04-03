package com.a602.actors.domain.chat.repository;

import static com.a602.actors.domain.chat.entity.QParticipants.*;
import static com.a602.actors.domain.member.QMember.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.a602.actors.domain.chat.dto.ParticipantsDto;
import com.a602.actors.domain.chat.entity.ChatRoom;
import com.a602.actors.domain.chat.entity.Participants;
import com.a602.actors.domain.member.dto.MemberDtoForChat;
import com.a602.actors.domain.member.dto.QMemberDtoForChat;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ParticipantsRepositoryCustomImpl {
	private final ChatRoomRepository chatRoomRepository;
	private final ParticipantsRepository participantsRepository;
	private final JPAQueryFactory jpaQueryFactory;

	public void saveAllInvitedMembers(ParticipantsDto.Request inviteRequest){
		ChatRoom chatRoom = chatRoomRepository.findById(inviteRequest.getChatRoomId())
			.orElseThrow(() -> new RuntimeException("채팅방을 찾을 수 없습니다."));

		List<Participants> participants = inviteRequest.getMemberIdList().stream()
			.map(memberId -> Participants.builder()
				.chatRoom(chatRoom)
				.memberId(memberId)
				.build())
			.collect(Collectors.toList());

		participantsRepository.saveAll(participants);
	}

	public List<MemberDtoForChat> getAllParticipants(Long chatRoomId){
		return jpaQueryFactory.select(new QMemberDtoForChat(member.id, member.stageName, member.profileImage))
			.from(participants)
			.innerJoin(member)
			.on(participants.memberId.eq(member.id))
			.where(participants.chatRoom.id.eq(chatRoomId))
			.fetch();
	}
}
