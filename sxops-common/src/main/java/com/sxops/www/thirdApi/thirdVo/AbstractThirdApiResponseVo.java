package com.sxops.www.thirdApi.thirdVo;

import lombok.Data;

import java.util.List;

@Data
public abstract class AbstractThirdApiResponseVo {
    /**
     * 状态码
     */
    private String status;
    /**
     * 返回状态说明
     */
    private String info;

    /**
     * 搜索方案数目(最大值为1000)
     */
    private String count;
    /**
     * 城市建议列表
     */
    private Suggestion suggestion;
    /**
     * 行政区列表
     */
    private List<District> districts;

    public AbstractThirdApiResponseVo(String status, String info, String count, Suggestion suggestion, List<District> districts) {
        this.status = status;
        this.info = info;
        this.count = count;
        this.suggestion = suggestion;
        this.districts = districts;
    }

    public AbstractThirdApiResponseVo() {
    }
}
