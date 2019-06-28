package com.ligx.string;

import org.junit.Test;

/**
 * Author: ligongxing.
 * Date: 2019年06月24日.
 */
public class 字符串原地压缩 {

    public void compression(char[] array) {
        int slow = 0;
        int fast = 0;
        int count = 0;

        while (fast < array.length) {
            count = 0;
            array[slow] = array[fast];
            while (fast < array.length && array[slow] == array[fast]) {
                count++;
                fast++;
            }
            if (count == 1) {
                slow++;
            }
        }

        for (int k = 0; k < slow; k++) {  //将压缩后的字符串输出
            System.out.print(array[k]);
        }
    }

    @Test
    public void test() {
        char[] c = {'a','a', 'b', 'b', 'b', 'c', 'd', 'd', 'e'};
        compression(c);
    }
}
