package com.sxops.www.linfen.service.carInfo;

import com.sxops.www.linfen.dao.model.carInfo.CarInfo;
import com.sxops.www.linfen.service.basic.BaseService;
import org.springframework.stereotype.Repository;

/**
 * 车辆信息接口
 */
@Repository
public interface CarInfoService extends BaseService<CarInfo> {


    /**
     * 新增
     *
     * @param carInfo
     */
    CarInfo insertCarInfo(CarInfo carInfo);

    /**
     * 校验数据完整性接口
     *
     * @param carInfo
     * @param isUpdate
     */
    void checkCarModelIsNotNull(CarInfo carInfo, boolean isUpdate);

    /**
     * 更新接口
     * @param carInfo
     */
    CarInfo updateCarInfo(CarInfo carInfo);
}
