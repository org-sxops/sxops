package com.sxops.www.linfen.service.impl.carInfo;

import com.sxops.www.common.enums.APIStatus;
import com.sxops.www.common.enums.UserAssociatedEnum;
import com.sxops.www.common.util.StringUtils;
import com.sxops.www.linfen.customException.CarInfoException;
import com.sxops.www.linfen.dao.mapper.carInfo.CarInfoMapper;
import com.sxops.www.linfen.dao.model.basic.UserAssociated;
import com.sxops.www.linfen.dao.model.carInfo.CarInfo;
import com.sxops.www.linfen.service.carInfo.CarInfoService;
import com.sxops.www.linfen.service.impl.basic.BaseServiceImpl;
import com.sxops.www.linfen.service.login.LoginService;
import com.sxops.www.linfen.service.userAssociated.UserAssociatedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.UUID;


@Service
@Slf4j
public class CarInfoServiceImpl extends BaseServiceImpl<CarInfo, CarInfoMapper> implements CarInfoService {

    @Autowired
    private UserAssociatedService userAssociatedService;
    @Autowired
    private LoginService loginService;

    @Override
    public CarInfo insertCarInfo(CarInfo carInfo) {
        this.checkCarModelIsNotNull(carInfo, false);
        String carInfoUUid = this.getCarInfoUUid();
        carInfo.setId(null);
        carInfo.setCreateTime(new Date());
        carInfo.setUpdateTime(new Date());
        carInfo.setUuid(carInfoUUid);
        this.insert(carInfo);
        userAssociatedService.insertOrUpdateModelByUuId(carInfo.getOwnedUserUuid(), carInfoUUid, carInfo.getEnable(), UserAssociatedEnum.OTHER_CAR.getOtherType());
        return carInfo;
    }

    /**
     * 车辆信息校验
     *
     * @param CarInfo
     * @param isUpdate
     */
    @Override
    public void checkCarModelIsNotNull(CarInfo CarInfo, boolean isUpdate) {
        log.info("模块:【车辆信息】，操作:【入库操作数据校验】参数：[ CarInfo: {}, 是否是更新操作： {}]", CarInfo.toString(), isUpdate);
        if (ObjectUtils.isEmpty(CarInfo)) {
            throw new CarInfoException(APIStatus.ERROR_2001.getCode(), "车辆信息为空");
        }
        StringBuffer buffer = new StringBuffer();
        if (isUpdate) {
            if (StringUtils.isEmpty(CarInfo.getId())) {
                buffer.append("ID为空，");
            }
            if (StringUtils.isEmpty(CarInfo.getUuid())) {
                buffer.append("UUID为空，");
            }
        }
        if (StringUtils.isEmpty(CarInfo.getCarLicensePlate())) {
            buffer.append("车牌信息为空，");
        }
        if (buffer.length() > 0) {
            log.info("模块:【车辆信息】，操作:【入库操作数据校验】,参数：[ CarInfo: {}, 是否是更新操作： {}],错误信息：{}", CarInfo.toString(), isUpdate, buffer.toString());
            throw new CarInfoException(APIStatus.ERROR_2001.getCode(), "车辆信息中:" + buffer.toString() + " 不允许进行入库操作");
        }
        CarInfo.setOwnedUserUuid(loginService.getLoginUser().getUuid());

    }


    @Override
    public CarInfo updateCarInfo(CarInfo carInfo) {
        this.checkCarModelIsNotNull(carInfo, true);
        this.updateByPrimaryKey(carInfo);
        userAssociatedService.insertOrUpdateModelByUuId(carInfo.getOwnedUserUuid(), carInfo.getUuid(), carInfo.getEnable(), UserAssociatedEnum.OTHER_CAR.getOtherType());
        return null;
    }

    /**
     * 车辆信息生产UUID
     *
     * @return
     */
    private synchronized String getCarInfoUUid() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }


}
