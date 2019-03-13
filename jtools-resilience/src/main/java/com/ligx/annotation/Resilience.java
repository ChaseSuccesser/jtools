package com.ligx.annotation;

import java.lang.annotation.*;

/**
 * Author: ligongxing.
 * Date: 2019/03/13.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Resilience {

    String groupKey();

    String commandKey();

    String threadPoolKey();

    String fallbackMethod() default "";

    HystrixProperty[] commandProperties() default {};

    HystrixProperty[] threadPoolProperties() default {};
}
