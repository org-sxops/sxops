package com.sxops.www.thirdApi.thirdVo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 行政区域查询对象
 */
@ApiModel(value = "DistrictRequestVo",description = "行政区域查询对象")
@Data
public class DistrictRequestVo  {
    /**
     * 查询关键字
     */
    private String keywords;
    /**
     * 子级行政区
     */
    private Integer subdistrict;
    /**
     * 根据区划过滤
     */
    private String filter;
    /**
     * 需要第几页数据
     */
    private Integer page;
    /**
     * 最外层返回数据个数
     */
    private Integer offset;
    /**
     * 返回结果控制
     */
    private String extensions;
    /**
     * 返回数据格式类型
     */
    private String output;
    /**
     * 回调函数
     */
    private String callback;

}
