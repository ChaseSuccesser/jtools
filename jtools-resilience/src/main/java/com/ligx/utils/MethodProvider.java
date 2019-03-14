package com.ligx.utils;

import com.ligx.FallbackMethod;
import com.ligx.MethodExecutionAction;
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

    public static FallbackMethod getFallbackMethod(JoinPoint joinPoint) {
        Class<?> declaringClass = joinPoint.getTarget().getClass();
        Method resilienceMethod = AopUtil.getMethod(joinPoint);

        String fallbackName = getFallbackName(resilienceMethod);

        Class<?>[] fallbackParameterTypes = resilienceMethod.getParameterTypes();
        Class<?>[] extendFallbackParameterTypes = Arrays.copyOf(fallbackParameterTypes, fallbackParameterTypes.length + 1);
        extendFallbackParameterTypes[fallbackParameterTypes.length] = Throwable.class;

        Optional<Method> fallbackMethod = ReflectUtil.getMethod(declaringClass, fallbackName, fallbackParameterTypes);
        Optional<Method> exFallbackMethod = ReflectUtil.getMethod(declaringClass, fallbackName, extendFallbackParameterTypes);
        Method method = fallbackMethod.orElse(exFallbackMethod.orElse(null));
        if (method == null) {
            throw new RuntimeException("fallback method wasn't found: " + fallbackName + "(" + Arrays.toString(fallbackParameterTypes) + ")");
        }
        return new FallbackMethod(method, exFallbackMethod.isPresent());
    }

    private static String getFallbackName(Method resilienceMethod) {
        return resilienceMethod.getAnnotation(Resilience.class).fallbackMethod();
    }

    public static Object[] createArgsForFallback(MethodExecutionAction fallbackAction, Throwable exception) {
        Object[] args = fallbackAction.getArgs();
        boolean exFallback = fallbackAction.isExtendFallback();
        if (exFallback) {
            args = Arrays.copyOf(args, args.length + 1);
            args[args.length - 1] = exception;
        }
        return args;
    }
}
