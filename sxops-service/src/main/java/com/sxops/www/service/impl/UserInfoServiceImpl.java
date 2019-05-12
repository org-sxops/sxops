package com.sxops.www.service.impl;

import com.sxops.www.dao.mapper.UserInfoMapper;
import com.sxops.www.dao.model.UserInfo;
import com.sxops.www.service.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo, UserInfoMapper> implements UserInfoService {
}
