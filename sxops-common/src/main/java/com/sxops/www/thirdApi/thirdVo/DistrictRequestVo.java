package com.sxops.www.thirdApi.thirdVo;

import lombok.Data;

/**
 * 行政区域查询对象
 */
@Data
public class DistrictRequestVo extends AbstractThirdApiRequestVo {
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

    public DistrictRequestVo(String key) {
        super(key);
    }

    public DistrictRequestVo(String key, String keywords, Integer subdistrict, String filter, Integer page, Integer offset, String extensions, String output, String callback) {
        super(key);
        this.keywords = keywords;
        this.subdistrict = subdistrict;
        this.filter = filter;
        this.page = page;
        this.offset = offset;
        this.extensions = extensions;
        this.output = "JSON";
        this.callback = callback;
    }
}
