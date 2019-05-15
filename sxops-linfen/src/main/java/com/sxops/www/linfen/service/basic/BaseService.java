package com.sxops.www.linfen.service.basic;

import com.sxops.www.common.model.Pager;

import java.util.List;

/**
 * Description: [baseService]
 * Created on 2017年11月02日
 *
 * @author <a href="mailto: miaozhihong@sxops.com">缪志红</a>
 * @version 1.0
 * Copyright (c) 2017年 山西省壹加柒网络技术有限公司
 */
public interface BaseService<T> {

    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param record 实体对象
     * @author 缪志红
     */
    void insert(T record);

    /**
     * 保存一个实体集合，null的属性也会保存，不会使用数据库默认值
     *
     * @param records 实体对象集合
     * @author 缪志红
     */
    void insertList(List<T> records);

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param record 实体对象
     * @author 缪志红
     */
    void insertSelective(T record);

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param record 实体对象
     * @author 缪志红
     */
    void updateByPrimaryKey(T record);

    /**
     * 根据主键更新属性不为null的值
     *
     * @param record 实体对象
     * @author 缪志红
     */
    void updateByPrimaryKeySelective(T record);

    /**
     * 主键条件进行逻辑删除(因代码生成规则问题，本方法未实现，需要自己通过修改方法实现)
     *
     * @param key 主键
     * @author 缪志红
     */
    void deleteLogic(Object key);

    /**
     * 根据主键条件进行物理删除
     *
     * @param key 主键
     * @author 缪志红
     */
    void deletePhysics(Object key);

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param record 实体对象
     * @return 影响记录数
     * @author 缪志红
     */
    List<T> select(T record);

    /**
     * 查询全部结果
     *
     * @return 影响记录数
     * @author 缪志红
     */
    List<T> selectAll();

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param key 主键
     * @return 影响记录数
     * @author 缪志红
     */
    T selectByPrimaryKey(Object key);

    /**
     * 分页查询记录
     *
     * @param pager  分页对象
     * @param record 查询条件对象
     * @return 分页记录
     * @author 缪志红
     */
    Pager<T> selectPage(Pager<T> pager, T record);

}
