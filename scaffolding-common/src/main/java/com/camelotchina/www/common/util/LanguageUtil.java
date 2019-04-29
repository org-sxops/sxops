package com.camelotchina.www.common.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Description: [语言工具类]</p>
 * Copyright (c) 2017 北京柯莱特科技有限公司
 * Created on 2018/3/28
 *
 * @author <a href="mailto: liruifeng@camelotchina.com">尹归晋</a>
 * @version 1.0
 */
@Component
public class LanguageUtil {

    /**
     * 当前语言环境是否是中文
     * @return
     */
    public Boolean isCh() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String lang = request.getHeader("language");
        if ("en".equals(lang)) {
            return false;
        }
        return true;
    }
}
