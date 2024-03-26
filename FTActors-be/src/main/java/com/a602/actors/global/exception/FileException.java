package com.a602.actors.global.exception;

import com.a602.actors.global.exception.BaseExceptionAbs;
import com.a602.actors.global.exception.ExceptionCodeSet;

public class FileException extends BaseExceptionAbs {
    public FileException(ExceptionCodeSet exceptionCode) {
        super(exceptionCode);
    }
}
