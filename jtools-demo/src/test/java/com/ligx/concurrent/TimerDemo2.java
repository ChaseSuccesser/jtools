package com.ligx.concurrent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: ligongxing.
 * @date: 2023年03月03日.
 */
public class TimerDemo2 {

    static class TimerTask1 extends TimerTask {
        @Override
        public void run() {
            System.out.println("1 boom...");
            new Timer().schedule(new TimerTask2(), 3000);
        }
    }

    static class TimerTask2 extends TimerTask {
        @Override
        public void run() {
            System.out.println("2 boom....");
            new Timer().schedule(new TimerTask1(), 2000);
        }
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask1(), 2000);

        while (true) {
            System.out.println(new Date().getSeconds());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
