package com.barry.stream;

import java.io.InputStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo1 {

    public static void main(String[] args) {


        int[] num={1,2,3};
        int count=0;
        // 外部迭代
        for(int i=0;i<num.length;i++){

            count+=num[i];
        }
        System.out.println(count);


        //内部迭代
        System.out.println(IntStream.of(num).sum());

        //内部迭代
        //map就是中间操作，返回stream的操作
        //sum就是终止操作
        System.out.println(IntStream.of(num).map(i->i*2).sum());


        System.out.println(IntStream.of(num).map(StreamDemo1::doubleNum).sum());
        System.out.println("惰性求值就是终止没有调用的情况下，中间操作不会执行");

        IntStream.of(num).map(StreamDemo1::doubleNum);
    }


    public static int doubleNum(int i){
        System.out.println("执行乘以2");
        return i*2;
    }
}
