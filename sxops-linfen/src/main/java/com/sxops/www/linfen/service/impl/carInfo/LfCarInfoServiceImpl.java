package com.sxops.www.linfen.service.impl.carInfo;

import com.sxops.www.common.enums.APIStatus;
import com.sxops.www.common.util.StringUtils;
import com.sxops.www.linfen.customException.CarInfoException;
import com.sxops.www.linfen.dao.mapper.carInfo.LfCarInfoMapper;
import com.sxops.www.linfen.dao.model.carInfo.LfCarInfo;
import com.sxops.www.linfen.service.carInfo.LfCarInfoService;
import com.sxops.www.linfen.service.impl.basic.BaseServiceImpl;
import com.sxops.www.linfen.service.login.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.UUID;


@Service
@Slf4j
public class LfCarInfoServiceImpl extends BaseServiceImpl<LfCarInfo, LfCarInfoMapper> implements LfCarInfoService {

    @Autowired
    private LoginService loginService;

    @Override
    public LfCarInfo insertCarInfo(LfCarInfo carInfo) {
        this.checkCarModelIsNotNull(carInfo, false);
        carInfo.setId(null);
        carInfo.setOwnedUserUuid(loginService.getLoginUser().getUuid());
        carInfo.setCreateTime(new Date());
        carInfo.setUpdateTime(new Date());
        carInfo.setUuid(getCarInfoUUid());
        this.insert(carInfo);
        return carInfo;
    }

    /**
     * 车辆信息校验
     *
     * @param CarInfo
     * @param isUpdate
     */
    @Override
    public void checkCarModelIsNotNull(LfCarInfo CarInfo, boolean isUpdate) {
        log.info("模块:【车辆信息】，操作:【入库操作数据校验】参数：[ CarInfo: {}, 是否是更新操作： {}]", CarInfo.toString(), isUpdate);
        if (ObjectUtils.isEmpty(CarInfo)) {
            throw new CarInfoException(APIStatus.ERROR_2001.getCode(),"车辆信息为空");
        }
        StringBuffer buffer = new StringBuffer();
        if (isUpdate) {
            if (StringUtils.isEmpty(CarInfo.getId())) {
                buffer.append("ID为空，");
            }
            if (StringUtils.isEmpty(CarInfo.getUuid())) {
                buffer.append("UUID为空，");
            }
            if (StringUtils.isEmpty(CarInfo.getOwnedUserUuid())) {
                buffer.append("车主信息为空，");
            }
        }
        if (StringUtils.isEmpty(CarInfo.getCarLicensePlate())) {
            buffer.append("车牌信息为空，");
        }
        if (buffer.length() > 0) {
            log.info("模块:【车辆信息】，操作:【入库操作数据校验】,参数：[ CarInfo: {}, 是否是更新操作： {}],错误信息：{}", CarInfo.toString(), isUpdate, buffer.toString());
            throw new CarInfoException(APIStatus.ERROR_2001.getCode(),"车辆信息中:" + buffer.toString() + " 不允许进行入库操作");
        }

    }

    /**
     * 车辆信息生产UUID
     *
     * @return
     */
    private synchronized String getCarInfoUUid() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return "lfcar"+uuid;
    }


}
