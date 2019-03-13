package com.ligx.utils;

import com.ligx.annotation.Resilience;
import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

/**
 * Author: ligongxing.
 * Date: 2019/03/13.
 */
public class MethodProvider {

    public static Method getFallbackMethod(JoinPoint joinPoint) {
        Class<?> declaringClass = joinPoint.getTarget().getClass();
        Method resilienceMethod = AopUtil.getMethod(joinPoint);

        String fallbackName = getFallbackName(resilienceMethod);

        Class<?>[] fallbackParameterTypes = resilienceMethod.getParameterTypes();
        Class<?>[] extendFallbackParameterTypes = Arrays.copyOf(fallbackParameterTypes, fallbackParameterTypes.length + 1);
        extendFallbackParameterTypes[fallbackParameterTypes.length] = Throwable.class;

        Optional<Method> fallbackMethod = AopUtil.getMethod(declaringClass, fallbackName, fallbackParameterTypes);
        Optional<Method> exFallbackMethod = AopUtil.getMethod(declaringClass, fallbackName, extendFallbackParameterTypes);
        Method method = fallbackMethod.orElse(exFallbackMethod.orElse(null));
        if (method == null) {
            throw new RuntimeException("fallback method wasn't found: " + fallbackName + "(" + Arrays.toString(fallbackParameterTypes) + ")");
        }
        return method;
    }

    private static String getFallbackName(Method resilienceMethod) {
        return resilienceMethod.getAnnotation(Resilience.class).fallbackMethod();
    }
}
