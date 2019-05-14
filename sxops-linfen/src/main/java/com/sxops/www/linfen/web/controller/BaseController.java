package com.sxops.www.linfen.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * <p>Description: [基础控制层]</p>
 * Copyright (c) 2017 山西省壹加柒网络技术有限公司
 * Created on 2017年11月02日
 *
 * @author <a href="mailto: yinguijin@sxops.com">尹归晋</a>
 * @version 1.0
 */
public class BaseController {

    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
        // Date 类型转换
        binder.registerCustomEditor(Long.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if(StringUtils.isNumeric(text)){
                    setValue(Long.parseLong(text));
                }
            }
        });
        //Double 类型转化
        binder.registerCustomEditor(Double.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if(StringUtils.isNumeric(text)){
                    setValue(Double.parseDouble(text));
                }
            }
        });
        //Float 类型转化
        binder.registerCustomEditor(Float.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if(StringUtils.isNumeric(text)){
                    setValue(Float.parseFloat(text));
                }
            }
        });
    }
}
