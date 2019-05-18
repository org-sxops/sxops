package com.sxops.www.basicException;

import com.alibaba.fastjson.JSON;
import com.sxops.www.common.enums.APIStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

/**
 * @Description 自定义Http异常
 * @Author gewei [geweihome@163.com]
 * @Date 23:58 2019-05-18
 * @Param
 * @return
 **/
@Slf4j
public class MyHttpRequestException extends AbstractBasicException {

    /**
     * <p>Discription:[字段功能描述]</p>
     */
    private static final long serialVersionUID = -7832693038932796035L;

    /**
     * 自定义Http异常构造器
     *
     * @param apiStatus {@link APIStatus} 请求枚举类
     */
    public MyHttpRequestException(APIStatus apiStatus) {
        super(apiStatus);
    }

    /**
     * 自定义Http异常构造器
     *
     * @param apiStatus {@link APIStatus} 请求枚举类
     * @param exception {@link Throwable}类型异常
     */
    public MyHttpRequestException(APIStatus apiStatus, Throwable exception) {
        super(apiStatus, exception);
    }

    /**
     * 自定义Http异常构造器
     *
     * @param code      code
     * @param message   信息
     * @param exception 异常
     */
    public MyHttpRequestException(int code, String message, Throwable exception) {
        super(code, message, exception);
    }

    /**
     * 返回结果出现错误(异常制造)
     *
     * @param url       url调用地址
     * @param entity    {@link HttpEntity<E>}
     * @param response  {@link ResponseEntity<T>}
     * @param time      执行时间
     * @param exception 异常
     * @param <E>       {@link HttpEntity<E>}
     * @param <T>       {@link ResponseEntity<T>}
     * @return {@link MyHttpRequestException}
     */
    public static <T, E> MyHttpRequestException responseError(String url, HttpEntity<E> entity, ResponseEntity<T> response, Long time, Throwable exception) {
        StringBuilder message = new StringBuilder();
        message.append("调用接口[")
                .append(url)
                .append("]，请求参数：:[")
                .append(JSON.toJSON(entity))
                .append("]，HTTP CODE:[")
                .append(response.getStatusCode())
                .append("]，返回结果:[")
                .append(response.getBody())
                .append("]，执行时间:[")
                .append(time)
                .append("]");
        log.error(message.toString(), exception);
        return new MyHttpRequestException(APIStatus.ERROR_7001, exception);
    }

    /**
     * 返回结果出现错误(异常制造)
     *
     * @param url      url调用地址
     * @param entity   {@link HttpEntity<String>}
     * @param response {@link ResponseEntity<T>}
     * @param time     执行时间
     * @param <T>      {@link ResponseEntity<T>}
     * @return {@link MyHttpRequestException}
     */
    public static <T> MyHttpRequestException responseError(String url, HttpEntity<String> entity, ResponseEntity<T> response, Long time) {
        return responseError(url, entity, response, time, null);
    }

    /**
     * 返回结果出现错误(异常制造)
     *
     * @param url      url
     * @param params   params
     * @param response {@link ResponseEntity<String>}
     * @param time     请求时间
     * @return {@link MyHttpRequestException}
     */
    public static MyHttpRequestException responseError(String url, String params, ResponseEntity<String> response, long time) {
        StringBuilder message = new StringBuilder();
        message.append("调用接口[")
                .append(url)
                .append("]，请求参数：:[")
                .append(params)
                .append("]，HTTP CODE:[")
                .append(response.getStatusCode())
                .append("]，返回结果:[")
                .append(response.getBody())
                .append("]，执行时间:[")
                .append(time)
                .append("]");
        log.error(message.toString());
        return new MyHttpRequestException(APIStatus.ERROR_7001, null);
    }
}
