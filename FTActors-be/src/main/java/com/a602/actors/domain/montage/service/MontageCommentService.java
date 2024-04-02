package com.a602.actors.domain.montage.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.a602.actors.domain.montage.dto.MontageReportDto;
import com.a602.actors.global.common.config.FileUtil;
import com.a602.actors.global.common.enums.FolderType;
import com.a602.actors.global.exception.ExceptionCodeSet;
import com.a602.actors.global.exception.FileException;
import com.a602.actors.global.exception.MontageException;
import org.springframework.stereotype.Service;

import com.a602.actors.domain.montage.dto.MontageCommentDto;
import com.a602.actors.domain.montage.entity.Comment;
import com.a602.actors.domain.montage.entity.Montage;
import com.a602.actors.domain.montage.repository.MontageRepository;
import com.a602.actors.domain.notification.document.Notify;
import com.a602.actors.domain.notification.service.NotificationService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

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
        // String originalString = montage.getTitle() + "에 새로운 댓글이 달렸습니다.";
        // String utf8String = new String(originalString.getBytes(StandardCharsets.UTF_8));
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

    public String report(MontageReportDto.CreateReport req,
                         MultipartFile file,
                         Long montageId,
                         Long commentId) throws IOException, MontageException, FileException {

        Long reporterId = 1L;

        // 파일이 없는 경우
        if(file == null){
            throw new MontageException(ExceptionCodeSet.FILE_NOT_EXISTS);
        }

        // montageId에 맞는 데이터가 없으면 예외 처리
        Comment comment = montageRepository.getComment(montageId, commentId)
                .orElseThrow(() -> new MontageException(ExceptionCodeSet.ENTITY_NOT_EXISTS));

        // 자기 자신은 신고 불가
        if(Objects.equals(comment.getMember().getId(), reporterId)){
            throw new MontageException(ExceptionCodeSet.SELF_REPORT);
        }

        //MemberRepository.findByNickname();
        System.out.println("HELLO");
        String savedName = FileUtil.makeFileName(file.getOriginalFilename());

        String url = FileUtil.uploadFile(file, savedName, FolderType.REPORT_PATH);
        log.info("URL : {}", url);
        montageRepository.addCommentReport(1L, montageId, commentId, req.getReason(), url);

        return "";
    }


}
