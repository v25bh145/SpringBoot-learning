package com.example.accessingdatamysql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//告诉Hibernate这是一个表示数据表的类
@Entity
public class User {
    //标注这个属性是数据表的主键
    @Id
    //主键的生成策略=:
    /*
    –IDENTITY：采用数据库ID自增长的方式来自增主键字段，Oracle 不支持这种方式；
    –AUTO： JPA自动选择合适的策略，是默认选项；
    –SEQUENCE：通过序列产生主键，通过@SequenceGenerator 注解指定序列名，MySql不支持这种方式
    –TABLE：通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植。
     */
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String email;


    //使用IDEA的alt + enter，自动创建getter和setter
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
