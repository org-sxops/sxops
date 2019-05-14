package com.sxops.www.linfen;

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
@ServletComponentScan(basePackages = {"com.sxops.www"})
@MapperScan(basePackages = "com.sxops.www.linfen.dao.mapper")
@EnableAsync
public class LinFenApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinFenApplication.class, args);
	}
}
