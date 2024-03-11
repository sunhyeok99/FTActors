package com.a602.actors.domain.chat.controller;

import java.util.List;

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
	public ChatRoomDto createRoom(@RequestParam String name){
		return chatService.createRoom(name);
	}

	@GetMapping("/all")
	public List<ChatRoomDto> findAllChatRooms(){
		return chatService.findAllChatRooms();
	}

	@GetMapping("/id")
	public ChatRoomDto findRoomById(@RequestParam String roomId){
		return chatService.findRoomById(roomId);
	}
}
