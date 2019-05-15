package com.sxops.www.linfen.dao.model.basic;


import com.sxops.www.common.annotation.Ignore;
import com.sxops.www.common.annotation.Like;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Description: [实体类]</p>
 * Copyright (c) 2017 山西省壹加柒网络技术有限公司
 * Created on 2017年11月10日
 *
 * @author <a href="mailto: liruifeng@sxops.com">尹归晋</a>
 * @version 1.0
 */
@Table(name = "exception_log")
public class ExceptionLog {

    /**
     * id
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 操作时间范围查询-结束时间
     **/
    @Transient
    @Ignore
    private String createTimeEnd;

    @Transient
    private String serverName;


    /**
     * <p>Description:[获取id]</p>
     * Created on 2017年11月10日
     *
     * @return Long id
     * @author [尹归晋]
     */
    public Long getId() {
        return id;
    }


    /**
     * <p>Discription:[设置id]</p>
     * Created on 2017年11月10日
     *
     * @param id id
     * @author [尹归晋]
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>Description:[获取]</p>
     * Created on 2017年11月10日
     *
     * @return String
     * @author [尹归晋]
     */
    public String getUri() {
        return uri;
    }


    /**
     * <p>Discription:[设置]</p>
     * Created on 2017年11月10日
     *
     * @param uri
     * @author [尹归晋]
     */
    public void setUri(String uri) {
        this.uri = uri;
    }


    /**
     * <p>Description:[获取操作人id]</p>
     * Created on 2017年11月10日
     *
     * @return String 操作人id
     * @author [尹归晋]
     */
    public String getOperatorCode() {
        return operatorCode;
    }


    /**
     * <p>Discription:[设置操作人id]</p>
     * Created on 2017年11月10日
     *
     * @param operatorCode 操作人id
     * @author [尹归晋]
     */
    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }


    /**
     * <p>Description:[获取操作人]</p>
     * Created on 2017年11月10日
     *
     * @return String 操作人
     * @author [尹归晋]
     */
    public String getOperator() {
        return operator;
    }


    /**
     * <p>Discription:[设置操作人]</p>
     * Created on 2017年11月10日
     *
     * @param operator 操作人
     * @author [尹归晋]
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }


    /**
     * <p>Description:[获取操作人ip]</p>
     * Created on 2017年11月10日
     *
     * @return String 操作人ip
     * @author [尹归晋]
     */
    public String getOperatorIp() {
        return operatorIp;
    }


    /**
     * <p>Discription:[设置操作人ip]</p>
     * Created on 2017年11月10日
     *
     * @param operatorIp 操作人ip
     * @author [尹归晋]
     */
    public void setOperatorIp(String operatorIp) {
        this.operatorIp = operatorIp;
    }


    /**
     * <p>Description:[获取错误描述]</p>
     * Created on 2017年11月10日
     *
     * @return String 错误描述
     * @author [尹归晋]
     */
    public String getDescription() {
        return description;
    }


    /**
     * <p>Discription:[设置错误描述]</p>
     * Created on 2017年11月10日
     *
     * @param description 错误描述
     * @author [尹归晋]
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * <p>Description:[获取主机]</p>
     * Created on 2017年11月10日
     *
     * @return String 主机
     * @author [尹归晋]
     */
    public String getHostName() {
        return hostName;
    }


    /**
     * <p>Discription:[设置主机]</p>
     * Created on 2017年11月10日
     *
     * @param hostName 主机
     * @author [尹归晋]
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }


    /**
     * <p>Description:[获取错误详细]</p>
     * Created on 2017年11月10日
     *
     * @return String 错误详细
     * @author [尹归晋]
     */
    public String getDetail() {
        return detail;
    }


    /**
     * <p>Discription:[设置错误详细]</p>
     * Created on 2017年11月10日
     *
     * @param detail 错误详细
     * @author [尹归晋]
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }


    /**
     * <p>Description:[获取浏览器信息]</p>
     * Created on 2017年11月10日
     *
     * @return String 浏览器信息
     * @author [尹归晋]
     */
    public String getBrowerMessage() {
        return browerMessage;
    }


    /**
     * <p>Discription:[设置浏览器信息]</p>
     * Created on 2017年11月10日
     *
     * @param browerMessage 浏览器信息
     * @author [尹归晋]
     */
    public void setBrowerMessage(String browerMessage) {
        this.browerMessage = browerMessage;
    }


    /**
     * <p>Description:[获取]</p>
     * Created on 2017年11月10日
     *
     * @return Date
     * @author [尹归晋]
     */
    public Date getCreateTime() {
        return createTime;
    }


    /**
     * <p>Discription:[设置]</p>
     * Created on 2017年11月10日
     *
     * @param createTime
     * @author [尹归晋]
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(String createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
