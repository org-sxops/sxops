package com.sxops.www.common.util;

import com.sxops.www.common.constant.I18nConst;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * <p>Discription: [国际化配置] </p>
 * Created on: 2017/11/9 10:17
 * @author [尹归晋]
 */
@Component
public class I18nUtil {
    @Resource
    private MessageSource messageSource;

    /**
     * <p>Discription: [获取指定key的国际化信息] </p>
     * Created on: 2017/11/9 10:17
     * @param key
     * @return String 国际化信息
     * @author [尹归晋]
     */
    public String getMessage(String key) {
        return getMessage(key, null);
    }

    /**
     *<p>Discription: [获取指定key的国际化信息] </p>
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @return
     */
    public String getMessage(String code, Object[] args){
        return getMessage(code, args, "");
    }

    /**
     *<p>Discription: [获取指定key的国际化信息] </p>
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @param defaultMessage : 没有设置key的时候的默认值.
     * @return
     */
    public String getMessage(String code,Object[] args,String defaultMessage){
        //这里使用比较方便的方法，不依赖request.
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }
    /**
     * Discription:[获取当前语言格式：zh或en]
     * @return 当前语言
     *
     * Created on 2017/11/9
     * @author: 尹归晋
     */
    public String getLanguage(){
        Locale locale = LocaleContextHolder.getLocale();
        return locale.getLanguage();
    }

    /**
     * <p>Discription: [获取当前语言是否是英文] </p>
     * Created on: 2017/11/20 15:01
     * @return true 英文 false 中文
     * @author [尹归晋]
     */
    public Boolean isEn(){
        Locale locale = LocaleContextHolder.getLocale();
        return I18nConst.LANGUAGE_EN.equals(locale.getLanguage());
    }
}