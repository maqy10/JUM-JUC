package juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author maqiuyue
 * @Date 2020/7/2 17:12
 * @description
 *
 *
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是
 * 如果有一个线程想去写共享资源来，就不应该再有其它线程可以对该资源进行读或写
 *
 * 小总结：
 *   读 - 读能共存
 *   读 - 写不能共存
 *   写 - 写不能共存
 *
 */

class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key,Object value){
        readWriteLock.writeLock().lock(); //对写入加写锁
        try{
            System.out.println(Thread.currentThread().getName()+"\t 开始写入"+key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public void get(String key){
        readWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t 读取数据"+key);
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }


    }
}

public class ReadWriteLockDemo {

    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        // 先写完才能读,同一时刻，只允许一个写，允许多个读
        for (int i = 0; i < 5; i++) {
            final  int tempInt = i;
            new Thread(()->{
                myCache.put(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();
        }


        for (int i = 0; i < 5; i++) {
            final  int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt+"");
            },String.valueOf(i)).start();
        }

    }


}
