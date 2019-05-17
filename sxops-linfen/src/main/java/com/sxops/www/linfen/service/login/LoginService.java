package com.sxops.www.linfen.service.login;

import com.sxops.www.linfen.dao.model.userInfo.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginService {

    UserInfo getLoginUser();
}
