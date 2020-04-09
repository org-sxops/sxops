package com.sxops.www.common.xss;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


/** 
 * <p>Description: [过滤多个相同属性拼接问题，只取第一个属性值]</p>
 * Created on 2017年11月20日
 * @author  <a href="mailto: gewei@sxops.com">葛伟</a>
 * @version 1.0 
 * Copyright (c) 2017 山西省壹加柒网络技术有限公司
 */ 
public class XSSHttpRequestParamsFirst extends HttpServletRequestWrapper {
    public XSSHttpRequestParamsFirst(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
    	return super.getParameter(name);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        String [] newValue = new String[1];
        //取数组中的第一个参数
        if(values != null){
            newValue[0] = values[0];
            return newValue;
        }
        return null;
    }

}