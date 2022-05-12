package com.kakaoent.matilda.config;

import com.kakaoent.matilda.controller.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
//    private List<HandlerInterceptor> interceptors;

//    public WebMvcConfig(List<HandlerInterceptor> interceptors) {
//        this.interceptors = interceptors;
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor())
                .order(-1)
                .addPathPatterns("/*")
                .excludePathPatterns("/hello");
//        System.out.println(interceptors.stream().map(interceptor -> interceptor.getClass().getName()).collect(Collectors.joining(",")));
    }
}
