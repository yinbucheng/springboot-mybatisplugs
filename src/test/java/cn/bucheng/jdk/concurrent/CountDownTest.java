package cn.bucheng.jdk.concurrent;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author ：yinchong
 * @create ：2019/7/2 14:49
 * @description：
 * @modified By：
 * @version:
 */
public class CountDownTest {

    @Test
    public void testCountDown() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for(int i=0;i<10;i++) {
            Thread thread = new Thread(() -> {
                System.out.println("count---down");
                countDownLatch.countDown();
            });
            thread.start();
        }

        countDownLatch.await();
        System.out.println("finish");
    }
}
