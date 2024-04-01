package com.a602.actors.domain.notification.document;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Document
@Getter
@Builder
public class Notify {
	@Id
	private ObjectId id;
	private Long receiverId;
	private String content;
	private NotificationType notificationType;
	private char isRead;
	@CreatedDate
	private LocalDateTime createdAt;

	public enum NotificationType {
		COMMENT, FOLLOW, CHAT
	}
}
