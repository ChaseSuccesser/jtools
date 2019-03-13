package com.ligx;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Author: ligongxing.
 * Date: 2019/03/13.
 */
@Getter
@Setter
public class MethodExecutionAction {

    private Object object;
    private Method method;
    private Object[] args;
    private boolean extendFallback;

    public MethodExecutionAction(Object object, Method method, Object[] args, boolean extendFallback) {
        this.object = object;
        this.method = method;
        this.args = args;
        this.extendFallback = extendFallback;
    }

    public Object execute() throws RuntimeException {
        return executeWithArgs(args);
    }

    public Object executeWithArgs(Object[] args) throws RuntimeException {
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(object, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e.getCause());
        }
        return result;
    }

}
