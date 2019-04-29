package com.camelotchina.www.common.constant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>Discription: [常量类] </p>
 * Created on: 2017/10/31 16:54
 * @author [尹归晋]
 */
public interface Const {

	/**
	 * 删除标识位，未删除
	 */
	boolean IS_DELETE_FALSE = false;

	/**
	 * 删除表示为，已删除
	 */
	boolean IS_DELETE_TRUE = true;

	/**
	 * 删除Integer标识位，未删除
	 */
	Integer IS_DELETE_INTEGER_FALSE = 0;

	/**
	 * 删除Integer标识位，已删除
	 */
	Integer IS_DELETE_INTEGER_TRUE = 1;

	/**
	 * 启用状态，禁用
	 */
	boolean IS_ABLE_FALSE = false;

	/**
	 * 启用状态，启用
	 */
	boolean IS_ABLE_TRUE = true;

	/**
	 *  用户信息在redis中保存的时间
	 */
	int TIME_USERINFO_IN_REDIS = 60*60*24*7;


	/**
	 * 分隔符：逗号
	 **/
	String SEPARATOR_COMMA = ",";


	/**
	 * 分隔符：冒号
	 **/
	String SEPARATOR_COLON = ":";

	/**
	 * 分隔符：减号
	 **/
	String SEPARATOR_MINUS = "-";

	/**
	 * 分隔符：下划线
	 **/
	String SEPARATOR_UNDER_LINE = "_";

	/**
	 * 分隔符：空格
	 **/
	String SEPARATOR_SPACE = " ";

	/**
	 * 分隔符：@
	 */
	String SEPARATOR_AT = "@";

	/**
	 * 左括号
	 */
	String SEPARATOR_LEFTBRACES = "(";
	/**
	 * 右括号
	 */
	String SEPARATOR_RIGHTBRACES = ")";

	/** null字符串 */
	String STRING_NULL = "null";

	/** 默认的排序号 **/
	int SORTNUM_DEFAULT = 0;

	/** 树形结构顶级level **/
	Integer TOP_LEVEL = 1;

	/** 树形结构2级level **/
	Integer TWO_LEVEL = 2;

	/** 全局提示 **/
	String GLOBAL_ERROR_INFO = "系统繁忙，请稍后再试！";

	/** 参数校验没有选择文件 **/
	String PARAM_CHECK_FILE_NULL = "请选择文件！";

	/** 参数校验必填参数为空 **/
	String PARAM_CHECK_PARAM_IS_NULL = "必填参数为空！";

	/** excel检查错误 **/
	String FILE_CHECK_EXCEL_ERROR = "文件类型必须是Excel！";

	/** 集合条数限制 20 **/
	int LIST_LIMIT = 10;

    /** 图片检查错误 **/
    String FILE_CHECK_IMG_ERROR = "文件类型必须是图片类型！";

	/**
	 * 布尔值的字符串值yes
	 */
	String BOOLEAN_DES_YES = "yes";

	/**
	 * 布尔值的字符串值no
	 */
	String BOOLEAN_DES_NO = "no";

	/**
	 * 布尔值true
	 */
	boolean BOOLEAN_TRUE = true;

	/**
	 * 布尔值false
	 */
	boolean BOOLEAN_FALSE = false;


	String DESC = "DESC";

	String ASC = "ASC";

	/**
	 * 图片删除
	 */
	String IMG_DELETE = "delete";

}

