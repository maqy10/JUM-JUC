package juc;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author maqiuyue
 * @Date 2020/6/28 16:07
 * @description 题目： 现在两个线程，可以操作初始值为零的一个变量，实现一个线程对该变量加1，
 * 一个线程对该变量减1，实现交替，来10轮，变量初始值为零。
 * <p>
 *    多线程，对同一资源类，进行操作（1.有资源类，2.线程所带的资源类，让资源类高内聚，写在资源类里，3.多个线程操作，加锁）
 * 1.高聚低合前提下，线程操作资源类
 * 2.判断/干活/通知
 * 3.防止虚假唤醒 ,用while做判断
 *
 * 知识小总结： 多线程编程套路+ while判断+新版写法
 */

class Aircondition {
    private int number = 0;
    private Lock lock = new ReentrantLock(); //可重入非公平递归锁
    private Condition condition = lock.newCondition();
    //旧版写法
   /* public synchronized void increment() throws Exception {
        //1.判断
        while (number != 0){
            this.wait();
        }
        //2.干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //3.通知
        this.notifyAll(); //唤醒其他等待的线程
    }*/

    //旧版写法
   /* public synchronized void decrement() throws Exception {
        while (number == 0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        this.notifyAll(); //唤醒其他等待的线程
    }*/

   public void increment() throws Exception{
       lock.lock();
        try{
            //判断
            while (number != 0){
                condition.await(); //等待
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll(); //唤醒其他线程
        }catch (Exception e){
           e.printStackTrace();
        }finally {
            lock.unlock();
        }
   }

    public void decrement() throws Exception{
        lock.lock();
        try{
            //判断
            while (number == 0){
                condition.await(); //等待
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll(); //唤醒其他线程
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}

public class ProdConsumerDemo04 {
    public static void main(String[] args) throws Exception {

        Aircondition aircondition = new Aircondition();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"D").start();


    }
}
