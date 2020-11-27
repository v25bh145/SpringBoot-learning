package com.example.consumingrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumingRestApplication {

	//一个控制台日志
	private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConsumingRestApplication.class, args);
	}

	//组件，使用Jackson JSON处理传入的数据(JSON形式与对象形式转换的桥梁)
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception
	{
		return args -> {
			//getForObject: 将url中获取的JSON资源通过对象类的domain class转换为对象(domain class)形式
			//这个url将返回类似的数据格式：(依据这个格式可以摸清楚Quote.java与Value.java是怎么构造的，其实就是两个domain class)
			/*
			{
			   type: "success",
			   value: {
				  id: 10,
				  quote: "Really loving Spring Boot, makes stand alone Spring apps easy."
			   }
			}
			 */
			//我们将会看到这样的输出(输出成功即complete):
			//Quote{type='success', value='Quote{id='6, quote='It embraces convention over configuration, providing an experience on par with frameworks that excel at early stage development, such as Ruby on Rails.'}}
			Quote quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
			log.info(quote.toString());
		};
	}
}
