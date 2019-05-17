package com.sxops.www.linfen.service.userInfo;

import com.sxops.www.linfen.dao.model.userInfo.UserInfo;
import com.sxops.www.linfen.service.basic.BaseService;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoService extends BaseService<UserInfo> {

    UserInfo insertUserInfo(UserInfo userInfo);

    void checkUserModelIsNotNull(UserInfo userInfo, boolean isUpdate);
}
