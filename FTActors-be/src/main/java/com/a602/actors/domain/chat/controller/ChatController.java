package com.a602.actors.domain.chat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a602.actors.domain.chat.dto.ChatRoomDto;
import com.a602.actors.domain.chat.service.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
	private final ChatService chatService;

	@PostMapping
	public ResponseEntity<String> createRoom(@RequestParam String title){
		chatService.createChatRoom(title);
		return new ResponseEntity<>("생성 완료", HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<ChatRoomDto>> findAllChatRooms(){
		return new ResponseEntity<>(chatService.findAllChatRooms(), HttpStatus.OK);
	}

	@GetMapping("/id")
	public ResponseEntity<ChatRoomDto> findRoomById(@RequestParam Long roomId){
		return new ResponseEntity<>(chatService.findRoomById(roomId), HttpStatus.OK);
	}

	// Todo : 채팅 메시지 암호화 및 메시지큐 활용한 저장

}
