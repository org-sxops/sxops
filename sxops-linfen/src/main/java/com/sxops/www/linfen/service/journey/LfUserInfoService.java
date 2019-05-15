package com.sxops.www.linfen.service.journey;

import com.sxops.www.linfen.dao.model.journey.LfUserInfo;
import com.sxops.www.linfen.service.basic.BaseService;
import org.springframework.stereotype.Repository;

@Repository
public interface LfUserInfoService extends BaseService<LfUserInfo> {
    void insertUserInfo(LfUserInfo userInfo);
}
