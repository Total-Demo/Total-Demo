package com.barry.lamdba;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 变量引用
 */
public class VarDemo {

    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        //list=null; 匿名函数引用外面变量必须待是final java参数是传值而不是传引用，导致里外不一致
        Consumer<String> consumer=x->System.out.println(x+list);
        consumer.accept("abc");
    }
}
