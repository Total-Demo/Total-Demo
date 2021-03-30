package com.barry.lamdba;

import java.util.function.Function;

/**
 * 级联表达式和柯里化
 * 柯里化：把多个参数的函数转换为只有一个参数的函数
 * 柯里化的目的：函数的标准化
 * 高阶函数：返回函数的函数
 *
 */
public class CurryDemo {

    public static void main(String[] args) {
        //实现了x+y的级联表达式
        Function<Integer,Function<Integer,Integer>> function=x -> y -> x+y;
        System.out.println(function.apply(2).apply(3));


        Function<Integer,Function<Integer,Function<Integer,Integer>>> function2=x -> y ->z-> x+y+z;
        int[] a={2,3,4};
        Function f=function2;
        for(int i=0;i<a.length;i++){
            if(f instanceof Function){
                Object o=f.apply(a[i]);
                if(o instanceof Function){
                    f=(Function)o;
                }else {
                    System.out.println("调用结束：结果为"+o);
                }
            }

        }
    }

}
