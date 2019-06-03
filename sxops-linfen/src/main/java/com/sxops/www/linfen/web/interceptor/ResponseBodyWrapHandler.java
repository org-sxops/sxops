package com.sxops.www.linfen.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.sxops.www.basicException.ResultModel;
import com.sxops.www.common.enums.APIStatus;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {

    /**
     * 对外接口返回值作其他处理
     */
    private static final String EXTERNAL = "external";

    private final HandlerMethodReturnValueHandler delegate;

    public ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }

    /**
     * 若返回值为true则对返回值进行包装,若为false则不处理
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        boolean supportsReturnType = delegate.supportsReturnType(returnType);
        return supportsReturnType;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        Object resultModel = changeResult(returnValue);
        delegate.handleReturnValue(resultModel, returnType, mavContainer, webRequest);
    }

    /**
     * <p>Description: [对返回值类型进行判断并包装为Http最外层获取的数据格式]<p>
     * Created on 2018年9月29日 下午12:31:47
     *
     * @param body
     * @return
     * @author <a href="mailto: xiyang@camelotchina.com">息阳</a>
     * @version 1.0
     * Copyright (c) 2018年
     */
    private Object changeResult(Object body) {

        String requestMethodStr = getRequestMethodStr();


        if (body instanceof JSONObject) {
            return body;
        }

        if (RequestMethod.POST.name().equals(requestMethodStr)) {
            return ResultModel.success(APIStatus.SUCESS, body);
        } else if (RequestMethod.DELETE.name().equals(requestMethodStr)) {
            return ResultModel.success(APIStatus.SUCESS, body);
        }
        ResultModel<Object> success = ResultModel.success(APIStatus.SUCESS, body);

        return success;
    }


    /**
     * <p>Description: [获取Requset请求的类型]<p>
     * Created on 2018年10月12日 下午3:03:04
     *
     * @return request请求类型
     * @author <a href="mailto: xiyang@camelotchina.com">息阳</a>
     * @version 1.0
     * Copyright (c) 2018年
     */
    private String getRequestMethodStr() {
        String requestMethodStr = "";

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        requestMethodStr = request.getMethod();

        return requestMethodStr;
    }

}


