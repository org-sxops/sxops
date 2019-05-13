package com.sxops.www.dao.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import javax.persistence.*;

@Table(name = "l_user_info")
public class LUserInfo {
    /**
     * ID
     */

    @Id
    private Long id;

    /**
     * UUID
     */
    private String uuid;

    /**
     * 用户名称
     */
    @ApiModelProperty("用户名称")
    @Column(name = "user_name")
    private String userName;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    @Column(name = "phone_num")
    private Integer phoneNum;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String sex;

    /**
     * 详细地址
     */
    @ApiModelProperty("详细地址")
    private String address;

    /**
     * 区域编码
     */
    @ApiModelProperty("区域编码")
    @Column(name = "area_coding")
    private Integer areaCoding;

    /**
     * 身份证号码
     */
    @ApiModelProperty("身份证号码")
    @Column(name = "identity_cards")
    private String identityCards;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    @Column(name = "car_number")
    private String carNumber;

    /**
     * 营业执照编码
     */
    @ApiModelProperty("营业执照编码")
    @Column(name = "business_license_num")
    private String businessLicenseNum;

    /**
     * 用户类型
     */
    @ApiModelProperty("用户类型")
    @Column(name = "customer_type")
    private Integer customerType;

    /**
     * 用户邮箱
     */
    @ApiModelProperty("用户邮箱")
    private String email;

    /**
     * 用户头像URL
     */
    @Column(name = "avatarUrl")
    private String avatarurl;

    /**
     * 状态: 1.启用 2.停用 3.暂时锁定
     */
    @ApiModelProperty("区域编码")
    private Integer status;

    /**
     * 是否删除
     */
    @ApiModelProperty("区域编码")
    @Column(name = "is_detele")
    private Boolean isDetele;

    /**
     * 创建时间
     */
    @Column(name = "create_datetime")
    private Date createDatetime;

    /**
     * 更新时间
     */
    @Column(name = "update_datetime")
    private Date updateDatetime;

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
     * 获取UUID
     *
     * @return uuid - UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置UUID
     *
     * @param uuid UUID
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
     * 获取手机号
     *
     * @return phone_num - 手机号
     */
    public Integer getPhoneNum() {
        return phoneNum;
    }

    /**
     * 设置手机号
     *
     * @param phoneNum 手机号
     */
    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
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
     * 获取区域编码
     *
     * @return area_coding - 区域编码
     */
    public Integer getAreaCoding() {
        return areaCoding;
    }

    /**
     * 设置区域编码
     *
     * @param areaCoding 区域编码
     */
    public void setAreaCoding(Integer areaCoding) {
        this.areaCoding = areaCoding;
    }

    /**
     * 获取身份证号码
     *
     * @return identity_cards - 身份证号码
     */
    public String getIdentityCards() {
        return identityCards;
    }

    /**
     * 设置身份证号码
     *
     * @param identityCards 身份证号码
     */
    public void setIdentityCards(String identityCards) {
        this.identityCards = identityCards;
    }

    /**
     * 获取车牌号
     *
     * @return car_number - 车牌号
     */
    public String getCarNumber() {
        return carNumber;
    }

    /**
     * 设置车牌号
     *
     * @param carNumber 车牌号
     */
    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    /**
     * 获取营业执照编码
     *
     * @return business_license_num - 营业执照编码
     */
    public String getBusinessLicenseNum() {
        return businessLicenseNum;
    }

    /**
     * 设置营业执照编码
     *
     * @param businessLicenseNum 营业执照编码
     */
    public void setBusinessLicenseNum(String businessLicenseNum) {
        this.businessLicenseNum = businessLicenseNum;
    }

    /**
     * 获取用户类型
     *
     * @return customer_type - 用户类型
     */
    public Integer getCustomerType() {
        return customerType;
    }

    /**
     * 设置用户类型
     *
     * @param customerType 用户类型
     */
    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    /**
     * 获取用户邮箱
     *
     * @return email - 用户邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置用户邮箱
     *
     * @param email 用户邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取用户头像URL
     *
     * @return avatarUrl - 用户头像URL
     */
    public String getAvatarurl() {
        return avatarurl;
    }

    /**
     * 设置用户头像URL
     *
     * @param avatarurl 用户头像URL
     */
    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }

    /**
     * 获取状态: 1.启用 2.停用 3.暂时锁定
     *
     * @return status - 状态: 1.启用 2.停用 3.暂时锁定
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态: 1.启用 2.停用 3.暂时锁定
     *
     * @param status 状态: 1.启用 2.停用 3.暂时锁定
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取是否删除
     *
     * @return is_detele - 是否删除
     */
    public Boolean getIsDetele() {
        return isDetele;
    }

    /**
     * 设置是否删除
     *
     * @param isDetele 是否删除
     */
    public void setIsDetele(Boolean isDetele) {
        this.isDetele = isDetele;
    }

    /**
     * 获取创建时间
     *
     * @return create_datetime - 创建时间
     */
    public Date getCreateDatetime() {
        return createDatetime;
    }

    /**
     * 设置创建时间
     *
     * @param createDatetime 创建时间
     */
    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    /**
     * 获取更新时间
     *
     * @return update_datetime - 更新时间
     */
    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    /**
     * 设置更新时间
     *
     * @param updateDatetime 更新时间
     */
    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}