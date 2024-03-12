// package com.a602.actors.domain.chat.repository;
//
// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.LinkedHashMap;
// import java.util.List;
// import java.util.Map;
//
// import org.springframework.stereotype.Repository;
//
// import com.a602.actors.domain.chat.dto.ChatRoomDto;
// import com.querydsl.jpa.impl.JPAQueryFactory;
//
// import jakarta.annotation.PostConstruct;
// import lombok.RequiredArgsConstructor;
//
// @Repository
// @RequiredArgsConstructor
// public class ChatRoomRepositoryImpl{
// 	// Todo : Entity 만들어서 JPA 연결하기
// 	private Map<String, ChatRoomDto> chatRoomDtoMap;
// 	private final JPAQueryFactory queryFactory;
//
// 	@PostConstruct
// 	private void init(){
// 		chatRoomDtoMap = new LinkedHashMap<>();
// 	}
//
// 	public List<ChatRoomDto> findAllChatRooms(){
// 		// 생성순서 최근 순으로 반환
// 		List<ChatRoomDto> chatRoomDtos = new ArrayList<>(chatRoomDtoMap.values());
// 		Collections.reverse(chatRoomDtos);
// 		return chatRoomDtos;
// 	}
//
// 	public ChatRoomDto findChatRoomById(String id){
// 		return chatRoomDtoMap.get(id);
// 	}
//
// 	public ChatRoomDto createChatRoom(String name){
// 		// static Method임
// 		ChatRoomDto chatRoomDto = ChatRoomDto.createChatRoomDto(name);
// 		chatRoomDtoMap.put(chatRoomDto.getRoomId(), chatRoomDto);
// 		return chatRoomDto;
// 	}
// }
