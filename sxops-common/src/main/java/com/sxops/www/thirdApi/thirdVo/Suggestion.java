package com.sxops.www.thirdApi.thirdVo;

import lombok.Data;

import java.util.List;

/**
 * 建议结果列表
 */
@Data
public class Suggestion {
    /**
     * 建议关键字列表
     */
    private List<String> keywords;
    /**
     * 建议城市列表
     */
    private List<Citie> cities;

}
