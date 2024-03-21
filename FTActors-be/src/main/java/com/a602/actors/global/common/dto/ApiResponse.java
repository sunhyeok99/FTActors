package com.a602.actors.global.common.dto;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private final Integer status;
    private final String message;
    private T data;

    public ApiResponse(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ApiResponse<String> error(Integer status, String message) {
        return new ApiResponse<>(status, message, "");
    }
}
