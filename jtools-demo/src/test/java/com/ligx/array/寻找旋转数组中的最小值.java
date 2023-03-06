package com.ligx.array;

/**
 * @author: ligongxing.
 * @date: 2023年03月06日.
 */
public class 寻找旋转数组中的最小值 {

    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int middle = (low + high) / 2;
            if (nums[middle] < nums[high]) {
                high = middle;
            } else {
                low = middle + 1;
            }
        }
        return nums[low];
    }
}
