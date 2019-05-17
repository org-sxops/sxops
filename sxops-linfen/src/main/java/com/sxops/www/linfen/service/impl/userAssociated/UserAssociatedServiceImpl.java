package com.sxops.www.linfen.service.impl.userAssociated;

import com.sxops.www.common.enums.APIStatus;
import com.sxops.www.common.enums.StatusEnum;
import com.sxops.www.linfen.customException.CarInfoException;
import com.sxops.www.linfen.dao.mapper.basic.UserAssociatedMapper;
import com.sxops.www.linfen.dao.model.basic.UserAssociated;
import com.sxops.www.linfen.service.impl.basic.BaseServiceImpl;
import com.sxops.www.linfen.service.userAssociated.UserAssociatedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserAssociatedServiceImpl extends BaseServiceImpl<UserAssociated, UserAssociatedMapper> implements UserAssociatedService {


    @Resource
    private UserAssociatedMapper userAssociatedMapper;

    @Override
    public void updateModelByUuId(String ownedUserUuid, String otherUuid, Integer enable, Integer otherType) {
        UserAssociated userAssociated = new UserAssociated();
        userAssociated.setOtherType(otherType);
        userAssociated.setOtherUuid(otherUuid);
        userAssociated.setUserUuid(ownedUserUuid);
        userAssociated.setEnable(enable);
        //TODO
        userAssociatedMapper.updateByPrimaryKey(userAssociated);
    }
}
