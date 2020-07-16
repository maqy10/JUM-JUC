package ThreadPool;

import java.util.concurrent.*;

/**
 * @Author maqiuyue
 * @Date 2020/7/7 17:28
 * @description 创建线程池的方法：
 * public ThreadPoolExecutor(int corePoolSize,
 * int maximumPoolSize,
 * long keepAliveTime,
 * TimeUnit unit,
 * BlockingQueue<Runnable> workQueue) {
 * this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
 * Executors.defaultThreadFactory(), defaultHandler);
 * <p>
 * 参数：
 * 1. corePoolSize: 线程池中的常驻核心线程数
 * 2.maximumPoolSize: 线程池中能够容纳同时执行的最大线程数，此值必须大于等于1
 * 3.keepAlive：多余的空闲线程的存活时间，当前池中线程数量超过corePoolSize时，当空闲时间达到keepAliveTime时，
 * 多余线程会被销毁直到只剩下corePoolSize个线程为止
 * 4.unit: keepAliveTime的单位
 * 5.workQueue：任务队列，被提交但尚未被执行的任务
 * 6.threadFactory:表示生成线程池中工作线程的线程工厂，用于创建线程，一般默认的即可
 * 7.handler:拒绝策略，表示当队列满了，并且工作线程大于等于线程池的最大线程数时如何来拒绝请求执行的runnable的策略
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        // 线程池最大容纳数为 最大线程数+等待队列数
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                // 如何知道是5，看cpu密集数或IO密集数，如果cpu使用高，就是cpu核数+1(Runtime.getRuntime().availableProcessors()+1)
                5,  // 2个核心线程，线程多了之后会扩容为最大线程数5个
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3), // 3个等待队列
                Executors.defaultThreadFactory(),
                //new ThreadPoolExecutor.AbortPolicy() //抛出异常，阻止系统正常运行  （默认） 拒绝策略，最大容纳数满会拒绝
               // new ThreadPoolExecutor.CallerRunsPolicy() //满了之后，回退到调用者处,从而降低流量  例：main调用的线程，多余的回退给调者 此处9个线程，处理不了的，回退给main,处理： 输出结果  main	办理业务
                //new ThreadPoolExecutor.DiscardPolicy()// 默默的丢弃无法处理的任务，不予任何处理也不抛出异常，如果允许任务丢失，这是最好的一种策略
                new ThreadPoolExecutor.DiscardOldestPolicy() //抛弃队列中等待最久的任务，然后把当前任务加入队列中尝试再次提交当前任务。

        );

        try {
            //模拟有10个顾客过来银行办理业务，
            // 目前池子里有5个工作人员提供服务，3个等待队列，所以最多可以有8个线程，大于8个将会抛异常//RejectedExecuteException
            // 如果有的线程执行的特别快，大于8个，也可能不会报异常
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t办理业务 ");
                });
                // TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }

    public static void initPool() {
        //底层实现都是 new ThreadPoolExecutor
        //   ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个受理线程
        // ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池1个受理线程
       ExecutorService threadPool = Executors.newCachedThreadPool();//一池N个工作线程，类似一个银行有N个受理窗口
//
//        try{
//            //模拟有10个顾客过来银行办理业务，目前池子里有5个工作人员提供服务
//            for (int i = 1; i <= 10; i++) {
//                threadPool.execute(()->{
//                    System.out.println(Thread.currentThread().getName()+"\t办理业务 ");
//                });
//                // TimeUnit.SECONDS.sleep(1);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//
//        }
    }
}

