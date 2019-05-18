
package com.sxops.www.thirdApi.conntroller;

import com.sxops.www.common.annotation.OpLog;
import com.sxops.www.thirdApi.service.DistrictService;
import com.sxops.www.thirdApi.thirdVo.AbstractThirdApiResponse;
import com.sxops.www.thirdApi.thirdVo.District;
import com.sxops.www.thirdApi.thirdVo.DistrictRequestVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


@RequestMapping("District")
@RestController
@Api(value = "行政区域查询", tags = "行政区域查询")
@Slf4j
public class DistrictController {


    @Autowired
    private DistrictService districtService;


    @ApiOperation(value = "行政区域查询[geweiHome@163.com]")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keywords", value = "查询关键字", paramType = "query",required = true, dataType = "String"),
            @ApiImplicitParam(name = "subdistrict", value = "子级行政区 (可选值：0、1、2、3等数字，并以此类推)", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "page", value = "当前页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "offset", value = "当页大小", paramType = "query", dataType = "int")
    })
    @GetMapping("getPageDistrict")
    @OpLog(desc = "行政区域查询")
    public  List<District> getPageUser(@ApiIgnore DistrictRequestVo districtRequestVo) {
        log.info("模块：【行政区域查询】，操作：【分页查询】,参数：【{}】", districtRequestVo.toString());
        return districtService.getDistrictByParam(districtRequestVo);
    }


}
