package com.ligx.aop;

import com.ligx.utils.AopUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.Method;

/**
 * Author: ligongxing.
 * Date: 2019/03/13.
 */
@Aspect
public class ResilienceAspect {

    @Pointcut("@annotation(com.ligx.annotation.Resilience)")
    public void resilienceAnnotationPointcut() {
    }

    @Around("resilienceAnnotationPointcut()")
    public Object methodsAnnotatedWithResilience(final ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = AopUtil.getMethod(joinPoint);
    }
}
