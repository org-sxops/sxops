package com.sxops.linfen.common.util;

import com.sxops.linfen.common.util.log.LogMessage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * <p>Description: [md5工具类]</p>
 * Copyright (c) 2017 山西省壹加柒网络技术有限公司
 * Created on 2017年8月22日
 * @author  <a href="mailto: liuchao@sxops.com">刘超</a>
 * @version 1.0 
 */ 
public class Md5Util {
	private final static Logger LOGGER = LoggerFactory.getLogger(Md5Util.class);
	// 用于加密的字符
	static final char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'd', 'e', 'f' };	
	
	
	/**
	 * <p>Discription:[字符串加密]</p>
	 * Created on 2017年8月22日
	 * @param val 字符串
	 * @return 数据经过加密后生成的字符串
	 * @author:[刘超]
	 */
	public final static String strToMd5(String val) { 
		// 使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
		byte[] bytes = val.getBytes();
		return md5(bytes);
	}
	
	/**
	 * <p>Discription:[md5加密]</p>
	 * Created on 2017年8月22日
	 * @param bytes byte数组
	 * @return String 数据经过加密后生成的字符串
	 * @author:[刘超]
	 */
	public final static String md5(byte[] bytes){
		try {
			// 信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
			MessageDigest mdInst = MessageDigest.getInstance("MD5");

			// MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
			mdInst.update(bytes);

			// 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
			byte[] md = mdInst.digest();

			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) { // i = 0
				byte byte0 = md[i]; // 95
				str[k++] = md5String[byte0 >>> 4 & 0xf]; // 5
				str[k++] = md5String[byte0 & 0xf]; // F
			}

			// 返回经过加密后的字符串
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(LogMessage.getNew().add("生成MD5加密字符串失败：", e).toString());
			e.printStackTrace();
		}	
		return null;
	}
}
