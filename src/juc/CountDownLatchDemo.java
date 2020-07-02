package juc;

import java.util.concurrent.CountDownLatch;

/**
 * @Author maqiuyue
 * @Date 2020/7/2 15:53
 * @description
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        // 保证其他线程先执行完，最后执行主线程
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 离开教室");
                countDownLatch.countDown();//计数
            },i+"").start();
        }
        countDownLatch.await(); //等待
        System.out.println(Thread.currentThread().getName()+"\t 班长关门走人");
    }

    public static void closeDoor(){
        for (int i = 0; i < 6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 离开教室");
            },"A").start();
        }
        System.out.println(Thread.currentThread().getName()+"\t 班长关门走人");
    }
}
