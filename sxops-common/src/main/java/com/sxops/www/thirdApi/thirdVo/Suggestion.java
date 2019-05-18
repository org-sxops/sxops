package com.sxops.www.thirdApi.thirdVo;

import java.util.List;

/**
 * 建议结果列表
 */
public class Suggestion {
    /**
     * 建议关键字列表
     */
    private List<String> keywords;
    /**
     * 建议城市列表
     */
    private List<Citie> cities;

    public Suggestion() {
    }

    public Suggestion(List<String> keywords, List<Citie> cities) {
        this.keywords = keywords;
        this.cities = cities;
    }
}
