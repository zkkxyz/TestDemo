package com.zkk.test.delayTest;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by zkk on 2018/4/7 10:31 .
 */
public class DelayDequeMain {
    public static void main(String[] args) throws Exception {
        DelayQueue<DelayEvent> queue = new DelayQueue<DelayEvent>();
        Thread threads[] = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            DelayTask task = new DelayTask(i + 1, queue);
            threads[i] = new Thread(task);
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        do {
            int counter = 0;
            DelayEvent delayEvent;
            do {
                delayEvent = queue.poll();
                if (delayEvent != null) {
                    counter++;
                }
            } while (delayEvent != null);
            System.out.println("At "+ new Date() + " you have read " + counter+ " event");
            TimeUnit.MILLISECONDS.sleep(500);
        } while (queue.size() > 0);
    }
}
