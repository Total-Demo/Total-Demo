package com.barry.lamdba;


import java.text.DecimalFormat;
import java.util.function.Function;

/*interface IMoneyFormat{
    String format(int i);
}*/

/**
 * 函数接口
 */
class MyMoney{

    private final int money;
    public MyMoney(int money){
        this.money=money;
    }

    //public void printMoney(IMoneyFormat moneyFormat){
                            //Function 函数接口
    public void printMoney(Function<Integer,String> moneyFormat){
        System.out.println("我的存款："+moneyFormat.apply(this.money));

    }
}

public class MoneyDemo {

    public static void main(String[] args) {
        MyMoney myMoney=new MyMoney(999999);
        //myMoney.printMoney(i -> new DecimalFormat("#,###").format(i));

        Function<Integer,String> moneyFormat=i -> new DecimalFormat("#,###").format(i);

        //函数接口链式操作：定义更少de接口，更加灵活的操作
        myMoney.printMoney(moneyFormat.andThen(s->"人民币："+s));
    }
}
