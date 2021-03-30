package com.barry.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo3 {

    public static void main(String[] args) {

        String name="my name is 007";
        //把每个单词的长度打印出来
        Stream.of(name.split(" ")).filter(s->s.length()>2).map(x->x.length()).forEach(System.out::println);

        //flatMap A->B属性（是个集合）最终得到所有的A元素里面所有的B属性集合
        //intStream/longStream并不是Stream的子类，所以要进行装箱boxed
        Stream.of(name.split(" ")).flatMap(x->x.chars().boxed()).forEach(
                i->System.out.print((char)i.intValue())
        );

        //peek用于debug，是个中间操作，和foreach是终止操作
        System.out.println("--------------------peek-------------");
        Stream.of(name.split(" ")).peek(System.out::println).forEach(System.out::println);

        //limit 使用，主要用于无限流
        System.out.println("--------------------limit-------------");
        new Random().ints().filter(x->x>10 && x<100).limit(10).forEach(System.out::println);

    }



}
