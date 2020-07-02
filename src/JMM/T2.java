package JMM;

/**
 * @Author maqiuyue
 * @Date 2020/7/1 11:05
 * @description
 *
 * JMM java 内存模型
 *
 *  JMM 可见性（通知机制）
 */

class MyNumber{
    volatile int number = 10; //具备对其他线程的通知性
    public void addTo1205(){
        this.number = 1205;
    }
}

public class T2 {
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();
        System.out.println(myNumber.number);
        new Thread(()->{
            try {
                Thread.sleep(3000);
                myNumber.addTo1205();
                System.out.println(Thread.currentThread().getName()+"\t update number,number value: "+myNumber.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();  // 线程A的修改对线程main不可见，实例变量加上volatile修饰符，即可实现不可见
        while (myNumber.number == 10){
            //需要有一种通知机制告诉main线程，number已经改为1205

        }
        System.out.println(Thread.currentThread().getName()+"\t A update number,number value: "+myNumber.number);
    }
}
