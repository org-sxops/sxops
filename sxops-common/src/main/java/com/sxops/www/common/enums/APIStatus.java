package com.sxops.www.common.enums;


import lombok.Getter;

@Getter
public enum APIStatus {
    /**
     * 200 统一认证为成功
     * <p>
     * 错误码分为4位数
     * 组成方式：错误类型（1位） + 错误信息（3位）
     * 例如 ： 请求参数有误 1001
     */
    SUCESS(200, "成功", "SUCESS"),
    ERROR(400, "失败", "ERROR"),
    /**
     * 1 用户登录 权限类
     */
    ERROR_1001(1001, "用户未登录", ""),
    ERROR_1002(1002, "用户权限不足", ""),
    /**
     * 2 请求参数类
     */
    ERROR_2001(2001, "请求参数有误", ""),
    ERROR_2002(2002, "请求失败", ""),

    /**
     * 3 内部错误类
     */
    ERROR_3001(3001, "服务器内部错误(运行超时)", ""),
    ERROR_3002(3002, "数据类型转换异常", ""),
    ERROR_3003(3003, "空指针异常", ""),

    /**
     * 4 数据库异常类
     */
    ERROR_4001(4001, "数据库异常", ""),
    ERROR_4002(4002, "数据库主键重复", ""),
    ERROR_4003(4003, "数据库操作失败", ""),


    /**
     * 5 附件类
     */
    ERROR_5001(5001, "文件超大", ""),


    /**
     * 其他自定义
     */

    ERROR_6001(6001, "服务器内部错误", ""),

    /** HTTP请求异常 */
    ERROR_7001(7001, "调用外部接口错误", ""),


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
