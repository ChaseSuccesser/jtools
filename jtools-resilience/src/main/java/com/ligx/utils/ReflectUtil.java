package com.ligx.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

/**
 * Author: ligongxing.
 * Date: 2019/03/14.
 */
public class ReflectUtil {

    public static Optional<Method> getMethod(Class<?> type, String methodName, Class<?>... parameterTypes) {
        Method[] methods = type.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName) && Arrays.equals(method.getParameterTypes(), parameterTypes)) {
                return Optional.of(method);
            }
        }
        return Optional.empty();
    }


    // ------------------------ 动态修改注解的属性值 ------------------------
    /**
     * 批量更新注解中嵌套注解的属性值
     *
     * @param annotation                  注解对象
     * @param fieldNameOfNestedAnnotation 嵌套注解对应的字段名
     * @param nestedNameValuePair         key:嵌套注解的属性名,value:嵌套注解的属性值
     * @throws Exception
     */
    public static void update(Object annotation, String fieldNameOfNestedAnnotation, Map<String, Object> nestedNameValuePair) throws Exception {
        if (nestedNameValuePair == null || nestedNameValuePair.size() == 0) {
            return;
        }
        Map<String, Object> memberValues = getMemberValuesOfAnnotation(annotation);
        Object nestedAnnotation = memberValues.get(fieldNameOfNestedAnnotation);
        update(nestedAnnotation, nestedNameValuePair);
    }

    /**
     * 更新注解中嵌套注解指定属性的值
     *
     * @param annotation                  注解对象
     * @param fieldNameOfNestedAnnotation 嵌套注解对应的字段名
     * @param key                         嵌套注解的属性名
     * @param value                       嵌套注解的属性值
     * @throws Exception
     */
    public static void update(Object annotation, String fieldNameOfNestedAnnotation, String key, String value) throws Exception {
        Map<String, Object> memberValues = getMemberValuesOfAnnotation(annotation);
        Object nestedAnnotation = memberValues.get(fieldNameOfNestedAnnotation);
        update(nestedAnnotation, key, value);
    }

    /**
     * 批量更新注解的属性值
     *
     * @param annotation    注解对象
     * @param nameValuePair key:注解的属性名,value:注解的属性值
     * @throws Exception
     */
    public static void update(Object annotation, Map<String, Object> nameValuePair) throws Exception {
        if (nameValuePair == null || nameValuePair.size() == 0) {
            return;
        }
        Map<String, Object> memberValues = getMemberValuesOfAnnotation(annotation);
        if (memberValues != null && memberValues.size() > 0) {
            for (String name : nameValuePair.keySet()) {
                memberValues.put(name, nameValuePair.get(name));
            }
        }
    }

    /**
     * 更新注解中指定属性的值
     *
     * @param annotation 注解对象
     * @param key        注解的属性名
     * @param value      注解的属性值
     * @throws Exception
     */
    public static void update(Object annotation, String key, Object value) throws Exception {
        Map<String, Object> memberValues = getMemberValuesOfAnnotation(annotation);
        if (memberValues != null && memberValues.size() > 0) {
            memberValues.put(key, value);
        }
    }

    public static Map<String, Object> getMemberValuesOfAnnotation(Object annotation) throws Exception {
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
        Field memberValuesField = invocationHandler.getClass().getDeclaredField("memberValues");
        memberValuesField.setAccessible(true);
        Map<String, Object> memberValues = (Map<String, Object>) memberValuesField.get(invocationHandler);
        return memberValues;
    }
}
