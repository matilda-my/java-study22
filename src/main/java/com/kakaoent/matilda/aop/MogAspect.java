package com.kakaoent.matilda.aop;

import com.kakaoent.matilda.etc.ThreadLocalX;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
public class MogAspect {

    @Around("@annotation(com.kakaoent.matilda.aop.MogTrace)")
    public void printMog(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Map<String, String> params = getMethodParameterKeyMap(pjp, signature);

        String msg = String.format("%s.%s(%s)", pjp.getTarget().getClass().getName(), signature.getMethod().getName(),
                params.keySet().stream().map(k -> k + "=" + params.get(k)).collect(Collectors.joining(", ")));

        pushMethodTrace(msg);
        increaseCurrentDepth();

        Object result = pjp.proceed();

        decreaseCurrentDepth();
    }

    private void decreaseCurrentDepth() {
        Integer currentDepth = ThreadLocalX.THREAD_LOCAL_CURRENT_DEPTH.get();
        ThreadLocalX.THREAD_LOCAL_CURRENT_DEPTH.set(currentDepth - 1);
    }

    private void increaseCurrentDepth() {
        Integer currentDepth = ThreadLocalX.THREAD_LOCAL_CURRENT_DEPTH.get();
        ThreadLocalX.THREAD_LOCAL_CURRENT_DEPTH.set(currentDepth + 1);
    }

    private void pushMethodTrace(String msg) {
        List<ThreadLocalX.MethodWithDepth> prev = ThreadLocalX.THREAD_LOCAL_METHOD.get();
        Integer currentDepth = ThreadLocalX.THREAD_LOCAL_CURRENT_DEPTH.get();

        prev.add(new ThreadLocalX.MethodWithDepth(currentDepth, msg));
    }

    private Map<String, String> getMethodParameterKeyMap(ProceedingJoinPoint pjp, MethodSignature signature) {
        Map<String, String> params = new HashMap<>();
        Object[] args = pjp.getArgs();
        Parameter[] methodParameters = signature.getMethod().getParameters();
        int i = 0;

        for (String name: signature.getParameterNames()) {
            params.put(name, getParameterValue(args[i], methodParameters[i]));
            i++;
        }

        return params;
    }

    private String getParameterValue(Object arg, Parameter methodParameter) {
        String value = arg.toString();
        Annotation[] annotations = methodParameter.getAnnotations();
        if (annotations.length > 0 && isExcludeParamAnnotation(annotations)) {
            value = "*";
        }
        return value;
    }

    private boolean isExcludeParamAnnotation(Annotation[] annotations) {
        Optional<Annotation> optionalAnnotation = Arrays.stream(annotations)
                .filter(annotation -> annotation.annotationType().equals(ExcludeParam.class)).findAny();
        return optionalAnnotation.isPresent();
    }
}
