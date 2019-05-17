package com.sxops.www.linfen.web.controller.userInfo;

import com.sxops.www.common.annotation.OpLog;
import com.sxops.www.common.model.AjaxInfo;
import com.sxops.www.common.model.Pager;
import com.sxops.www.linfen.dao.model.userInfo.UserInfo;
import com.sxops.www.linfen.service.userInfo.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


@RequestMapping("userInfo")
@RestController
@Api(value = "用户信息", tags = "用户信息")
@Slf4j
public class UserInfoController {


    @Autowired
    private UserInfoService userInfoService;


    @ApiOperation(value = "新增接口,[geweiHome@163.com]")
    @PostMapping("addUser")
    @OpLog(desc = "用户新增接口")
    public UserInfo addUser(@RequestBody UserInfo userInfo) {
        log.info("模块：【用户信息】，操作：【分页查询】,参数：【{}】", userInfo.toString());
        return userInfoService.insertUserInfo(userInfo);
    }
    @ApiOperation(value = "查询接口,[geweiHome@163.com]")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "phoneNum", value = "手机号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "当页大小", paramType = "query", dataType = "int")
    })
    @GetMapping("getPageUser")
    public Pager<UserInfo> getPageUser(@ApiIgnore Pager pager, @ApiIgnore UserInfo userInfo) {
        log.info("模块：【用户信息】，操作：【分页查询】,参数：【{}】", userInfo.toString());
        return userInfoService.selectPage(pager, userInfo);
    }

    @ApiOperation(value = "更新接口[geweiHome@163.com]")
    @PostMapping("updateUser")
    @OpLog(desc = "用户更新接口")
    public UserInfo updateUser(@RequestBody UserInfo userInfo) {
        log.info("模块：【用户信息】，操作：【用户更新接口】,参数：【{}】", userInfo.toString());
        userInfoService.checkUserModelIsNotNull(userInfo, true);
        userInfoService.updateByPrimaryKey(userInfo);
        return userInfo;
    }

}
