package com.a602.actors.domain.montage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.a602.actors.domain.montage.dto.MontageCommentDto;
import com.a602.actors.domain.montage.entity.Comment;
import com.a602.actors.domain.montage.entity.Montage;
import com.a602.actors.domain.montage.repository.MontageRepository;
import com.a602.actors.domain.notification.document.Notify;
import com.a602.actors.domain.notification.service.NotificationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MontageCommentService {

    private final MontageRepository montageRepository;
    private final NotificationService notificationService;

    public MontageCommentService(MontageRepository montageRepository, NotificationService notificationService){
        //this.MontageCommentRepositoryImpl = MontageCommentRepositoryImpl;
        this.montageRepository = montageRepository;
        this.notificationService = notificationService;
    }

    public List<MontageCommentDto.Response> getAllComments(Long montageId){

        List<MontageCommentDto.Response> allComments = new ArrayList<>();

        List<Comment> result = montageRepository.findCommentAndReplies(montageId);

        // 자기가 자기 자신의 댓글을 달면 어떻게 되나요..
        for(Comment comment : result){
            Long referenceId = comment.getId();
            List<MontageCommentDto.MontageReply> replies = new ArrayList<>();
            for(Comment findReply : result){
                if(findReply.getReferenceId() == null || !findReply.getReferenceId().equals(referenceId))
                    continue;

                replies.add(MontageCommentDto.MontageReply.toReply(findReply));
            }

            if(comment.getReferenceId() == null)
                allComments.add(MontageCommentDto.Response.toDto(comment, replies));
        }

        return allComments;
    }

    public String writeComment(MontageCommentDto.CreateRequest req){
        log.info("WRITE COMMENT ");
        montageRepository.saveComment(req);

        Montage montage = montageRepository.getMontage(req.getMontageId()).get();
        // 몽타쥬 올린 사람한테 댓글 알림 전송
        notificationService.send(montage.getMember().getId(), Notify.NotificationType.COMMENT, montage.getTitle() + "에 새로운 댓글이 달렸습니다.");

        return "";
    }

    public String updateComment(MontageCommentDto.UpdateRequest req){
        log.info("UPDATE COMMENT ");
        montageRepository.updateComment(req);

        return "";
    }

    public String deleteComment(Long montageId, Long commentId){
        // 댓글을 지워도 답글은 놔두도록 해야한다..
        log.info("DELETE COMMENT ");
        montageRepository.deleteComment(montageId, commentId);

        return "";
    }

}
