package com.a602.actors.global.exception;

import com.a602.actors.global.exception.BaseExceptionAbs;
import com.a602.actors.global.exception.ExceptionCodeSet;

public class MontageException extends BaseExceptionAbs {
    public MontageException(ExceptionCodeSet exceptionCode) {
        super(exceptionCode);
    }
}
