package com.a602.actors.domain.chat.repository;

import java.util.List;

import com.a602.actors.domain.chat.dto.ChatRoomDto;

public interface ChatRoomRepositoryCustom {
	List<ChatRoomDto.Response> getMyChatRoomList(Long memberId);
}
