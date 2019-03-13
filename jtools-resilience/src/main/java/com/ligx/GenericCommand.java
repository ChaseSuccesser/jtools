package com.ligx;

import com.ligx.utils.MethodProvider;

/**
 * Author: ligongxing.
 * Date: 2019/03/13.
 */
public class GenericCommand extends AbstractHystrixCommand<Object> {

    public GenericCommand(MetaHolder metaHolder) {
        super(metaHolder);
    }

    @Override
    protected Object run() throws Exception {
        return getCommandAction().execute();
    }

    @Override
    protected Object getFallback() {
        MethodExecutionAction fallbackAction = getFallbackAction();
        if (fallbackAction != null) {
            Object[] args = MethodProvider.createArgsForFallback(fallbackAction, getExecutionException());
            return fallbackAction.executeWithArgs(args);
        } else {
            return super.getFallback();
        }
    }

}
