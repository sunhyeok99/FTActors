package com.a602.actors.domain.montage.service;


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

    public List<MontageDto.MontageComments> getAllComments(Long montageId){

        List<MontageDto.MontageComments> allComments = new ArrayList<>();

        List<Comment> result = montageCommentRepository.findByMontageId(montageId);

        for(Comment comment : result){
            Long referenceId = comment.getId();
            List<MontageDto.MontageReply> replies = new ArrayList<>();
            for(Comment findReply : result){
                if(findReply.getReferenceId() == null || !findReply.getReferenceId().equals(referenceId))
                    continue;
                
                replies.add(MontageDto.MontageReply.toReply(findReply));
            }

            if(comment.getReferenceId() == null)
                allComments.add(MontageDto.MontageComments.toDto(comment, replies));

        }

        return allComments;
    }
}
