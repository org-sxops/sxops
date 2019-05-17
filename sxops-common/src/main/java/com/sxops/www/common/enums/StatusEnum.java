package com.sxops.www.common.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {
    FALSE(0, "否"),
    TRUE(1, "是"),
    WOMAN(0, "女"),
    MAN(1, "男"),

    ;

    private Integer enbleCode;
    private String enbleDesc;

    StatusEnum(Integer enbleCode, String enbleDesc) {
        this.enbleCode = enbleCode;
        this.enbleDesc = enbleDesc;
    }
}
