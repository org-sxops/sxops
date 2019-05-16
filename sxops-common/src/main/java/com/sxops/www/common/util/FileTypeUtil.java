package com.sxops.www.common.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Discription: 获取和判断文件头信息
 *    |--文件头是位于文件开头的一段承担一定任务的数据，一般都在开头的部分。
 *    |--头文件作为一种包含功能函数、数据接口声明的载体文件，用于保存程序的声明(declaration),而定义文件用于保存程序的实现(implementation)。
 *    |--为了解决在用户上传文件的时候在服务器端判断文件类型的问题，故用获取文件头的方式，直接读取文件的前几个字节，来判断上传文件是否符合格式。
 * Created on: 2017/11/15 10:02
 * @author [葛伟]
 */
public class FileTypeUtil {

	private static final String [] FILE_TYPE_EXCEL = {"xls","xlsx"};

	/**
	 * <p>Discription: [判断是excel] </p>
	 * Created on: 2017/11/15 10:37
	 * @param fileName 文件名
	 * @return Boolean true 是 false否
	 * @author [葛伟]
	 */
	public static Boolean isExcel(String fileName) throws IOException {
		String extension = FilenameUtils.getExtension(fileName);
		return contain(FILE_TYPE_EXCEL,extension);
	}

	/**
	 * <p>Discription: [判断文件拓展名是否在指定格式里] </p>
	 * Created on: 2017/11/15 12:32
	 * @param targetExtnsion 指定格式
	 * @param extension 文件拓展名
	 * @return
	 * @author [葛伟]
	 */
	private static Boolean contain(String[] targetExtnsion, String extension){
		for (String item : targetExtnsion) {
			if(item.equals(extension)){
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>Description:[验证后缀名是否是zip格式]</p>
	 * Created on 2017年11月13日
	 *
	 * @param fileName 文件名
	 * @author 葛伟
	 */
	public static boolean isZip(String fileName) {
		if (org.apache.commons.lang3.StringUtils.isBlank(fileName)) {
			return false;
		}
		// zip格式
		final String FILE_TYPE_ZIP = "zip";
		// 获取文件后缀
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		return FILE_TYPE_ZIP.equals(suffix);
	}

	/**
	 * <p>Description:[验证附件是否是图片格式]</p>
	 * Created on 2017年11月13日
	 *
	 * @param file 附件
	 * @author 葛伟
	 */
	public static boolean isImg(MultipartFile file) throws IOException {
		if (StringUtils.isBlank(file.getOriginalFilename())) {
			return false;
		}
		// 获取文件后缀
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		// 图片文件后缀名
		String[] fileType = {"jpg", "png", "gif", "bmp", "jpeg"};
		List<String> list = Arrays.asList(fileType);
		if (!list.contains(suffix.toLowerCase())) {
			return false;
		}
		/*//根据图片内容判断是否为图片文件
		InputStream inputStream = file.getInputStream();
		BufferedImage bi = ImageIO.read(inputStream);
		return (bi != null);*/
		return true;
	}

}