package com.a602.actors.domain.montage.repository;

import com.a602.actors.domain.montage.dto.MontageCommentDto;
import com.a602.actors.domain.montage.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MontageCommentRepository {
    List<Comment> findCommentAndReplies(Long montageId);
    void saveComment(MontageCommentDto.CreateRequest req);
    void updateComment(MontageCommentDto.UpdateRequest req);
}
