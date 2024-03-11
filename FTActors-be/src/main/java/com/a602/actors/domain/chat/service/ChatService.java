package com.a602.actors.domain.chat.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.a602.actors.domain.chat.dto.ChatRoomDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
	private final ObjectMapper objectMapper;
	private Map<String, ChatRoomDto> chatRooms;

	@PostConstruct
	private void init(){
		chatRooms = new HashMap<>();
	}

	public ChatRoomDto createRoom(String name){
		// uuid 대신, pk값 중 max를 리턴해서 +1로 쓰자. (이거 트랜젝션 관리 잘 해야될듯)
		String randomId = UUID.randomUUID().toString();
		ChatRoomDto chatRoomDto = ChatRoomDto.builder()
			.roomId(randomId)
			.name(name)
			.build();

		chatRooms.put(randomId, chatRoomDto);

		return chatRoomDto;
	}

	public List<ChatRoomDto> findAllChatRooms(){
		return new ArrayList<>(chatRooms.values());
	}

	public ChatRoomDto findRoomById(String roomId){
		return chatRooms.get(roomId);
	}

	public <T> void sendMessage(WebSocketSession session, T message){
		try{
			session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
		} catch(IOException e){
			log.error("메시지 전송 에러 !!! " + e.getMessage());
		}
	}
}
