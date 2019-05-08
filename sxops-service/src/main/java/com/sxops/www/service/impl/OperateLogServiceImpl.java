package com.sxops.www.service.impl;

import com.sxops.www.common.model.Pager;
import com.sxops.www.common.util.StringUtils;
import com.sxops.www.dao.mapper.OperateLogMapper;
import com.sxops.www.dao.model.OperateLog;
import com.sxops.www.service.OperateLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * <p> Description: [操作日志服务实现类]</p>
 * Created on: 2017/11/10 15:32
 *
 * @author <a href="mailto: liruifeng@sxops.com">尹归晋</a>
 * @version 1.0
 */
@Service
public class OperateLogServiceImpl extends BaseServiceImpl<OperateLog, OperateLogMapper> implements OperateLogService {


    @Override
    public Pager<OperateLog> selectPage(Pager<OperateLog> pager, OperateLog record) {
        Example example = new Example(record.getClass());
        Example.Criteria criteria = buildCriteriaByEntity(record, example);
        //时间范围查询
        if(StringUtils.isNotEmpty(record.getOperateTimeStart())){
            criteria.andGreaterThanOrEqualTo("operateTime",record.getOperateTimeStart());
        }
        if(StringUtils.isNotEmpty(record.getOperateTimeEnd())){
            criteria.andLessThanOrEqualTo("operateTime",record.getOperateTimeEnd());
        }
        example.orderBy("operateTime").desc();
        Page<OperateLog> pageInfo = PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<OperateLog> operateLogList = myBaseMapper.selectByExample(example);
        for (OperateLog operateLog : operateLogList){
            operateLog.setOperator(StringUtils.defaultString(operateLog.getOperatorCode())+" "+StringUtils.defaultString(operateLog.getOperatorCode()));
        }
        pager.setPageInfo(pageInfo);
        pager.setRecords(operateLogList);
        return pager;
    }
}
