package com.sxops.www.ecm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>Description: [拦截配置]</p>
 * Created on 2017年12月21日
 * @author <a href="mailto:liuxiangping@sxops.com">尹归晋</a>
 * @version 1.0 Copyright (c) 2017 山西省壹加柒网络技术有限公司 交付部
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {


	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
	}
}
