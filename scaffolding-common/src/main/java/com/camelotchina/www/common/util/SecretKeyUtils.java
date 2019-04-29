package com.camelotchina.www.common.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Description: [处理秘钥的工具类]</p>
 * Created on: 2018/1/5
 *
 * @author: <a href="mailto: liqun@camelotchina.com">liqun<br/>
 * @version: 1.0 <br/>
 * Copyright (c) 2016年 北京柯莱特科技有限公司 交付部
 */
public class SecretKeyUtils {

    /**
     * <p>Description:[用于对字段进行MD5加密]</p>
     * Created on 2018/1/5
     * @param code 要加密的字段
     * @return 返回32位加密后的数据
     * @author:[jinguoqing]
     */
    public static String encryption(String code){

        byte[] secretBytes = new byte[1];
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    code.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
