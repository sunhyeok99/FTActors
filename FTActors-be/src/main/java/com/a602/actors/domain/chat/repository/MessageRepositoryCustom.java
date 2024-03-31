package com.a602.actors.domain.chat.repository;

import com.a602.actors.domain.chat.dto.ChatMessageDto;

public interface MessageRepositoryCustom {
	public void saveMessage(ChatMessageDto messageDto);
}
