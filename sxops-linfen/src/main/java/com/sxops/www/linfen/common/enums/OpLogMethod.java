package com.sxops.www.linfen.common.enums;

/**
 * <p>Discription: [日志记录方式] </p>
 * Created on: 2017/11/10 14:47
 * @author [尹归晋]
 */
public enum OpLogMethod {
    /** 普通日志，不记录请求和响应 **/
    NORMAL,
    /** 普通日志，记录请求 **/
    NORMAL_AND_REQUEST;
    OpLogMethod() {
    }
}
