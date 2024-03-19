package com.a602.actors.domain.chat.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.a602.actors.domain.chat.dto.ChatRoomDto;
import com.a602.actors.domain.chat.entity.ChatRoom;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChatMapper {
	List<ChatRoomDto> ChatRoomListToChatRoomDtoList(List<ChatRoom> chatRoomList);
	ChatRoomDto ChatRoomToChatRoomDto(ChatRoom chatRoomList);
}
