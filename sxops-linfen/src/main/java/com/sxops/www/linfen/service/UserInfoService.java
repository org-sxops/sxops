package com.sxops.www.linfen.service;

import com.sxops.www.linfen.dao.model.LUserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoService extends BaseService<LUserInfo> {
    void insertUserInfo(LUserInfo userInfo);
}
