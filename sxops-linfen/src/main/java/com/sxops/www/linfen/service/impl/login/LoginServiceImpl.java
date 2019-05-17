package com.sxops.www.linfen.service.impl.login;

import com.sxops.www.common.enums.StatusEnum;
import com.sxops.www.common.util.CollectionUtils;
import com.sxops.www.linfen.dao.model.userInfo.UserInfo;
import com.sxops.www.linfen.service.login.LoginService;
import com.sxops.www.linfen.service.userInfo.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class LoginServiceImpl  implements LoginService {


    @Autowired
    private UserInfoService userInfoService;
    @Override
    public UserInfo getLoginUser() {
        String uuid = "gewei";
        UserInfo UserInfo = new UserInfo();
        UserInfo.setUuid(uuid);
        UserInfo.setEnable(StatusEnum.TRUE.getEnbleCode());
        List<UserInfo> select = userInfoService.select(UserInfo);
        if(CollectionUtils.isEmpty(select)){
            UserInfo.setCreateSource("LinFen-Server");
            UserInfo.setAreaCoding("0357");
            UserInfo.setAddress("遥远的地方");
            UserInfo.setCreateTime(new Date());
            UserInfo.setUpdataTime(new Date());
            UserInfo.setPassword("abcd1234");
            UserInfo.setAvatarurl("/001/001");
            UserInfo.setIdentityCards("131199999999999999");
            UserInfo.setPhone("13000000000");
            UserInfo.setSex("1");
            UserInfo.setEmail("geweiHome@163.com");
            UserInfo.setUserName("葛伟");
            userInfoService.insert(UserInfo);
        }else {
            return select.get(0);
        }
        return UserInfo;
    }


}
