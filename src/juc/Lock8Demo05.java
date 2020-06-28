package juc;

import jdk.nashorn.internal.runtime.ECMAException;

import java.util.concurrent.TimeUnit;

/**
 * @Author maqiuyue
 * @Date 2020/6/28 9:33
 * @description
 *
 *   8 锁
 *   1.标准访问，请问，先打印邮件还是短信,先打邮件
 *   2.暂停4秒钟在邮件方法，请问先打印邮件还是短信  ,先打印邮件
 *   3.新增普通sayHello方法，请问先打印邮件还是hello,先打印hello
 *   4.两个phone,先phone1打印邮件，还是phone2短信， 先打印短信
 *   5.两个静态同步方法，同一部手机，请问先打印邮件还是短信,先打邮件
 *   6.两个静态同步方法，两部手机，请问先打印邮件还是短信,先打邮件
 *   7. 1个静态同步方法，1个普通同步方法，同一部手机，请问先打印邮件还是短信,先打短信
 *   8. 1个静态同步方法，1个普通同步方法，2部手机，请问先打印邮件还是短信,先打短信
 *
 *
 * 笔记： （synchronized方法，锁的是对象 ，static锁的是全局锁，锁的是类）
 *  1，2锁： 一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中的一个synchronized
 *   方法了，其它的线程只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些synchronized方法
 *
 *   锁的是当前对象this，被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法
 * 3锁： 加普通方法后发现和同步锁无关
 * 4锁： 换成两个对象后，不是同一把锁，两个对象，两把锁
 * 5、6锁：synchronized实现同步的基础： java中的每一个对象都可以作为锁，具体表现为以下3种形式。
 *     对于普通同步方法，锁是当前实例对象，对于同步方法块，锁是Synchronized括号里配置的对象
 *
 *   对于静态同步方法，锁的是当前类的Class对象
 *
 *  总结：
 *    当一个线程试图访问同步代码块时，它首先必须得到锁，退出或抛出异常时，必须释放锁，
 *    也就是说如果一个实例对象的非静态同步方法获取锁后，该实例对象的其非静态同步方法必须等待获取锁的方法释放锁后才能获取锁，（此种锁的时对象）
 *    可是别的实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是不同的锁，所以不需要等待该实例对象
 *    已获取锁的非静态同步方法释放锁后，就可获取他们自己的锁
 *
 *    所有的静态同步方法用的也是同一把锁 —— 类对象Class本身，
 *    这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞争条件的。
 *    但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁。
 *    而不管是同一个实例对象的静态同步方法之间，还是不同的实例对象的静态同步方法之间，
 *    只要他们是同一个类的实例对象.
 */

class Phone{
    public synchronized void sendSMS() throws Exception{

        System.out.println("*********sendSMS");
    }

    public static synchronized void sendEmail() throws Exception{
        TimeUnit.SECONDS.sleep(4);
        System.out.println("*********sendEmail");
    }

    public void sayHello() throws Exception{
        System.out.println("***********sayHello");
    }
}

public class Lock8Demo05 {
    public static void main(String[] args) throws InterruptedException{
        Phone phone = new Phone();
        Phone phone1 = new Phone();
        new Thread(()->{
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();

        Thread.sleep(100); //正常情况下，A与B谁会先执行是不确定的 ,此钟情况，先执行A

        new Thread(()->{
            try {
               //phone.sendSMS();
               // phone.sayHello();
                 phone1.sendSMS();

            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}
