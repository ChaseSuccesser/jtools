package com.ligx.concurrent;

/**
 * @author: ligongxing.
 * @date: 2021年05月18日.
 */
public class 生产者消费者_自己写队列 {

    public static void main(String[] args) {
        SynQueue synQueue = new SynQueue();

        new Thread(new Producer(synQueue)).start();
        new Thread(new Consumer(synQueue)).start();
    }
}

class SynQueue {
    private int front = 0;
    private int rear = 0;
    private int max = 10;
    private int[] chars = new int[max];

    public synchronized void put(int c) {
        while ((rear + 1) % max == front) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        rear = (rear + 1) % max;
        chars[rear] = c;
    }

    public synchronized int get() {
        while (front == rear) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        front = (front + 1) % max;
        return chars[front];
    }
}

class Producer implements Runnable {
    private SynQueue synQueue;

    public Producer(SynQueue synQueue) {
        this.synQueue = synQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synQueue.put(i);
            System.out.println("put:" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private SynQueue synQueue;

    public Consumer(SynQueue synQueue) {
        this.synQueue = synQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int c = synQueue.get();
            System.out.println("get:" + c);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}