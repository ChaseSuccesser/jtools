package com.ligx;

import com.ligx.conf.HystrixPropertiesManager;
import com.netflix.hystrix.HystrixCommand;

/**
 * Author: ligongxing.
 * Date: 2019/03/13.
 */
public abstract class AbstractHystrixCommand<T> extends HystrixCommand<T> {

    private MethodExecutionAction commandAction;
    private MethodExecutionAction fallbackAction;

    public AbstractHystrixCommand(MetaHolder metaHolder) {
        super(HystrixPropertiesManager.setter(metaHolder));
        this.commandAction = metaHolder.getCommandAction();
        this.fallbackAction = metaHolder.getFallbackAction();
    }

    @Override
    protected abstract T run() throws Exception;

    @Override
    protected T getFallback() {
        throw new RuntimeException("No fallback available.", getExecutionException());
    }
}
