package com.a602.actors.domain.notification.dto;

import java.time.LocalDateTime;

import com.a602.actors.domain.notification.entity.Note;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class NoteDto {

	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		// Todo : member의 unique 값을 뭐로 저장하냐에 따라서 id 타입 바꾸기
		// 지금은 String login_id를 참조

		// String sender_id;
		String receiver_id;
		String content;
		String type;
		char isRead;
		LocalDateTime created_at;

		public static Response from(Note note){
			return Response.builder()
				// .sender_id(note.getSender().getMemberId())
				.receiver_id(note.getReceiver().getLoginId())
				.content(note.getContent())
				.type(note.getNotificationType().name())
				.isRead(note.getIsRead())
				.created_at(note.getCreatedAt())
				.build();
		}
	}
}
