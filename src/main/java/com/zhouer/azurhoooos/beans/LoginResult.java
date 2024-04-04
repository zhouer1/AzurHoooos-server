package com.zhouer.azurhoooos.beans;

import lombok.Data;

import java.util.List;

@Data
public class LoginResult {
    private String msg;
    private boolean isSuccess;
    private String statusCode;
    private String token;
    private List<String> access;
}
