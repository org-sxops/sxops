package com.sxops.linfen.common.util;

import com.google.common.base.Strings;

/**
 * <p>Description: [验证字符串中是否存在中文字符工具类]</p>
 * Copyright (c) 2017 山西省壹加柒网络技术有限公司
 * Created on 2017年10月23日
 *
 * @author <a href="mailto: miaozhihong@sxops.com">缪志红</a>
 * @version 1.0
 */
public class CheckContainChinese {

    /**
     * 验证字符串中是否存在中文字符
     * @param checkStr 需要验证的字符串
     * @return boolean 验证结果，true表示存在中文字符，false不存在
     */
    public static boolean checkStringContainChinese(String checkStr) {
        if (!Strings.isNullOrEmpty(checkStr)) {
            char[] checkChars = checkStr.toCharArray();
            for (char checkChar : checkChars) {
                if (checkCharContainChinese(checkChar)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 验证字符是否是中文
     * @param checkChar 需要验证的字符
     * @return boolean true表示是中文字符，false不是
     */
    private static boolean checkCharContainChinese(char checkChar) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(checkChar);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }

    /**
     * Discription:[生成随机码]
     * @param min 最小值
     * @param max 最大值
     * @return 随机码
     *
     * Created on 2017/11/9
     * @author: 尹归晋
     */
    private static int getRandNum(int min, int max) {
        int randNum = min + (int)(Math.random() * ((max - min) + 1));
        return randNum;
    }

    /**
     * Discription:[返回6-10位数随机码]
     * @return 6-10随机码
     *
     * Created on 2017/11/9
     * @author: 尹归晋
     */
    public static int randCode(){
        return getRandNum(100000, 999999999);
    }

}
