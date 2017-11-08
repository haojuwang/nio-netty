package com.lihao.netty.decode;


import org.msgpack.annotation.Message;

//必须要有注解
@Message
public class Student {
    private int number;
    private String grades;
    private String name;

    //必须提供空的构造方法
    public Student() {

    }
    public Student(int number, String grades, String name) {
        this.number = number;
        this.grades = grades;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "number=" + number +
                ", grades='" + grades + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
