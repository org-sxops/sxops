package com.sxops.www.linfen.dao.model.userInfo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import javax.persistence.*;

@Table(name = "lf_user_info")
@ToString
@Data
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
     * email
     */
    private String email;

}