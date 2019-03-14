package com.ligx;

import com.ligx.annotation.Resilience;
import com.ligx.utils.AopUtil;
import com.ligx.utils.MethodProvider;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Method;

/**
 * Author: ligongxing.
 * Date: 2019/03/13.
 */
public class MetaHolderBuilder {

    public static MetaHolder create(JoinPoint joinPoint) {
        Class<?> declaringClass = joinPoint.getTarget().getClass();
        Method method = AopUtil.getMethod(joinPoint);
        Object proxy = joinPoint.getThis();
        Object obj = joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();

        MetaHolder metaHolder = MetaHolder.builder()
                .proxyObj(proxy)
                .obj(obj)
                .method(method)
                .args(args)
                .build();

        FallbackMethod fallbackMethod = MethodProvider.getFallbackMethod(joinPoint);
        metaHolder.setFallbackMethod(fallbackMethod.getFallbackMethod());
        metaHolder.setExtendFallback(fallbackMethod.isExtend());

        Resilience resilience = method.getAnnotation(Resilience.class);
        // todo 动态修改Resilience的属性
        metaHolder.setResilience(resilience);

        String groupKey = StringUtils.isNotBlank(resilience.groupKey()) ? resilience.groupKey() : declaringClass.getSimpleName();
        String commandKey = StringUtils.isNotBlank(resilience.commandKey()) ? resilience.commandKey() : method.getName();
        String threadPoolKey = StringUtils.isNotBlank(resilience.threadPoolKey()) ? resilience.threadPoolKey() : groupKey;
        metaHolder.setGroupKey(groupKey);
        metaHolder.setCommandKey(commandKey);
        metaHolder.setThreadPoolKey(threadPoolKey);

        metaHolder.setCommandAction(createCommandAction(metaHolder));
        metaHolder.setFallbackAction(createFallbackAction(metaHolder));

        return metaHolder;
    }

    private static MethodExecutionAction createCommandAction(MetaHolder metaHolder) {
        return new MethodExecutionAction(metaHolder.getObj(), metaHolder.getMethod(), metaHolder.getArgs(), false);
    }

    private static MethodExecutionAction createFallbackAction(MetaHolder metaHolder) {
        return new MethodExecutionAction(metaHolder.getObj(), metaHolder.getFallbackMethod(), metaHolder.getArgs(), metaHolder.isExtendFallback());
    }

}
