package com.a602.actors.domain.chat.entity;

import java.util.ArrayList;
import java.util.List;

import com.a602.actors.global.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "chat_room")
public class ChatRoom extends BaseEntity {
	@Column(nullable = false)
	String title;

	@OneToMany(mappedBy = "chatRoom")
	List<Participants> participants = new ArrayList<>();

	public ChatRoom(String title){
		this.title = title;
	}
}
