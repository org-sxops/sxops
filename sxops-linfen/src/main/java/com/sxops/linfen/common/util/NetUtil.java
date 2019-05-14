package com.sxops.linfen.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Description: [网络工具类]</p>
 * Copyright (c) 2017 山西省壹加柒网络技术有限公司
 * Created on 2018/3/28
 *
 * @author <a href="mailto: liruifeng@sxops.com">尹归晋</a>
 * @version 1.0
 */
public class NetUtil {

    /**
     * 获取真实的ip
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
