package com.sxops.www.ecm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.sxops.www"})
@ServletComponentScan(basePackages = {"com.sxops"})
@MapperScan(basePackages = "com.sxops.www.dao.mapper")
@EnableAsync
public class EcmApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcmApplication.class, args);
		System.out.println("ecm服务启动");
	}
}
