package com.sxops.www.linfen.dao.model.basic;

import lombok.ToString;

import javax.persistence.*;

@Table(name = "lf_user_associated")
@ToString
public class UserAssociated {
    @Id
    private Long id;

    /**
     * 用户uuid
     */
    @Column(name = "user_uuid")
    private String userUuid;

    /**
     * 其他属性UUID
     */
    @Column(name = "other_uuid")
    private String otherUuid;

    /**
     * 其他属性类型 
     */
    @Column(name = "other_type")
    private Integer otherType;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户uuid
     *
     * @return user_uuid - 用户uuid
     */
    public String getUserUuid() {
        return userUuid;
    }

    /**
     * 设置用户uuid
     *
     * @param userUuid 用户uuid
     */
    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    /**
     * 获取其他属性UUID
     *
     * @return other_uuid - 其他属性UUID
     */
    public String getOtherUuid() {
        return otherUuid;
    }

    /**
     * 设置其他属性UUID
     *
     * @param otherUuid 其他属性UUID
     */
    public void setOtherUuid(String otherUuid) {
        this.otherUuid = otherUuid;
    }

    /**
     * 获取其他属性类型 
     *
     * @return other_type - 其他属性类型 
     */
    public Integer getOtherType() {
        return otherType;
    }

    /**
     * 设置其他属性类型 
     *
     * @param otherType 其他属性类型 
     */
    public void setOtherType(Integer otherType) {
        this.otherType = otherType;
    }
}