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
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleException(Exception e) {
//        return makeResponseFormat(ExceptionCodeSet.INTERNAL_SERVER_ERROR);
//    }

    private ResponseEntity<ErrorResponse> makeResponseFormat(ExceptionCodeSet exceptionCode) {
        return ResponseEntity.status(exceptionCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .code(exceptionCode.getCode())
                        .message(exceptionCode.getMessage())
                        .build()
                );
    }

}
