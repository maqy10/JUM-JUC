package juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author maqiuyue
 * @Date 2020/7/2 16:07
 * @description
 *
 * Semaphore
 *
 *  在信号量上我们定义两种操作：
 *  acquire(获取）当一个线程调用acquire操作时，它要么成功获取信号量（信号减1），
 *  要到一直等一下去，直到有线程释放信号量，或超时。
 *
 * release(释放）实际上会将信号量的值加1，然后唤醒等待的线程
 *
 * 信号量主要用于两个目换，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。
 *
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模板资源类，有三个空车位  为1的时候，等价于synchronized

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();//占用
                    System.out.println(Thread.currentThread().getName()+"\t抢占到了车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }

    }
}
