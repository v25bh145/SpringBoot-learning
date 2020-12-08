package com.example.caching;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class SimpleBookRepository implements BookRepository {

	@Override
	//启动对"books"的缓存，把这行注解分别注释掉与不注释掉各运行一遍，康康命令行输出的区别
	//Cacheable的用法: https://www.cnblogs.com/fashflying/p/6908028.html
	@Cacheable("books")
	public Book getByIsbn(String isbn) {
		simulateSlowService();
		return new Book(isbn, "Some book");
	}

	// Don't do this at home
	private void simulateSlowService() {
		try {
			//num+L: 表示长整型
			long time = 3000L;
			//sleep持续3s，我们假设这里在模拟某些很慢的服务
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

}
