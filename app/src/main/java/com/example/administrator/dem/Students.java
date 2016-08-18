package com.example.administrator.dem;

public class Students {
    String name,id,age;
    public Students(String id, String name, String age){
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getAge() {
        return age;
    }

}
