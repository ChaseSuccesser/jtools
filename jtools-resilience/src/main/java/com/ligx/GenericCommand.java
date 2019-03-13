package com.ligx;

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
        // todo
    }

    @Override
    protected Object getFallback() {
        // todo
    }
}
