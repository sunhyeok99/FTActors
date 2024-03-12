package com.a602.actors.domain.montage.service;


import com.a602.actors.domain.montage.dto.MontageCommentDto;
import com.a602.actors.domain.montage.dto.MontageDto;
import com.a602.actors.domain.montage.entity.Comment;
import com.a602.actors.domain.montage.repository.MontageCommentRepository;
import com.a602.actors.domain.montage.repository.MontageCommentRepositoryImpl;
import com.a602.actors.domain.montage.repository.MontageCommentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MontageCommentService {

    private final MontageCommentRepositoryImpl MontageCommentRepositoryImpl;
    private final MontageCommentRepository montageCommentRepository;

    public MontageCommentService(MontageCommentRepositoryImpl MontageCommentRepositoryImpl, MontageCommentRepository montageCommentRepository){
        this.MontageCommentRepositoryImpl = MontageCommentRepositoryImpl;
        this.montageCommentRepository = montageCommentRepository;
    }

    public List<MontageCommentDto.Response> getAllComments(Long montageId){

        List<MontageCommentDto.Response> allComments = new ArrayList<>();

        List<Comment> result = montageCommentRepository.findByMontageId(montageId);

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

    public String writeComment(MontageCommentDto.Request req){




        return "";
    }

    public String updateComment(Long montageId){
        return null;
    }

    public String deleteComment(Long montageId){
        // 댓글을 지워도 답글은 놔두도록 해야한다..

        return null;
    }

}
