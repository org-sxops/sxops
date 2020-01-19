package com.sxops.www.common.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;

import com.sxops.www.basicException.MyHttpRequestException;
import com.sxops.www.common.enums.APIStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * Description: [http请求] Created on 2018/8/7 13:55
 *
 * @author 葛伟 Copyright (c)
 */
@Slf4j
@ManagedBean("httpRequest")
public class HttpRequestUtils {

	@Autowired
	private RestTemplate restTemplate;
	@Value("${third-api.district-url}")
	private String districtUrl;

	public static void main(String[] args) {
		JSONObject json = JSONObject.parseObject("{}");
    	System.out.println(json.toJSONString());
	}

	/**
	 * @Description 发送http get请求
	 * @Author gewei [geweihome@163.com]
	 * @Date 00:12 2019-05-19
	 * @Param [url, params]
	 * @return org.springframework.http.ResponseEntity<java.lang.String>
	 **/
	public String sendGetRequest(String url, String params) {
		long startTime = System.currentTimeMillis();
 		ResponseEntity<String> response = restTemplate.getForEntity(url+params, String.class);
		if(!response.getStatusCode().equals(HttpStatus.OK) || "{}".equals(response.getBody()) ||
				(JSONObject.parseObject(response.getBody()).get("code") != null && !"200".equals(JSONObject.parseObject(response.getBody()).get("code")))) {
			logErrorMessage(url,response);
    		throw MyHttpRequestException.responseError(url, params, response, (System.currentTimeMillis() - startTime));
    	}
		log.info("调用接口[{}];请求参数[{}];HTTP CODE：[{}];返回结果：[{}];请求时间：[{}]",
				url, JSON.toJSON(params),response.getStatusCode(), response.getBody(), (System.currentTimeMillis() - startTime));
		return response.getBody();
	}

	/**
	 * [发送http请求不对异常进行捕获]
	 *
	 * @param url    url
	 * @param params params
	 * @return {@link  ResponseEntity<String> }
	 * @author 息阳
	 */
	public ResponseEntity<String> sendRequestNoException(String url, String params) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = new HttpEntity<>(params, headers);
		long startTime = System.currentTimeMillis();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		log.info("调用接口[{}];请求参数[{}];\nHTTP CODE：[{}];返回结果：[{}];请求时间：[{}]",
				url, JSON.toJSON(entity), response.getStatusCode(), response.getBody(), (System.currentTimeMillis() - startTime));
		return response;
	}

	/**
	 * 对url开头进行判断,如果为流程中心地址,则在控制台打印异常消息内容
	 * 如果条件允许  可以将打印日志改为抛出一种自定义异常 从而避免被捕获
	 * @param url url
	 * @param response {@link ResponseEntity<String>}
	 */
	private void logErrorMessage(String url,ResponseEntity<String> response){
		try {
			if (url.startsWith(districtUrl)){
				String body = response.getBody();
				JSONObject jsonObject = JSONObject.parseObject(body);
				log.info("获取行政区域异常信息为:{}", jsonObject.get("message").toString());
			}
		} catch (Exception e) {
			log.trace("吞噬异常,吞噬内容为:{}",e);
		}
	}

	/**
	 * 判断必须的参数,如果参数为空,则抛出对应的异常
	 *
	 * @param objects 被判断的参数
	 */
	private void isParameterNotNull(Object... objects) {
		for (int i = 0; i < objects.length; i++) {
			Object object = objects[i];
			if (object == null) {
				throw new MyHttpRequestException(APIStatus.ERROR_7001);
			}
		}
	}

    /**
     * <p>Discription: 发送HTTP  POST请求</p>
     * Created on 2018/11/9 18:53
     *
     * @param url   POST请求API地址
     * @param json  请求的JSON串
     * @param clazz 请求返回值类型(如果你用Json方式取值则可以写 String.class)
     * @param <T>   泛型
     * @return Response返回对象
     * @author: <a href="mailto: xiyang@camelotchina.com">息阳</a>
     * @version 1.0
     */
    public <T> ResponseEntity<T> sendPostRequest(String url, String json, Class<T> clazz) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        long startTime = System.currentTimeMillis();
        ResponseEntity<T> response = null;
        try {
            response = restTemplate.exchange(url, HttpMethod.POST, entity, clazz);
        } catch (Exception e) {
            log.info("Http请求发生异常,异常信息为:{}", e);
        } finally {
            log.info("调用接口[{}];请求参数[{}];\nHTTP CODE：[{}];返回结果：[{}];请求时间：[{}]",
                    url,
                    JSON.toJSON(entity),
                    response == null ? null : response.getStatusCode(),
                    response == null ? null : response.getBody(),
                    (System.currentTimeMillis() - startTime));
        }
        if (!response.getStatusCode().equals(HttpStatus.OK) || "{}".equals(response.getBody())) {
            throw MyHttpRequestException.responseError(url, entity, response, (System.currentTimeMillis() - startTime));
        }
//        log.info("调用接口[{}];请求参数[{}];\nHTTP CODE：[{}];返回结果：[{}];请求时间：[{}]",
//                url, JSON.toJSON(entity), response.getStatusCode(), response.getBody(), (System.currentTimeMillis() - startTime));
        return response;
    }

    /**
     * <p>Discription: 发送HTTP  POST请求</p>
     * Created on 2018/11/9 18:53
     *
     * @param url   POST请求API地址
     * @param json  请求的JSON串
     * @param clazz 请求返回值类型(如果你用Json方式取值则可以写 String.class)
     * @param <T>   泛型
     * @return Response返回对象
     * @author: <a href="mailto: xiyang@camelotchina.com">刘香平</a>
     * @version 1.0
     */
    public <T> ResponseEntity<T> sendPostRequest(String url, String json, Class<T> clazz, String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        List<String> cookies = new ArrayList<>();
        cookies.add(token);
        headers.put(HttpHeaders.COOKIE,cookies);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        long startTime = System.currentTimeMillis();
        ResponseEntity<T> response = null;
        try {
            response = restTemplate.exchange(url, HttpMethod.POST, entity, clazz);
        } catch (Exception e) {
            log.info("Http请求发生异常,异常信息为:{}", e);
        } finally {
            log.info("调用接口[{}];请求参数[{}];\nHTTP CODE：[{}];返回结果：[{}];请求时间：[{}]",
                    url,
                    JSON.toJSON(entity),
                    response == null ? null : response.getStatusCode(),
                    response == null ? null : response.getBody(),
                    (System.currentTimeMillis() - startTime));
        }
        if (!response.getStatusCode().equals(HttpStatus.OK) || "{}".equals(response.getBody())) {
            throw MyHttpRequestException.responseError(url, entity, response, (System.currentTimeMillis() - startTime));
        }
        return response;
    }



}
