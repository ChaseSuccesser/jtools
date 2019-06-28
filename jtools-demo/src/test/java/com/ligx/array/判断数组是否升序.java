package com.ligx.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 判断数组是否升序 {

    public boolean isASC(int[] a, int length) {
        if (length == 1) {
            return true;
        } else if (a[length - 1] > a[length]) {
            return false;
        } else {
            return isASC(a, length - 1);
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(true, isASC(new int[]{1, 2, 3, 4}, 3));
        Assert.assertEquals(false, isASC(new int[]{1, 2, 4, 3}, 3));
    }
}
