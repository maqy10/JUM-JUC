package juc;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author maqiuyue
 * @Date 2020/6/24 15:00
 * @description
 */

/**
 * 写时复制：
 *  CopyOnWrite 容器即写时复制的容器，往一个容器添加元素的时候，不直接往当前容器Object[] 添加，
 *  而是先将当前容器Object[] 进行copy，复制出一个新的容器Object[] newElements,然后新的容器Object[] newElements 里添加
 *  元素，添加完元素后，再将原容器的引用指向新的容器 setArray（newElements);这样做的好处是可以对CopyOnWrite容器进行并
 *  发的读，而不需要加锁，因为当前不会添加任何元素，所以CopyOnWrite容器也是一个读写分离的思想，读和写不同的容器
 *
 */
public class NotSafeDemo3 {

    public static void main(String[] args) {

        /**
         * Synchronized　加的是重锁，保证数据一致性，但是会影响并发性能
         * Map,Set.List都是线程安全的
         */


        /**
         * 说明线程不安全的例子 （List /Set /Map）
         * 故障现象：
         *   java.util.ConcurrentModificationException
         * 导致原因:
         *   多线程抢夺同一个资源类，没有加锁
         *
         * 解决方法：
         * 1.用Vector类，线程安全，其内部实现，方法上加了synchronized
         * 2.用Conllections.synchronizedList(new ArrayList<>())
         * 3.new CopyOnWriteArrayList<>()  推荐 底层实现 ReentrantLock 重入锁 ,写时复制，读写分离
         *
         * 优化建议
         *
         */

        Map<String,String> map =  new ConcurrentHashMap<>(); //new HashMap<>(); Map解决线程安全问题
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }




    }


    /**
     * HashSet 底层数据结构是HashMap，
     * add方法实现:
     *  public boolean add(E e) {
     *         return map.put(e, PRESENT)==null;
     *     }
     *   private static final Object PRESENT = new Object();
     *
     * 解决方案：
     * CopyOnWriteArraySet
     *
     */
    public static void setNoSafe(){
        Set<String> set =  new CopyOnWriteArraySet<>(); // new HashSet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }


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
    public static void listNotSafe(){
        /**
         * 写时复制：
         *  CopyOnWrite 容器即写时复制的容器，往一个容器添加元素的时候，不直接往当前容器Object[] 添加，
         *  而是先将当前容器Object[] 进行copy，复制出一个新的容器Object[] newElements,然后新的容器Object[] newElements 里添加
         *  元素，添加完元素后，再将原容器的引用指向新的容器 setArray（newElements);这样做的好处是可以对CopyOnWrite容器进行并
         *  发的读，而不需要加锁，因为当前不会添加任何元素，所以CopyOnWrite容器也是一个读写分离的思想，读和写不同的容器
         *
         */
        List<String> list =  new CopyOnWriteArrayList<>(); //new ArrayList<>();

        //list.forEach(System.out::println);  //消费型 接口  forEach(Consumer<? super T> action)

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
