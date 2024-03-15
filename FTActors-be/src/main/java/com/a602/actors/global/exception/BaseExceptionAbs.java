package com.a602.actors.global.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public abstract class BaseExceptionAbs extends RuntimeException implements BaseException {

    private ExceptionCodeSet exceptionCode;

    @Override
    public ExceptionCodeSet getExceptionCode() {
            return this.exceptionCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.exceptionCode.getHttpStatus();
    }

    @Override
    public String getMessage() {
        return this.exceptionCode.getMessage();
    }

    @Override
    public Integer status() {
        return this.exceptionCode.getStatus();
    }
}
