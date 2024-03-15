package com.a602.actors.domain.chat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a602.actors.domain.chat.dto.ChatRoomDto;
import com.a602.actors.domain.chat.service.ChatService;
import com.a602.actors.global.common.dto.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
	private final ChatService chatService;

	@PostMapping
	public ApiResponse<String> createRoom(@RequestParam String title){
		chatService.createChatRoom(title);
		return new ApiResponse<>(HttpStatus.OK.value(), "채팅방 생성 완료", "생성 완료");
	}

	@GetMapping("/all")
	public ApiResponse<List<ChatRoomDto>> findAllChatRooms(){
		return new ApiResponse<>(HttpStatus.OK.value(), "모든 채팅방 리스트 조회 성공", chatService.findAllChatRooms());
	}

	@GetMapping("/id")
	public ApiResponse<ChatRoomDto> findRoomById(@RequestParam Long roomId){
		return new ApiResponse<>(HttpStatus.OK.value(), "id에 해당하는 채팅방 조회 성공", chatService.findRoomById(roomId));
	}

	// Todo : 채팅 메시지 암호화 및 메시지큐 활용한 저장

}
