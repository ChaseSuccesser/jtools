package com.ligx.tag;

/**
 * Author: ligongxing.
 * Date: 2018年09月20日.
 */
public class MethodTag {

    private String className;

    private String methodName;


    public String getSimpleDesc() {
        return className + "." + methodName;
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

    @Override
    public String toString() {
        return "MethodTag{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
