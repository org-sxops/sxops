package com.sxops.www.linfen.web.controller;

import com.sxops.www.linfen.dao.model.LUserInfo;
import com.sxops.www.linfen.service.UserInfoService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RequestMapping("test")
@RestController
@Api(value = "测试接口", tags = "测试接口")
@Slf4j
public class TestController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "新增接口,[geweiHome@163.com]")
    @PostMapping("addUser")
    public LUserInfo addUser(@RequestBody  LUserInfo userInfo ) {

        userInfoService.insertUserInfo(userInfo);
        return userInfo;
    }


    @ApiOperation(value = "查询接口,[geweiHome@163.com]")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "名称", paramType = "query"),
            @ApiImplicitParam(name = "phoneNum", value = "手机号", paramType = "query")
    })
    @GetMapping("getUser")
    public List<LUserInfo> getUser(@ApiIgnore LUserInfo userInfo) {
        List<LUserInfo> userList = userInfoService.select(userInfo);
        return userList;
    }


}
