package com.a602.actors.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<ErrorResponse> handleMemberException(MemberException e) {
        return makeResponseFormat(e.getExceptionCode());
    }

    @ExceptionHandler(RecruitmentException.class)
    public ResponseEntity<ErrorResponse> handleRecruitmentException(RecruitmentException e) {
        return makeResponseFormat(e.getExceptionCode());
    }

    @ExceptionHandler(ApplyException.class)
    public ResponseEntity<ErrorResponse> handleRecruitmentException(ApplyException e) {
        return makeResponseFormat(e.getExceptionCode());
    }

    @ExceptionHandler(MontageException.class)
    public ResponseEntity<ErrorResponse> handleMontageException(MontageException e) {
        return makeResponseFormat(e.getExceptionCode());
    }
    @ExceptionHandler(FileException.class)
    public ResponseEntity<ErrorResponse> handleFileException(FileException e) {
        return makeResponseFormat(e.getExceptionCode());
    }


    private ResponseEntity<ErrorResponse> makeResponseFormat(ExceptionCodeSet exceptionCode) {
        System.out.println("msg : " + exceptionCode.getMessage());
        return ResponseEntity.status(exceptionCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(exceptionCode.getStatus())
                        .message(exceptionCode.getMessage())
                        .build()
                );
    }

}
