package com.sxops.www.service.impl;

import com.sxops.www.dao.mapper.LUserInfoMapper;
import com.sxops.www.dao.model.LUserInfo;
import com.sxops.www.service.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends BaseServiceImpl<LUserInfo, LUserInfoMapper> implements UserInfoService {
}
