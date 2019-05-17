package com.sxops.www.linfen.service.userAssociated;

import com.sxops.www.linfen.dao.model.basic.UserAssociated;
import com.sxops.www.linfen.service.basic.BaseService;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAssociatedService extends BaseService<UserAssociated> {


    /**
     *  关联表更新
     * @param ownedUserUuid 用户UUID
     * @param otherUuid 关联属性UUID
     * @param enable 是否启用
     * @param otherType 类型
     */
    void updateModelByUuId(String ownedUserUuid, String otherUuid, Integer enable, Integer otherType);
}
