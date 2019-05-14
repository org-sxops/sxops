package com.sxops.www.linfen.dao.util;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用Mapper接口
 * Created by camelot on 2017/10/17.
 */
@Repository
public interface MyBaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
