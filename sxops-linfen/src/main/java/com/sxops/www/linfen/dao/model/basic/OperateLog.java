package com.sxops.www.linfen.dao.model.basic;


import com.sxops.www.common.annotation.Ignore;
import com.sxops.www.common.annotation.Like;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * <p>Description: [实体类]</p>
 * Copyright (c) 2017 山西省壹加柒网络技术有限公司
 * Created on 2017年11月10日
 * @author  <a href="mailto: gewei@sxops.com">葛伟</a>
 * @version 1.0
 */
@Data
@Table(name = "lf_operate_log")
public class OperateLog {

	@Id
	private Long id;

	/**
	 * 
	 **/
	@Like
	@Column(name = "operate_desc")
	private String operateDesc;

	/**
	 * 操作人id
	 **/
	@JsonIgnore
	@Column(name = "operator_code")
	private String operatorCode;
	/**
	 * 操作人id
	 **/
	@JsonIgnore
	@Column(name = "operator_name")
	private String operatorName;

	/**
	 * 操作人
	 **/
	@Transient
	private String operator;

	/**
	 * 请求路径
	 **/
	private String uri;

	/**
	 * 操作人ip
	 **/
	@Column(name = "operate_ip")
	private String operateIp;

	/**
	 * 请求
	 **/
	private String request;

	/**
	 * 系统
	 **/
	@Column(name = "source_system")
	private String sourceSystem;

	/**
	 * 操作时间
	 **/
	@Column(name = "operate_time")
	private Date operateTime;

	/** 操作时间范围查询-开始时间 **/
	@Transient
	@Ignore
	private String operateTimeStart;

	/** 操作时间范围查询-结束时间 **/
	@Transient
	@Ignore
	private String operateTimeEnd;


}
