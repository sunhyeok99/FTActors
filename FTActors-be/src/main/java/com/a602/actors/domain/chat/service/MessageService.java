package com.a602.actors.domain.chat.service;

import org.springframework.stereotype.Service;

import com.a602.actors.domain.chat.dto.ChatMessageDto;
import com.a602.actors.domain.chat.repository.MessageRepositoryCustomImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {
	private final MessageRepositoryCustomImpl messageRepositoryCustom;

	public void saveMessage(ChatMessageDto messageDto){
		messageRepositoryCustom.saveMessage(messageDto);
	}
}
