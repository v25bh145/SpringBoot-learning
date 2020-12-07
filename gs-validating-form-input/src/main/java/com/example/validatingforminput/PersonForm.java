package com.example.validatingforminput;

import javax.validation.constraints.*;


public class PersonForm {

    @NotNull
    //字符串长度
    @Size(min=2, max=30)
    private String name;

    @NotNull
    //年龄最小18
    @Min(18)
    private Integer age;

    public String toString() {
        return "Person(Name: " + this.name + ", Age: " + this.age + ")";
    }

    //自动创建getter和setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
