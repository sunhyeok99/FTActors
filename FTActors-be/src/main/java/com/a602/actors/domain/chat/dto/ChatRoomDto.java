package com.a602.actors.domain.chat.dto;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ChatRoomDto {
	private Long id;
	private String title;

	@Builder
	@Getter
	@Setter
	public static class Response implements Comparable<Response> {
		private Long id;
		private String title;
		private LocalDateTime created_at;
		private LocalDateTime updated_at;

		@QueryProjection
		public Response(Long id, String title, LocalDateTime created_at, LocalDateTime updated_at){
			this.id = id;
			this.title = title;
			this.created_at = created_at;
			this.updated_at = updated_at;
		}

		@Override
		public int compareTo(Response r){
			return r.updated_at.compareTo(updated_at);
		}
	}
}
