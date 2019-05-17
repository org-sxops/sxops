package com.sxops.www.linfen.service.impl.login;

import com.sxops.www.common.util.CollectionUtils;
import com.sxops.www.linfen.dao.model.userInfo.LfUserInfo;
import com.sxops.www.linfen.service.login.LoginService;
import com.sxops.www.linfen.service.userInfo.LfUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class LoginServiceImpl  implements LoginService {


    @Autowired
    private LfUserInfoService lfUserInfoService;
    @Override
    public LfUserInfo getLoginUser() {
        String uuid = "lfusgewei";
        LfUserInfo lfUserInfo = new LfUserInfo();
        lfUserInfo.setUuid(uuid);
        lfUserInfo.setEnabled("1");
        List<LfUserInfo> select = lfUserInfoService.select(lfUserInfo);
        if(CollectionUtils.isEmpty(select)){
            lfUserInfo.setCreateSource("LinFen-Server");
            lfUserInfo.setAreaCoding("0357");
            lfUserInfo.setAddress("遥远的地方");
            lfUserInfo.setCreateTime(new Date());
            lfUserInfo.setUpdataTime(new Date());
            lfUserInfo.setPassword("abcd1234");
            lfUserInfo.setAvatarurl("/001/001");
            lfUserInfo.setIdentityCards("131199999999999999");
            lfUserInfo.setPhone("13000000000");
            lfUserInfo.setSex("1");
            lfUserInfo.setEmail("geweiHome@163.com");
            lfUserInfo.setUserName("葛伟");
            lfUserInfoService.insert(lfUserInfo);
        }else {
            return select.get(0);
        }
        return lfUserInfo;
    }


}
