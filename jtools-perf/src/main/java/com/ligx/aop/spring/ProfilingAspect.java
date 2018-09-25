package com.ligx.aop.spring;

import com.ligx.recorder.AccurateRecorder;
import com.ligx.recorder.RecorderMaintainer;
import com.ligx.tag.MethodTag;
import com.ligx.tag.MethodTagMaintainer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Author: ligongxing.
 * Date: 2018/09/21.
 */
@Component
@Aspect
public class ProfilingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfilingAspect.class);

    @Pointcut("@annotation(com.ligx.base.Profiling)")
    private void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) {
        String className = pjp.getTarget().getClass().getSimpleName();
        String methodName = ((MethodSignature) pjp.getSignature()).getMethod().getName();

        int methodTagId = MethodTagMaintainer.getInstance().addMethodTag(new MethodTag(className, methodName));
        RecorderMaintainer.getInstantce().addRecorder(methodTagId);

        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            Object[] args = pjp.getArgs();
            result = pjp.proceed(args);
        } catch (Throwable throwable) {
            LOGGER.error("ProfilingAspect#around, class={}, method={}.", className, methodName, throwable);
        } finally {
            long endTime = System.currentTimeMillis();
            recordCostTime(methodTagId, startTime, endTime);
        }
        return result;
    }


    private void recordCostTime(int methodTagId, long startTime, long endTime) {
        AccurateRecorder recorder = RecorderMaintainer.getInstantce().getRecorder(methodTagId);
        if (recorder != null) {
            recorder.recordTime(startTime, endTime);
        } else {
            LOGGER.warn("ProfilingAspect#recordCostTime, methodTagId={}", methodTagId);
        }
    }
}
