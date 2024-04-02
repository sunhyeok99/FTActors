package com.a602.actors.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a602.actors.domain.chat.entity.ChatMessage;

@Repository
public interface MessageRepository extends JpaRepository<ChatMessage, Long> {

}
