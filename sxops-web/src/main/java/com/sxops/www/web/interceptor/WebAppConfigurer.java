package com.sxops.www.web.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>Description: [拦截器适配器]</p>
 * Created on 2017年12月21日
 * @author <a href="mailto:liuxiangping@sxops.com">尹归晋</a>
 * @version 1.0 Copyright (c) 2017 山西省壹加柒网络技术有限公司 交付部
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

	@Autowired
	private CommonInterceptor commonInterceptor;

	public void addInterceptors(InterceptorRegistry registry) {
		
		super.addInterceptors(registry);
		registry.addInterceptor(this.commonInterceptor);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedHeaders(new String[] { "*" }).allowedMethods(new String[] { "*" })
						.allowedOrigins(new String[] { "*" });
			}
		};
	}
}
