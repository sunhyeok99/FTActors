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


    private ResponseEntity<ErrorResponse> makeResponseFormat(ExceptionCodeSet exceptionCode) {
        return ResponseEntity.status(exceptionCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(exceptionCode.getStatus())
                        .message(exceptionCode.getMessage())
                        .build()
                );
    }

}
