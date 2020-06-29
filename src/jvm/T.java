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
 */
public class T {
    public static void main(String[] args) {
        Thread t1 = new Thread();
        t1.start();
        t1.start();  //Exception in thread "main" java.lang.IllegalThreadStateException
    }
}
