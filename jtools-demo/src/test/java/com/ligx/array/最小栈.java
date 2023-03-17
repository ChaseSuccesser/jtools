package com.ligx.array;

import java.util.Stack;

/**
 * @author: ligongxing.
 * @date: 2023年03月17日.
 */
public class 最小栈 {

    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    public 最小栈() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
        // 为保证第一次push时，minStack里能取出元素和要push的元素进行大小比较，所以提前放一个最大值进去
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        dataStack.push(val);
        minStack.push(Math.min(val, minStack.peek()));
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
