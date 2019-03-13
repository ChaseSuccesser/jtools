package com.ligx;

import com.ligx.annotation.Resilience;
import lombok.*;

import java.lang.reflect.Method;

/**
 * Author: ligongxing.
 * Date: 2019/03/13.
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetaHolder {

    private Resilience resilience;
    private String groupKey;
    private String commandKey;
    private String threadPoolKey;

    private Object proxyObj;
    private Object obj;
    private Method method;
    private Object[] args;

    private Method fallbackMethod;
    private boolean extendFallback;

    private MethodExecutionAction commandAction;
    private MethodExecutionAction fallbackAction;

}
