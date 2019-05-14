package com.sxops.linfen.service;

import com.sxops.linfen.dao.model.LUserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoService extends BaseService<LUserInfo> {
    void insertUserInfo(LUserInfo userInfo);
}
