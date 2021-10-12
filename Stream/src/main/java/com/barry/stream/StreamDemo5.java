package com.barry.stream;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo5 {

    public static void main(String[] args) {

        //调用parallel 产生并行流
        IntStream.range(1,100).parallel().peek(StreamDemo5::debug).count();

    }

    public static void debug(int i){
        System.out.println("debug:"+i);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



}
