package com.barry.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo4 {

    public static void main(String[] args) {

        String name="my name is 007";
        //使用并行流
        name.chars().parallel().forEach(x->System.out.print((char)x));
        System.out.println();
        //使用 forEachOrdered 保证顺序
        name.chars().parallel().forEachOrdered(x->System.out.print((char)x));
        System.out.println();
        //收集到list
        List<String> collect = Stream.of(name.split(" ")).collect(Collectors.toList());
        System.out.println(collect);

        //  使用redis拼接字符串
        Optional<String> reduce = Stream.of(name.split(" ")).reduce((x, y) -> x + "|" + y);
        System.out.println(reduce.orElse(""));

        //带初始值的reduce
        String reduce1 = Stream.of(name.split(" ")).reduce("", (x, y) -> x + "|" + y);
        System.out.println(reduce1);

        //计算所有单词总长度
        Integer reduce2 = Stream.of(name.split(" ")).map(x -> x.length()).reduce(0, (x, y) -> x + y);
        System.out.println(reduce2);

        //max 使用
        Optional<String> max = Stream.of(name.split(" ")).max((x, y) -> x.length() - y.length());
        System.out.println(max.get());

        //短路操作
        OptionalInt first = new Random().ints().findFirst();
        System.out.println(first.getAsInt());
    }



}
