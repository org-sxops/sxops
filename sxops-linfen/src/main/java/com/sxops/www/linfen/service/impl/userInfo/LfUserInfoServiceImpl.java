package com.sxops.www.linfen.service.impl.userInfo;

import com.sxops.www.linfen.customException.UserInfoException;
import com.sxops.www.common.enums.APIStatus;
import com.sxops.www.common.util.StringUtils;
import com.sxops.www.linfen.dao.mapper.userInfo.LfUserInfoMapper;
import com.sxops.www.linfen.dao.model.userInfo.LfUserInfo;
import com.sxops.www.linfen.service.impl.basic.BaseServiceImpl;
import com.sxops.www.linfen.service.userInfo.LfUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.UUID;


@Service
@Slf4j
public class LfUserInfoServiceImpl extends BaseServiceImpl<LfUserInfo, LfUserInfoMapper> implements LfUserInfoService {

    @Override
    public LfUserInfo insertUserInfo(LfUserInfo userInfo) {
        this.checkUserModelIsNotNull(userInfo, false);
        userInfo.setId(null);
        userInfo.setCreateTime(new Date());
        userInfo.setUpdataTime(new Date());
        userInfo.setUuid(getUserInfoUUid());
        this.insert(userInfo);
        return userInfo;
    }

    /**
     * 用户信息校验
     * @param userInfo
     * @param isUpdate
     */
    @Override
    public void checkUserModelIsNotNull(LfUserInfo userInfo, boolean isUpdate) {
        log.info("模块:【用户信息】，操作:【入库操作数据校验】参数：[ userInfo: {}, 是否是更新操作： {}]", userInfo.toString(), isUpdate);
        if (ObjectUtils.isEmpty(userInfo)) {
            throw new UserInfoException(APIStatus.ERROR_2001.getCode(),"用户信息为空");
        }
        StringBuffer buffer = new StringBuffer();
        if (isUpdate) {
            if (StringUtils.isEmpty(userInfo.getId())) {
                buffer.append("ID为空，");
            }
            if (StringUtils.isEmpty(userInfo.getUuid())) {
                buffer.append("UUID为空，");
            }
        }
        if (StringUtils.isEmpty(userInfo.getPhone())) {
            buffer.append("手机号码为空，");
        }
        if (StringUtils.isEmpty(userInfo.getPassword())) {
            buffer.append("密码为空，");
        }
        if (StringUtils.isEmpty(userInfo.getSex()) || userInfo.getSex().length() > 2) {
            buffer.append("性别为空或数据不正确，");
        }
        if (buffer.length() > 0) {
            log.info("模块:【用户信息】，操作:【入库操作数据校验】,参数：[ userInfo: {}, 是否是更新操作： {}],错误信息：{}", userInfo.toString(), isUpdate, buffer.toString());
            throw new UserInfoException(APIStatus.ERROR_2001.getCode(),"用户信息中:" + buffer.toString() + "不允许进行入库操作");
        }
    }

    /**
     * 用户信息生产UUID
     * @return
     */
    private synchronized String getUserInfoUUid() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return "lfus"+uuid;
    }


}
