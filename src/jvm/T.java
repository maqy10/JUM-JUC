package jvm;

/**
 * @Author maqiuyue
 * @Date 2020/6/29 16:48
 * @description
 *
 * java方法，放到java栈，native方法放到native栈
 *
 * 程序计数器，所占内存很少，几乎可忽略不计
 * 执行的是native方法，这个计数器是空的。
 * 程序计数器，用以完成分支、循环、跳转、异常处理、线程恢复等基础功能。不会发生内存溢出错误
 *
 * native方法调用的C语言的第三方函数库
 *
 * java栈、native方法栈、pc寄存器，线程私有 ,不存在垃圾回收
 * 方法区、堆，线程共有  ,存在垃圾回收，大总分垃圾回收在堆中
 *
 *
 * 方法区：存储了类的结构信息（模板）  ，例如：运行时常量池、字段和方法数据、构造函数和普通方法的字节码内容。
 * 方法区是规范，在不同虚拟机里实现是不一样的，最典型的是永久代（PermGen space）和元空间(Metaspace).
 *      方法区 f = new 永久代
 *      方法区 f = new 无空间
 *
 * 实例变量，存在堆中
 *
 *
 * stack
 *  栈管运行，堆管存储
 *
 *  栈也叫栈内存，主管Java程序的运行，是在线程创建时创建，它的生命期是跟随线程的生命期，线程结束栈内存也就释放。
 *  对于栈来说，不存在增圾回收问题，只要线程一结束该栈就自动释放，生命周期和线程一致，是线程私有的，
 *  8种基本类型的变量+对象的引用变量+实例方法都是在函数的栈内存中分配
 *
 *  java方法 = 栈帧
 *
 *
 */
public class T {


    public static void m1(){
        /*System.out.println("222");
        System.out.println("*******m1");
        System.out.println("33333");*/
        m1(); //Exception in thread "main" java.lang.StackOverflowError
            // 此时main在栈顶，调用m1，m1迭代调用，不停入栈m1栈帧，导致栈溢出错误
    }

    public static void main(String[] args) {
     /*   Thread t1 = new Thread();
        t1.start();
        t1.start();*/  //Exception in thread "main" java.lang.IllegalThreadStateException

        System.out.println("1111");
        m1();
        System.out.println("444");
    }
}
