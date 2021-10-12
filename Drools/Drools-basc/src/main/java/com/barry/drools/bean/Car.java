package com.barry.drools.bean;

import lombok.Data;

@Data
public class Car {

    private String name;
    private Double price;
    private Double finalPrice;
    private Person person;

    public Car(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Car(String name, Double price, Double finalPrice, Person person) {
        this.name = name;
        this.price = price;
        this.finalPrice = finalPrice;
        this.person = person;
    }

    public Car() {
    }


}
