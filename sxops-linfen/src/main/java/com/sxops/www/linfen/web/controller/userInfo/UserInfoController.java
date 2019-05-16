package com.sxops.www.linfen.web.controller.userInfo;

import com.sxops.www.common.annotation.OpLog;
import com.sxops.www.common.model.AjaxInfo;
import com.sxops.www.common.model.Pager;
import com.sxops.www.linfen.dao.model.userInfo.LfUserInfo;
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
    @OpLog(desc = "用户新增接口")
    public LfUserInfo addUser(@RequestBody LfUserInfo userInfo) {
        log.info("模块：【用户信息】，操作：【分页查询】,参数：【{}】", userInfo.toString());
        return lfUserInfoService.insertUserInfo(userInfo);
    }
    @ApiOperation(value = "查询接口,[geweiHome@163.com]")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "phoneNum", value = "手机号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "当页大小", paramType = "query", dataType = "int")
    })
    @GetMapping("getPageUser")
    public Pager<LfUserInfo> getPageUser(@ApiIgnore Pager pager, @ApiIgnore LfUserInfo userInfo) {
        log.info("模块：【用户信息】，操作：【分页查询】,参数：【{}】", userInfo.toString());
        return lfUserInfoService.selectPage(pager, userInfo);
    }

    @ApiOperation(value = "更新接口[geweiHome@163.com]")
    @PostMapping("updateUser")
    @OpLog(desc = "用户更新接口")
    public LfUserInfo updateUser(@RequestBody LfUserInfo userInfo) {
        log.info("模块：【用户信息】，操作：【用户更新接口】,参数：【{}】", userInfo.toString());
        lfUserInfoService.checkUserModelIsNotNull(userInfo, true);
        lfUserInfoService.updateByPrimaryKey(userInfo);
        return userInfo;
    }

}
