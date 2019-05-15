package com.sxops.www.linfen.service.carInfo;

import com.sxops.www.linfen.dao.model.journey.LfCarInfo;
import com.sxops.www.linfen.dao.model.journey.LfUserInfo;
import com.sxops.www.linfen.service.basic.BaseService;
import org.springframework.stereotype.Repository;

/**
 * 车辆信息接口
 */
@Repository
public interface LfCarInfoService extends BaseService<LfCarInfo> {


    /**
     * 新增
     *
     * @param carInfo
     */
    LfCarInfo insertCarInfo(LfCarInfo carInfo);

    /**
     * 校验数据完整性接口
     *
     * @param carInfo
     * @param isUpdate
     */
    void checkCarModelIsNotNull(LfCarInfo carInfo, boolean isUpdate);
}
