package com.barry.lamdba;


/**
 * Lamdba表达式
 */
@FunctionalInterface
interface Interface1{
    int doubleNum(int i);

    //新特性2  接口  默认实现的方法
    default int add(int a, int b){
        return a+b;
    }
}

@FunctionalInterface
interface Interface2{
    int doubleNum(int i);

    //新特性2  接口  默认实现的方法
    default int add(int a, int b){
        return a+b;
    }
}

@FunctionalInterface
interface Interface3 extends Interface1, Interface2 {

    @Override
    default int add(int a, int b) {
        return Interface1.super.add(a,b);
    }
}

public class LamdbaDemo {
//lamdba 表达式实现了一个指定接口的对象实例，接口里必须只有一个方法需要实现

    public static void main(String[] args) {
        Interface1 i1= i -> i*2;

        System.out.println(i1.doubleNum(5));
        System.out.println(i1.add(1,3));
        Interface1 i2=(i)->i*2;
        Interface1 i3=(int i)->i*2;
        Interface1 i4= i -> {
            return i*2;
        };
    }

}
