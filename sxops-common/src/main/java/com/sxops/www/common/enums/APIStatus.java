package com.sxops.www.common.enums;


import lombok.Getter;

@Getter
public enum APIStatus {
    /** 200 Ok. GET、PUT或PATCH请求正常处理. */
    SUCESS_000000(000000,"成功","SUCESS"),
    ERROR_100000(100000,"失败"," "),
    ERROR_100001(100001,"请求参数有误",""),
    ERROR_100002(100002,"请求被拒绝",""),

    ERROR_100102(100102,"数据库主键重复",""),
    ERROR_100103(100103,"数据库异常",""),
    ERROR_100104(100104,"数据类型转换异常",""),
    ERROR_100105(100105,"文件超大",""),


    ERROR_100200(102000,"服务器内部错误(运行超时)",""),





    ;

    private int code;
    private String message;
    private String enMessage;

    APIStatus(int code, String message, String enMessage) {
        this.code = code;
        this.message = message;
        this.enMessage = enMessage;
    }
}
