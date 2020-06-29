package jvm;

/**
 * @Author maqiuyue
 * @Date 2020/6/29 11:14
 * @description
 *
 * java自带的类，执行的是BootStrap加载器 ,BootStrap启动类加载器，先加载$JAVAHOME/jre/lib/rt.jar包中的类 c++
 * 自己定义的类，执行的是AppClassLoader应用类加载器 $CLASSPATH
 * javax.开头的包，是扩展包 ,扩展类加载器，Extension  $JAVAHOME/jre/lib/ext/*.jar
 * Luncher.class JVM调用的相关入口程序
 *
 *
 * 加载，先从Bootstrap启动类加载器找，找不到去 Extenstion扩展类加载器，找不到去AppClassLoader应用类加载器中找
 *
 *
 * car.class 通过 getClassLoader()加载后变成car Class ，Class.forName返回使用的是大Class
 */

public class MyObject {
    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(object.getClass().getClassLoader().getParent().getParent());
        System.out.println(object.getClass().getClassLoader().getParent());
        System.out.println(object.getClass().getClassLoader()); //返回的应该是BootStrap类加载器，C++写的，所以返回null

        MyObject myObject = new MyObject();
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent()); //Object类的上级//null BootStrap启动类加载器 C++
        System.out.println(myObject.getClass().getClassLoader().getParent());//Object类//sun.misc.Launcher$ExtClassLoader@1b6d3586
        System.out.println(myObject.getClass().getClassLoader()); //sun.misc.Launcher$AppClassLoader@18b4aac2

    }
}
