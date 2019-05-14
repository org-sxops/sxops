package com.sxops.www.linfen.common.util.excel;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.ClassUtils;
import org.springframework.web.util.HtmlUtils;

public class ExcelExportUtils {
	
	/**
	 * 多线程会并发生成临时文件的文件名，所以用同一对象加锁控制
	 */
	final static String LOCKOBJECT = "lockObject"; 
	/**
	 * 每次查询并生成一个临时excel文件的行数
	 */
	public final static Integer EXCELSIZE = 30000;
	/**
	 * 工作空间下的项目名称
	 */
	final static String PROJECTNAME = "didi-visitor";
	
	/**
	 * 此属性值作为单文件下载和多文件打包下载的一个标准：即要下载的总数据条数大于此值，则进行多文件打包下载；要是下载的总数据条数小于此值，则进行单文件下载。
	 */
	final static Integer COUNT = 250000;
	
	/**
	 * <p>Discription:[生成一个UUID]</p>
	 * Created on 2018年1月18日 下午7:54:11
	 * @return String 生成的UUID
	 * @author:[全冉]
	 */
	private static String getUUID() { 
		String uuid = UUID.randomUUID().toString(); 
		//去掉“-”符号 
		return uuid.replaceAll("-", "");
	}
	
	/**
	 * <p>Discription:[不同的系统下，每次操作都生成一个临时的文件夹]</p>
	 * Created on 2018年1月18日 下午7:56:56
	 * @return String 返回临时文件夹的路径
	 * @author:[全冉]
	 */
	public static String getTemExcelDirectory() {
		String path = ""; // 临时文件夹路径
    	String sep = File.separator; // 平台共用路径间隔符
    	String linuxTemExcelDirectory = sep+"usr"+sep+"local"; // linux系统临时excel的目录
    	String pathSuffix = "temExcelFolder" + sep + getUUID();
    	
		String osName = System.getProperty("os.name");
		if (osName.indexOf("Windows") >= 0 || osName.indexOf("window") >= 0 ) {
			//windows系统走此
			String sourcePath = ClassUtils.getDefaultClassLoader().getResource("").getPath().substring(1);
			String[] sourcePathArray = sourcePath.split("/");
			for (int i=0;i<sourcePathArray.length;i++) {
				if (PROJECTNAME.equals(sourcePathArray[i])) {
					path += pathSuffix;
					break;
				}
				path += sourcePathArray[i]+sep;
			}
		} else {
			//linux系统走此
			path = linuxTemExcelDirectory + sep + pathSuffix;
		}
		return path;
	}
	
	/**
	 * <p>Discription:[每个线程都调此方法将数据导入到临时文件夹里一个名为"temExcelFile_X"的excel文件里]</p>
	 * Created on 2018年1月19日 上午10:45:53
	 * @param fileName 文件名称
	 * @param path 存临时excel文件的文件夹路径
	 * @param recordList 要导入临时excel的数据
	 * @author:[全冉]
	 */
	public static void writeExcelToTemDir(String fileName, String path, List<List<String>> recordList) {
		BufferedWriter buff = null;
		try {
			// 创建临时excel文件时，需要生成不同的名字，这块代码可能并发执行，有可能存在多个线程同时操作同一个excel文件，所以加锁
			synchronized (LOCKOBJECT) {
				// 临时文件夹路径不存在就创建
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();
				}
				// 临时文件夹下所有文件名字组成的字符串数组
				String[] children = file.list();
				String filePath = path + File.separator + fileName + "_" + (children.length+1) + ".xls";
				System.out.println("文件名为：【"+fileName + "_" + (children.length+1) + ".xls"+"】");
				OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(filePath),"GBK");
				// 生成文件
				buff = new BufferedWriter(ow);
			}
			
			// 临时excel文件里的每一行数据
			List<String> currentRecord = new ArrayList<String>();
			StringBuffer currentSb = null;
			for (Integer j = 0; j < recordList.size(); j ++) {
				currentRecord = recordList.get(j);
				currentSb = new StringBuffer();
				// 循环，将一行数据的每个列都追加到一个可变字符串上
				for (int m = 0; m < currentRecord.size(); m ++) {
					if (m == currentRecord.size()-1) {
						currentSb.append(currentRecord.get(m));
					} else {
						currentSb.append(currentRecord.get(m)).append("\t");
					}
				}
				// 往临时excel里写入当前行的数据
				buff.write(currentSb.toString());
				// 往临时excel里写入换行
				buff.write("\r\n");
			}
			buff.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (buff != null) {
					buff.close();
					buff = null;
				}
				// 召唤jvm的垃圾回收器
				System.gc();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * <p>Discription:[将临时文件从临时文件下载到本地]</p>
	 * Created on 2018年1月29日 下午6:58:18
	 * @param fileName 下载的文件名称
	 * @param response response对象
	 * @param path 存储临时excel的临时文件夹路径
	 * @param num 临时的excle文件个数
	 * @param excelSize 临时excel文件的行数
	 * @param headList excel表头
	 * @author:[全冉]
	 */
	public static void downloadTemExcel(String fileName, HttpServletResponse response, String path, Integer num, Integer excelSize, List<String> headList) {
		File file = new File(path);
		if (file.isDirectory()) {
			String[] children = file.list();
			// 判断是单文件下载还是多文件打包下载
			Integer allRecordCount = excelSize*children.length;
			if (allRecordCount <= COUNT) {
				// 单文件下载(下载到本地是一个excel文件)
				singleFileDownload(fileName, path, children, response, headList);
			}
			if (allRecordCount > COUNT) {
				// 多文件打包下载
				multiFileDownload(fileName, path, children, allRecordCount, COUNT, response, headList);
			}
		}
	}
	
	/**
	 * <p>Discription:[单文件下载]</p>
	 * Created on 2018年1月29日 下午7:12:34
	 * @param fileName 下载的文件名称
	 * @param path 存储临时excel的临时文件夹路径
	 * @param children path路径下的所有临时excel的名字拼成的字符串数组
	 * @param response response对象
	 * @param headList excel表头
	 * @author:[全冉]
	 */
	private static void singleFileDownload(String fileName, String path, String[] children, HttpServletResponse response, List<String> headList) {
		InputStream fis = null;
    	OutputStream os = null;
    	File outfile = null;
    	byte[] buffer = null;
		try {
			// 生成表头
			StringBuffer headSb = new StringBuffer();
			for (int i = 0; i < headList.size(); i ++) {
				if (i == headList.size()-1) {
					headSb.append(headList.get(i)).append("\r\n");
				} else {
					headSb.append(headList.get(i)).append("\t");
				}
			}
			byte[] headBuffer = headSb.toString().getBytes("GBK");
			
			// 将表头的字节长度也加进到下载的文件字节长度里
			long countLength = headBuffer.length;
			for (int i = 0; i < children.length; i ++) {
				outfile = new File(path, children[i]);
				countLength += outfile.length();
			}
			
			// 设置response对象，获取response的输出流
			response.reset(); //重置结果集
    	    response.addHeader("Content-Disposition", "attachment;filename=" + new String((fileName+".xls").getBytes("utf-8"), "iso8859-1")); //返回头 文件名
    	    response.addHeader("Content-Length", "" + countLength); //返回头 文件大小
    	    response.setContentType("application/octet-stream");  //设置数据种类
    	    os = new BufferedOutputStream(response.getOutputStream());
    	    
    	    // 将表头插入到excel中
    	    fis = new BufferedInputStream(new ByteArrayInputStream(headBuffer));
    	    fis.read(headBuffer); //读取文件流
    	    os.write(headBuffer); // 输出文件
    	    os.flush();
    	    
    	    // 将每一个临时excel都导出
			for (int i = 0; i < children.length; i ++) {
				outfile = new File(path, children[i]);
				fis = new BufferedInputStream(new FileInputStream(outfile));
				buffer = new byte[fis.available()]; 
	    	    fis.read(buffer); //读取文件流
	    	    os.write(buffer); // 输出文件
	    	    os.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
					os = null;
				}
				if (fis != null) {
					fis.close();
					fis = null;
				}
				// 召唤jvm的垃圾回收器
				System.gc();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * <p>Discription:[多个文件，打包下载]</p>
	 * Created on 2018年1月29日 下午7:26:21
	 * @param zipName 压缩包名称
	 * @param path 存储临时excel的临时文件夹路径
	 * @param children path路径下的所有临时excel的名字拼成的字符串数组
	 * @param allRecordCount 所有临时excel文件的行数之和
	 * @param count 下载到客户端的excel最多能装的记录条数
	 * @param response response对象
	 * @param headList excel表头
	 * @author:[全冉]
	 */
	private static void multiFileDownload(String zipName, String path, String[] children, Integer allRecordCount,
                                          Integer count, HttpServletResponse response, List<String> headList) {
		// 生成表头
		StringBuffer headSb = new StringBuffer();
		for (int i = 0; i < headList.size(); i ++) {
			if (i == headList.size()-1) {
				headSb.append(headList.get(i)).append("\r\n");
			} else {
				headSb.append(headList.get(i)).append("\t");
			}
		}
		
		// 计算下载到客户端的zip包里会有多少个excel文件
		Integer excelNum = allRecordCount/count;
		if (allRecordCount%count != 0) {
			excelNum++; 
		}
		// 临时文件里多少个excel生成一个zip包里的excel
		Integer temNum = children.length/excelNum;
		
		// beforeList的值为往压缩包里放入新文件的依据；afterList的值为压缩包里关闭一个新文件流的依据
		List<Integer> beforeList = new ArrayList<Integer>();
		List<Integer> afterList = new ArrayList<Integer>();
		for (int i = 0; i < children.length; i ++) {
			if (i%temNum == 0) {
				beforeList.add(i);
			}
			if (i != 0 && i%temNum == 0) {
				afterList.add(i-1);
			}
			if (i == children.length-1) {
				afterList.add(i);
			}
		}
		
        ZipOutputStream zipos = null; 
        DataOutputStream os = null; 
        InputStream is = null;
        try {
        	// 解决不同浏览器压缩包名字含有中文时乱码的问题 
        	response.setContentType("APPLICATION/OCTET-STREAM"); 
            response.setHeader("Content-Disposition", "attachment; filename=" + new String((zipName+".zip").getBytes("utf-8"), "iso8859-1")); 
            // 设置压缩流：直接写入response，实现边压缩边下载 
        	zipos = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream())); 
        	// 设置压缩方式
        	zipos.setMethod(ZipOutputStream.DEFLATED);
        	// 压缩包里多个文件的名字下标
        	Integer nameIndex = 1;
	        // 循环将文件写入压缩流 
	        for (int i = 0; i < children.length; i ++) {
	        	// 添加ZipEntry对象到压缩流中
	        	if (beforeList.contains(i)) {
	        		zipos.putNextEntry(new ZipEntry(zipName+"_"+nameIndex+".xls"));
	        		nameIndex++;
	        		
	        		// 表头输入到文件中
	        		os = new DataOutputStream(zipos); 
	        		is = new BufferedInputStream(new ByteArrayInputStream(headSb.toString().getBytes("GBK")));
	        		byte[] b = new byte[100]; 
	        		int length = 0; 
					while ((length = is.read(b)) != -1) {
						os.write(b, 0, length); 
					}
					is.close();
					is = null;
	        	}
	        	// 生成当前File对象
	        	File file = new File(path, children[i]);
				// 将压缩流写入文件流 
				os = new DataOutputStream(zipos); 
				is = new FileInputStream(file); 
				byte[] b = new byte[100]; 
				int length = 0; 
				while ((length = is.read(b)) != -1) {
					os.write(b, 0, length); 
				} 
				is.close();
				is = null;
				// 关闭当前Entry对象的压缩流
				if (afterList.contains(i)) {
					zipos.closeEntry(); 
				}
	        }
	        os.flush(); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } finally {
        	try { 
        		if (is != null) {
        			is.close();
        			is = null;
        		}
        		if (os != null) {
                    os.close(); 
                    os = null;
        		}
        		if (zipos != null) {
        			zipos.close();
        			zipos = null;
        		}
        		// 召唤jvm的垃圾回收器
        		System.gc();
        	} catch (IOException e) { 
                e.printStackTrace(); 
        	}  
        }
    }
	
	/**
	 * <p>Discription:[实体类装换为字符串List集合]</p>
	 * Created on 2018年1月19日 下午1:59:50
	 * @param obj Objec的子类
	 * @param propertyNames 多个属性
	 * @return List<String> 返回的一行excel数据,均为String类型
	 * @author:[全冉]
	 */
    public static List<String> changList(Object obj, String... propertyNames) {
        List<String> list = new ArrayList<String>();
        String value = "";
        Object object = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(String propertyName : propertyNames){
            try {
	            object = Reflections.invokeGetter(obj, propertyName);
	            if (object == null ) {
	            	//value = "" ; nothing to do
	            } else if(object instanceof Date) {
	            	value = sdf.format((Date) object);
	            } else if(object instanceof String) {
	            	value = HtmlUtils.htmlUnescape(object.toString());
	            } else {
	            	value = object.toString();
	            }
            } catch (Exception e) {
            	throw new RuntimeException(e.getClass().getName()+":"+e.getMessage());
            }
            list.add(value);
            object = null;
            value = "";
        }
        return list;
    }
    
    /**
	 * <p>Discription:[递归删除目录下的所有子子孙孙文件和文件件，最后再删除当前空目录]</p>
	 * Created on 2018年1月17日 下午6:01:30
	 * @param dir 将要删除的文件目录
	 * @return Boolean true:如果所有删除成功，返回true
	 *                 false:如果某一个删除失败，后续的不在删除，并且返回false
	 * @author:[全冉]
	 */
	public static Boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            // 递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
