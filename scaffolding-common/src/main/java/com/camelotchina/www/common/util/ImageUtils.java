package com.camelotchina.www.common.util;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description: [图片工具类]</p>
 * Copyright (c) 2017 北京柯莱特科技有限公司
 * Created on 2018年1月7日
 *
 * @author <a href="mailto: miaozhihong@camelotchina.com">缪志红</a>
 * @version 1.0
 */
public class ImageUtils {

    /** 图片格式JPG */
    public static final String IMG_JPG = "JPG";

    /** 图片后缀.jpg */
    public static final String IMG_SUFFIX_JPG = ".jpg";

    /**
     * <p>Description:[封装下载图片的response]</p>
     * Created on 2018/1/7
     *
     * @author 缪志红
     */
    public static void getResponseHeader(HttpServletResponse response, String encodeName) {
        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.addHeader("charset", "utf-8");
        response.addHeader("Pragma", "no-cache");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodeName + "\"; filename*=utf-8''" + encodeName);
    }
}
