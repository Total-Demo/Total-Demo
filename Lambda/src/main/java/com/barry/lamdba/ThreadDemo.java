package com.barry.lamdba;

/**
 * 函数式编程
 */
public class ThreadDemo {

    public static void main(String[] args) {
        new Thread(()-> System.out.println("haha")).start();
    }
}
