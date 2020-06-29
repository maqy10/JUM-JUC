package java.lang;

/**
 * @Author maqiuyue
 * @Date 2020/6/29 14:11
 * @description
 */
public class String {

    //错误: 在类 java.lang.String 中找不到 main 方法, 请将 main 方法定义为:
    //   public static void main(String[] args)
    //否则 JavaFX 应用程序类必须扩展javafx.application.Application


    //  沙箱安全，防止此种自已写的代码，污染Java源代码，使用了双亲委派机制，实现沙箱安全.
  // JVM使用了双亲委派机制，从上往下找，BootStrap -> Extension -> AppClassLoader 先找到先使用，其后的不管，因为在之前先找到了，String类并进行使用，自定义的String类将不会再管
    public static void main(String[] args) {
        System.out.println("*****hello 1205");

    }

}
