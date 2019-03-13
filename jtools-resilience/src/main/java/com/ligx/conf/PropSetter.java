package com.ligx.conf;

/**
 * Author: ligongxing.
 * Date: 2019/03/13.
 */
public interface PropSetter<S, V> {

    void set(S setter, V value) throws IllegalArgumentException;
}
