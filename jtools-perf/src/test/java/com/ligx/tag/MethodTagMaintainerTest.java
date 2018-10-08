package com.ligx.tag;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: ligongxing.
 * Date: 2018/10/08.
 */
public class MethodTagMaintainerTest {


    @Test
    public void addDifferentMethodTag() throws InterruptedException {
        CountDownLatch beginLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(10);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            MethodTag methodTag = new MethodTag("Test", "test" + i);
            executorService.execute(new MethodTagThread(beginLatch, endLatch, methodTag));
        }

        beginLatch.countDown();
        endLatch.await();
        System.out.println("END..........");
    }

    @Test
    public void addSameMethodTag() throws InterruptedException {
        MethodTag methodTag = new MethodTag("Test", "test1");

        CountDownLatch beginLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(10);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new MethodTagThread(beginLatch, endLatch, methodTag));
        }

        beginLatch.countDown();
        endLatch.await();
        System.out.println("END..........");
    }


    class MethodTagThread implements Runnable {

        private CountDownLatch beginLatch;
        private CountDownLatch endLatch;
        private MethodTag methodTag;

        public MethodTagThread(CountDownLatch beginLatch, CountDownLatch endLatch, MethodTag methodTag) {
            this.beginLatch = beginLatch;
            this.endLatch = endLatch;
            this.methodTag = methodTag;
        }

        @Override
        public void run() {
            try {
                beginLatch.await();
                int methodTagId = MethodTagMaintainer.getInstance().addMethodTag(methodTag);
                System.out.println(String.format("methodTag:%s, id:%s", methodTag.getSimpleDesc(), methodTagId));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                endLatch.countDown();
            }
        }
    }
}