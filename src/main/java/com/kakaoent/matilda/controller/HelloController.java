package com.kakaoent.matilda.controller;

import com.kakaoent.matilda.model.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }

    @GetMapping("/api1")
    public String api1(@RequestParam String txId) {
        return "your txId is : " + txId;
    }

    @GetMapping("/api2/api3")
    public void api2(@RequestParam String txId) {
        throw new NullPointerException();
    }
}
