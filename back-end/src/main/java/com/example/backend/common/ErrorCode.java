package com.example.backend.common;

/**
 * 错误码
 */
public enum ErrorCode {

    PARAMS_ERROR(400, "请求参数错误", ""),
    NULL_ERROR(401, "请求数据为空", ""),
    NO_AUTH(402, "无权限", ""),
    NO_LOGIN(403, "未登录", ""),
    SYSTEM_ERROR(500, "系统内部异常", ""),
    LOGININ_ERROR(404, "账号或密码输入错误", ""),
    NOTMATCH_ERROR(405, "账号密码不匹配", "");


    private final int code;
    private final String message;
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
