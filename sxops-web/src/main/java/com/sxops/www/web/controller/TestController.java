package com.sxops.www.web.controller;

import com.sxops.www.dao.model.LUserInfo;
import com.sxops.www.service.UserInfoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("test")
@RestController
@Api(value = "测试接口", tags = "测试接口")
public class TestController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "新增接口,[geweiHome@163.com]")
    @PostMapping("addUser")
    public LUserInfo addUser( @RequestBody  LUserInfo userInfo ) {
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
