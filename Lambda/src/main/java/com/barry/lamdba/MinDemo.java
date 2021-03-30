package com.barry.lamdba;

import java.util.stream.IntStream;

/**
 * 函数式编程
 */
public class MinDemo {

    public static void main(String[] args) {
        int[] nums={333,555,1234,4325};

        int min= IntStream.of(nums).parallel().min().getAsInt();
        System.out.println(min);
    }
}
