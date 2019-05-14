package com.sxops.www.linfen.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * Created on 2015年3月5日
 * @author  <a href="mailto: liruifeng@sxops.com">Goma 尹归晋</a>
 * @version 1.0 
 * Copyright (c) 2015 山西省壹加柒网络技术有限公司 交付部
 */
public class CookieHelper {

	/** utf8编码 **/
	private static final String UTF_8 = "UTF-8";
	
	private static Integer COOKIE_MAX_AGE = 1000 * 60 * 60 * 24 * 7;

	/**
	 * 
	 * <p>Discription:[添加cookie,默认有效期7天]</p>
	 * Created on 2015年3月5日
	 * @param response
	 * @param name
	 * @param value
	 * @author:[Goma 尹归晋]
	 */
	public static void setCookie(HttpServletResponse response, String name, String value) throws UnsupportedEncodingException {
		setCookie(response, name, value, COOKIE_MAX_AGE);
	}
	/**
	 * 
	 * <p>Discription:[设置带有httponly属性的cookie]</p>
	 * Created on 2017年9月8日
	 * @param response
	 * @param name
	 * @param value
	 * @author:[hanshixiong]
	 */
	public static void setHttpOnlyCookie(HttpServletResponse response, String name, String value) {
		Date d = new Date(System.currentTimeMillis()+COOKIE_MAX_AGE);
		response.addHeader("Set-Cookie", name+"="+value+";Expires="+d.toGMTString()+"; Path=/;HttpOnly");
	}
	
	/**
	 * 
	 * <p>Discription:[添加回家机制的cookie]</p>
	 * Created on 2015年3月5日
	 * @param response
	 * @param name
	 * @param value
	 * @author:[Goma 尹归晋]
	 */
	public static void setDialogCookie(HttpServletResponse response, String name, String value) throws UnsupportedEncodingException {
		if (value == null){
			value = "";
		}
		@SuppressWarnings("deprecation")
        Cookie cookie = new Cookie(name, URLEncoder.encode(value, UTF_8 ));
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	/**
	 * 
	 * <p>Discription:[添加cookie，并指定有效期]</p>
	 * Created on 2015年3月5日
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 * @author:[Goma 尹归晋]
	 */
	@SuppressWarnings("deprecation")
	public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) throws UnsupportedEncodingException {
		if (value == null) {
			value = "";
		}
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, UTF_8));
		if(maxAge!=0){
			cookie.setMaxAge(maxAge);
		}else{
			cookie.setMaxAge(COOKIE_MAX_AGE);
		}
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	/**
	 * 
	 * <p>Discription:[获得制定的cookie]</p>
	 * Created on 2015年3月5日
	 * @param request
	 * @param name
	 * @return
	 * @author:[Goma 尹归晋]
	 */
	public static Cookie getCookie(HttpServletRequest request, String name){
		Cookie[] cookies = request.getCookies();
		if(cookies != null ){
			for( Cookie c : cookies ){
				if( name.equals(c.getName()) ){
					return c;
				}

			}
		}
		return null;
	}
	
	/**
	 * 
	 * <p>Discription:[获得制定cookie值]</p>
	 * Created on 2015年3月5日
	 * @param request
	 * @param name
	 * @return
	 * @author:[Goma 尹归晋]
	 */
	@SuppressWarnings("deprecation")
	public static String getCookieVal(HttpServletRequest request, String name) throws UnsupportedEncodingException {
		Cookie cookie = getCookie(request, name);
		return cookie == null ? "" : URLDecoder.decode(cookie.getValue(),UTF_8);
	}
	
	/**
	 * 
	 * <p>Discription:[删除制定cookie]</p>
	 * Created on 2015年3月5日
	 * @param request
	 * @param response
	 * @param name
	 * @author:[Goma 尹归晋]
	 */
	public static void delCookie(HttpServletRequest request, HttpServletResponse response, String name){
		Cookie cookie = getCookie(request, name);
		if( cookie != null ){
			cookie.setValue("");
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	}
}
