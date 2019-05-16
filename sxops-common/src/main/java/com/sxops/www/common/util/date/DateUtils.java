package com.sxops.www.common.util.date;

import com.sxops.www.common.constant.Const;

import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description : 日期工具类
 * Author : miaozh
 * CreateTime : 2016年10月8日 下午4:38:19
 */
public class DateUtils {

	private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

	private static final Object object = new Object();

	/**
	 * 获取SimpleDateFormat
	 * @param pattern 日期格式
	 * @return SimpleDateFormat对象
	 * @throws RuntimeException 异常：非法日期格式
	 */
	public static SimpleDateFormat getDateFormat(String pattern)
			throws RuntimeException {
		SimpleDateFormat dateFormat = threadLocal.get();
		if (dateFormat == null) {
			synchronized (object) {
				if (dateFormat == null) {
					dateFormat = new SimpleDateFormat(pattern);
					dateFormat.setLenient(false);
					threadLocal.set(dateFormat);
				}
			}
		}
		dateFormat.applyPattern(pattern);
		return dateFormat;
	}

	/**
	 * 获取日期中的某数值。如获取月份
	 * @param date 日期
	 * @param dateType 日期格式
	 * @return 数值
	 */
	private static int getInteger(Date date, int dateType) {
		int num = 0;
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
			num = calendar.get(dateType);
		}
		return num;
	}

	/**
	 * 增加日期中某类型的某数值。如增加日期
	 * @param date 日期字符串
	 * @param dateType 类型
	 * @param amount 数值
	 * @return 计算后日期字符串
	 */
	private static String addInteger(String date, int dateType, int amount) {
		String dateString = null;
		DateStyle dateStyle = getDateStyle(date);
		if (dateStyle != null) {
			Date myDate = StringToDate(date, dateStyle);
			myDate = addInteger(myDate, dateType, amount);
			dateString = DateToString(myDate, dateStyle);
		}
		return dateString;
	}

	/**
	 * 增加日期中某类型的某数值。如增加日期
	 * @param date 日期
	 * @param dateType 类型
	 * @param amount 数值
	 * @return 计算后日期
	 */
	private static Date addInteger(Date date, int dateType, int amount) {
		Date myDate = null;
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(dateType, amount);
			myDate = calendar.getTime();
		}
		return myDate;
	}

	/**
	 * 获取精确的日期
	 * @param timestamps 时间long集合
	 * @return 日期
	 */
	private static Date getAccurateDate(List<Long> timestamps) {
		Date date = null;
		long timestamp = 0;
		Map<Long, long[]> map = new HashMap<Long, long[]>();
		List<Long> absoluteValues = new ArrayList<Long>();

		if (timestamps != null && timestamps.size() > 0) {
			if (timestamps.size() > 1) {
				for (int i = 0; i < timestamps.size(); i++) {
					for (int j = i + 1; j < timestamps.size(); j++) {
						long absoluteValue = Math.abs(timestamps.get(i)
								- timestamps.get(j));
						absoluteValues.add(absoluteValue);
						long[] timestampTmp = { timestamps.get(i),
								timestamps.get(j) };
						map.put(absoluteValue, timestampTmp);
					}
				}

				// 有可能有相等的情况。如2012-11和2012-11-01。时间戳是相等的。此时minAbsoluteValue为0
				// 因此不能将minAbsoluteValue取默认值0
				long minAbsoluteValue = -1;
				if (!absoluteValues.isEmpty()) {
					minAbsoluteValue = absoluteValues.get(0);
					for (int i = 1; i < absoluteValues.size(); i++) {
						if (minAbsoluteValue > absoluteValues.get(i)) {
							minAbsoluteValue = absoluteValues.get(i);
						}
					}
				}

				if (minAbsoluteValue != -1) {
					long[] timestampsLastTmp = map.get(minAbsoluteValue);

					long dateOne = timestampsLastTmp[0];
					long dateTwo = timestampsLastTmp[1];
					if (absoluteValues.size() > 1) {
						timestamp = Math.abs(dateOne) > Math.abs(dateTwo) ? dateOne
								: dateTwo;
					}
				}
			} else {
				timestamp = timestamps.get(0);
			}
		}

		if (timestamp != 0) {
			date = new Date(timestamp);
		}
		return date;
	}

	/**
	 * 根据开始时间和结束时间获取间隔的时间（单位/小时）
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static Double getIntervalHours(String startTime, String endTime){
		BigDecimal startTimeStamp = BigDecimal.valueOf(StringToDate(startTime, DateStyle.HH_MM_SS).getTime());
		BigDecimal endTimeStamp = BigDecimal.valueOf(StringToDate(endTime, DateStyle.HH_MM_SS).getTime());
		BigDecimal hours = endTimeStamp.subtract(startTimeStamp).divide(new BigDecimal(1000*60*60), 2, BigDecimal.ROUND_DOWN);
		return hours.doubleValue();
	}

	/**
	 * 判断字符串是否为日期字符串
	 * @param date 日期字符串
	 * @return true or false
	 */
	public static boolean isDate(String date) {
		boolean isDate = false;
		if (date != null) {
			if (getDateStyle(date) != null) {
				isDate = true;
			}
		}
		return isDate;
	}

	/**
	 * 获取日期字符串的日期风格。失敗返回null。
	 * @param date 日期字符串
	 * @return 日期风格
	 */
	public static DateStyle getDateStyle(String date) {
		DateStyle dateStyle = null;
		Map<Long, DateStyle> map = new HashMap<Long, DateStyle>();
		List<Long> timestamps = new ArrayList<Long>();
		for (DateStyle style : DateStyle.values()) {
			if (style.isShowOnly()) {
				continue;
			}
			Date dateTmp = null;
			if (date != null) {
				try {
					ParsePosition pos = new ParsePosition(0);
					dateTmp = getDateFormat(style.getValue()).parse(date, pos);
					if (pos.getIndex() != date.length()) {
						dateTmp = null;
					}
				} catch (Exception e) {
				}
			}
			if (dateTmp != null) {
				timestamps.add(dateTmp.getTime());
				map.put(dateTmp.getTime(), style);
			}
		}
		Date accurateDate = getAccurateDate(timestamps);
		if (accurateDate != null) {
			dateStyle = map.get(accurateDate.getTime());
		}
		return dateStyle;
	}

	/**
	 * 将日期字符串转化为日期。失败返回null。
	 * @param date 日期字符串
	 * @return 日期
	 */
	public static Date StringToDate(String date) {
		DateStyle dateStyle = getDateStyle(date);
		return StringToDate(date, dateStyle);
	}

	/**
	 * 将日期字符串转化为日期。失败返回null。
	 * @param date 日期字符串
	 * @param pattern 日期格式
	 * @return 日期
	 */
	public static Date StringToDate(String date, String pattern) {
		Date myDate = null;
		if (date != null) {
			try {
				myDate = getDateFormat(pattern).parse(date);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return myDate;
	}


	/**
	 * 将日期字符串转化为日期。失败返回null。
	 * @param date 日期字符串
	 * @param dateStyle 日期风格
	 * @return 日期
	 */
	public static Date StringToDate(String date, DateStyle dateStyle) {
		Date myDate = null;
		if (dateStyle != null) {
			myDate = StringToDate(date, dateStyle.getValue());
		}
		return myDate;
	}

	/**
	 * 将日期转化为日期字符串。失败返回null。
	 * @param date 日期
	 * @param pattern 日期格式
	 * @return 日期字符串
	 */
	public static String DateToString(Date date, String pattern) {
		String dateString = null;
		if (date != null) {
			try {
				dateString = getDateFormat(pattern).format(date);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dateString;
	}

	/**
	 * 将日期转化为日期字符串。失败返回null。
	 * @param date 日期
	 * @param dateStyle 日期风格
	 * @return 日期字符串
	 */
	public static String DateToString(Date date, DateStyle dateStyle) {
		String dateString = null;
		if (dateStyle != null) {
			dateString = DateToString(date, dateStyle.getValue());
		}
		return dateString;
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * @param date 旧日期字符串
	 * @param newPattern 新日期格式
	 * @return 新日期字符串
	 */
	public static String StringToString(String date, String newPattern) {
		DateStyle oldDateStyle = getDateStyle(date);
		return StringToString(date, oldDateStyle, newPattern);
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * @param date 旧日期字符串
	 * @param newDateStyle 新日期风格
	 * @return 新日期字符串
	 */
	public static String StringToString(String date, DateStyle newDateStyle) {
		DateStyle oldDateStyle = getDateStyle(date);
		return StringToString(date, oldDateStyle, newDateStyle);
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * @param date 旧日期字符串
	 * @param olddPattern 旧日期格式
	 * @param newPattern 新日期格式
	 * @return 新日期字符串
	 */
	public static String StringToString(String date, String olddPattern,
			String newPattern) {
		return DateToString(StringToDate(date, olddPattern), newPattern);
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * @param date 旧日期字符串
	 * @param olddDteStyle 旧日期风格
	 * @param newParttern 新日期格式
	 * @return 新日期字符串
	 */
	public static String StringToString(String date, DateStyle olddDteStyle,
			String newParttern) {
		String dateString = null;
		if (olddDteStyle != null) {
			dateString = StringToString(date, olddDteStyle.getValue(),
					newParttern);
		}
		return dateString;
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * @param date 旧日期字符串
	 * @param olddPattern 旧日期格式
	 * @param newDateStyle 新日期风格
	 * @return 新日期字符串
	 */
	public static String StringToString(String date, String olddPattern,
			DateStyle newDateStyle) {
		String dateString = null;
		if (newDateStyle != null) {
			dateString = StringToString(date, olddPattern,
					newDateStyle.getValue());
		}
		return dateString;
	}

	/**
	 * 将日期字符串转化为另一日期字符串。失败返回null。
	 * @param date 旧日期字符串
	 * @param olddDteStyle 旧日期风格
	 * @param newDateStyle 新日期风格
	 * @return 新日期字符串
	 */
	public static String StringToString(String date, DateStyle olddDteStyle,
			DateStyle newDateStyle) {
		String dateString = null;
		if (olddDteStyle != null && newDateStyle != null) {
			dateString = StringToString(date, olddDteStyle.getValue(),
					newDateStyle.getValue());
		}
		return dateString;
	}

    /**
     * 格式化时间
     * @param date
     * @param dateStyle
     * @return 时间
     */
	public static Date dateForMat(Date date, DateStyle dateStyle){
		return StringToDate(DateToString(date, dateStyle),dateStyle);
	}

	/**
	 * 增加日期的年份。失败返回null。
	 * @param date 日期
	 * @param yearAmount 增加数量。可为负数
	 * @return 增加年份后的日期字符串
	 */
	public static String addYear(String date, int yearAmount) {
		return addInteger(date, Calendar.YEAR, yearAmount);
	}

	/**
	 * 增加日期的值。失败返回null。
	 * @param date 日期
	 * @param type 添加的类型 年、月、日、时、分、秒
	 * @param amount 增加数量
	 * @return
	 */
	public static Date add (Date date,int type ,int amount) {
		return addInteger(date, type , amount);
	}

	/**
	 * 增加日期的年份。失败返回null。
	 * @param date 日期
	 * @param yearAmount 增加数量。可为负数
	 * @return 增加年份后的日期
	 */
	public static Date addYear(Date date, int yearAmount) {
		return addInteger(date, Calendar.YEAR, yearAmount);
	}

	/**
	 * 增加日期的月份。失败返回null。
	 * @param date 日期
	 * @param monthAmount 增加数量。可为负数
	 * @return 增加月份后的日期字符串
	 */
	public static String addMonth(String date, int monthAmount) {
		return addInteger(date, Calendar.MONTH, monthAmount);
	}

	/**
	 * 增加日期的月份。失败返回null。
	 * @param date 日期
	 * @param monthAmount 增加数量。可为负数
	 * @return 增加月份后的日期
	 */
	public static Date addMonth(Date date, int monthAmount) {
		return addInteger(date, Calendar.MONTH, monthAmount);
	}

	/**
	 * 增加日期的天数。失败返回null。
	 * @param date 日期字符串
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加天数后的日期字符串
	 */
	public static String addDay(String date, int dayAmount) {
		return addInteger(date, Calendar.DATE, dayAmount);
	}

	/**
	 * 增加日期的天数。失败返回null。
	 * @param date 日期
	 * @param dayAmount 增加数量。可为负数
	 * @return 增加天数后的日期
	 */
	public static Date addDay(Date date, int dayAmount) {
		return addInteger(date, Calendar.DATE, dayAmount);
	}

	/**
	 * 增加日期的小时。失败返回null。
	 * @param date 日期字符串
	 * @param hourAmount 增加数量。可为负数
	 * @return 增加小时后的日期字符串
	 */
	public static String addHour(String date, int hourAmount) {
		return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
	}

	/**
	 * 增加日期的小时。失败返回null。
	 * @param date 日期
	 * @param hourAmount 增加数量。可为负数
	 * @return 增加小时后的日期
	 */
	public static Date addHour(Date date, int hourAmount) {
		return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
	}

	/**
	 * 增加日期的分钟。失败返回null。
	 * @param date 日期字符串
	 * @param minuteAmount 增加数量。可为负数
	 * @return 增加分钟后的日期字符串
	 */
	public static String addMinute(String date, int minuteAmount) {
		return addInteger(date, Calendar.MINUTE, minuteAmount);
	}

	/**
	 * 增加日期的分钟。失败返回null。
	 * @param date 日期
	 * @param minuteAmount 增加数量。可为负数
	 * @return 增加分钟后的日期
	 */
	public static Date addMinute(Date date, int minuteAmount) {
		return addInteger(date, Calendar.MINUTE, minuteAmount);
	}

	/**
	 * 增加日期的秒钟。失败返回null。
	 * @param date 日期字符串
	 * @param secondAmount 增加数量。可为负数
	 * @return 增加秒钟后的日期字符串
	 */
	public static String addSecond(String date, int secondAmount) {
		return addInteger(date, Calendar.SECOND, secondAmount);
	}

	/**
	 * 增加日期的秒钟。失败返回null。
	 * @param date 日期
	 * @param secondAmount 增加数量。可为负数
	 * @return 增加秒钟后的日期
	 */
	public static Date addSecond(Date date, int secondAmount) {
		return addInteger(date, Calendar.SECOND, secondAmount);
	}

	/**
	 * 获取日期的年份。失败返回0。
	 * @param date 日期字符串
	 * @return 年份
	 */
	public static int getYear(String date) {
		return getYear(StringToDate(date));
	}

	/**
	 * 获取日期的年份。失败返回0。
	 * @param date 日期
	 * @return 年份
	 */
	public static int getYear(Date date) {
		return getInteger(date, Calendar.YEAR);
	}

	/**
	 * 获取日期的月份。失败返回0。
	 * @param date 日期字符串
	 * @return 月份
	 */
	public static int getMonth(String date) {
		return getMonth(StringToDate(date));
	}

	/**
	 * 获取日期的月份。失败返回0。
	 * @param date 日期
	 * @return 月份
	 */
	public static int getMonth(Date date) {
		return getInteger(date, Calendar.MONTH) + 1;
	}

	/**
	 * 获取日期的天数。失败返回0。
	 * @param date 日期字符串
	 * @return 天
	 */
	public static int getDay(String date) {
		return getDay(StringToDate(date));
	}

	/**
	 * 获取日期的天数。失败返回0。
	 * @param date 日期
	 * @return 天
	 */
	public static int getDay(Date date) {
		return getInteger(date, Calendar.DATE);
	}

	/**
	 * 获取日期的小时。失败返回0。
	 * @param date 日期字符串
	 * @return 小时
	 */
	public static int getHour(String date) {
		return getHour(StringToDate(date));
	}

	/**
	 * 获取日期的小时。失败返回0。
	 * @param date 日期
	 * @return 小时
	 */
	public static int getHour(Date date) {
		return getInteger(date, Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取日期的分钟。失败返回0。
	 * @param date 日期字符串
	 * @return 分钟
	 */
	public static int getMinute(String date) {
		return getMinute(StringToDate(date));
	}

	/**
	 * 获取日期的分钟。失败返回0。
	 * @param date 日期
	 * @return 分钟
	 */
	public static int getMinute(Date date) {
		return getInteger(date, Calendar.MINUTE);
	}

	/**
	 * 获取日期的秒钟。失败返回0。
	 * @param date 日期字符串
	 * @return 秒钟
	 */
	public static int getSecond(String date) {
		return getSecond(StringToDate(date));
	}

	/**
	 * 获取日期的秒钟。失败返回0。
	 * @param date 日期
	 * @return 秒钟
	 */
	public static int getSecond(Date date) {
		return getInteger(date, Calendar.SECOND);
	}

	/**
	 * 获取日期 。默认yyyy-MM-dd格式。失败返回null。
	 * @param date 日期字符串
	 * @return 日期
	 */
	public static String getDate(String date) {
		return StringToString(date, DateStyle.YYYY_MM_DD);
	}

	/**
	 * 获取日期。默认yyyy-MM-dd格式。失败返回null。
	 * @param date 日期
	 * @return 日期
	 */
	public static String getDate(Date date) {
		return DateToString(date, DateStyle.YYYY_MM_DD);
	}

	/**
	 * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
	 * @param date 日期字符串
	 * @return 时间
	 */
	public static String getTime(String date) {
		return StringToString(date, DateStyle.HH_MM_SS);
	}

	/**
	 * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
	 * @param date 日期
	 * @return 时间
	 */
	public static String getTime(Date date) {
		return DateToString(date, DateStyle.HH_MM_SS);
	}

	/**
	 * 获取日期的星期。失败返回null。
	 * @param date 日期字符串
	 * @return 星期
	 */
	public static Week getWeek(String date) {
		Week week = null;
		DateStyle dateStyle = getDateStyle(date);
		if (dateStyle != null) {
			Date myDate = StringToDate(date, dateStyle);
			week = getWeek(myDate);
		}
		return week;
	}

	/**
	 * 获取日期的星期。失败返回null。
	 * @param date 日期
	 * @return 星期
	 */
	public static Week getWeek(Date date) {
		Week week = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		switch (weekNumber) {
		case 0:
			week = Week.SUNDAY;
			break;
		case 1:
			week = Week.MONDAY;
			break;
		case 2:
			week = Week.TUESDAY;
			break;
		case 3:
			week = Week.WEDNESDAY;
			break;
		case 4:
			week = Week.THURSDAY;
			break;
		case 5:
			week = Week.FRIDAY;
			break;
		case 6:
			week = Week.SATURDAY;
			break;
		}
		return week;
	}

	/**
	 * 获取两个日期相差的天数
	 * @param date 日期字符串
	 * @param otherDate 另一个日期字符串
	 * @return 相差天数。如果失败则返回-1
	 */
	public static int getIntervalDays(String date, String otherDate) {
		return getIntervalDays(StringToDate(date), StringToDate(otherDate));
	}

	/**
	 * @param date 日期
	 * @param otherDate 另一个日期
	 * @return 相差天数。如果失败则返回-1
	 */
	public static int getIntervalDays(Date date, Date otherDate) {
		int num = -1;
		Date dateTmp = StringToDate(DateUtils.getDate(date),
				DateStyle.YYYY_MM_DD);
		Date otherDateTmp = StringToDate(DateUtils.getDate(otherDate),
				DateStyle.YYYY_MM_DD);
		if (dateTmp != null && otherDateTmp != null) {
			long time = Math.abs(dateTmp.getTime() - otherDateTmp.getTime());
			num = (int) (time / (24 * 60 * 60 * 1000));
		}
		return num;
	}

	/**
	 * @param date 日期
	 * @param otherDate 另一个日期
	 * @return 相差分钟数。如果失败则返回-1
	 */
	public static int getIntervalMinute(Date date, Date otherDate) {
		int num = -1;
		if (date != null && otherDate != null) {
			long time = Math.abs(date.getTime() - otherDate.getTime());
			num = (int) (time / (1000*60));
		}
		return num;
	}


	/**
	 * Description :获取当前时间yyyyMMddHHmmss字符串
	 * Author : miaozh
	 * CreateTime : 2016年10月10日 下午2:43:39
	 * @return String
	 */
	public static String getSecondString() {
		return DateToString(new Date(), DateStyle.YYYYMMDDHHMMSS);
	}

	/**
	 * Description : 获取当前时间yyyyMMddHHmmssSSS字符串
	 * Author : miaozh
	 * CreateTime : 2016年10月10日 下午2:46:08
	 * @return String
	 */
	public static String getMillisecondString() {
		return DateToString(new Date(), DateStyle.YYYYMMDDHHMMSSSSS);
	}

	/**
	 * Discription:[返回开始时间到结束的时间段值 例：分段计算时间值,将24小时分为48个时间段，例如：1,2标识00:00到01:00]
	 * @param startTime 开始时间9:00
	 * @param endTime 结束时间10:30
	 * @return
	 *
	 * Created on 2017/11/3
	 * @author: 葛伟
	 */
	public static String splitTime(Date startTime, Date endTime ) {
		//开始时间
		StringBuffer splitTime = new StringBuffer();
		int startHour = DateUtils.getHour(startTime);
		int startMinute = DateUtils.getMinute(startTime);
		int startCount = startHour * 2;
		if (startMinute > 0 ) {
			startCount = startCount + 1;
		}
		//结束时间
		int endHour = DateUtils.getHour(endTime);
		int endMinute = DateUtils.getMinute(endTime);
		int endCount = endHour * 2;
		if (endMinute > 0) {
			endCount = endCount +1;
		}
		//如果结束是00:00 最后时间
		if(endHour == 0 && endMinute == 0) {
			endCount = 48;
		}
		//之所以需要++ 原因在于开始时间为9:00结束时间为9：30，不加1的情况18表示是8:30到9:00的时间段这样是错误的，我需要的是9:00到9:30的所以是19。
		startCount ++;
		endCount ++;
		//如果跨天会议  结束时间的小时小于开始时间的小时认定为跨天会议
		if(endHour < startHour) {
			for (int i = startCount;i < 49; i++) {
				splitTime.append(i);
				splitTime.append(Const.SEPARATOR_COMMA);
			}
			if (endCount != 49) {
				for (int j = 1; j < endCount; j++) {
					splitTime.append(j);
					if (j < endCount - 1) {
						splitTime.append(Const.SEPARATOR_COMMA);
					}
				}
			}
			return  splitTime.toString();
		}
		//不是跨天会议
		if (startCount == endCount) {
			splitTime.append(startCount);
			return  splitTime.toString();
		}
		for (int i = startCount;i < endCount; i++) {
			splitTime.append(i);
			if (i < endCount - 1) {
				splitTime.append(Const.SEPARATOR_COMMA);
			}
		}
		return  splitTime.toString();
	}


	/**
	 * Discription:[返回开始时间到结束的时间段值 例：分段计算时间值,将24小时分为48个时间段，例如：1,2标识00:00到01:00]
	 * @param startTime 开始时间9:00
	 * @param endTime 结束时间10:30
	 * @return
	 *
	 * Created on 2017/11/3
	 * @author: 葛伟
	 */
	public static String splitTime(String startTime, String endTime ) {
		Date startDate = StringToDate(startTime, DateStyle.HH_MM_SS);
		Date endDate = StringToDate(endTime, DateStyle.HH_MM_SS);
		return splitTime(startDate, endDate);
	}

	/**
	 * Discription:[24小时分为48个时间段，根据时间段的数值获取真实的时间 -- 会议开始时间]
	 * @param number 24小时分为48个时间段的值
	 * @return 数值解析后的真实时间 -- 会议开始时间
	 *
	 * Created on 2017/11/22
	 * @author: 葛伟
	 */
	public static Date numberByStartTime(int number) {
		int i = (number - 1) % 2;
		int hour = (number - 1) / 2;
		int minute = 0;
		if (i > 0) {
			minute = 30;
		}
		String endTime = hour + Const.SEPARATOR_COLON + minute;
		return StringToDate(endTime, DateStyle.HH_MM);
	}

	/**
	 * Discription:[24小时分为48个时间段，根据时间段的数值获取真实的时间 -- 会议结束时间]
	 * @param number 24小时分为48个时间段的值
	 * @return 数值解析后的真实时间 -- 会议结束时间
	 *
	 * Created on 2017/11/22
	 * @author: 葛伟
	 */
	public static Date numberByEndTime(int number) {
		return addMinute(numberByStartTime(number), 30);
	}

	/**
	 * Discription:[判断两个日期是否相等]
	 * @param d1 日期1
	 * @param d2 日期2
	 * @return
	 *
	 * Created on 2017/11/3
	 * @author: 葛伟
	 */
	public static boolean isSameDate(Date d1, Date d2) {
		if(null == d1 || null == d2)
			return false;
		//return getOnlyDate(d1).equals(getOnlyDate(d2));
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(d1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(d2);
		return  cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
	}

	/**
	 * Discription:[获取两个时间的时间差]
	 * @param date1 时间一
	 * @param date2 时间二
	 * @param type 获取的类型  天 时 分
	 * @return 时间一 减 时间二 的值
	 *
	 * Created on 2017/11/10
	 * @author: 葛伟
	 */
	public static int dayDiff(Date date1, Date date2, int type) {
		long diff = date1.getTime() - date2.getTime();
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff / nh;
		// 计算差多少分钟
		long min = diff / nm;
		if (Calendar.DATE == type) {
			return (int) day;
		} else if (Calendar.HOUR_OF_DAY == type) {
			return (int) hour;
		} else {
			return (int) min;
		}
	}

	/**
	 * Discription:[根据参数时间获取前推的整点时间：列2017-11-22 17:13返回17:00；列2017-11-22 17:59返回17:30]
	 * @param date 时间
	 * @return 列2017-11-22 17:13返回17:00；列2017-11-22 17:59返回17:30
	 *
	 * Created on 2017/11/22
	 * @author: 葛伟
	 */
	public static Date wholeHour(Date date){
		int hour = DateUtils.getHour(date);
		int minute = DateUtils.getMinute(date);
		if (minute >= 30) {
			minute = 30;
		}else {
			minute = 00;
		}
		String endTime = hour + Const.SEPARATOR_COLON + minute;
		return StringToDate(endTime, DateStyle.HH_MM);
	}


	/**
	 * <p>Description:[计算相差的月份，占用一个整月（比如2-1至2-28）按一个月，但是非整月部分按和15、31比较算：
     * 如2-01到2-28是1个月
     *  2-01到3-01是1.5个月
     *  2-02到3-01是1.0个月
     *  ]</p>
	 * Created on 2017年11月28日
	 *
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return 相差的月数0.5的倍数
	 * @author 葛伟
	 */
	public static double getMonths(Date startDate, Date endDate) {
        int startYear = DateUtils.getYear(startDate);
        int endYear = DateUtils.getYear(endDate);
        int startMonth = DateUtils.getMonth(startDate);
        int endMonth = DateUtils.getMonth(endDate);
        int startDay = DateUtils.getDay(startDate);
        int endDay = DateUtils.getDay(endDate);
        int endLastDay = DateUtils.getLastDay(endDate);
        int startLastDay = DateUtils.getLastDay(startDate);
        int intervalYears = endYear - startYear;
        double intervalMonths;
        int intervalDays;
        if (intervalYears >= 1) {
            intervalDays = (startLastDay - startDay) + endDay + 1;
            intervalMonths = (12.0 - startMonth) + endMonth + 1;
			if (startDay == 1 && endDay == endLastDay) {
				intervalDays = 0;
			} else {
				if (startDay != 1) {
					intervalMonths -= 1.0;
				} else {
					intervalDays = endDay;
				}
				if (endDay != endLastDay) {
					intervalMonths -= 1.0;
				} else {
					intervalDays = startLastDay - startDay + 1;
				}
			}
        } else {
            intervalMonths = endMonth - startMonth + 1.0;
            if (intervalMonths > 1.0) {
				intervalDays = (startLastDay - startDay) + endDay + 1;
				if (startDay == 1 && endDay == endLastDay) {
					intervalDays = 0;
				} else {
					if (startDay != 1) {
						intervalMonths -= 1.0;
					} else {
						intervalDays = endDay;
					}
					if (endDay != endLastDay) {
						intervalMonths -= 1.0;
					} else {
						intervalDays = startLastDay - startDay + 1;
					}
				}
				intervalMonths = intervalMonths < 0.0 ? 0.0 : intervalMonths;
			} else {
                intervalMonths = 0.0;
                intervalDays = endDay - startDay + 1;
            }

        }
		if (intervalDays == 0) {
			intervalMonths += 0.0;
		} else if (intervalDays <= 15) {
            intervalMonths += 0.5;
        } else if (intervalDays >= 31) {
            intervalMonths += 1.0;
            if (intervalDays - 31 > 15) {
                intervalMonths += 1.0;
            } else if (intervalDays - 31 >= 1) {
                intervalMonths += 0.5;
            }
        } else {
            intervalMonths += 1.0;
        }
        for (int i = 1; i < intervalYears; i++) {
            intervalMonths += 12.0;
        }
        return intervalMonths;
    }



	/**
	 * <p>Description:[计算当前时间所处的拆分时间段]</p>
	 * Created on 2017年12月05日
	 *
	 * @return 拆分时间段Integer值
	 * @author 葛伟
	 */
	public static Integer getSplitTimeByNow() {
		return getSplitTime(new Date());
	}

	/**
	 * <p>Description:[计算时间所处的拆分时间段]</p>
	 * Created on 2018年1月14日
	 *
	 * @return 拆分时间段Integer值
	 * @author 葛伟
	 */
	public static Integer getSplitTime(Date date) {
		int hour = DateUtils.getHour(date);
		int minute = DateUtils.getMinute(date);
		int splitTime = hour * 2 + 1;
		if (minute > 30) {
			splitTime += 1;
		}
		return splitTime;
	}

	/**
	 * Discription:[获取当前月的第一天的日期]
	 * @return 当前月第一天
	 *
	 * Created on 2017/12/15
	 * @author: 葛伟
	 */
	public static Date getfirstDate(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		return StringToDate(getDateFormat(DateStyle.YYYY_MM_DD.getValue()).format(c.getTime()));
	}

	/**
	 * Discription:[获取当前月的最后一天的日期]
	 * @return 当前月的最后一天
	 *
	 * Created on 2017/12/15
	 * @author: 葛伟
	 */
	public static Date getlastDate(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return StringToDate(getDateFormat(DateStyle.YYYY_MM_DD.getValue()).format(c.getTime()));
	}

	/**
	 * Discription:[获取指定月的最后一天的日期]
	 * @return 指定的最后一天
	 *
	 * Created on 2017/12/15
	 * @author: 葛伟
	 */
	public static int getLastDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		return lastDay;
	}

	/**
	 * Discription:[获取指定月的第一天的日期]
	 * @return 指定的第一天
	 *
	 * Created on 2017/12/15
	 * @author: 葛伟
	 */
	public static int getFirstDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int firstDay = c.getActualMinimum(Calendar.DAY_OF_MONTH);
		return firstDay;
	}

	/**
	 * <p>Description:[获取当前日期字符串]</p>
	 * Created on 2018/1/2
	 *
	 * @author 葛伟
	 */
	public static String getCurrentDateString() {
		return DateToString(new Date(), DateStyle.YYYY_MM_DD);
	}

	/**
	 * <p>Description:[获取当前日期]</p>
	 * Created on 2018/1/2
	 *
	 * @author 葛伟
	 */
	public static Date getCurrentDate() {
		return StringToDate(getCurrentDateString(), DateStyle.YYYY_MM_DD);
	}


	/**
	 * <p>Discription:[时间是否小于当前时间 ]</p>
	 * Created on 2017年12月26日
	 * @param targetDate 目标日期
	 * @param targetTime 目标时间
	 * @return
	 * @author:[葛伟]
	 */
	public static boolean compareNowHours(Date targetDate, Date targetTime){
		Date date = new Date();
		if (targetDate.after(date)) {
			return false;
		}
		Date nowTime = DateUtils.StringToDate(DateUtils.getHour(date) + Const.SEPARATOR_COLON + DateUtils.getMinute(date) + Const.SEPARATOR_COLON + DateUtils.getSecond(date), DateStyle.HH_MM_SS);
		int compare = nowTime.compareTo(targetTime);
		if (compare >= 0) {
			return true;
		}else {
			return false;
		}
	}
}