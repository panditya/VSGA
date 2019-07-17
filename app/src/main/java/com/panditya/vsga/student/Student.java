package com.panditya.vsga.student;

public class Student {
    protected Integer id;
    protected String name;
    protected String hobby;
    protected String address;

    public Student(Integer id, String name, String hobby, String address) {
        this.id = id;
        this.name = name;
        this.hobby = hobby;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
