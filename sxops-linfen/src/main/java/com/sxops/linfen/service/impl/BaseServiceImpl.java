package com.sxops.linfen.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxops.linfen.service.BaseService;
import com.sxops.linfen.common.annotation.Ignore;
import com.sxops.linfen.common.annotation.Like;
import com.sxops.linfen.common.model.Pager;
import com.sxops.linfen.dao.util.MyBaseMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * Description: [baseService]
 * Created on 2017年11月02日
 *
 * @author <a href="mailto: miaozhihong@sxops.com">缪志红</a>
 * @version 1.0
 * Copyright (c) 2017年 山西省壹加柒网络技术有限公司
 */
public class BaseServiceImpl<T, D extends MyBaseMapper<T>> implements BaseService<T> {

    protected Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    D myBaseMapper;

    @Override
    public void insert(T record) {
        myBaseMapper.insert(record);
    }

    @Override
    public void insertList(List<T> records) {
        myBaseMapper.insertList(records);
    }

    @Override
    public void insertSelective(T record) {
        myBaseMapper.insertSelective(record);
    }

    @Override
    public void updateByPrimaryKey(T record) {
        myBaseMapper.updateByPrimaryKey(record);
    }

    @Override
    public void updateByPrimaryKeySelective(T record) {
        myBaseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void deleteLogic(Object key) {
    }

    @Override
    public void deletePhysics(Object key) {
        myBaseMapper.deleteByPrimaryKey(key);
    }

    @Override
    public List<T> select(T record) {
        return myBaseMapper.select(record);
    }

    @Override
    public List<T> selectAll() {
        return myBaseMapper.selectAll();
    }

    @Override
    public T selectByPrimaryKey(Object key) {
        return myBaseMapper.selectByPrimaryKey(key);
    }

    @Override
    public Pager<T> selectPage(Pager<T> pager, T record) {
        Page<T> pageInfo = PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<T> dictionaryList = myBaseMapper.select(record);
        pager.setPageInfo(pageInfo);
        pager.setRecords(dictionaryList);
        return pager;
    }

    /**
     * <p>Discription: [根据实体类构造example查询条件] </p>
     * Created on: 2017/11/6 13:06
     * @param obj 实体对象实例
     * @param example 查询调价对象
     * @return Example.Criteria 查询条件
     * @author [尹归晋]
     */
    public Example.Criteria buildCriteriaByEntity(Object obj, Example example)  {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        Example.Criteria criteria = example.createCriteria();
        if(declaredFields!=null){
            for (Field item:declaredFields) {
                boolean isStatic = Modifier.isStatic(item.getModifiers());
                if(isStatic){
                    continue;
                }
                Ignore ignore = item.getDeclaredAnnotation(Ignore.class);
                if(ignore!=null){
                    continue;
                }
                String fieldName = item.getName();

                Like like = item.getDeclaredAnnotation(Like.class);


                String firstLetter = fieldName.substring(0, 1).toUpperCase();
                String getter = "get" + firstLetter + fieldName.substring(1);
                Method method;
                Object value;
                try {
                    method = obj.getClass().getMethod(getter, new Class[] {});
                    value = method.invoke(obj, new Object[] {});
                    if(value == null){
                        continue;
                    }
                    if(value instanceof String && StringUtils.isEmpty(value.toString())){
                        continue;
                    }
                } catch (Exception e) {
                   throw new RuntimeException("构造查询条件失败："+e.getMessage());
                }
                if(like!=null){
                    criteria.andLike(fieldName, "%"+value.toString()+"%");
                    continue;
                }
                criteria.andEqualTo(fieldName, value);
            }
        }
        return criteria;
    }

    /**
     * <p>Discription: [在指定字符【前面】加百分号%，适用于like条件] </p>
     * Created on: 2017/11/6 12:11
     * @param source 源字符
     * @return String 处理完的字符
     * @author [尹归晋]
     */
    protected String percentPrefix(String source){
        return "%"+source;
    }

    /**
     * <p>Discription: [在指定字符【后面】加百分号%，适用于like条件] </p>
     * Created on: 2017/11/6 12:11
     * @param source 源字符
     * @return String 处理完的字符
     * @author [尹归晋]
     */
    protected String percentSuffix(String source){
        return source+"%";
    }

    /**
     * <p>Discription: [在指定字符【两面】加百分号%，适用于like条件] </p>
     * Created on: 2017/11/6 12:11
     * @param source 源字符
     * @return String 处理完的字符
     * @author [尹归晋]
     */
    protected String percent(String source){
        return "%"+source+"%";
    }
}
