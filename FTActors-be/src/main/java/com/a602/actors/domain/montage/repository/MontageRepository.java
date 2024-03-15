package com.a602.actors.domain.montage.repository;

import com.a602.actors.domain.montage.dto.MontageCommentDto;
import com.a602.actors.domain.montage.entity.Comment;
import com.a602.actors.domain.montage.entity.Montage;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MontageRepository {

    List<Montage> getAllMontages();
    void saveMontage(String title, String url);
    boolean addLike(Long montageId, Long memberId);
    List<Comment> findCommentAndReplies(Long montageId);
    void saveComment(MontageCommentDto.CreateRequest req);
    void updateComment(MontageCommentDto.UpdateRequest req);
    void deleteComment(Long montageId, Long commentId);

}
