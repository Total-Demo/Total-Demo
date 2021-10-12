package com.barry.drools.bean;

import lombok.Data;

import java.util.List;

@Data
public class Person {

    private String name;
    private int age;
    private List<Car> cars;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
