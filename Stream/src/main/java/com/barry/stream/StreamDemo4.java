package com.barry.stream;

import java.util.List;
import java.util.Random;
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
    }



}
