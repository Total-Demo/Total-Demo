package com.barry.lamdba;



@FunctionalInterface
interface IMath{
    int add(int a, int b);
}

@FunctionalInterface
interface IMath2{
    int add(int a, int b);
}


@FunctionalInterface
interface IMath3{
    int add(int a, int b);
}

public class TypeDemo {

    public static void main(String[] args) {
        //变量类型定义
        IMath iMath=(x, y)->x+y;

        //数组里
        IMath[] iMaths={(x, y)->x+y};

        //强转
        Object o=(IMath)(x, y)->x+y;

        //通过返回类型
        IMath iMath1=createLamdba();

        //TypeDemo typeDemo=new TypeDemo();
        //typeDemo.test((x,y)->x+y);

        //当有二义性的时候，使用强转对应的接口解决
        TypeDemo typeDemo=new TypeDemo();
        typeDemo.test((IMath2)(x, y)->x+y);
    }
    

    public void test(IMath2 iMath){

    }
    public void test(IMath3 iMath){

    }
    public void test(IMath iMath){

    }
    public static IMath createLamdba(){
        return (x,y)->x+y;
    }
}
