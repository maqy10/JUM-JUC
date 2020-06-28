package juc;

import jdk.nashorn.internal.runtime.SharedPropertyMap;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author maqiuyue
 * @Date 2020/6/28 17:34
 * @description 备注：多线程之间按顺序调用 ，实现A->B->C
 * 三个线程启动，要求如下：
 * <p>
 * AA 打印5次，BB打印10次，CC打印15次
 * AA 打印5次，BB打印10次，CC打印15次
 */

class ShareData {
    private int number = 1;  //A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    // 多个线程顺序，多个condition
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            while (number != 1) {
                c1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //顺序1完之后是2，将number值改为2
            number = 2;
            //如何通知2个
            c2.signal(); //通知B线程
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void print10() {
        lock.lock();
        try {
            while (number != 2) {
                c2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //顺序1完之后是2，将number值改为2
            number = 3;
            //如何通知2个
            c3.signal(); //通知B线程
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (number != 3) {
                c3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //顺序1完之后是2，将number值改为2
            number = 1;
            //如何通知2个
            c1.signal(); //通知B线程
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print(int count) {

        if (count == 5) {
            lock.lock();
            try {
                while (number != 1) {
                    c1.await();
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t" + i);
                }
                //顺序1完之后是2，将number值改为2
                number = 2;
                //如何通知2个
                c2.signal(); //通知B线程
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

}

public class ConditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareData.print5();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareData.print10();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareData.print15();
            }
        }, "C").start();
    }

}
