package com.sxops.www.thirdApi.thirdVo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public abstract class AbstractThirdApiRequestVo {


    @Value("${third-api.district-key}")
    private String districtKey;

    private String key;

    public AbstractThirdApiRequestVo(String key) {
        this.key = districtKey;
    }
}
