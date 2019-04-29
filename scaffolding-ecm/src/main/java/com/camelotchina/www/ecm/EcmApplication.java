package com.camelotchina.www.ecm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.camelotchina.www"})
@ServletComponentScan(basePackages = {"com.camelotchina"})
@MapperScan(basePackages = "com.camelotchina.www.dao.mapper")
@EnableAsync
public class EcmApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcmApplication.class, args);
	}
}
