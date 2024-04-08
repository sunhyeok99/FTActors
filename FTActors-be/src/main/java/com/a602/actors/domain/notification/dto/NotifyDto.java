package com.a602.actors.domain.notification.dto;

import java.time.LocalDateTime;


import com.a602.actors.domain.notification.document.Notify;
import lombok.Builder;
import lombok.Getter;

// @Getter
// @Builder
// @AllArgsConstructor
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotifyDto {
	// Todo : member의 unique 값을 뭐로 저장하냐에 따라서 id 타입 바꾸기
	// 지금은 Long id를 참조

	@Getter
	@Builder
	public static class Response {
		String id;
		Long receiverId;
		String content;
		String type;
		char isRead;
		LocalDateTime createdAt;

		public static NotifyDto.Response from(Notify notify){
			return NotifyDto.Response.builder()
				.id(notify.getId().toString())
				.receiverId(notify.getReceiverId())
				.content(notify.getContent())
				.type(notify.getNotificationType().name())
				.isRead(notify.getIsRead())
				.createdAt(notify.getCreatedAt())
				.build();
		}
	}
}
