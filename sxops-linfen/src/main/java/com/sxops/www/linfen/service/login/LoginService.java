package com.sxops.www.linfen.service.login;

import com.sxops.www.linfen.dao.model.journey.LfUserInfo;
import com.sxops.www.linfen.service.basic.BaseService;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginService {

    LfUserInfo getLoginUser();
}
