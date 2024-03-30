package com.a602.actors.domain.chat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ApiResponse<String> createChatRoom(@RequestParam String title){
		chatService.createChatRoom(title);
		return new ApiResponse<>(HttpStatus.OK.value(), "채팅방 생성 완료", "");
	}

	@GetMapping("/room/all")
	public ApiResponse<List<ChatRoomDto>> getAllChatRooms(){
		return new ApiResponse<>(HttpStatus.OK.value(), "모든 채팅방 리스트 조회 성공", chatService.findAllChatRooms());
	}

	@GetMapping("/room/mylist")
	public ApiResponse<List<ChatRoomDto.Response>> getMyChatRoomList(@RequestParam Long memberId){
		// Todo : 사용자의 id는 security 만들어지면 SecurityContextHolder에서 가져오는 것으로 변경
		return new ApiResponse<>(HttpStatus.OK.value(), "현재 참여중인 채팅방 리스트", chatService.getMyChatRoomList(memberId));
	}

	@GetMapping("/room/id")
	public ApiResponse<ChatRoomDto> getChatRoomByRoomId(@RequestParam Long roomId){
		return new ApiResponse<>(HttpStatus.OK.value(), "id에 해당하는 채팅방 조회 성공", chatService.findRoomByRoomId(roomId));
	}

	@PostMapping("/room/in")
	public ApiResponse<String> joinChat(@RequestParam Long roomId, @RequestParam Long memberId){
		// Todo : 사용자의 id는 security 만들어지면 SecurityContextHolder에서 가져오는 것으로 변경
		chatService.joinChat(roomId, memberId);
		return new ApiResponse<>(HttpStatus.CREATED.value(), "채팅방에 접속 성공", "");
	}

	@DeleteMapping("/room/out")
	public ApiResponse<String> quitChat(@RequestParam Long roomId, @RequestParam Long memberId){
		// Todo : 사용자의 id는 security 만들어지면 SecurityContextHolder에서 가져오는 것으로 변경
		chatService.quitChat(roomId, memberId);
		return new ApiResponse<>(HttpStatus.OK.value(), "채팅방 퇴장 성공", "");
	}

	// Todo : 채팅 메시지 암호화 및 메시지큐 활용한 저장

}
