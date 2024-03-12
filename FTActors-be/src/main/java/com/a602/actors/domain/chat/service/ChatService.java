package com.a602.actors.domain.chat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.a602.actors.domain.chat.dto.ChatRoomDto;
import com.a602.actors.domain.chat.entity.ChatRoom;
import com.a602.actors.domain.chat.mapper.ChatMapper;
import com.a602.actors.domain.chat.repository.ChatRoomRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
	private final ChatRoomRepository chatRoomRepository;
	private final ChatMapper chatMapper;

	public void createChatRoom(String title){
		chatRoomRepository.save(new ChatRoom(title));
	}

	public List<ChatRoomDto> findAllChatRooms(){
		return chatMapper.ChatRoomListToChatRoomDtoList(chatRoomRepository.findAll());
	}

	public ChatRoomDto findRoomById(Long id){
		ChatRoom chatRoom =  chatRoomRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("존재하지 않는 채팅방입니다."));
		return chatMapper.ChatRoomToChatRoomDto(chatRoom);
	}
}
