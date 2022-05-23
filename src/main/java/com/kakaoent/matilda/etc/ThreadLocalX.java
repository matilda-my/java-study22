package com.kakaoent.matilda.etc;

public class ThreadLocalX {
    public static final ThreadLocal<String> THREAD_LOCAL = ThreadLocal.withInitial(() -> "리퀘스트 시작함다");

}
