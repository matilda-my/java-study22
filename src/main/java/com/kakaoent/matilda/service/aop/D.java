package com.kakaoent.matilda.service.aop;

import com.kakaoent.matilda.aop.ExcludeParam;
import com.kakaoent.matilda.aop.MogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class D {
    @MogTrace
    public void d() {
        log.warn("d");
    }
}
