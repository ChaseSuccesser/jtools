package com.ligx;

import lombok.Getter;
import lombok.Setter;

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

    public MethodExecutionAction(Object object, Method method, Object[] args) {
        this.object = object;
        this.method = method;
        this.args = args;
    }


    public Object execute() throws Exception {
        return executeWithArgs(args);
    }

    public Object executeWithArgs(Object[] args) throws Exception {
        method.setAccessible(true);
        return method.invoke(object, args);
    }

}
