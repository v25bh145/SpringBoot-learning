package com.example.relationaldataaccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class RelationalDataAccessApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(RelationalDataAccessApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RelationalDataAccessApplication.class, args);
	}

	//在容器里面找这个组件，如果找到了就注入进来(根据type注入)
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {

		log.info("Creating tables");

		//我们使用的数据库：见pom.xml 27-32行，使用h2database，(嵌入式，并且是内存模式)
		//https://www.cnblogs.com/cnjavahome/p/8995650.html
		jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE customers(" +
				"id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

		//将名字分为姓和名

		//guides上面的用法，将   [对象型]   类型(这里是String型)的数组转化为list集合
		//stream 化为流
		//map 流的中间操作，collect则是流的终端操作

//		List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
//				.map(name -> name.split(" "))
//				.collect(Collectors.toList());

		//一步到位，返回其元素为指定值的顺序有序流
		List<Object[]> splitUpNames = Stream.of("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long")
				.map(name -> name.split(" "))
				.collect(Collectors.toList());

		//打印每个分割后的姓&名
		splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

		//batchUpdate: 批操作注入，将splitUpNames[i][0]注入first_name，splitUpName[i][1]注入last_name
		jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?, ?)", splitUpNames);

		log.info("Querying for customer records where fist_name = 'Josh': ");

		//query(sql, params..., callback(rs, rowNum))
		//sql: 查询的sql语句(参数用"?"表示);
		//params: "?"对应的参数
		//callback(rs, rowNum): rs: ResultSet类型，使用rs.next()向下迭代，使用getXXX(Type)获取数据; rowNum: 数据条数(因为这里只有一条数据，就没有循环收集数据了)
		//query函数返回callback的返回值
		//之后使用forEach打印出数据(从这里可以看出来，这里的执行顺序是串行，先获取到数据再执行forEach方法)
		jdbcTemplate.query("SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] {"Josh"},
				(rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
		).forEach(customer -> log.info(customer.toString()));

	}
}
