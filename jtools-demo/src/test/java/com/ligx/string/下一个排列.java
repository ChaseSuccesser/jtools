package com.ligx.string;

/**
 * @author: ligongxing.
 * @date: 2023年03月17日.
 */
public class 下一个排列 {

    /**
     * 下一个排列
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }


    /**
     * 下一个更大的数
     */
    public int nextGreaterElement(int n) {
        char[] chars = Integer.toString(n).toCharArray();

        int i = chars.length - 2;
        while (i >= 0 && chars[i] > chars[i + 1]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }
        int j = chars.length - 1;
        while (j >= 0 && chars[i] > chars[j]) {
            j--;
        }
        swap(chars, i, j);
        reverse(chars, i + 1, chars.length - 1);
        long value = Long.parseLong(new String(chars));
        return value > Integer.MAX_VALUE ? -1 : (int) value;
    }

    private void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(char[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
