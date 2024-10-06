package com.example.backend.model.domain.request;

import lombok.Data;

/**
 * 用户注册请求体
 */
@Data
public class UserRegisterRequest {

    private String userAccount;

    private String userPassword;

    private String checkPassword;
}
