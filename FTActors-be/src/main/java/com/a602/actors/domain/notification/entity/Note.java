package com.a602.actors.domain.notification.entity;

import com.a602.actors.domain.member.Member;
import com.a602.actors.global.common.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Note extends BaseEntity {

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name = "sender_id")
	private Member sender;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name = "receiver_id")
	private Member receiver;

	private String content;

	private NotificationType notificationType;

	private char isRead;


	// public Note of(Member sender, Member receiver, String content){
	//
	// }

	public enum NotificationType {
		COMMENT, FOLLOW, CHAT
	}
}
