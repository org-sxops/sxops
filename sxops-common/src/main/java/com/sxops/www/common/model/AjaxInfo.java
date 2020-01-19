package com.sxops.www.common.model;


import com.sxops.www.common.constant.Const;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>Description: [ajax返回前台信息vo]</p>
 * Created on 2016年3月31日
 * Copyright (c) 2016 山西省壹加柒网络技术有限公司
 * @author <a href="mailto: liuchao@sxops.com">葛伟</a>
 * @version 1.0
 */
public class AjaxInfo<T> {


    /**
     * <p>Discription:[成功请求返回状态码:200]</p>
     */
    public static final Integer CODE_SUCCESS = 200;

    /**
     * <p>Discription:[请求参数错误:400]</p>
     */
    public static final Integer CODE_PARAM_ERROR = 400;

    /**
     * 参数错误提示信息
     */
    public static final String PARAM_ERROR_MSG = "参数错误";

    /**
     * <p>Discription:[身份验证错误:401]</p>
     */
    public static final Integer CODE_UNAUTHORIZED_ERROR = 401;


    /**
     * <p>Discription:[拦截器无权限:401]</p>
     */
    public static final Integer CODE_APP_UNAUTHORIZED_ERROR = 411;

    /**
     * 权限不足描述信息
     */
    public static final String CODE_UNAUTHORIZED_ERROR_MSG = "权限不足";

    /**
     * <p>Discription:[请求被拒绝错误:403]</p>
     */
    public static final Integer CODE_FORBIDDEN_ERROR = 403;


    /**
     * <p>Discription:[资源不存在:404]</p>
     */
    public static final Integer CODE_NOT_FOUND_ERROR = 404;

    /**
     * <p>Discription:[资源状态变更冲突:409]</p>
     */
    public static final Integer CODE_DISABLED_STATE_ERROR = 409;

    /**
     * <p>Discription:[服务系统错误:500]</p>
     */
    public static final Integer CODE_SERVER_ERROR = 500;

    /**
     * <p>Discription:[200:成功，其他失败]</p>
     */
    @ApiModelProperty("返回状态码")
    private Integer code;
    /**
     * <p>Discription:[请求返回信息]</p>
     */
    @ApiModelProperty("返回状态信息")
    private String msg;
    /**
     * <p>Discription:[请求返回数据]</p>
     */
    @ApiModelProperty("请求返回数据")
    private T data;

    
    /**
     * <p>Discription:[判断是否返回成功]</p>
     * Created on 2017年10月20日
     * @return Boolean
     * @author [葛伟]
     */
    public Boolean isSuccess() {
        if (this.getCode().equals(CODE_SUCCESS)) {
            return true;
        }
        return false;
    }

    /**
     * <p>Discription: [产生操作成功的AJAX的返回值] </p>
     * Created on: 2017/5/23 17:04
     * @return AjaxInfo 包含【code】为：200
     * @author <a href="mailto: gewei@sxops.com">葛伟</a>
     */
    public static AjaxInfo renderSuccess() {
        AjaxInfo ajaxInfo = new AjaxInfo();
        ajaxInfo.setCode(CODE_SUCCESS);
        return ajaxInfo;
    }

    /**
     * <p>Discription: [产生操作成功的AJAX的返回值] </p>
     * Created on: 2017/5/23 17:04
     * @return AjaxInfo 包含【code】为：200
     * @author <a href="mailto: gewei@sxops.com">葛伟</a>
     */
    public static AjaxInfo renderSuccess(String msg) {
        AjaxInfo ajaxInfo = new AjaxInfo();
        ajaxInfo.setCode(CODE_SUCCESS);
        ajaxInfo.setMsg(msg);
        return ajaxInfo;
    }

    public static AjaxInfo renderSuccess(Object data) {
        AjaxInfo ajaxInfo = new AjaxInfo();
        ajaxInfo.setCode(CODE_SUCCESS);
        ajaxInfo.setMsg("SUCCESS");
        ajaxInfo.setData(data);
        return ajaxInfo;
    }

    public static AjaxInfo renderSuccess(Integer code, Object data) {
        AjaxInfo ajaxInfo = new AjaxInfo();
        ajaxInfo.setCode(code);
        ajaxInfo.setData(data);
        return ajaxInfo;
    }
    /**
     * <p>Discription: [获取一个错误] </p>
     * Created on: 2017/11/2 15:20
     * @param msg 错误信息
     * @return code 错误码
     * @author [葛伟]
     */
    public static AjaxInfo renderError(Integer code,String msg){
        setHttpStatus(CODE_SERVER_ERROR);
        AjaxInfo ajaxInfo = new AjaxInfo();
        ajaxInfo.setCode(code);
        ajaxInfo.setMsg(msg);
        return ajaxInfo;
    }

    /**
     * <p>Discription: [获取一个警告，前台获取弹出警告，但是操作不中断] </p>
     * Created on: 2017/11/2 15:20
     * @param msg 提示信息
     * @return code 提示码
     * @author [葛伟]
     */
    public static AjaxInfo renderWarn(Integer code,String msg){
        AjaxInfo ajaxInfo = new AjaxInfo();
        ajaxInfo.setCode(code);
        ajaxInfo.setMsg(msg);
        return ajaxInfo;
    }

    /**
     * <p>Discription: [获取一个警告，前台获取弹出警告，但是操作不中断] </p>
     * Created on: 2017/11/2 15:20
     * @param msg 提示信息
     * @return code 提示码
     * @author [葛伟]
     */
    public static AjaxInfo renderWarn(Integer code,String msg,Object object){
        AjaxInfo ajaxInfo = new AjaxInfo();
        ajaxInfo.setCode(code);
        ajaxInfo.setMsg(msg);
        ajaxInfo.setData(object);
        return ajaxInfo;
    }
    /**
     * <p>Discription: [获取一个错误] </p>
     * Created on: 2017/11/2 15:20
     * @return code 错误码
     * @author [葛伟]
     */
    public static AjaxInfo renderError(Integer code){
        setHttpStatus(CODE_SERVER_ERROR);
        AjaxInfo ajaxInfo = new AjaxInfo();
        ajaxInfo.setCode(code);
        return ajaxInfo;
    }

    /**
     * <p>Description: [获取一个错误] </p>
     * Created on: 2017/11/20
     * @param object 错误信息
     * @return code 错误码
     * @author [葛伟]
     */
    public static AjaxInfo renderError(Integer code, Object object){
        setHttpStatus(CODE_SERVER_ERROR);
        AjaxInfo ajaxInfo = new AjaxInfo();
        ajaxInfo.setCode(code);
        ajaxInfo.setData(object);
        return ajaxInfo;
    }

    /**
     * <p>Discription: [默认设置一个错误错误码为500，提示信息为系统繁忙] </p>
     * Created on: 2017/11/2 15:20
     * @return code 错误码500  提示信息为系统繁忙
     * @author [葛伟]
     */
    public static AjaxInfo renderError(){
        setHttpStatus(CODE_SERVER_ERROR);
        AjaxInfo ajaxInfo = new AjaxInfo();
        ajaxInfo.setCode(CODE_SERVER_ERROR);
        ajaxInfo.setMsg(Const.GLOBAL_ERROR_INFO);
        return ajaxInfo;
    }

    /**
     * <p>Description: [默认设置一个错误错误码为500，提示信息为系统繁忙，输出异常信息] </p>
     * Created on: 2018/2/6
     * @return code 错误码500  提示信息为系统繁忙
     * @author [葛伟]
     */
    public static AjaxInfo renderErrorData(Exception e){
        setHttpStatus(CODE_SERVER_ERROR);
        AjaxInfo ajaxInfo = new AjaxInfo();
        ajaxInfo.setCode(CODE_SERVER_ERROR);
        ajaxInfo.setMsg(Const.GLOBAL_ERROR_INFO);
        StringBuilder exception = new StringBuilder(e.toString());
        for(StackTraceElement elem : e.getStackTrace()) {
            exception.append("\n");
            exception.append(elem);
        }
        ajaxInfo.setData(exception.toString());
        return ajaxInfo;
    }

    /**
     * <p>Description: [返回参数错误提示信息] </p>
     * Created on: 2017/12/08
     * @return code 错误码400  提示信息为参数错误
     * @author [葛伟]
     */
    public static AjaxInfo renderParamError(){
        setHttpStatus(CODE_SERVER_ERROR);
        AjaxInfo ajaxInfo = new AjaxInfo();
        ajaxInfo.setCode(CODE_PARAM_ERROR);
        ajaxInfo.setMsg(PARAM_ERROR_MSG);
        return ajaxInfo;
    }

    /**
     * <p>Description: [返回权限不足提示信息] </p>
     * Created on: 2017/12/26
     * @return code 错误码401  提示信息为权限不足
     * @author [葛伟]
     */
    public static AjaxInfo renderUnauthorzied(){
        AjaxInfo ajaxInfo = new AjaxInfo();
        ajaxInfo.setCode(CODE_UNAUTHORIZED_ERROR);
        ajaxInfo.setMsg(CODE_UNAUTHORIZED_ERROR_MSG);
        return ajaxInfo;
    }


    public Integer getCode() {
        return code;
    }

    public AjaxInfo setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public AjaxInfo setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public AjaxInfo setData(T data) {
        this.data =
                data;
        return this;
    }

    /**
     * 设置响应的httpstatus
     * @param code
     */
    private static void setHttpStatus(int code) {
        /*ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        response.setStatus(code);*/
    }

}
