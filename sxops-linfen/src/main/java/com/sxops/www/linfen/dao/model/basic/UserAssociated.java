package com.sxops.www.linfen.dao.model.basic;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "lf_user_associated")
@ToString
@Data
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

    private Integer enable;

   }