package com.example.backend.exception;

import com.example.backend.common.ErrorCode;
import lombok.Data;

/**
 * 自定义异常类
 */
@Data
public class MyException extends java.lang.RuntimeException {
    private final int code;
    private final String description;

    public MyException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public MyException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public MyException(ErrorCode errorCode, String description){
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

}
