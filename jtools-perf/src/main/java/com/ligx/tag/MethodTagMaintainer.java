package com.ligx.tag;

import com.ligx.base.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Author: ligongxing.
 * Date: 2018/09/20.
 */
public class MethodTagMaintainer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodTagMaintainer.class);

    // 用来生成MethodTagId
    private AtomicInteger index = new AtomicInteger(0);

    private AtomicReferenceArray<MethodTag> methodTagArr = new AtomicReferenceArray<>(Constants.MAX_METHOD_TAG_ID);


    private static final MethodTagMaintainer instance = new MethodTagMaintainer();

    private MethodTagMaintainer() {
    }

    public static MethodTagMaintainer getInstance() {
        return instance;
    }


    public int addMethodTag(MethodTag methodTag) {
        int methodTagId = index.getAndIncrement();
        if (methodTagId > Constants.MAX_METHOD_TAG_ID) {
            LOGGER.warn("MethodTagMaintainer#addMethodTag, MethodTag={}, methodTagId={} > {}",
                    methodTag, methodTagId, Constants.MAX_METHOD_TAG_ID);
            return -1;
        }
        methodTagArr.set(methodTagId, methodTag);
        return methodTagId;
    }

    public MethodTag getMethodTag(int methodTagId) {
        if (0 <= methodTagId && methodTagId <= Constants.MAX_METHOD_TAG_ID) {
            return methodTagArr.get(methodTagId);
        }
        return null;
    }

    public int getMethodTagCount() {
        return index.get();
    }
}
