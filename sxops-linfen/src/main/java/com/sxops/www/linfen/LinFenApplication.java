package com.sxops.www.linfen;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.sxops.www"})
@ServletComponentScan(basePackages = {"com.sxops.www"})
@MapperScan(basePackages = "com.sxops.www.linfen.dao.mapper")
@EnableAsync
@Slf4j
public class LinFenApplication {
	public static void main(String[] args) {
		SpringApplication.run(LinFenApplication.class, args);
		log.info("LinFen-server 已启动");
	}

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	@Bean
	public RestTemplate restTemplate(){
		RestTemplate restTemplate = restTemplateBuilder.build();
		restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
		return restTemplate;
	}


}
