package com.yly.consumer.controller.security;

public class SimpleReponse {
    private String message;

    public SimpleReponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
