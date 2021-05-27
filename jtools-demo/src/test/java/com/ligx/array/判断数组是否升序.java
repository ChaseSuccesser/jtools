package com.ligx.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Author: ligongxing.
 * Date: 2019/06/14.
 */
public class 判断数组是否升序 {

    private boolean isASC(int[] arr, int index) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        if (index == 0) {
            return true;
        }
        if (arr[index - 1] > arr[index]) {
            return false;
        } else {
            return isASC(arr, index - 1);
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(true, isASC(new int[]{1, 2, 3, 4}, 3));
        Assert.assertEquals(false, isASC(new int[]{2, 1, 3, 4}, 3));
        Assert.assertEquals(false, isASC(new int[]{1, 2, 4, 3}, 3));
    }
}
