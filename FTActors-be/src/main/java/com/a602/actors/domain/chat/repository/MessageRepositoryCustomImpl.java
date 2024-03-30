package com.a602.actors.domain.chat.repository;

import org.springframework.stereotype.Repository;

import com.a602.actors.domain.chat.dto.ChatMessageDto;
import com.a602.actors.domain.chat.entity.ChatMessage;
import com.a602.actors.domain.member.repository.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MessageRepositoryCustomImpl implements MessageRepositoryCustom {
	private final ChatRoomRepository chatRoomRepository;
	private final MemberRepository memberRepository;
	private final MessageRepository messageRepository;

	public void saveMessage(ChatMessageDto messageDto){
		ChatMessage message = ChatMessage.builder()
			.chatRoom(chatRoomRepository.findById(Long.parseLong(messageDto.getRoomId()))
				.orElseThrow(() -> new EntityNotFoundException("채팅방이 존재하지 않습니다.")))
			.member(memberRepository.findById(messageDto.getSender())
				.orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다.")))
			.content(messageDto.getMessage())
			.isRead('F')
			.type(messageDto.getType().name())
			.build();

		messageRepository.save(message);
	}
}
