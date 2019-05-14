package com.sxops.linfen.common.util;

import com.alibaba.fastjson.JSONObject;
import com.sxops.linfen.common.util.log.LogMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by didi on 2017/5/3.
 */
public class HttpClientUtils {
	
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    private static String DEFAULT_CONTENT_TYPE = "application/x-linfen-form-urlencoded; charset=UTF-8";

    private int iGetResultCode;
    
    /** HttpClient */
    private static CloseableHttpClient httpclient;
    
    private static CloseableHttpResponse response;

    public HttpClientUtils() {
    }

    public HttpClientUtils(CloseableHttpClient httpclient) {
    	HttpClientUtils.httpclient = httpclient;
    }

    public int getiGetResultCode() {
        return iGetResultCode;
    }

    public void setiGetResultCode(int iGetResultCode) {
        this.iGetResultCode = iGetResultCode;
    }

    public String getStrGetResponseBody() {
        return strGetResponseBody;
    }

    public void setStrGetResponseBody(String strGetResponseBody) {
        this.strGetResponseBody = strGetResponseBody;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    private String strGetResponseBody;
    private String errorInfo;

    public static String urlEncode(String source,String encode) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source,encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "0";
        }
        return result;
    }

    public static String urlEncodeGBK(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source,"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "0";
        }
        return result;
    }

    /**
     * Http Get请求
     *
     * @param url   the url
     * @param param the param
     * @return boolean
     * @author mahaiyuan
     * @date 2015年9月8日 上午11:39:17
     */
    public  boolean executeGetMethod(String url, String param) {
        return executeGetMethod(url, param, null);
    }

    /**
     * HTTP GET请求
     *
     * @param url     the url
     * @param param   the param
     * @param headers the headers
     * @return boolean
     * @author mahaiyuan
     * @date 2015年9月8日 上午11:41:00
     */
    public  boolean executeGetMethod(String url, String param, Map<String, String> headers) {
        if (url == null || url.length() <= 0) {
            errorInfo = "url为空值";
            LOGGER.error(LogMessage.getNew().add("发起HTTP请求，请求URL：" + url + "; 发送请求异常：", errorInfo).toString());
            return false;
        }
        StringBuffer serverURL = new StringBuffer(url);
        if (param != null && param.length() > 0) {
            serverURL.append("?");
            serverURL.append(param);
        }
        HttpGet httpget = new HttpGet(serverURL.toString());
        if (null != headers && !headers.isEmpty()) {
            Iterator<String> it = headers.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                String value = headers.get(key);
                httpget.setHeader(key, value);
            }
        }
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            entity.getContent();
            iGetResultCode = response.getStatusLine().getStatusCode();
            strGetResponseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
            if (iGetResultCode >= 200 && iGetResultCode < 303) {
                return true;
            } else if (iGetResultCode >= 400 && iGetResultCode < 500) {
                errorInfo = "请求资源不存在或内部错误" + iGetResultCode;
            } else {
                errorInfo = "服务器出错" + iGetResultCode;
            }
            LOGGER.error(LogMessage.getNew().add("发起HTTP请求，请求URL：" + url + "; 发送请求异常：", errorInfo).toString());
        } catch (Exception ex) {
        	LOGGER.error(LogMessage.getNew().add("发起HTTP请求，请求URL：" + url + "; 发送请求异常：", ex).toString());
        } finally {
            httpget.releaseConnection();
        }
        return false;
    }

    /**
     * HTTP POST请求封装方法
     * 默认Content-type: application/x-linfen-form-urlencoded; charset=UTF-8
     *
     * @param strURL 请求地址
     * @param param  请求参数
     * @return boolean
     * @author mahaiyuan
     * @date 2015年11月12日 上午10:47:36
     */
    public  boolean executePostMethod(String strURL, Map<String, String> param) {
        try {
            return executePostMethod(strURL, param, DEFAULT_CONTENT_TYPE, null);
        }catch (Exception e){
            return false;
        }
    }

    /**
     * HTTP POST请求封装方法
     *
     * @param strURL       请求地址
     * @param param        请求参数
     * @param contentType  请求头中Content-Type的值[必要参数]
     * @param otherHeaders 其他请求头[非必要]
     * @return 返回POST请求结果 ，成功返回true,否则返回false;响应内容封装在strGetResponseBody字段中
     * @throws Exception the exception
     * @author mahaiyuan
     * @date 2015年9月11日 上午9:50:12
     */
    public  boolean executePostMethod(String strURL, Map<String, String> param,
                                     String contentType, Map<String, String> otherHeaders) throws Exception {
        LOGGER.info("url={}, param={}", strURL, JSONObject.toJSONString(param));
        if (strURL == null || strURL.length() <= 0) {
            errorInfo = "URL为空值";
            LOGGER.error(LogMessage.getNew().add("发起HTTP请求，请求URL：" + strURL + "; 发送请求异常：", errorInfo).toString());
            return false;
        }
        HttpPost httppost = new HttpPost(strURL);
        httppost.setHeader("Content-type", contentType);
        if (null != otherHeaders) {
            Iterator<String> it = otherHeaders.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                String value = otherHeaders.get(key);
                httppost.setHeader(key, value);
            }
        }
        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        for (Entry<String, String> entry : param.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nvps));
            response = httpclient.execute(httppost);
            iGetResultCode = response.getStatusLine().getStatusCode();
            strGetResponseBody = EntityUtils.toString(response.getEntity());
            LOGGER.info("executePostMethod resultCode={}", iGetResultCode);
            if (iGetResultCode >= 200 && iGetResultCode < 303) {
                if (302 != iGetResultCode) {
                    LOGGER.info("executePostMethod responseBody={}", strGetResponseBody);
                }
                return true;
            } else if (iGetResultCode >= 400 && iGetResultCode < 500) {
                errorInfo = "请求资源不存在或内部错误" + iGetResultCode;
            } else {
                errorInfo = "服务器出错" + iGetResultCode;
            }
            LOGGER.error(LogMessage.getNew().add("发起HTTP请求，请求URL：" + strURL + "; 发送请求异常：", errorInfo).toString());
        } catch (Exception ex) {
            LOGGER.error(LogMessage.getNew().add("发起HTTP请求，请求URL：" + strURL + "; 发送请求异常：", ex).toString());
        } finally {
            httppost.releaseConnection();
        }
        return false;
    }

    /**
     * 向指定URL发送GET方法的请求
     * @param url 发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param header 请求头
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param ,Map<String, String> header) {
        String result = "";
        BufferedReader in = null;
        String urlNameString = url + "?" + param;
        try {
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setConnectTimeout(5000);  
            if(header != null && !header.isEmpty()){
            	Set<Entry<String, String>> entrySet = header.entrySet();
            	for (Entry<String, String> entry : entrySet) {
            		connection.setRequestProperty(entry.getKey(),entry.getValue());
				}
            }
            connection.connect();
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            LOGGER.error(LogMessage.getNew().add("发起HTTP请求，请求URL：" + urlNameString + "; 发送请求异常：", e).toString());
            return null;
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
            	LOGGER.error(LogMessage.getNew().add("发起HTTP请求，请求URL：" + urlNameString + "; 关闭输入流发生异常：", e2).toString());
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param map 请求头
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param, Map<String, String> map) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            if (map != null && map.size() > 0){
                for (String key:map.keySet()){
                    conn.setRequestProperty(key, map.get(key));
                }
            } else {
                conn.setRequestProperty("Content-type", "application/x-linfen-form-urlencoded");
            }
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            LOGGER.error(LogMessage.getNew().add("发起HTTP请求，请求URL：" + url + "; 请求发生异常：", e).toString());
            return null;
        }
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }catch(IOException ex){
                LOGGER.error(LogMessage.getNew().add("发起HTTP请求，请求URL：" + url + "; 关闭输入流发生异常：", ex).toString());
            }
        }
        return result;
    }

    /**
     * Gets cookie.
     *
     * @param request the request
     * @param name    the name
     * @return the cookie
     */
    public static String getCookie(HttpServletRequest request, String name) {
        String value = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    value = cookie.getValue();
                    break;
                }
            }
        }
        return value;
    }
}
