package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author maqiuyue
 * @Date 2020/6/29 9:28
 * @description
 *
 *
 */



class Mythread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("**********come in call method()");
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //FutureTask<Integer> futureTask = new FutureTask(()->{return 1024;});
        FutureTask<Integer> futureTask = new FutureTask(new Mythread());
        new Thread(futureTask,"A").start();
        Integer result = futureTask.get();
        System.out.println("*******结果："+result);
    }
}
