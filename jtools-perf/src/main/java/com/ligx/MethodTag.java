package com.ligx;

/**
 * Author: ligongxing.
 * Date: 2018年09月20日.
 */
public class MethodTag {

    private String className;

    private String methodName;

    public MethodTag() {
    }

    public MethodTag(String className, String methodName) {
        this.className = className;
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
