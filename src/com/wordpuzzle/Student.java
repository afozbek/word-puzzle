package com.wordpuzzle;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = -4769335040431480336L;

    private String name;
    private Number no;

    public Student(String name, Number no) {
        this.name = name;
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getNo() {
        return no;
    }

    public void setNo(Number no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "İsminiz: " + name + "\nNumaranız: " + no;
    }
}
