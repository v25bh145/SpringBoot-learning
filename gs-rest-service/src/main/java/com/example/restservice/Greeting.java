package com.example.restservice;

//RestfulAPI接口 - 返回JSON
public class Greeting {
    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    //getter在IDEA里只用在上面的final字段的提示中点击"创建getter"就可以一步创建了
    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
