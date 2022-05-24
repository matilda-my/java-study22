package com.kakaoent.matilda.etc;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThreadLocalX {
    public static final ThreadLocal<String> THREAD_LOCAL = ThreadLocal.withInitial(() -> "리퀘스트 시작함다");
    public static final ThreadLocal<Integer> THREAD_LOCAL_CURRENT_DEPTH = ThreadLocal.withInitial(() -> 0);
    public static final ThreadLocal<List<MethodWithDepth>> THREAD_LOCAL_METHOD = ThreadLocal.withInitial(ArrayList::new);

    @Data
    public static class MethodWithDepth {
        private final Integer depth;
        private final String msg;
    }
}
