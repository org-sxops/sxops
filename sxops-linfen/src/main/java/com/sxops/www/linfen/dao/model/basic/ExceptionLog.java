package com.sxops.www.linfen.dao.model.basic;


import com.sxops.www.common.annotation.Ignore;
import com.sxops.www.common.annotation.Like;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Description: [实体类]</p>
 * Copyright (c) 2017 山西省壹加柒网络技术有限公司
 * Created on 2017年11月10日
 *
 * @author <a href="mailto: gewei@sxops.com">葛伟</a>
 * @version 1.0
 */
@Data
@Table(name = "lf_exception_log")
public class ExceptionLog {

    /**
     * id
     **/
    @Id
    private Long id;

    /**
     *
     **/
    private String uri;

    /**
     * 操作人id
     **/
    @Column(name = "operator_code")
    private String operatorCode;

    /**
     * 操作人
     **/
    private String operator;

    /**
     * 操作人ip
     **/
    @Column(name = "operator_ip")
    private String operatorIp;

    /**
     * 错误描述
     **/
    @Like
    private String description;

    /**
     * 主机
     **/
    @Column(name = "host_name")
    private String hostName;

    /**
     * 错误详细
     **/
    private String detail;

    /**
     * 浏览器信息
     **/
    @Column(name = "brower_message")
    private String browerMessage;

    /**
     *
     **/
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 操作时间范围查询-开始时间
     **/
    @Transient
    @Ignore
    private String createTimeStart;

    /**
     * 日志类型
     */
    @Column(name = "exception_type")
    private String exceptionType;

    /**
     * 操作时间范围查询-结束时间
     **/
    @Transient
    @Ignore
    private String createTimeEnd;

    @Transient
    private String serverName;

}
