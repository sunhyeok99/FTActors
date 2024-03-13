package com.a602.actors.domain.montage.dto;

import com.a602.actors.domain.montage.entity.Comment;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class MontageCommentDto {


    @Builder
    @Getter
    @Setter
    public static class CreateRequest{
        private final Long montageId;
        private final Long parentId; //NULL 이면..
        private final String content;
        private final Boolean isDeleted = false;
    }

    @Builder
    @Getter
    @Setter
    public static class UpdateRequest{
        private final Long montageId;
        private final Long commentId;
        private final String content;
    }

    @Builder
    @Getter
    @Setter
    public static class Response{

        private final Long memberId;
        private final Long commentId;
        private final String content;
        private final Boolean isDeleted;
        private final List<MontageReply> reply;

        private final LocalDateTime created_at;
        private final LocalDateTime updated_at;

        //@QueryProjection
        public Response(Long memberId, Long commentId, String content, Boolean isDeleted, List<MontageReply> reply,
                               LocalDateTime created_at, LocalDateTime updated_at){
            this.memberId = memberId;
            this.commentId = commentId;
            this.content = content;
            this.isDeleted = isDeleted;
            this.reply = reply;
            this.created_at = created_at;
            this.updated_at = updated_at;
        }

        public static MontageCommentDto.Response toDto(Comment comment, List<MontageReply> replies){
            return Response
                    .builder()
                    .commentId(comment.getId())
                    .memberId(comment.getMember().getId())
                    .content(comment.getContent())
                    .isDeleted(comment.isDeleted())
                    .reply(replies)
                    .created_at(comment.getCreatedAt())
                    .updated_at(comment.getUpdatedAt())
                    .build();
        }
    }

    @Builder
    @Getter
    @Setter
    public static class MontageReply{
        private final Long commentId;
        private final Long memberId;
        private final String content;
        private final Boolean isDeleted;

        private final LocalDateTime created_at;
        private final LocalDateTime updated_at;

        @QueryProjection
        public MontageReply(Long commentId, Long memberId, String content,
                            Boolean isDeleted,
                            LocalDateTime created_at, LocalDateTime updated_at){
            this.commentId = commentId;
            this.memberId = memberId;
            this.content = content;
            this.isDeleted = isDeleted;
            this.created_at = created_at;
            this.updated_at = updated_at;
        }

        public static MontageReply toReply(Comment comment){
            return MontageReply
                    .builder()
                    .commentId(comment.getId())
                    .memberId(comment.getMember().getId())
                    .content(comment.getContent())
                    .isDeleted(comment.isDeleted())
                    .created_at(comment.getCreatedAt())
                    .updated_at(comment.getUpdatedAt())
                    .build();
        }
    }


}
