package juc;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author maqiuyue
 * @Date 2020/6/24 15:00
 * @description
 */
public class NotSafeDemo3 {
    /**
     * ArrayList知识点：
     * new ArrayList 底层实现object[10]，
     * java懒加载，
     * 超过10个元素之后，会自动扩容，是原值的一半，即新的大为原来大小*1.5，
     * 将数据用Arrays.copyof复制到新数组
     * ArrayList不安全:
     *
     *
     *
     * HashMap知识点:
     * HashMap初始值16，扩容为原值的一倍,即2的移位，2的4次方，到2的5次方，递增
     */
    public static void main(String[] args) {

        /**
         * Synchronized　加的是重锁，保证数据一致性，但是会影响并发性能
         * Map,Set.List都是线程安全的
         */


        /**
         * 说明线程不安全的例子
         * 故障现象：
         *   java.util.ConcurrentModificationException
         * 导致原因:
         *
         * 解决方法：
         * 1.用Vector类，线程安全，其内部实现，方法上加了synchronized
         * 2.用Conllections.synchronizedList(new ArrayList<>())
         * 3.new CopyOnWriteArrayList<>()  推荐 底层实现 ReentrantLock 重入锁
         *
         * 优化建议
         */
       List<String> list =  new CopyOnWriteArrayList<>(); //new ArrayList<>();

        //list.forEach(System.out::println);  //消费型 接口  forEach(Consumer<? super T> action)

        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
