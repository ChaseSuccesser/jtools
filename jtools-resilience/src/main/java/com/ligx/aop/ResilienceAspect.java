package com.ligx.aop;

import com.ligx.GenericCommand;
import com.ligx.MetaHolder;
import com.ligx.MetaHolderBuilder;
import com.ligx.utils.AopUtil;
import com.netflix.hystrix.HystrixExecutable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Author: ligongxing.
 * Date: 2019/03/13.
 */
@Aspect
public class ResilienceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResilienceAspect.class);

    @Pointcut("@annotation(com.ligx.annotation.Resilience)")
    public void resilienceAnnotationPointcut() {
    }

    @Around("resilienceAnnotationPointcut()")
    public Object methodsAnnotatedWithResilience(final ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Method method = AopUtil.getMethod(joinPoint);
        String methodName = method.getName();

        // todo 限流

        MetaHolder metaHolder = MetaHolderBuilder.create(joinPoint);

        HystrixExecutable hystrixExecutable = new GenericCommand(metaHolder);

        Object result = null;
        try {
            result = hystrixExecutable.execute();
        } catch (Exception e) {
            LOGGER.error("ResilienceAspect#methodsAnnotatedWithResilience, ", e);
        }
        return result;
    }
}
