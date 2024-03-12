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

	// private final ObjectMapper objectMapper;
	// private Map<String, ChatRoomDto> chatRooms;
	//
	// @PostConstruct
	// private void init(){
	// 	chatRooms = new HashMap<>();
	// }
	//
	// public ChatRoomDto createRoom(String title){
	// 	// uuid 대신, pk값 중 max를 리턴해서 +1로 쓰자. (이거 트랜젝션 관리 잘 해야될듯)
	// 	String randomId = UUID.randomUUID().toString();
	// 	ChatRoomDto chatRoomDto = ChatRoomDto.builder()
	// 		.roomId(randomId)
	// 		.title(title)
	// 		.build();
	//
	// 	chatRooms.put(randomId, chatRoomDto);
	//
	// 	return chatRoomDto;
	// }
	//
	// public List<ChatRoomDto> findAllChatRooms(){
	// 	return new ArrayList<>(chatRooms.values());
	// }
	//
	// public ChatRoomDto findRoomById(String roomId){
	// 	return chatRooms.get(roomId);
	// }
	//
	// public <T> void sendMessage(WebSocketSession session, T message){
	// 	try{
	// 		session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
	// 	} catch(IOException e){
	// 		log.error("메시지 전송 에러 !!! " + e.getMessage());
	// 	}
	// }
}
