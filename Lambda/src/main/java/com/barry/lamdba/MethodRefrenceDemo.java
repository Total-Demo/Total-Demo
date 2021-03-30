package com.barry.lamdba;


import java.util.function.*;

/**
 * 方法引用
 */
class  Dog{
    private String name="啸天犬";

    public int food=10;

    public Dog(){

    }
    public Dog(String name){
        this.name=name;
    }
    //狗叫 静态方法
    public static void bark(Dog dog){
        System.out.println(dog+"叫了");
    }
    //public int eat(Dog this,int num){  JDK默认会把当前实例传入给非静态方法，参数名为this,位置为第一个 ：非静态方法使用类名来方法引用
    public int eat(int num){
        System.out.println("吃了"+num+"斤");
        food-=num;
        return food;
    }
    @Override
    public String toString() {
        return this.name;
    }
}

public class MethodRefrenceDemo {

    public static void main(String[] args) {
        //Consumer<String> consumer=i->System.out.println(i);

        //方法引用
        Consumer<String> consumer=System.out::println;
        consumer.accept("哈哈哈哈");

        //静态方法的方法引用
        Consumer<Dog> consumer1= Dog::bark;
        Dog dog=new Dog();
        consumer1.accept(dog);

        // 非静态方法的方法引用
        Function<Integer,Integer> function=dog::eat;
        System.out.println("还剩"+function.apply(3)+"斤");

        UnaryOperator<Integer> unaryOperator=dog::eat;
        System.out.println("还剩"+unaryOperator.apply(3)+"斤");

        IntUnaryOperator intUnaryOperator=dog::eat;
        System.out.println("还剩"+intUnaryOperator.applyAsInt(3)+"斤");

        //非静态方法使用类名来方法引用
        BiFunction<Dog,Integer,Integer> biFunction= Dog::eat;
        System.out.println("还剩"+biFunction.apply(dog,1)+"斤");

        //构造函数的方法引用
        Supplier<Dog> supplier= Dog::new;
        System.out.println("创建了新对象："+supplier.get());

        //带参数的构造函数的方法引用
        Function<String, Dog> function2= Dog::new;
        System.out.println("又创建了新对象："+function2.apply("旺才"));
    }
}
