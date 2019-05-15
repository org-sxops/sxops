package com.sxops.www.linfen.service.impl.journey;

import com.sxops.www.linfen.dao.mapper.journey.LfUserInfoMapper;
import com.sxops.www.linfen.dao.model.journey.LfUserInfo;
import com.sxops.www.linfen.service.journey.LfUserInfoService;
import com.sxops.www.linfen.service.impl.basic.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@Service
public class LfUserInfoServiceImpl extends BaseServiceImpl<LfUserInfo, LfUserInfoMapper> implements LfUserInfoService {

    @Override
    public void insertUserInfo(LfUserInfo userInfo) {
        userInfo.setId(null);
        userInfo.setCreateTime(new Date());
        userInfo.setUpdataTime(new Date());
        userInfo.setUuid(getUserInfoUUid());
        this.insert(userInfo);
    }


    private synchronized String getUserInfoUUid(){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }
}
