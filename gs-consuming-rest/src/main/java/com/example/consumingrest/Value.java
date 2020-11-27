package com.example.consumingrest;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {

    private long id;
    private String quote;

//    public Value() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    //重载的是Object的方法
    @Override
    public String toString() {
        return "Quote{" +
                "id='" + id +
                ", quote='" + quote + '\'' +
                '}';
    }
}
