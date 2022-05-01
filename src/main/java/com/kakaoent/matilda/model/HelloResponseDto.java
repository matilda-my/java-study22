package com.kakaoent.matilda.model;

import lombok.Getter;

@Getter
public class HelloResponseDto {
    private final String name;
    private final int amount;

    public HelloResponseDto(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }
}
