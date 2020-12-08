package com.example.caching;

//接口，图书库
public interface BookRepository {

	Book getByIsbn(String isbn);

}
