package com.camelotchina.www.service.impl;

import com.camelotchina.www.dao.mapper.ExceptionLogMapper;
import com.camelotchina.www.dao.model.ExceptionLog;
import com.camelotchina.www.service.ExceptionLogService;
import org.springframework.stereotype.Service;

/**
 * <p> Description: [操作日志服务实现类]</p>
 * Created on: 2017/11/10 15:32
 *
 * @author <a href="mailto: liruifeng@camelotchina.com">尹归晋</a>
 * @version 1.0
 */
@Service
public class ExceptionLogServiceImpl extends BaseServiceImpl<ExceptionLog, ExceptionLogMapper> implements ExceptionLogService{

    //@Override
    //public Pager<ExceptionLog> selectPage(Pager<ExceptionLog> pager, ExceptionLog record) {
    //    Example example = new Example(record.getClass());
    //    Example.Criteria criteria = buildCriteriaByEntity(record, example);
    //    //时间范围查询
    //    if(StringUtils.isNotEmpty(record.getCreateTimeStart())){
    //        criteria.andGreaterThanOrEqualTo("createTime",record.getCreateTimeStart());
    //    }
    //    if(StringUtils.isNotEmpty(record.getCreateTimeEnd())){
    //        criteria.andLessThanOrEqualTo("createTime",record.getCreateTimeEnd());
    //    }
    //    example.orderBy("createTime").desc();
    //    example.selectProperties("id","uri","operatorCode","operator","operatorIp","description","hostName","serverName","createTime");
    //    Page<ExceptionLog> pageInfo = PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
    //    List<ExceptionLog> operateLogList = myBaseMapper.selectByExample(example);
    //    for (ExceptionLog exceptionLog : operateLogList){
    //        //为了不加字段，将系统名存储到了Operator，查询列表时，将Operator赋值给serverName，operator存储操作人
    //        exceptionLog.setServerName(exceptionLog.getOperator());
    //        exceptionLog.setOperator(StringUtils.defaultString(exceptionLog.getOperatorCode())+" "+StringUtils.defaultString(exceptionLog.getOperatorCode()));
    //    }
    //    pager.setPageInfo(pageInfo);
    //    pager.setRecords(operateLogList);
    //    return pager;
    //}
}
