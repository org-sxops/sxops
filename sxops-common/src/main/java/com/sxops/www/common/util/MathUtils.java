package com.sxops.www.common.util;

import java.math.BigDecimal;

/**
 * <p>Description: [数字计算工具类]</p>
 * Copyright (c) 2017 山西省壹加柒网络技术有限公司
 * Created on 2017年11月23日
 *
 * @author <a href="mailto: gewei@sxops.com">葛伟</a>
 * @version 1.0
 */
public class MathUtils {

    /** 一百的常量 */
    private static final int HUNDRED_NUMBER = 100;

    /** 默认保留小数位数 */
    private static final int DEFAULT_DECIMAL_NUMBER = 2;

    /**
     *  <p>Description:[根据两个整数获取BigDecimal数值]</p>
     * @param a 分子数字
     * @param b 分母数字
     * @return BigDecimal 保留4位小数返回值
     */
    public static BigDecimal getBigDecimal(int a, int b) {
        if (b == 0) {
            return new BigDecimal(0).setScale(DEFAULT_DECIMAL_NUMBER, BigDecimal.ROUND_HALF_UP);
        }
        return new BigDecimal((float)a/b).setScale(DEFAULT_DECIMAL_NUMBER, BigDecimal.ROUND_HALF_UP);
    }

    /**
     *  <p>Description:[根据两个整数获取BigDecimal数值]</p>
     * @param a 分子数字
     * @param b 分母数字
     * @return BigDecimal 保留4位小数返回值
     */
    public static BigDecimal getBigDecimal(long a, long b) {
        if (b == 0) {
            return new BigDecimal(0).setScale(DEFAULT_DECIMAL_NUMBER, BigDecimal.ROUND_HALF_UP);
        }
        return new BigDecimal((float)a/b).setScale(DEFAULT_DECIMAL_NUMBER, BigDecimal.ROUND_HALF_UP);
    }

    /**
     *  <p>Description:[根据两个整数获取BigDecimal数值]</p>
     * @param a 分子数字
     * @param b 分母数字
     * @return BigDecimal 保留4位小数返回值
     */
    public static BigDecimal getBigDecimal(double a, double b) {
        if (b == 0) {
            return new BigDecimal(0).setScale(DEFAULT_DECIMAL_NUMBER, BigDecimal.ROUND_HALF_UP);
        }
        return new BigDecimal((float)a/b).setScale(DEFAULT_DECIMAL_NUMBER, BigDecimal.ROUND_HALF_UP);
    }

    /**
     *  <p>Description:[计算一个数值的百分比字符串，保留两位小数]</p>
     * @param bigDecimal 参数
     * @return String 保留2位小数返回值
     */
    public static String getPercentBigDecimal(BigDecimal bigDecimal) {
        return getBigDecimal(bigDecimal.multiply(new BigDecimal(HUNDRED_NUMBER)), DEFAULT_DECIMAL_NUMBER).toString() + "%";
    }

    /**
     *  <p>Description:[BigDecimal保留对应的小数位数]</p>
     * @param bigDecimal 数据参数
     * @param scale 保留小数位数
     * @return BigDecimal 保留小数后的数据
     */
    public static BigDecimal getBigDecimal(BigDecimal bigDecimal, int scale) {
        return bigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     *  <p>Description:[BigDecimal保留两位小数位数]</p>
     * @param bigDecimal 数据参数
     * @return BigDecimal 保留两位小数后的数据
     */
    public static BigDecimal getBigDecimal(BigDecimal bigDecimal) {
        return bigDecimal.setScale(DEFAULT_DECIMAL_NUMBER, BigDecimal.ROUND_HALF_UP);
    }
}
