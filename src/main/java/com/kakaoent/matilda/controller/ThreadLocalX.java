package com.kakaoent.matilda.controller;

public class ThreadLocalX {
    public static final ThreadLocal<String> THREAD_LOCAL = ThreadLocal.withInitial(() -> "리퀘스트 시작함다");

}
