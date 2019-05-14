package com.sxops.linfen.common.util.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

/** 
 * <p>Description: []</p>
 * Created on 2016年11月3日
 * @author  <a href="mailto: liuchao@sxops.com">刘超</a>
 * @version 1.0 
 * Copyright (c) 2016 山西省壹加柒网络技术有限公司 交付部
 */ 
public class ExcelExport {

	// 常量，基础输出路径
	static final String BASE_PATH = "";
	// 常量，excel一个sheet最多的行数
	static final int MAX_ROW = 65536;

	// 保存excel workbook对象
	private HSSFWorkbook mbook = null;
	// 保存excel worksheet对象
	private HSSFSheet msheet = null;
	// 用户设定文件名
	private String mfile = null;
	// 文件输出流
	private OutputStream mstreamOut = null;
	// excel cells风格 居中对齐
	private HSSFCellStyle centerStyle = null;
	// excel cells风格 左对齐
	private HSSFCellStyle leftStyle = null;
	// excel cells风格 右对齐
	private HSSFCellStyle rightStyle = null;
	// 当前在excel中输出的行数
	private int mcIndex;
	// 实际输出文件地址
	private String mrealPath = null;
	// 当前在excel中输出的sheet页序列数
	private int msIndex;
	// 保存的excel表头字段，用于数据太多新建表的时候使用
	private List<String> mkeys = null;
	// 保存的excel表头样式
	private List<HSSFCellStyle> styles = null;
//	// 合并单元格的开始行
//	private int togetherRow;
	//表头样式-居中对齐
	public static final Integer ALIGN_CENTER = 0;
	//表头样式-右对齐
	public static final Integer ALIGN_RIGHT = 1;
	//表头样式-左对齐
	public static final Integer ALIGN_LEFT = 2;
	
	/**
	 * @Description: (创建excel文件对象)
	 * @param  fileName 文件名，传全路径
	 * @param sheetName 输出excel sheet的名称，传“”时为数据导出
	 * @return boolean 是否成功
	 */
	public boolean ceateExcel(String fileName, String sheetName) {
		if (fileName.isEmpty()) {
			return false;
		}
		try {
			mcIndex = 1;
			msIndex = 0;
			//togetherRow = 0 ;
			mbook = new HSSFWorkbook();
			msheet = mbook.createSheet(sheetName.isEmpty() ? "数据导出"
					: sheetName);
			mfile = fileName;
			mrealPath = BASE_PATH + mfile;
			mrealPath = mrealPath.replace("//", "/");
			mstreamOut = new FileOutputStream(mrealPath);
			centerStyle = mbook.createCellStyle();
			centerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			leftStyle = mbook.createCellStyle();
			leftStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			rightStyle = mbook.createCellStyle();
			rightStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * @Description: (创建excel文件对象)
	 * @param out 输出流
	 * @param  sheetName 输出excel sheet的名称，传“”时为数据导出
	 * @return boolean 是否成功
	 */
	public boolean ceateExcel(OutputStream out, String sheetName) {
		if (out==null) {
			return false;
		}
		try {
			mcIndex = 1;
			msIndex = 0;
			//togetherRow = 0 ;
			mbook = new HSSFWorkbook();
			mstreamOut = out;
			msheet = mbook.createSheet(sheetName.isEmpty() ? "数据导出"
					: sheetName);
			centerStyle = mbook.createCellStyle();
			centerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			leftStyle = mbook.createCellStyle();
			leftStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			rightStyle = mbook.createCellStyle();
			rightStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * <p>Discription:[新建一个sheet页]</p>
	 * Created on 2017年12月7日 下午3:18:08
	 * @param sheetName sheet名称
	 * @return boolean 返回true表明创建成功
	 * @author:[全冉]
	 */
	public boolean createSheet(String sheetName) {
		if (StringUtils.isEmpty(sheetName)) {
			++ msIndex;
			sheetName += "sheet" + ++ msIndex;
		}
		try {
			msheet = mbook.createSheet(sheetName);
			mcIndex = 1;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}
	
	/**
	 * @Description: TODO(完成对excel的编辑，并输出文件)
	 * @return String 返回文件路径 - 之前输入的路径
	 */
	public String endEdit() {
		try {
			//自动调整宽度
			if (null != mkeys) {
				for(int i = 0 ; i < mkeys.size() ; i ++ ){
					msheet.autoSizeColumn((short)i);
					msheet.setColumnWidth(i, mkeys.get(i).getBytes("utf-8").length*256);
				}
			}
			mbook.write(mstreamOut);
			if(mstreamOut instanceof ServletOutputStream){
				mstreamOut.flush();
			}else{
				mstreamOut.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mrealPath;
	}

	/**
	 * @Description: (添加excel表头)
	 * @param  keys 定义excel表头数据，注意需要跟后面传输的数据类里面属性顺序一致
	 * @return boolean 是否成功
	 */
	public boolean addHead(List<String> keys) {
		mkeys = keys;
		HSSFRow row = msheet.createRow(0);
		int index = 0;
		for (String key : keys) {
			row.createCell(index).setCellValue(" "+key+" ");
			row.getCell(index++).setCellStyle(centerStyle);
		}
		return true;
	}
	
	/**
	 * @Description: TODO(添加excel表头)
	 * @param keys 定义excel表头数据，注意需要跟后面传输的数据类里面属性顺序一致
	 * @param style 对应列对其方式 ,参考:ALIGN_RIGHT,ALIGN_LEFT,ALIGN_CENTER
	 * @return boolean 是否成功
	 */
	public boolean addHead(List<String> keys,List<Integer> style) {
		//添加样式
		this.styles = new ArrayList<HSSFCellStyle>();
		for (Integer integer : style) {
			switch (integer) {
			case 0:
				this.styles.add(centerStyle);
				break;
			case 1:
				this.styles.add(rightStyle);
				break;
			case 2:
				this.styles.add(leftStyle);
				break;
			default:
				this.styles.add(centerStyle);
				break;
			}
		}
		//添加表头字段
		mkeys = keys;
		HSSFRow row = msheet.createRow(0);
		int index = 0;
		for (String key : keys) {
			row.createCell(index).setCellValue(" "+key+" ");
			row.getCell(index++).setCellStyle(centerStyle);
		}
		return true;
	}
	
	/**
	 * <p>Discription:[融合单元个]</p>
	 * Created on 2016年11月3日
	 * @param rowStartNum  行开始
	 * @param rowEndNum    行结束
	 * @param colStartNum  列开始
	 * @param colEndNum    列结束
	 * @return boolean 返回值
	 * @author:[刘超]
	 */
	public boolean merge(int rowStartNum,int rowEndNum,int colStartNum,int colEndNum){
		msheet.addMergedRegion(new CellRangeAddress(rowStartNum,rowEndNum,colStartNum,colEndNum)) ;
		return true;
	}

	/**
	 * @Description: TODO(当sheet页超过最大限制时，创建新sheet页)
	 */
	private void createNewSheet() {
		String sName = msheet.getSheetName();
		sName = String.format("%s_%d", sName.indexOf("_")!=-1?sName.substring(0, sName.indexOf("_")):sName, ++msIndex);
		msheet = mbook.createSheet(sName);
		mcIndex = 1;
		addHead(mkeys);
	}
	
	/**
	 * @Description: TODO(添加一项数据)
	 * @param	datas 数据列表
	 * @return boolean 添加是否成功
	 */	
	public boolean addList(List<String> datas) {
		if (datas == null) {
			return false;
		}
		int index = 0;
		if (mcIndex >= MAX_ROW) {
			createNewSheet();
		}
		HSSFRow row = msheet.createRow(mcIndex++);
		if(this.styles == null){//判断样式是否为空,如果为空,则所以cell居中
			for (String data : datas) {
				data = data == null ? " ":" " + data + " ";
				row.createCell(index).setCellValue(data);
				row.getCell(index++).setCellStyle(centerStyle);
			}
		}else{
			int s_index = 0;
			for (String data : datas) {
				data = data == null ? " ":" " + data + " ";
				row.createCell(index).setCellValue(data);
				row.getCell(index++).setCellStyle(this.styles.get(s_index));
				s_index++;
			}
		}
		return true;
	}

//	public static void main(String[] args) throws Exception{
//		ExcelExport excelExport = new ExcelExport();
//		excelExport.ceateExcel("d://user.xls", "用户信息");
//		excelExport.addHead(Arrays.asList(new String[]{"id","userName"}));
//		for(int i = 0 ; i < 3000000 ; i ++){
//			excelExport.addList(Arrays.asList("名称"+i,i));
//		}
//		excelExport.endEdit();
//	}

}
