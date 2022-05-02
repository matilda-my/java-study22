package com.kakaoent.matilda.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler(Exception.class)
    public String handler() {
        return "넌 나에게 에러를 줬어";
    }
}
