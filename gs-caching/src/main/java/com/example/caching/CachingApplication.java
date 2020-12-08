package com.example.caching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//开启缓存功能
//示例的缓存功能并没有使用特定的缓存库，而是使用ConcurrentHashMap(线程安全的哈希)
//ConcurrentHashMap: https://my.oschina.net/hosee/blog/675884
@EnableCaching
public class CachingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachingApplication.class, args);
	}

}
