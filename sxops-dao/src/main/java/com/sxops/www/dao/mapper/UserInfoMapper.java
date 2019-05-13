package com.sxops.www.dao.mapper;

import com.sxops.www.dao.model.UserInfo;
import com.sxops.www.dao.util.MyBaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserInfoMapper extends MyBaseMapper<UserInfo> {
}