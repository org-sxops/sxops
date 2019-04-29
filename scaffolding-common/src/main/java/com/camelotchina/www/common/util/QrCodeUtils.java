package com.camelotchina.www.common.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * <p>Description: [文件扩展名验证工具类]</p>
 * Copyright (c) 2017 北京柯莱特科技有限公司
 * Created on 2017年11月10日
 *
 * @author <a href="mailto: miaozhihong@camelotchina.com">缪志红</a>
 * @version 1.0
 */
public class QrCodeUtils {

    /**
     * 编码格式
     */
    private static final String CHARSET = "utf-8";

    /**
     * 图片格式
     */
    private static final String FORMAT_NAME = "JPG";

    /**
     * 二维码尺寸
     */
    private static final int QR_CODE_SIZE = 300;

    /**
     * <p>Description:[根据二维码内容回去byte[]]</p>
     * Created on 2017年11月9日
     *
     * @param content 二维码内容
     * @return byte[] 输出流
     * @author 缪志红
     */
    public static byte[] createImage(String content) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, FORMAT_NAME, out);
        return out.toByteArray();
    }
    
    /**
     * <p>Discription:[根据内容生产二维码]</p>
     * Created on 2018年1月5日
     * @param content 二维码内容
     * @param qrCodeSize 二维码尺寸（正方形）
     * @return BufferedImage 图片
     * @throws Exception
     * @author:[尹归晋]
     */
    public static BufferedImage createImage(String content, int qrCodeSize) throws Exception {
    	/*Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        return image;*/
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE, hints);
        int[] rec = bitMatrix.getEnclosingRectangle();  
        int resWidth = rec[2] + 1;  
        int resHeight = rec[3] + 1;  
        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);  
        resMatrix.clear();  
        for (int i = 0; i < resWidth; i++) {  
            for (int j = 0; j < resHeight; j++) {  
                if (bitMatrix.get(i + rec[0], j + rec[1])) { 
                     resMatrix.set(i, j); 
                } 
            }  
        }
        int width = resMatrix.getWidth();
        int height = resMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, resMatrix.get(x, y) == true ? Color.BLACK.getRGB():Color.WHITE.getRGB());
            }
        }
        return image;
    }

}
