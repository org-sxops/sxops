package com.sxops.www.common.util;

import com.sxops.www.common.util.log.LogMessage;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <p>Description: [根据模版生产二维码]</p>
 * Created on 2018年1月5日
 * @author  <a href="mailto:gewei@sxops.com">葛伟</a>
 * @version 1.0 
 * Copyright (c) 2018 山西省壹加柒网络技术有限公司
 */
public class QRCodeTemplateUtil {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(QRCodeTemplateUtil.class);
	
	/** >>>>>>>>>>>>模版一配置参数<<<<<<<<<<< */
	/** 模版一图片的宽度 */
	private int oneImageWidth = 2362;
	
	/** 模版一图片的高度 */
	private int oneImageHeight = 608;
	
	/** 模版一二维码定位X */
	private int oneQRCodeX = 49;
	
	/** 模版一二维码定位Y */
	private int oneQRCodeY = 48;
	
	/** 模版一二维码大小（正方形长高一致） */
	private int qrCodeSize = 518;
	
	/** 模版一文字定位X */
	private int oneWenZiX = 625;
	
	/** 模版一文字定位Y */
	private int oneWenZiY = 118;
	
	/** 模版一文字字体 */
	private float oneWenZiSize = 85;
	
	/** >>>>>>>>>>>>模版二配置参数<<<<<<<<<<< */
	/** 模版二图片的宽度 */
	private int twoImageWidth = 842;
	
	/** 模版二图片的高度 */
	private int twoImageHeight = 596;
	
	/** 模版二二维码定位X */
	private int twoQRCodeX = 573;
	
	/** 模版二二维码定位Y */
	private int twoQRCodeY = 92;
	
	/** 模版二二维码大小（正方形长高一致） */
	private int twoQRCodeSize = 168;
	
	/** 模版二文字定位X */
	private double twoWenZiX = 535;
	
	/** 模版二文字定位Y */
	private double twoWenZiY = 155;
	
	/** 模版二文字字体 */
	private float twoWenZiSize = 80;
	
	private Font definedFont = null;
	
	public QRCodeTemplateUtil(){
		InputStream is = null;
        try {
            is = getClass().getClassLoader().getResourceAsStream("file/FZLTHJW.TTF");
            definedFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            LOGGER.error(LogMessage.getNew().add("获取字体失败：", e).toString());
        } finally {   
           try {    
                if (null != is) is.close();   
            } catch (Exception e) {    
            	LOGGER.error(LogMessage.getNew().add("关闭字体文件流失败：", e).toString());
            }  
        }    
	}
	
	/**
	 * <p>Discription:[模版一二维码生成]</p>
	 * Created on 2018年1月5日
	 * @param name 会议室名称
	 * @param url 二维码内容
	 * @param oneTemplateUrl 模版一图片URL
	 * @return
	 * @throws Exception
	 * @author:[葛伟]
	 */
	public BufferedImage oneQRCodeTemplate(String name, String url, String oneTemplateUrl) throws Exception {
		
		BufferedImage oneTemplate = getBufferedImage(oneTemplateUrl);
		BufferedImage  qrCodeTemplateImage = new BufferedImage(oneImageWidth, oneImageHeight, BufferedImage.TYPE_INT_RGB);
		/** 设置图片的背景色 */
		Graphics2D main = qrCodeTemplateImage.createGraphics();
		main.setColor(Color.WHITE);
		main.fillRect(0, 0, oneImageWidth, oneImageHeight);
		/** 填充模版图片 */
		Graphics mainPic = qrCodeTemplateImage.getGraphics();
		if (oneTemplate != null) {
			mainPic.drawImage(oneTemplate, 0, 0, oneImageWidth, oneImageHeight, null);
			mainPic.dispose();
		}
		/** 二维码填充模版图片 */
		Graphics qrCodePic = qrCodeTemplateImage.getGraphics();
		qrCodePic.setColor(Color.white);
		BufferedImage qrCodeImg = QrCodeUtils.createImage(url, qrCodeSize);
		if (qrCodeImg != null) {
			qrCodePic.drawImage(qrCodeImg, oneQRCodeX, oneQRCodeY, qrCodeSize, qrCodeSize, null);
			qrCodePic.dispose();
		}
		/** 填充会议室名称 */
		Graphics2D roomName = qrCodeTemplateImage.createGraphics();
		roomName.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		roomName.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		roomName.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		definedFont = definedFont.deriveFont(oneWenZiSize);
        definedFont = definedFont.deriveFont(Font.BOLD);
        roomName.setFont(definedFont);
		String tempStr=new String();
		String tmpName = new String(name);
        for(int i=0; i<tmpName.length(); i++){
        	tempStr=name.substring(0, 1); 
        	if(StringUtils.isEmpty(tempStr)){
        		break;
        	}
            name=name.substring(1, name.length());
            int orgStringWight=roomName.getFontMetrics().stringWidth(tempStr);
            FontRenderContext frc = roomName.getFontRenderContext();
            TextLayout tl = new TextLayout(tempStr, definedFont, frc);
            Shape sha = tl.getOutline(AffineTransform.getTranslateInstance(oneWenZiX, oneWenZiY));
            roomName.setStroke(new BasicStroke(3.0f));
            roomName.setColor(Color.WHITE);
            roomName.draw(sha);
            roomName.setColor(Color.BLACK);
            roomName.fill(sha);
            oneWenZiX = oneWenZiX + orgStringWight;
        }
        roomName.dispose();
		return qrCodeTemplateImage;
	}
	
	/**
	 * <p>Discription:[模版二二维码生成]</p>
	 * Created on 2018年1月5日
	 * @param name 会议室名称
	 * @param url 二维码内容
	 * @param twoTemplateUrl 模版二图片URL
	 * @return
	 * @throws Exception
	 * @author:[葛伟]
	 */
	public BufferedImage twoQRCodeTemplate(String name, String url, String twoTemplateUrl) throws Exception {
		
		BufferedImage twoTemplate = getBufferedImage(twoTemplateUrl);
		BufferedImage  qrCodeTemplateImage = new BufferedImage(twoImageWidth, twoImageHeight, BufferedImage.TYPE_INT_RGB);
		/** 设置图片的背景色 */
		Graphics2D main = qrCodeTemplateImage.createGraphics();
		main.setColor(Color.WHITE);
		main.fillRect(0, 0, twoImageWidth, twoImageHeight);
		/** 填充模版图片 */
		Graphics mainPic = qrCodeTemplateImage.getGraphics();
		if (twoTemplate != null) {
			mainPic.drawImage(twoTemplate, 0, 0, twoImageWidth, twoImageHeight, null);
			mainPic.dispose();
		}
		/** 二维码填充模版图片 */
		Graphics qrCodePic = qrCodeTemplateImage.getGraphics();
		qrCodePic.setColor(Color.white);
		BufferedImage qrCodeImg = QrCodeUtils.createImage(url, twoQRCodeSize);
		if (qrCodeImg != null) {
			qrCodePic.drawImage(qrCodeImg, twoQRCodeX, twoQRCodeY, twoQRCodeSize, twoQRCodeSize, null);
			qrCodePic.dispose();
		}
		/** 填充会议室名称 */
		Graphics2D roomName = qrCodeTemplateImage.createGraphics();
		roomName.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		roomName.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		roomName.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		if(name.length() <= 2){
		}else if(name.length() == 3){
			twoWenZiSize = 65;
		}else{
			twoWenZiSize = 50;
		}
        definedFont = definedFont.deriveFont(twoWenZiSize);
        definedFont = definedFont.deriveFont(Font.BOLD);
        roomName.setFont(definedFont);
		String tempStr=new String();  
		String tmpName = new String(name);
		twoWenZiX = twoWenZiX - roomName.getFontMetrics().stringWidth(name);
        for(int i=0; i<tmpName.length(); i++){
        	tempStr=name.substring(0, 1); 
        	if(StringUtils.isEmpty(tempStr)){
        		break;
        	}
            name=name.substring(1, name.length());
            double orgStringWight = roomName.getFontMetrics().stringWidth(tempStr);
            FontRenderContext frc = roomName.getFontRenderContext();
            TextLayout tl = new TextLayout(tempStr, definedFont, frc);
            Shape sha = tl.getOutline(AffineTransform.getTranslateInstance(twoWenZiX, twoWenZiY));
            roomName.setStroke(new BasicStroke(3.0f));
            roomName.setColor(Color.WHITE);
            roomName.draw(sha);
            roomName.setColor(Color.BLACK);
            roomName.fill(sha);
            twoWenZiX = twoWenZiX + orgStringWight;
        }
        roomName.dispose();
		return qrCodeTemplateImage;
	}

	/**
	 * <p>Description:[获取图片]</p>
	 * Created on 2018/1/7
	 *
	 * @author 葛伟
	 */
	private static BufferedImage getBufferedImage(String qrCodeUrl) throws Exception {
		//new一个URL对象
		URL url = new URL(qrCodeUrl);
		//打开链接
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		//设置请求方式为"GET"
		conn.setRequestMethod("GET");
		//超时响应时间为5秒
		conn.setConnectTimeout(5 * 1000);
		//通过输入流获取图片数据
		InputStream inStream = conn.getInputStream();
		byte[] data = readInputStream(inStream);
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		return ImageIO.read(in);
	}

	/**
	 * <p>Description:[根据io流获取byte]</p>
	 * Created on 2018/1/7
	 *
	 * @author 葛伟
	 */
	private static byte[] readInputStream(InputStream inStream) throws Exception{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		//创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		//每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len;
		//使用一个输入流从buffer里把数据读取出来
		while( (len = inStream.read(buffer)) != -1 ){
			//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		//关闭输入流
		inStream.close();
		//把outStream里的数据写入内存
		return outStream.toByteArray();
	}
}
