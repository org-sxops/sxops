package com.camelotchina.www.common.util;

import java.util.regex.Pattern;

/**
 * <p>Description: [字符工具类]</p>
 * Created on 2018年1月6日
 * @author  <a href="mailto:liuxiangping@camelotchina.com">尹归晋</a>
 * @version 1.0 
 * Copyright (c) 2018 北京柯莱特科技有限公司 交付部
 */
public class CharUtil {

	public static void main(String[] args) {
        String[] strArr = new String[] { "www.micmiu.com", "!@#$%^&*()_+{}[]|\"'?/:;<>,.", "！￥……（）——：；“”‘’《》，。？、", "不要啊", "やめて", "韩佳人", "???" };
        for (String str : strArr) {
            System.out.println("===========> 测试字符串：" + str);
            System.out.println("正则判断结果：" + isChineseByREG(str) + " -- " + isChineseByName(str));
            System.out.println("Unicode判断结果 ：" + isChinese(str));
            System.out.println("详细判断列表：");
            char[] ch = str.toCharArray();
            for (int i = 0; i < ch.length; i++) {
                char c = ch[i];
                System.out.println(c + " --> " + (isChinese(c) ? "是" : "否"));
            }
        }
    }
 
    /**
     * <p>Discription:[根据Unicode编码完美的判断中文汉字和符号]</p>
     * Created on 2018年1月6日
     * @param c
     * @return
     * @author:[尹归晋]
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }
 
    /**
     * <p>Discription:[判断中文汉字和符号]</p>
     * Created on 2018年1月6日
     * @param strName
     * @return
     * @author:[尹归晋]
     */
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }
 
    /**
     * <p>Discription:[只能判断部分CJK字符（CJK统一汉字）]</p>
     * Created on 2018年1月6日
     * @param str
     * @return
     * @author:[尹归晋]
     */
    public static boolean isChineseByREG(String str) {
        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
        return pattern.matcher(str.trim()).find();
    }
 
    /**
     * <p>Discription:[只能判断部分CJK字符（CJK统一汉字）]</p>
     * Created on 2018年1月6日
     * @param str
     * @return
     * @author:[尹归晋]
     */
    public static boolean isChineseByName(String str) {
        if (str == null) {
            return false;
        }
        // 大小写不同：\\p 表示包含，\\P 表示不包含
        // \\p{Cn} 的意思为 Unicode 中未被定义字符的编码，\\P{Cn} 就表示 Unicode中已经被定义字符的编码
        String reg = "\\p{InCJK Unified Ideographs}&&\\P{Cn}";
        Pattern pattern = Pattern.compile(reg);
        return pattern.matcher(str.trim()).find();
    }
}
