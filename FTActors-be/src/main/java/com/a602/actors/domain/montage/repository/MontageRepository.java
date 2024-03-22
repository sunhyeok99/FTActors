package com.a602.actors.domain.montage.repository;

import com.a602.actors.domain.montage.dto.MontageCommentDto;
import com.a602.actors.domain.montage.dto.MontageDto;
import com.a602.actors.domain.montage.entity.Comment;
import com.a602.actors.domain.montage.entity.Montage;

import java.util.List;

public interface MontageRepository {

    List<MontageDto.Montages> getAllMontages();
    boolean addLike(Long montageId, Long memberId);
    List<Montage> getMyMontages(Long memberId);
    Montage getMontage(Long montageId);
    void saveMontage(String originalName, String saveName, String url);
    void deleteMontage(Long montageId);
    List<Comment> findCommentAndReplies(Long montageId);
    void saveComment(MontageCommentDto.CreateRequest req);
    void updateComment(MontageCommentDto.UpdateRequest req);
    void deleteComment(Long montageId, Long commentId);

    void addReport(Long reporterId, Long reporteeId, String reason, String link);

}
