package com.sxops.www.thirdApi.service;

import com.sxops.www.thirdApi.thirdVo.AbstractThirdApiResponse;
import com.sxops.www.thirdApi.thirdVo.District;
import com.sxops.www.thirdApi.thirdVo.DistrictRequestVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName DistrictService
 * @Description 行政区划查询service
 * @Author 葛伟 [geweiHome@163.com]
 * @Date 2019-05-18 16:45
 * @Version 1.0
 **/
@Repository
public interface DistrictService {

    /**
     * @return com.sxops.www.thirdApi.thirdVo.AbstractThirdApiResponse
     * @Description 根据参数获取行政区划列表
     * @Author gewei [geweihome@163.com]
     * @Date 16:52 2019-05-18
     * @Param [abstractThirdApiRequestVo]
     **/
     List<District> getDistrictByParam(DistrictRequestVo districtRequestVo);
}
