package com.a602.actors.global.exception;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {

    private final Integer status;
    private final String message;

}
