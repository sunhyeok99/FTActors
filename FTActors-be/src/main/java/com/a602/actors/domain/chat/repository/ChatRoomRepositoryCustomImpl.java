package com.a602.actors.domain.chat.repository;

import static com.a602.actors.domain.chat.entity.QChatRoom.*;
import static com.a602.actors.domain.chat.entity.QParticipants.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.a602.actors.domain.chat.dto.ChatRoomDto;
import com.a602.actors.domain.chat.dto.QChatRoomDto_Response;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Repository
@Transactional
@RequiredArgsConstructor
public class ChatRoomRepositoryCustomImpl implements ChatRoomRepositoryCustom{
	private final JPAQueryFactory jpaQueryFactory;

	public List<ChatRoomDto.Response> getMyChatRoomList(Long memberId){
		return jpaQueryFactory.
			select(new QChatRoomDto_Response(chatRoom.id, chatRoom.title, chatRoom.createdAt, chatRoom.updatedAt))
			.from(chatRoom)
			.innerJoin(participants)
			.on(chatRoom.id.eq(participants.chatRoom.id))
			.where(participants.memberId.eq(memberId))
			.fetch();
	}
}
