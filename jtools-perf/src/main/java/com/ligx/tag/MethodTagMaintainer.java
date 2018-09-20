package com.ligx.tag;

import com.ligx.Constants;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Author: ligongxing.
 * Date: 2018/09/20.
 */
public class MethodTagMaintainer {

    // 用来生成MethodTagId
    private AtomicInteger index = new AtomicInteger(0);

    private AtomicReferenceArray<MethodTag> methodTagArr = new AtomicReferenceArray<>(Constants.MAX_METHOD_TAG_ID);

    private MethodTagMaintainer() {
    }
}
