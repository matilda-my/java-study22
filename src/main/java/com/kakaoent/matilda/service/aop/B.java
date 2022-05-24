package com.kakaoent.matilda.service.aop;

import com.kakaoent.matilda.aop.MogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class B {
    private final C c;

    public B(C c) {
        this.c = c;
    }

    @MogTrace
    public void b(String b) {
        log.warn("b" + b);
        c.c(b, "c");
    }
}
