package com.a602.actors.global.exception;

import org.springframework.http.HttpStatus;

public interface BaseException {
    ExceptionCodeSet getExceptionCode();
    HttpStatus getHttpStatus();
    String getMessage();
    Integer status();
}
