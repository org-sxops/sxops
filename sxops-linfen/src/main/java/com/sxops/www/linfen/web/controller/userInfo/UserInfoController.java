package com.sxops.www.linfen.web.controller.userInfo;

import com.sxops.www.common.annotation.OpLog;
import com.sxops.www.common.model.AjaxInfo;
import com.sxops.www.common.model.Pager;
import com.sxops.www.linfen.dao.model.journey.LfUserInfo;
import com.sxops.www.linfen.service.userInfo.LfUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


@RequestMapping("userInfo")
@RestController
@Api(value = "用户信息", tags = "用户信息")
@Slf4j
public class UserInfoController {


    @Autowired
    private LfUserInfoService lfUserInfoService;



    @ApiOperation(value = "新增接口,[geweiHome@163.com]")
    @PostMapping("addUser")
    @OpLog(desc = "用户新增接口" )
    public AjaxInfo addUser(@RequestBody LfUserInfo userInfo ) {
        try {
            log.info("模块：【用户信息】，操作：【分页查询】,参数：【{}】",userInfo.toString());
            return AjaxInfo.renderSuccess(lfUserInfoService.insertUserInfo(userInfo));
        } catch (Exception e) {
            log.error("模块：【用户信息】，操作：【分页查询】",e);
            return AjaxInfo.renderErrorData(e);
        }
    }


    @ApiOperation(value = "查询接口,[geweiHome@163.com]")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "名称", paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "phoneNum", value = "手机号", paramType = "query",dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "当页大小",  paramType = "query", dataType = "int")
    })
    @GetMapping("getPageUser")
    public AjaxInfo getPageUser(@ApiIgnore Pager pager, @ApiIgnore LfUserInfo userInfo) {
        try {
            log.info("模块：【用户信息】，操作：【分页查询】,参数：【{}】",userInfo.toString());
            return AjaxInfo.renderSuccess(lfUserInfoService.selectPage(pager, userInfo));
        } catch (Exception e) {
            log.error("模块：【用户信息】，操作：【分页查询】",e.getMessage(),e);
            return AjaxInfo.renderErrorData(e);
        }
    }

    @ApiOperation(value = "更新接口[geweiHome@163.com]")
    @PostMapping("updateUser")
    @OpLog(desc = "用户更新接口" )
    public AjaxInfo updateUser(@RequestBody LfUserInfo userInfo ) {
        try {
            log.info("模块：【用户信息】，操作：【用户更新接口】,参数：【{}】",userInfo.toString());
            lfUserInfoService.checkUserModelIsNotNull(userInfo, true);
            lfUserInfoService.updateByPrimaryKey(userInfo);
            return AjaxInfo.renderSuccess(userInfo);
        } catch (Exception e) {
            log.error("模块：【用户信息】，操作：【用户更新接口】错误信息：[{}]",e.getMessage(),e);
            return AjaxInfo.renderErrorData(e);
        }
    }

}
