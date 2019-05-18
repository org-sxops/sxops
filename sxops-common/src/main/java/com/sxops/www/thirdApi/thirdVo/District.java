package com.sxops.www.thirdApi.thirdVo;

import lombok.Data;

import java.util.List;

/**
 * 行政区信息
 */
@Data
public class District extends AbstractThirdApiResponseVo {
    /**
     * 城市编码
     */
    private String citycode;
    /**
     * 区域编码
     */
    private String adcode;

    /**
     * 行政区名称
     */
    private String name;

    /**
     * 行政区边界坐标点
     */
    private String polyline;

    /**
     * 城市中心点
     */
    private String center;

    /**
     * 行政区划级别
     */
    private String level;

    /**
     *  下级行政区列表
     */
    private List<District> districts;

    public District() {
    }

    public District(String citycode, String adcode, String name, String polyline, String center, String level, List<District> districts) {
        this.citycode = citycode;
        this.adcode = adcode;
        this.name = name;
        this.polyline = polyline;
        this.center = center;
        this.level = level;
        this.districts = districts;
    }
}
