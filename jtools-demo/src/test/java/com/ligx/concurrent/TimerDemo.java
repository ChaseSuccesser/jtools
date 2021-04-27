package com.ligx.concurrent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: ligongxing.
 * @date: 2021年04月27日.
 */
public class TimerDemo {

    private static Timer timer = new Timer();

    static class TimerTask1 extends TimerTask {
        @Override
        public void run() {
            System.out.println(new Date().getSeconds() + ":a");
            timer.schedule(new TimerTask2(), 1000);
        }
    }

    static class TimerTask2 extends TimerTask {
        @Override
        public void run() {
            System.out.println(new Date().getSeconds() + ":b");
            timer.schedule(new TimerTask1(), 1000);
        }
    }

    public static void main(String[] args) {
        timer.schedule(new TimerTask1(), 1000);

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
