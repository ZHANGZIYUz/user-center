package com.example.backend.exception;


import com.example.backend.common.ErrorCode;
import com.example.backend.common.Result;
import com.example.backend.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler(MyException.class)
    public Result myExceptionHandler(MyException myException) {
        log.error("myException:", myException.getMessage(), myException);
        return ResultUtils.fail(myException.getCode(), myException.getMessage(), myException.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionHandler(RuntimeException runtimeException) {
        log.error("runtimeException:", runtimeException);
        return ResultUtils.system(ErrorCode.PARAMS_ERROR, runtimeException.getMessage());
    }
}
