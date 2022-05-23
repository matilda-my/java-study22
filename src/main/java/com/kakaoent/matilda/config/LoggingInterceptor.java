package com.kakaoent.matilda.config;

import com.kakaoent.matilda.etc.ThreadLocalX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

public class LoggingInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("[REQUEST] path: {}, HTTP method: {}, parameters: {}", request.getServletPath(), request.getMethod(),
                request.getParameterMap().entrySet().stream()
                        .map(e -> e.getKey() + ": " + String.join(", ", e.getValue()))
                        .collect(Collectors.joining("; ")));

        ThreadLocalX.THREAD_LOCAL.set("REQUEST: " + request.getParameter("txId"));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ContentCachingResponseWrapper cachingResponseWrapper = (ContentCachingResponseWrapper) response;
        logger.debug("[RESPONSE] status: {}, response: {}", response.getStatus(), new String(cachingResponseWrapper.getContentAsByteArray()));

        String prevValue = ThreadLocalX.THREAD_LOCAL.get();
        ThreadLocalX.THREAD_LOCAL.set(prevValue + " | RESPONSE: " + response.getStatus());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.debug("I AM DONE: " + ThreadLocalX.THREAD_LOCAL.get());

        ThreadLocalX.THREAD_LOCAL.remove();
    }
}
