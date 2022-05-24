package com.kakaoent.matilda.service.aop;

import com.kakaoent.matilda.aop.MogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class A {
    private final B b;
    private final D d;

    public A(B b, D d) {
        this.b = b;
        this.d = d;
    }

    @MogTrace
    public void a() {
        log.warn("a");
        b.b("b");
        d.d();
    }
}
