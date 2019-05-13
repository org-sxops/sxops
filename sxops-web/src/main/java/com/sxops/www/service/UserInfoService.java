package com.sxops.www.service;

import com.sxops.www.dao.model.LUserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoService extends BaseService<LUserInfo> {
    void insertUserInfo(LUserInfo userInfo);
}
