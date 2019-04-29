package com.camelotchina.www.common.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * <p>Description: [文件处理]</p>
 * Copyright (c) 2017 北京柯莱特科技有限公司
 * Created on 2018/4/24
 *
 * @author <a href="mailto: liruifeng@camelotchina.com">尹归晋</a>
 * @version 1.0
 */
@Component
public class FileUtil {


    public String getDownLoadFileName(String fileName) throws UnsupportedEncodingException {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String agent = request.getHeader("USER-AGENT").toLowerCase();
        String encodeName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
        if (agent!=null&&agent.indexOf("safari") > -1) {
            encodeName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        }
        return encodeName;
    }

}
