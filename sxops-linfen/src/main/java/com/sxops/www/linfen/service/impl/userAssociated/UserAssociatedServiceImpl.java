package com.sxops.www.linfen.service.impl.userAssociated;

import com.sxops.www.common.enums.APIStatus;
import com.sxops.www.common.enums.StatusEnum;
import com.sxops.www.common.util.CollectionUtils;
import com.sxops.www.linfen.customException.CarInfoException;
import com.sxops.www.linfen.dao.mapper.basic.UserAssociatedMapper;
import com.sxops.www.linfen.dao.model.basic.UserAssociated;
import com.sxops.www.linfen.service.impl.basic.BaseServiceImpl;
import com.sxops.www.linfen.service.userAssociated.UserAssociatedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class UserAssociatedServiceImpl extends BaseServiceImpl<UserAssociated, UserAssociatedMapper> implements UserAssociatedService {


    @Resource
    private UserAssociatedMapper userAssociatedMapper;

    @Override
    public void insertOrUpdateModelByUuId(String ownedUserUuid, String otherUuid, Integer enable, Integer otherType) {
        UserAssociated userAssociated = new UserAssociated();
        userAssociated.setOtherType(otherType);
        userAssociated.setOtherUuid(otherUuid);
        userAssociated.setUserUuid(ownedUserUuid);
        List<UserAssociated> select = userAssociatedMapper.select(userAssociated);
        if(CollectionUtils.isEmpty(select)){
            userAssociated.setEnable(enable);
            userAssociatedMapper.insert(userAssociated);
        }else{
            UserAssociated userAssociated1 = select.get(0);
            userAssociated1.setEnable(enable);
            userAssociatedMapper.updateByPrimaryKey(userAssociated1);
        }

    }
}
