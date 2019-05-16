package com.sxops.www.common.util;


import com.google.common.base.Charsets;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;

/**
 * <p>Discription: [安全处理] </p>
 * Created on: 2017/11/2 18:54
 *
 * @author [葛伟]
 */
public class SecurityUtils {

    private static final String AES = "AES";

    public static final String AES_KEY = "1c61bf8ca2ba4b6a96042bd0c57e8942";//加密key
    /**
     * <p>Discription: [对字符串进行md5摘要] </p>
     * Created on: 2017/11/2 19:13
     * @param content 需要计算摘要的字符串
     * @return 摘要
     * @author [葛伟]
     */
    public static String md5(String content) {
        byte[] md5Bytes = DigestUtils.md5(content);
        return Base64.encodeBase64String(md5Bytes);
    }

    /**
     * <p>Discription: [对字符串进行sha256摘要] </p>
     * Created on: 2017/11/2 19:13
     * @param content 需要计算摘要的字符串
     * @return 摘要
     * @author [葛伟]
     */
    public static String sha256(String content) {
        byte[] md5Bytes = DigestUtils.sha256(content);
        return Base64.encodeBase64String(md5Bytes);
    }

    /**
     * <p>Description:[加密]</p>
     * Created on 2017年11月17日
     *
     * @param content 需要加密的字符串
     * @return String 加密后的字符串
     * @author 葛伟
     */
    public static String aesEncrypt(String content) throws Exception {
        return aesEncrypt(content, AES_KEY);
    }

    /**
     * <p>Description: [加密] </p>
     * Created on: 2017/11/17 19:30
     * @param content   待加密的内容
     * @param key 秘钥字符串
     * @return String 加密后的内容
     * @author [葛伟]
     */
    public static String aesEncrypt(String content, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(AES);
        byte[] byteContent = content.getBytes(Charsets.UTF_8);
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));
        byte[] result = cipher.doFinal(byteContent);
        return Base64.encodeBase64String(result);
    }

    /**
     * <p>Description:[解密]</p>
     * Created on 2017年11月17日
     *
     * @param content 加密的串
     * @return String 解密后的字符串
     * @author 葛伟
     */
    public static String aesDecrypt(String content) throws Exception {
        return aesDecrypt(content, AES_KEY);
    }

    /**
     * <p>Discription: [解密] </p>
     * Created on: 2017/11/2 19:30
     * @param content   待解密的内容
     * @param key 秘钥字符串
     * @return String 解密后的内容
     * @author [葛伟]
     */
    public static String aesDecrypt(String content, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));
        byte[] result = cipher.doFinal(Base64.decodeBase64(content));
        return new String(result, Charsets.UTF_8);
    }

    /**
     * <p>Discription: [生成加密秘钥] </p>
     * Created on: 2017/11/2 19:48
     * @param key 秘钥字符串
     * @return SecretKeySpec 秘钥
     * @author [葛伟]
     */
    private static SecretKeySpec getSecretKey(String key) throws NoSuchAlgorithmException {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = KeyGenerator.getInstance(AES);
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(key.getBytes());
        //AES 要求密钥长度为 128
        kg.init(128, secureRandom);
        //生成一个密钥
        SecretKey secretKey = kg.generateKey();
        return new SecretKeySpec(secretKey.getEncoded(), AES);// 转换为AES专用密钥
    }
}