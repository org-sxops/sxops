package com.sxops.www.linfen.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description: [拦截器]</p>
 * Created on 2017年12月21日
 * @author  <a href="mailto:liuxiangping@sxops.com">尹归晋</a>
 * @version 1.0 
 * Copyright (c) 2017 山西省壹加柒网络技术有限公司 交付部
 */
@Component
public class CommonInterceptor extends HandlerInterceptorAdapter {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(CommonInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
