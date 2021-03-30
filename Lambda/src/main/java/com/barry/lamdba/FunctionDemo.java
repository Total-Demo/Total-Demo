package com.barry.lamdba;

import java.util.function.Consumer;
import java.util.function.IntPredicate;

/**
 * 函数接口
 */
public class FunctionDemo {

    public static void main(String[] args) {
        //断言函数接口
        //Predicate<Integer> predicate=i-> i<0;
        IntPredicate predicate= i-> i<0;
        System.out.println(predicate.test(5));

        //IntConsumer  自带类型的函数接口，不需要写泛型
        //消费函数接口
        Consumer<String> consumer=i->System.out.println(i);
        consumer.accept("hahahhaha");
    }
}
