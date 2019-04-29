package com.camelotchina.www.scheduler.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>Description: [Spring容器工具类]</p>
 * Created on 2017年8月31日
 * @author  <a href="mailto: fuyu@camelotchina.com">付宇</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
@Component
public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext APPLICATIONCONTEXT;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (APPLICATIONCONTEXT == null) {
			APPLICATIONCONTEXT = applicationContext;
		}
	}

	/**
	 * <p>Discription:[获取applicationContext]</p>
	 * Created on 2017年8月31日
	 * @return
	 * @author:[付宇]
	 */
	public static ApplicationContext getApplicationContext() {
		return APPLICATIONCONTEXT;
	}

	/**
	 * <p>Discription:[通过name获取 Bean]</p>
	 * Created on 2017年8月31日
	 * @param name
	 * @return
	 * @author:[付宇]
	 */
	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}

	/**
	 * <p>Discription:[通过class获取Bean]</p>
	 * Created on 2017年8月31日
	 * @param clazz
	 * @return
	 * @author:[付宇]
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}

	/**
	 * <p>Discription:[通过name,以及Clazz返回指定的Bean]</p>
	 * Created on 2017年8月31日
	 * @param name
	 * @param clazz
	 * @return
	 * @author:[付宇]
	 */
	public static <T> T getBean(String name, Class<T> clazz) {
		return getApplicationContext().getBean(name, clazz);
	}

}