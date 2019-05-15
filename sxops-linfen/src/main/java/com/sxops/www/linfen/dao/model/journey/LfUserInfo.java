package com.sxops.www.linfen.dao.model.journey;

import lombok.ToString;

import java.util.Date;
import javax.persistence.*;

@Table(name = "lf_user_info")
@ToString
public class LfUserInfo {
    /**
     * ID
     */
    @Id
    private Long id;

    /**
     * 用户唯一UUID
     */
    private String uuid;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private String sex;

    /**
     * 身份证号
     */
    @Column(name = "identity_cards")
    private String identityCards;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像URL
     */
    @Column(name = "avatarUrl")
    private String avatarurl;

    /**
     * 所在区域编码
     */
    @Column(name = "area_coding")
    private String areaCoding;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 用户类型
     */
    @Column(name = "user_type")
    private String userType;

    /**
     * 是否启用
     */
    private String enabled;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "updata_time")
    private Date updataTime;

    /**
     * 创建来源系统
     */
    @Column(name = "create_source")
    private String createSource;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户唯一UUID
     *
     * @return uuid - 用户唯一UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置用户唯一UUID
     *
     * @param uuid 用户唯一UUID
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取用户名称
     *
     * @return user_name - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取身份证号
     *
     * @return identity_cards - 身份证号
     */
    public String getIdentityCards() {
        return identityCards;
    }

    /**
     * 设置身份证号
     *
     * @param identityCards 身份证号
     */
    public void setIdentityCards(String identityCards) {
        this.identityCards = identityCards;
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取头像URL
     *
     * @return avatarUrl - 头像URL
     */
    public String getAvatarurl() {
        return avatarurl;
    }

    /**
     * 设置头像URL
     *
     * @param avatarurl 头像URL
     */
    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }

    /**
     * 获取所在区域编码
     *
     * @return area_coding - 所在区域编码
     */
    public String getAreaCoding() {
        return areaCoding;
    }

    /**
     * 设置所在区域编码
     *
     * @param areaCoding 所在区域编码
     */
    public void setAreaCoding(String areaCoding) {
        this.areaCoding = areaCoding;
    }

    /**
     * 获取详细地址
     *
     * @return address - 详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置详细地址
     *
     * @param address 详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取用户类型
     *
     * @return user_type - 用户类型
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 设置用户类型
     *
     * @param userType 用户类型
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * 获取是否启用
     *
     * @return enabled - 是否启用
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * 设置是否启用
     *
     * @param enabled 是否启用
     */
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return updata_time - 更新时间
     */
    public Date getUpdataTime() {
        return updataTime;
    }

    /**
     * 设置更新时间
     *
     * @param updataTime 更新时间
     */
    public void setUpdataTime(Date updataTime) {
        this.updataTime = updataTime;
    }

    /**
     * 获取创建来源系统
     *
     * @return create_source - 创建来源系统
     */
    public String getCreateSource() {
        return createSource;
    }

    /**
     * 设置创建来源系统
     *
     * @param createSource 创建来源系统
     */
    public void setCreateSource(String createSource) {
        this.createSource = createSource;
    }
}