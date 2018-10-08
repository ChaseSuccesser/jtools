package com.ligx.tag;

import com.ligx.base.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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

    private Map<String, Integer> map = new ConcurrentHashMap<>();

    private AtomicReferenceArray<MethodTag> methodTagArr = new AtomicReferenceArray<>(Constants.MAX_METHOD_TAG_ID);


    private static final MethodTagMaintainer instance = new MethodTagMaintainer();

    private MethodTagMaintainer() {
    }

    public static MethodTagMaintainer getInstance() {
        return instance;
    }


    public int addMethodTag(MethodTag methodTag) {
        map.compute(methodTag.getSimpleDesc(), (k, v) -> {
            if (v == null) {
                int val = index.getAndIncrement();
                if (val > Constants.MAX_METHOD_TAG_ID) {
                    LOGGER.error("MethodTagMaintainer#addMethodTag, methodTagId > MAX_METHOD_TAG_ID={}, MethodTag={}",
                            Constants.MAX_METHOD_TAG_ID, methodTag);
                    return -1;
                } else {
                    return val;
                }
            } else {
                return v;
            }
        });
        int methodTagId = map.get(methodTag.getSimpleDesc());
        methodTagArr.set(methodTagId, methodTag);
        LOGGER.info("MethodTagMaintainer#addMethodTag, methodTag={}, methodTagId={}", methodTag, methodTagId);
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
