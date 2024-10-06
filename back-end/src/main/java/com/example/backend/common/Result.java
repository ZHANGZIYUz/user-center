package com.example.backend.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 *
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {
    private int code;// 0表示成功 1表示失败
    private T data;
    private String message;
    private String description;

    public Result(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public Result(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public Result(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.description = errorCode.getDescription();
    }

    public Result(ErrorCode errorCode,String message) {
        this.code = errorCode.getCode();
        this.message = message;
        this.description = errorCode.getDescription();
    }

    public Result(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }
}
