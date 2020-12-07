package com.example.validatingforminput;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ValidatingFormInputApplication {
	//这个项目用于后端渲染
	//将模板中的form与form对象(在这里进行validate)绑定起来

	public static void main(String[] args) {
		SpringApplication.run(ValidatingFormInputApplication.class, args);
	}

}
