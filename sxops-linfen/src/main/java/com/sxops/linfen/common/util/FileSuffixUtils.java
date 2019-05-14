package com.sxops.linfen.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * <p>Description: [文件扩展名验证工具类]</p>
 * Copyright (c) 2017 山西省壹加柒网络技术有限公司
 * Created on 2017年10月18日
 *
 * @author <a href="mailto: miaozhihong@sxops.com">缪志红</a>
 * @version 1.0
 */
@Component
public class FileSuffixUtils {

    @Value("${file.suffix}")
    private String fileType;

    /**
     * <p>Description:[验证后缀名是否支持]</p>
     * Created on 2017年10月18日
     * @author 缪志红
     */
    public boolean isPermit(String fileName) {
        String prefixName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase().trim();
        //判断是否为允许上传的文件类型
        boolean isPermit = !Arrays.asList(fileType.split(",")).contains(prefixName);
        return !isPermit;
    }

}
