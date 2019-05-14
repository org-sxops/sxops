package com.sxops.linfen.service.impl;

import com.sxops.linfen.dao.mapper.LUserInfoMapper;
import com.sxops.linfen.dao.model.LUserInfo;
import com.sxops.linfen.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@Service
public class UserInfoServiceImpl extends BaseServiceImpl<LUserInfo, LUserInfoMapper> implements UserInfoService {

    @Autowired
    private LUserInfoMapper lUserInfoMapper;

    @Override
    public void insertUserInfo(LUserInfo userInfo) {
        userInfo.setId(null);
        userInfo.setCreateDatetime(new Date());
        userInfo.setUpdateDatetime(new Date());
        userInfo.setUuid(getUserInfoUUid());
        lUserInfoMapper.insert(userInfo);
    }


    private synchronized String getUserInfoUUid(){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }
}
