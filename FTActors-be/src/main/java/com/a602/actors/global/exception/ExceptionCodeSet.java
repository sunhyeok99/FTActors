package com.a602.actors.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCodeSet {

    // 유저
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, 1000, "회원이 존재하지 않습니다."),

    // 공고
    RECRUITMENT_NOT_FOUND(HttpStatus.BAD_REQUEST, 2000, "존재하지 않는 공고입니다"),

    // 지원
    APPLY_NOT_FOUND(HttpStatus.BAD_REQUEST, 3000, "존재하지 않는 지원내역입니다."),;

    private final HttpStatus httpStatus;
    private final Integer status;
    private final String message;


    public static ExceptionCodeSet findExceptionByCode(String code) {
        for (ExceptionCodeSet exceptionCode : ExceptionCodeSet.values()) {
            if (exceptionCode.getStatus().equals(code)) return exceptionCode;
        }
        return null;
    }

}
