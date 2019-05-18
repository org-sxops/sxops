package com.sxops.www.thirdApi.service.impl;

import com.alibaba.fastjson.JSON;
import com.sxops.www.basicException.MyHttpRequestException;
import com.sxops.www.common.enums.APIStatus;
import com.sxops.www.common.util.HttpRequestUtils;
import com.sxops.www.common.util.StringUtils;
import com.sxops.www.thirdApi.service.DistrictService;
import com.sxops.www.thirdApi.thirdVo.AbstractThirdApiResponse;
import com.sxops.www.thirdApi.thirdVo.District;
import com.sxops.www.thirdApi.thirdVo.DistrictRequestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DistrictServiceImpl
 * @Description TODO
 * @Author 葛伟 [geweiHome@163.com]
 * @Date 2019-05-18 16:55
 * @Version 1.0
 **/
@Service
@Slf4j
public class DistrictServiceImpl implements DistrictService {

    @Value("${third-api.district-key}")
    private String districtKey;
    @Value("${third-api.district-url}")
    private String districtUrl;

    @Resource(name = "httpRequest")
    private HttpRequestUtils httpRequestUtils;
    private static final String STATUS_FALSE = "0";

    @Override
    public List<District> getDistrictByParam(DistrictRequestVo districtRequestVo) {

        String param = intoParameters(districtRequestVo);
        String body = httpRequestUtils.sendGetRequest(districtUrl, param);
        return getBody(body);
    }

    private List<District> getBody(String body){
        AbstractThirdApiResponse abstractThirdApiResponse = JSON.parseObject(body, AbstractThirdApiResponse.class);
        if(STATUS_FALSE.equals(abstractThirdApiResponse.getStatus())){
            throw new MyHttpRequestException(APIStatus.ERROR_7001.getCode(),abstractThirdApiResponse.getInfo(),null);
        }
        return abstractThirdApiResponse.getDistricts();
    }

    /**
     * @return java.lang.String
     * @Description 组装请求参数
     * @Author gewei [geweihome@163.com]
     * @Date 17:04 2019-05-18
     * @Param [districtRequestVo]
     **/
    private String intoParameters(DistrictRequestVo districtRequestVo) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("?key=").append(districtKey);
        if (StringUtils.isNotEmpty(districtRequestVo.getKeywords())) {
            buffer.append("&keywords=").append(districtRequestVo.getKeywords());
        }
        if (!StringUtils.isEmpty(districtRequestVo.getCallback())) {
            buffer.append("&callback=").append(districtRequestVo.getCallback());
        }
        if (!StringUtils.isEmpty(districtRequestVo.getExtensions())) {
            buffer.append("&extensions=").append(districtRequestVo.getExtensions());
        }
        if (!StringUtils.isEmpty(districtRequestVo.getFilter())) {
            buffer.append("&filter=").append(districtRequestVo.getFilter());
        }
        if (!StringUtils.isEmpty(districtRequestVo.getOffset())) {
            buffer.append("&offset=").append(districtRequestVo.getOffset());
        }
        if (!StringUtils.isEmpty(districtRequestVo.getOutput())) {
            buffer.append("&output=").append(districtRequestVo.getOutput());
        } else {
            buffer.append("&output=").append("JSON");
        }
        if (!StringUtils.isEmpty(districtRequestVo.getPage())) {
            buffer.append("&page=").append(districtRequestVo.getPage());
        }
        if (!StringUtils.isEmpty(districtRequestVo.getSubdistrict())) {
            buffer.append("&subdistrict=").append(districtRequestVo.getSubdistrict());
        } else {
            buffer.append("&subdistrict=").append(3);
        }
        return buffer.toString();
    }
}
    