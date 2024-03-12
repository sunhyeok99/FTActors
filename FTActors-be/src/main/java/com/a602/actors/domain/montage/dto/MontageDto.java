package com.a602.actors.domain.montage.dto;

import com.a602.actors.domain.montage.entity.Comment;
import com.a602.actors.domain.montage.entity.Montage;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class MontageDto {

    @Builder
    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class Montages{
        private final String title;
        private final String link;
        private final Integer likeCount;

        public static MontageDto.Montages toDto(Montage montage){
            return Montages.builder()
                    .title(montage.getTitle())
                    .link(montage.getLink())
                    .build();
        }
    }
    @Builder
    @Getter
    @Setter
    public static class MontageComments{
        private final Long memberId;
        private final Long commentId;
        private final String content;
        private final List<MontageReply> reply;

        private final LocalDateTime created_at;
        private final LocalDateTime updated_at;

        //@QueryProjection
        public MontageComments(Long memberId, Long commentId, String content, List<MontageReply> reply,
                               LocalDateTime created_at, LocalDateTime updated_at){
            this.memberId = memberId;
            this.commentId = commentId;
            this.content = content;
            this.reply = reply;
            this.created_at = created_at;
            this.updated_at = updated_at;
        }

        public static MontageDto.MontageComments toDto(Comment comment, List<MontageReply> replies){
            return MontageComments
                    .builder()
                    .commentId(comment.getId())
                    .memberId(comment.getMember().getId())
                    .content(comment.getContent())
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

        private final LocalDateTime created_at;
        private final LocalDateTime updated_at;

        @QueryProjection
        public MontageReply(Long commentId, Long memberId, String content,
                            LocalDateTime created_at, LocalDateTime updated_at){
            this.commentId = commentId;
            this.memberId = memberId;
            this.content = content;
            this.created_at = created_at;
            this.updated_at = updated_at;
        }

        public static MontageReply toReply(Comment comment){
            return MontageReply
                    .builder()
                    .commentId(comment.getId())
                    .memberId(comment.getMember().getId())
                    .content(comment.getContent())
                    .created_at(comment.getCreatedAt())
                    .updated_at(comment.getUpdatedAt())
                    .build();
        }
    }

}
