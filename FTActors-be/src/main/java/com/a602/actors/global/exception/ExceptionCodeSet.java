package com.a602.actors.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCodeSet {

    // 유저
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "1000", "회원이 존재하지 않습니다."),

    // 공고
    RECRUITMENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "2000", "존재하지 않는 공고입니다"),




    //ETC
    OK(HttpStatus.OK, "0000", "성공적으로 동작하였습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "9000", "서버 동작 중 에러가 발생했습니다."),;


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


    public static ExceptionCodeSet findExceptionByCode(String code) {
        for (ExceptionCodeSet exceptionCode : ExceptionCodeSet.values()) {
            if (exceptionCode.getCode().equals(code)) return exceptionCode;
        }
        return null;
    }

}
