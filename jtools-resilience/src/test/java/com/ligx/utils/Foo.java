package com.ligx.utils;

import com.ligx.annotation.HystrixProperty;
import com.ligx.annotation.Resilience;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Author: ligongxing.
 * Date: 2019/03/14.
 */
public class Foo {

    @Resilience(
            groupKey = "Foo",
            commandKey = "foo",
            threadPoolKey = "Foo",
            commandProperties = {
                    @HystrixProperty(name = "circuit.enable", value = "true"),
                    @HystrixProperty(name = "circuit.request.threshold", value = "10")
            }
    )
    public void foo() {
        System.out.println("a");
    }

    public static void main(String[] args) throws Exception {
        Method method = Foo.class.getDeclaredMethod("foo");
        Resilience resilience = method.getAnnotation(Resilience.class);
        System.out.println("old groupKey: " + resilience.groupKey());
        HystrixProperty[] hystrixProperties = resilience.commandProperties();
        for (HystrixProperty property : hystrixProperties) {
            System.out.println("old " + property.name() + " : " + property.value());
        }
        System.out.println("--------------");


        // 动态修改注解中普通的属性
        ReflectUtil.update(resilience, "groupKey", "Foo2");

        Map<String, Object> memberValuesOfResilience = ReflectUtil.getMemberValuesOfAnnotation(resilience);
        // 动态修改嵌套注解的属性
        HystrixProperty[] hystrixProperties2 = (HystrixProperty[]) memberValuesOfResilience.get("commandProperties");
        for (HystrixProperty property : hystrixProperties2) {
            Map<String, Object> memberValuesOfHystrixProperty = ReflectUtil.getMemberValuesOfAnnotation(property);
            if (property.name().equals("circuit.enable")) {
                memberValuesOfHystrixProperty.put("value", "false");
            } else if (property.name().equals("circuit.request.threshold")) {
                memberValuesOfHystrixProperty.put("value", "20");
            }
        }

        System.out.println("new groupKey: " + resilience.groupKey());
        HystrixProperty[] hystrixProperties3 = resilience.commandProperties();
        for (HystrixProperty property : hystrixProperties3) {
            System.out.println("new " + property.name() + " : " + property.value());
        }
    }



}
