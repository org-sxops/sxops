package com.sxops.www.linfen.web.controller.carInfo;

import com.sxops.www.common.annotation.OpLog;
import com.sxops.www.common.model.AjaxInfo;
import com.sxops.www.common.model.Pager;
import com.sxops.www.linfen.dao.model.carInfo.LfCarInfo;
import com.sxops.www.linfen.service.carInfo.LfCarInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


@RequestMapping("carInfo")
@RestController
@Api(value = "车辆信息", tags = "车辆信息")
@Slf4j
public class CarInfoController {

    @Autowired
    private LfCarInfoService lfCarInfoService;

    @ApiOperation(value = "新增接口,[geweiHome@163.com]")
    @PostMapping("addCar")
    @OpLog(desc = "车辆新增接口")
    public LfCarInfo addCar(@RequestBody LfCarInfo carInfo) {
        log.info("模块：【用户信息】，操作：【分页查询】,参数：【{}】", carInfo.toString());
        return lfCarInfoService.insertCarInfo(carInfo);
    }

    @ApiOperation(value = "查询接口,[geweiHome@163.com]")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "CarName", value = "车辆名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "userInfoUuid", value = "用户UUID", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "当页大小", paramType = "query", dataType = "int")
    })
    @GetMapping("getPageCar")
    public Pager<LfCarInfo> getPageCar(@ApiIgnore Pager pager, @ApiIgnore LfCarInfo carInfo) {
        log.info("模块：【车辆信息】，操作：【分页查询】,参数：【{}】", carInfo.toString());
        return lfCarInfoService.selectPage(pager, carInfo);
    }

    @ApiOperation(value = "更新接口[geweiHome@163.com]")
    @PostMapping("updateCar")
    @OpLog(desc = "车辆信息更新接口")
    public LfCarInfo updateCar(@RequestBody LfCarInfo carInfo) {
            log.info("模块：【车辆信息】，操作：【车辆信息更新接口】,参数：【{}】", carInfo.toString());
            lfCarInfoService.checkCarModelIsNotNull(carInfo, true);
            lfCarInfoService.updateByPrimaryKey(carInfo);
            return  carInfo;
    }

}
