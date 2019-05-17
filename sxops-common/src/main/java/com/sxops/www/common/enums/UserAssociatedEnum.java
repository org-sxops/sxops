package com.sxops.www.common.enums;

import lombok.Getter;

@Getter
public enum  UserAssociatedEnum {

    OTHER_CAR(1, "车辆信息"),


    ;

    private Integer otherType;
    private String otherName;
    UserAssociatedEnum(int otherType, String otherName) {
        this.otherType = otherType;
        this.otherName = otherName;
    }
}
