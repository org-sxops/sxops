package com.sxops.www.ecm.controller;

import com.sxops.www.common.model.AjaxInfo;
import com.sxops.www.common.model.Pager;
import com.sxops.www.dao.model.OperateLog;
import com.sxops.www.service.OperateLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Description: [操作日志接口]
 * Created on 2017年11月02日
 * @author  <a href="mailto: liruifeng@sxops.com">尹归晋</a>
 * @version 1.0
 * Copyright (c) 2017年 山西省壹加柒网络技术有限公司
 */
@RestController
@RequestMapping("operateLog")
@Api(value = "操作日志接口", description = "操作日志接口")
public class OperateLogController extends BaseController {


    @Resource
    private OperateLogService operateLogService;


    @ApiOperation(value = "获取操作日志分页数据")
    @GetMapping("/queryPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "operateDesc", value = "操作描述", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "operatorCode", value = "操作人code", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "operateTimeStart", value = "开始时间",  paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "operateTimeEnd", value = "结束时间",  paramType = "query", dataType = "Date"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "当页大小",  paramType = "query", dataType = "int")
    })
    public AjaxInfo queryPage(@ApiIgnore Pager pager, @ApiIgnore OperateLog operateLog, HttpServletRequest request) {
        try {
            return AjaxInfo.renderSuccess(operateLogService.selectPage(pager, operateLog));
        } catch (Exception e) {
//            exceptionService.handler("获取操作日志分页数据异常", e, request);
            return AjaxInfo.renderError();
        }
    }

    /**
     * <p>Discription: [根据id查询日志详情] </p>
     * Created on: 2017/11/6 14:59
     * @param id 日志id
     * @return
     * @author [尹归晋]
     */
    @ApiOperation(value = "根据id查询日志详情")
    @GetMapping("detail")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "日志ID", required = true, paramType = "query", dataType = "long"),
    })
    public AjaxInfo detailById(Long id, HttpServletRequest request) {
        try {
            OperateLog operateLog = operateLogService.selectByPrimaryKey(id);
            return AjaxInfo.renderSuccess(operateLog);
        } catch (Exception e) {
//            exceptionService.handler("根据id查询日志详情异常", e, request);
            return AjaxInfo.renderError();
        }
    }
}
