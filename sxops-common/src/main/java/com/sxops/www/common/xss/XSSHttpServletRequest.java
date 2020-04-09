package com.sxops.www.common.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/** 
 * <p>Description: [xss脚本过滤]</p>
 * Created on 2017年11月21日
 * @author  <a href="mailto: gewei@sxops.com">葛伟</a>
 * @version 1.0 
 * Copyright (c) 2017 山西省壹加柒网络技术有限公司
 */ 
public class XSSHttpServletRequest extends HttpServletRequestWrapper {
	
	HTMLStringFilter htmlStringFilter;
	
    public XSSHttpServletRequest(HttpServletRequest request) {
        super(request);
        htmlStringFilter = new HTMLStringFilter();
        htmlStringFilter.init();
    }

    @Override
    public String getParameter(String name) {
        // 返回值之前 先进行过滤
        return super.getParameter(htmlStringFilter.cleanSafeHtml(name));
    }

    @Override
    public String[] getParameterValues(String name) {
        // 返回值之前 先进行过滤
        String[] values = super.getParameterValues(name);
        String [] newValue = new String[1];
        if(values != null){
            newValue[0] = htmlStringFilter.cleanSafeHtml(values[0]);
            return newValue;
        }
        return null;
    }

}