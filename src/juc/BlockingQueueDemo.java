package juc;

import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author maqiuyue
 * @Date 2020/7/3 17:25
 * @description
 *
 *  阻塞队列
 *   2.1 阻塞   必须要阻塞/不得不阻塞
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws ExecutionException,InterruptedException {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        // 抛异常
//        System.out.println(blockingQueue.add("a"));
//        blockingQueue.remove("a");

//        System.out.println(blockingQueue.offer("a"));//true
//        System.out.println(blockingQueue.offer("a"));//true
//        System.out.println(blockingQueue.offer("a")); //true
//        System.out.println(blockingQueue.offer("a")); //false
//
//        System.out.println(blockingQueue.poll(3, TimeUnit.MILLISECONDS));
//        System.out.println(blockingQueue.poll(3, TimeUnit.MILLISECONDS));
//        System.out.println(blockingQueue.poll(3, TimeUnit.MILLISECONDS)); //元素值
//        System.out.println(blockingQueue.poll(3, TimeUnit.MILLISECONDS)); //null


//        blockingQueue.put("a");
//        blockingQueue.put("a");
//        blockingQueue.put("a"); // 容量只有3个，多余的第四个发生阻塞
//
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take()); // 容量只有3个，多余的第四个发生阻塞
    }
}
