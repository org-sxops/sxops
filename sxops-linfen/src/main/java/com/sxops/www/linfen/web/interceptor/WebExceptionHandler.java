package com.sxops.www.linfen.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.sxops.www.basicException.AbstractBasicException;
import com.sxops.www.basicException.ResultModel;
import com.sxops.www.common.enums.APIStatus;
import com.sxops.www.linfen.dao.model.userInfo.UserInfo;
import com.sxops.www.linfen.service.basic.ExceptionService;
import com.sxops.www.linfen.service.login.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.impl.piccolo.util.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionTimedOutException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;

@Slf4j
@ControllerAdvice
@ResponseBody
@RestControllerAdvice
public class WebExceptionHandler {

    /**
     * Sso异常
     */
 /*   @ExceptionHandler(value = SsoException.class)
    public final ResultModel<String> handlerSsoException(SsoException e) {
        SSOClient ssoClient = SpringApplicationContextHolder.getApplicationContext().getBean(SSOClient.class);
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        response.setStatus(403);
        String loginUrl = "/sso/login";
        try {
            loginUrl = ssoClient.getLoginURI();
        } catch (Exception e1) {
            log.error("获取SSO登陆地址出错,错误详情{}", e1.getMessage());
            e = new SsoException(APIStatus.ERROR_708);
        }
        ResultModel<String> error = ResultModel.error(e.getCode(), e.getMessage(), loginUrl);
        log.info(error.getData());
        return error;
    }*/

    /**
     * 自定义Http异常异常
     */
   /* @ExceptionHandler(value = MyHttpRequestException.class)
    public final ResultModel handleHTTPException(MyHttpRequestException e) {
        log.warn("自定义Http异常:", e);
        return ResultModel.error(e.getCode(), e.getMessage());
    }*/

   @Autowired
   private ExceptionService exceptionService;
   @Autowired
   private LoginService loginService;
    /**
     * 自定义异常
     */
    @ExceptionHandler(value = AbstractBasicException.class)
    public final ResultModel handleBasicException(AbstractBasicException basicException) {
        log.warn("自定义异常:", basicException);
        exceptionService.handler(basicException,"AbstractBasicException");
        return ResultModel.error(basicException.getCode(), basicException.getMessage());
    }



    /**
     * HTTP请求参数异常
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public final Object handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        boolean contains = this.isForeignUri(request);
        if (contains) {
            JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
            jsonObject.put("status", 1);
            jsonObject.put("error", APIStatus.ERROR_2001.getMessage());
            return jsonObject;
        }
        log.error("HTTP请求参数异常:", e);
        exceptionService.handler(e,"HttpMessageNotReadableException");
        return ResultModel.error(APIStatus.ERROR_2001);
    }

    /**
     * 对外接口异常返回结果处理
     */
    private boolean isForeignUri(HttpServletRequest request) {
        boolean flag = false;
        String requestURI = request.getRequestURI();
        if (requestURI.contains("project-info") || requestURI.contains("project-manage")) {
            flag = true;
        }
        return flag;
    }

    /**
     * 键重复异常捕获并处理
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    public final ResultModel handlerDuplicateKeyException(DuplicateKeyException e) {
        log.warn("键重复异常:", e);
        exceptionService.handler(e,"DuplicateKeyException");
        return ResultModel.error(APIStatus.ERROR_4002);
    }

    /**
     * 数据库抛出的其他异常
     */
    @ExceptionHandler(value = DataAccessException.class)
    public final ResultModel handlerDataAccessException(DataAccessException e) {

        log.error("数据库抛出的其他异常:", e);
        exceptionService.handler(e,"DataAccessException");
        return ResultModel.error(APIStatus.ERROR_4001);
    }

    /**
     * 运行时异常
     */
    @ExceptionHandler(value = TransactionTimedOutException.class)
    public final ResultModel handleTransactionTimedOutException(Exception e) {
        exceptionService.handler(e,"TransactionTimedOutException");
        log.warn(e.getMessage(), e);
        return ResultModel.error(APIStatus.ERROR_3003);
    }

    /** 空指针异常 */
    public final ResultModel handlerNullPointerException(NullPointerException e) {
        log.warn("发生空指针异常,异常内容为:{}", e);
        exceptionService.handler(e,"handlerNullPointerException");
        return ResultModel.error(APIStatus.ERROR_3003.getCode(), "空指针异常");
    }

    /** 运行时异常 */
    @ExceptionHandler(value = RuntimeException.class)
    public final ResultModel handleRuntimeException(Exception e) {
        log.warn(e.getMessage(), e);
        exceptionService.handler(e,"RuntimeException");
        return ResultModel.error(APIStatus.ERROR_3001.getCode(), e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public final ResultModel handlerHttpRequestMethodNotSupportedException(Throwable ex) {
        Exception exception = (Exception) ex;
        log.warn(exception.getMessage(), exception);
        exceptionService.handler(exception,"HttpRequestMethodNotSupportedException");
        return ResultModel.error(APIStatus.ERROR_2002);
    }

    @ExceptionHandler(BindException.class)
    public final ResultModel handlerBeanPropertyBindingResult(Throwable ex) {
        Exception exception = (BindException) ex;
        log.error(exception.getMessage(), exception);
        exceptionService.handler(exception,"BindException");
        return ResultModel.error(APIStatus.ERROR_2001);
    }

    /**
     * 文件上传超大异常
     */
    @ExceptionHandler(MultipartException.class)
    public final ResultModel handleFileUploadException(MultipartException exception) {
        log.error("文件上传超大异常:"+exception.getMessage(), exception);
        exceptionService.handler(exception,"MultipartException");
        return ResultModel.error(APIStatus.ERROR_5001.getCode(),"文件太大了");
    }

    /**
     * 其他异常捕获并处理
     */
    @ExceptionHandler({Exception.class})
    public final ResultModel handleException(Throwable ex) {
        Exception exception = (Exception) ex;
        log.error(exception.getMessage(), exception);
        exceptionService.handler(exception,"Exception");
        return ResultModel.error(APIStatus.ERROR_6001);
    }

}
