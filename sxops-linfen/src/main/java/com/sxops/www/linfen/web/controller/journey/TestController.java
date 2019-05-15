package com.sxops.www.linfen.web.controller.journey;

import com.sxops.www.linfen.dao.model.journey.LfUserInfo;
import com.sxops.www.linfen.service.journey.LfUserInfoService;
import com.sxops.www.linfen.web.controller.basic.BaseController;
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
    private LfUserInfoService lfUserInfoService;

    @ApiOperation(value = "新增接口,[geweiHome@163.com]")
    @PostMapping("addUser")
    public LfUserInfo addUser(@RequestBody  LfUserInfo userInfo ) {

        lfUserInfoService.insertUserInfo(userInfo);
        return userInfo;
    }


    @ApiOperation(value = "查询接口,[geweiHome@163.com]")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "名称", paramType = "query"),
            @ApiImplicitParam(name = "phoneNum", value = "手机号", paramType = "query")
    })
    @GetMapping("getUser")
    public List<LfUserInfo> getUser(@ApiIgnore LfUserInfo userInfo) {
        List<LfUserInfo> userList = lfUserInfoService.select(userInfo);
        return userList;
    }


}
