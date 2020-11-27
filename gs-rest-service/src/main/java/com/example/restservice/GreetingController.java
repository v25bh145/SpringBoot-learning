package com.example.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

//注解声明这是一个Restful控制器
/*其与传统的MVC控制器的区别:
    RestfulWeb服务的控制器会返回一个数据对象，其将直接被HTTP响应返回为JSON格式，而传统的MVC服务器会返回一个渲染后(也就是加上HTML标签&样式，也叫后端渲染)的data给HTML
    在SpringBoot中，Restful控制器响应的数据对象将被自动转换为JSON格式
    详见:https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/converter/json/MappingJackson2HttpMessageConverter.html
 */
@RestController
public class GreetingController {

    private static final String template = "hello, %s!";
    //AtomicLong: 对长整型进行原子操作
    //在32位操作系统中，64位的long/double会被JVM当做两个分离的32位进行操作，而不具有原子性(不会还真的有人在用32位系统吧？不会吧不会吧不会吧)
    private final AtomicLong counter = new AtomicLong();

    //RestfulAPU中url的命名方式: https://imgchr.com/i/DsUBE8
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        //incrementAndGet 以原子方式自增1，之后返回值
        //format 格式化输出(%s/%d/%.2lf/...)
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}

//最后 打包JAR文件:
//mvnw clean package
//运行JAR文件:
//java -jar target/gs-rest-service-0.1.0.jar(去target看看这个文件名是什么，可能不是这个)
