package com.camelotchina.www.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("test")
@RestController
@Api(value = "测试接口",tags = "测试接口")
public class TestController extends BaseController {

    @ApiOperation(value = "测试接口,[geweiHome@163.com]" )
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value = "名称" ,paramType = "query"),
            @ApiImplicitParam(name="id",value = "ID" ,paramType = "query")
    })
    @GetMapping("nameAndId")
    public String getUser(String name,String id){
        System.out.println(name + id);
        return name + id;
    }
}
