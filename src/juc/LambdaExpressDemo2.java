package juc;

/**
 * @Author maqiuyue
 * @Date 2020/6/24 14:19
 * @description
 *
 *  1. 拷贝小括号，-> 写死右箭头，落地大括号
 *  2. @FunctionalInterface
 *  3. default 默认方法，可以定义一个或多个
 *  4. static方法，只能定义一个
 */
public class LambdaExpressDemo2 {

    public static void main(String[] args) {

        //无参数无返回值
//        Foo foo = () -> {System.out.println("***********chw");};
//        foo.sayHello();

        Foo foo = (int x,int y) -> {return x+y;};
        System.out.println(foo.add(3,5));
        System.out.println(foo.mul(3,5));

        System.out.println(Foo.div(9,2));
    }

}

// 接口中只有一个函数，不写@FuncationalInterface注解，底层也会自己加上
@FunctionalInterface  //说明是函数式接口
interface Foo{
//    public void sayHello();
    public int add(int x,int y);
    public default int mul(int x,int y){
        return x* y;
    }

    public static int div(int x,int y){
        return x/y;
    }
}
