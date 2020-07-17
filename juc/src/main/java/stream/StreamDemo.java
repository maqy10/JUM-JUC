package stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Author maqiuyue
 * @Date 2020/7/16 17:12
 * @description
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
class User{
    private Integer id;
    private String userName;
    private int age;
}


/**
 * 题目：请按照给出数据，找出同时满足以下条件的用户，也即以下条件全部满足
 * 偶数ID且年龄大于24且用户名转为大写且用户名字母倒排序，只输出一个用户名字
 *
 */
public class StreamDemo {

    public static void main(String[] args) {

        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 25);
        User u4 = new User(14, "d", 26);
        User u5 = new User(16, "e", 27);

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);


        /**
         * Stream自己不会存储元素，Stream不会改变源对象，相反他们会返回一个持有结果的新Stream
         * Stream操作是延迟执行的。这意味着他们会等到需要结果的时候才执行
         */

        list.stream().filter(user -> {
            return user.getId() % 2 == 0;
        }).filter(t -> {
            return t.getAge() > 24;
        }).map(m -> {
            return m.getUserName().toUpperCase();
        }).sorted(
                (o1,o2)->{return o2.compareTo(o1);
        }).limit(1).forEach(System.out::println);


   /*     list.stream().filter(user ->
                user.getId() % 2 == 0
        ).filter(t ->
                t.getAge() > 24
        ).map(m ->
                m.getUserName().toUpperCase()
        ).sorted(
                (o1,o2)-> o2.compareTo(o1)
        ).limit(1).forEach(System.out::println);
*/


/*============================四大函数式接口


        //函数型接口 ,有一个输入参数，一个返回参数
        Function<String,Integer> function = s -> {return s.length();};
        System.out.println(function.apply("abc"));

        // 断定型接口 一个输入参数，返回boolean类型值
        */
/*Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return false;
            }
        }*//*

        Predicate<String> predicate = s ->{return s.isEmpty();};
        System.out.println(predicate.test("s"));


        // 消费型接口 有一个输入参数，没有返回参数
        */
/*Consumer<String> consumer= new Consumer<String>() {
            @Override
            public void accept(String s) {
            }
        };*//*


        Consumer<String> consumer = s -> {
            System.out.println(s);
        };

        consumer.accept("siass");

        //供给型接口 没有输入参数，有一个返回参数

      */
/*  Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return null;
            }
        };
*//*


      Supplier<String> supplier = () ->{return "sss";};
        System.out.println(supplier.get());
    }


*/
    }

}
