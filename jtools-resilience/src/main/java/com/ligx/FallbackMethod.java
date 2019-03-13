package com.ligx;

import lombok.*;

import java.lang.reflect.Method;

/**
 * Author: ligongxing.
 * Date: 2019年03月13日.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FallbackMethod {

    private Method fallbackMethod;

    private boolean extend;
}
