package com.example.backend.common;

public class ResultUtils {
    public static <T> Result<T> success(T data) {
        return new Result<>(0, data, "success");
    }

    public static Result fail(ErrorCode errorCode) {
        return new Result(errorCode.getCode(), null, errorCode.getMessage(), errorCode.getDescription());
    }

    public static Result system(ErrorCode errorCode, String message) {
        return new Result(errorCode.getCode(), null, message, errorCode.getDescription());
    }

    public static Result fail(int code, String message, String description) {
        return new Result(code, null, message, description);
    }

    public static <T> Result<T> fail(T data) {
        return new Result<>(1, data, "error");
    }
}
