package com.example.consumingrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//domain class
//这个简单的类有一些属性以及getter/setter方法
//ignoreUnknown: 开启后将忽略类中不存在的字段(没有被绑定到这个类型中的任何属性都会被忽略)
//如果JSON中的字段名和键不匹配，可以使用@jsonproperty注释注定JSON的确切键
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    private String type;
    //Value在后面所写的Value.java中实现
    private Value value;

//    public Quote() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    //重载的是Object的方法
    @Override
    public String toString() {
        //这里将自动调用value的toString()方法
        return "Quote{" +
                "type='" + type + '\'' +
                ", value='" + value +
                '}';
    }
}
