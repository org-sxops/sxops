package com.sxops.www.thirdApi.thirdVo;

import lombok.Data;

/**
 * 城市列表
 */
@Data
public class Citie {
    /**
     * 名称
     */
    private String name;
    /**
     * 该城市包含此关键字的个数
     */
    private String num;
    /**
     * 该城市的citycode
     */
    private String citycode;
    /**
     * 该城市的adcode
     */
    private String adcode;

    public Citie() {
    }

    public Citie(String name, String num, String citycode, String adcode) {
        this.name = name;
        this.num = num;
        this.citycode = citycode;
        this.adcode = adcode;
    }
}
