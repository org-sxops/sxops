package com.sxops.www.linfen.dao.model.basic;


import com.sxops.www.common.annotation.Ignore;
import com.sxops.www.common.annotation.Like;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "operate_log")
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
	private String system;

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

	/**
	 * <p>Description:[获取]</p>
	 * Created on 2017年11月10日
	 * @return Long 
	 * @author [葛伟]
	 */	
	public Long getId() {
		return id;
	}


	/**
	 * <p>Discription:[设置]</p>
	 * Created on 2017年11月10日
	 * @param id 
	 * @author [葛伟]
	 */		
    public void setId(Long id) {
		this.id = id;
	}

	
	/**
	 * <p>Description:[获取]</p>
	 * Created on 2017年11月10日
	 * @return String 
	 * @author [葛伟]
	 */	
	public String getOperateDesc() {
		return operateDesc;
	}


	/**
	 * <p>Discription:[设置]</p>
	 * Created on 2017年11月10日
	 * @param operateDesc 
	 * @author [葛伟]
	 */		
    public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	/**
	 * <p>Description:[获取操作人]</p>
	 * Created on 2017年11月10日
	 * @return String 操作人
	 * @author [葛伟]
	 */	
	public String getOperator() {
		return operator;
	}


	/**
	 * <p>Discription:[设置操作人]</p>
	 * Created on 2017年11月10日
	 * @param operator 操作人
	 * @author [葛伟]
	 */		
    public void setOperator(String operator) {
		this.operator = operator;
	}

	
	/**
	 * <p>Description:[获取请求路径]</p>
	 * Created on 2017年11月10日
	 * @return String 请求路径
	 * @author [葛伟]
	 */	
	public String getUri() {
		return uri;
	}


	/**
	 * <p>Discription:[设置请求路径]</p>
	 * Created on 2017年11月10日
	 * @param uri 请求路径
	 * @author [葛伟]
	 */		
    public void setUri(String uri) {
		this.uri = uri;
	}

	
	/**
	 * <p>Description:[获取操作人ip]</p>
	 * Created on 2017年11月10日
	 * @return String 操作人ip
	 * @author [葛伟]
	 */	
	public String getOperateIp() {
		return operateIp;
	}


	/**
	 * <p>Discription:[设置操作人ip]</p>
	 * Created on 2017年11月10日
	 * @param operateIp 操作人ip
	 * @author [葛伟]
	 */		
    public void setOperateIp(String operateIp) {
		this.operateIp = operateIp;
	}

	
	/**
	 * <p>Description:[获取请求]</p>
	 * Created on 2017年11月10日
	 * @return String 请求
	 * @author [葛伟]
	 */	
	public String getRequest() {
		return request;
	}


	/**
	 * <p>Discription:[设置请求]</p>
	 * Created on 2017年11月10日
	 * @param request 请求
	 * @author [葛伟]
	 */		
    public void setRequest(String request) {
		this.request = request;
	}

	
	/**
	 * <p>Description:[获取系统]</p>
	 * Created on 2017年11月10日
	 * @return String 系统
	 * @author [葛伟]
	 */	
	public String getSystem() {
		return system;
	}


	/**
	 * <p>Discription:[设置系统]</p>
	 * Created on 2017年11月10日
	 * @param system 系统
	 * @author [葛伟]
	 */		
    public void setSystem(String system) {
		this.system = system;
	}

	
	/**
	 * <p>Description:[获取操作时间]</p>
	 * Created on 2017年11月10日
	 * @return Date 操作时间
	 * @author [葛伟]
	 */	
	public Date getOperateTime() {
		return operateTime;
	}


	/**
	 * <p>Discription:[设置操作时间]</p>
	 * Created on 2017年11月10日
	 * @param operateTime 操作时间
	 * @author [葛伟]
	 */		
    public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateTimeStart() {
		return operateTimeStart;
	}

	public void setOperateTimeStart(String operateTimeStart) {
		this.operateTimeStart = operateTimeStart;
	}

	public String getOperateTimeEnd() {
		return operateTimeEnd;
	}

	public void setOperateTimeEnd(String operateTimeEnd) {
		this.operateTimeEnd = operateTimeEnd;
	}
}
