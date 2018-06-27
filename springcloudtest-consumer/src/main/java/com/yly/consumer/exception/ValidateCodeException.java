package com.yly.consumer.exception;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {
    private String msg;

    public ValidateCodeException(String message) {
        super(message);
        this.msg = message;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
