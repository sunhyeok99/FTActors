package com.a602.actors.domain.montage.service;


import com.a602.actors.domain.montage.dto.MontageCommentDto;
import com.a602.actors.domain.montage.entity.Comment;
import com.a602.actors.domain.montage.repository.MontageRepository;
import com.a602.actors.domain.montage.repository.MontageRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MontageCommentService {

    private final MontageRepository montageRepository;

    public MontageCommentService(MontageRepository montageRepository){
        //this.MontageCommentRepositoryImpl = MontageCommentRepositoryImpl;
        this.montageRepository = montageRepository;
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
