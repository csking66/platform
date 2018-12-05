package com.domain.common.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.SimpleTimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 */
public class DateUtil {

	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	// 默认日期格式
	public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";

	// 默认时间格式
	public static final String DATETIME_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

	public static final String TIME_DEFAULT_FORMAT = "HH:mm:ss";

	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	// 日期格式化
	private static DateFormat dateFormat = null;
	
	// 日期格式化
    private static DateFormat yyyy_mm_dd_hh_mm = null;

	// 时间格式化
	private static DateFormat dateTimeFormat = null;

	private static DateFormat timeFormat = null;

	private static Calendar gregorianCalendar = null;

	// 取值范围：就是临界点包不包含等于

	public final static int LEFT_OPEN_RIGHT_OPEN = 1;
	public final static int LEFT_CLOSE_RIGHT_OPEN = 2;
	public final static int LEFT_OPEN_RIGHT_CLOSE = 3;
	public final static int LEFT_CLOSE_RIGHT_CLOSE = 4;
	/**
	 * 比较日期的模式 --只比较日期，不比较时间
	 */
	public final static int COMP_MODEL_DATE = 1;
	/**
	 * 比较日期的模式 --只比较时间，不比较日期
	 */
	public final static int COMP_MODEL_TIME = 2;
	/**
	 * 比较日期的模式 --比较日期，也比较时间
	 */
	public final static int COMP_MODEL_DATETIME = 3;

	static {
		dateFormat = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
		dateTimeFormat = new SimpleDateFormat(DATETIME_DEFAULT_FORMAT);
		yyyy_mm_dd_hh_mm = new SimpleDateFormat(YYYY_MM_DD_HH_MM);
		timeFormat = new SimpleDateFormat(TIME_DEFAULT_FORMAT);
		gregorianCalendar = new GregorianCalendar();
	}

	/**
	 * 日期格式化yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static Date formatDate(String date, String format) {

		try {
			return new SimpleDateFormat(format).parse(date);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期格式化yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateFormat(Date date) {

		return dateFormat.format(date);
	}

	/**
	 * 日期格式化yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateTimeFormat(Date date) {

		return dateTimeFormat.format(date);
	}

	/**
	 * 日期格式化
	 */
	public static Date getDate(Date date) {

		try {
			return dateFormat.parse(getDateFormat(date));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 时间格式化
	 * 
	 * @param date
	 * @return HH:mm:ss
	 */
	public static String getTimeFormat(Date date) {

		return timeFormat.format(date);
	}

	/**
	 * 日期格式化
	 * 
	 * @param date
	 * @param 格式类型
	 * @return
	 */
	public static String getDateFormat(Date date, String formatStr) {

		if (StringUtils.isNotBlank(formatStr)) {
			return new SimpleDateFormat(formatStr).format(date);
		}
		return null;
	}

	/**
	 * 日期格式化
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateFormat(String date) {

		try {
			return dateFormat.parse(date);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 时间格式化
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateTimeFormat(String date) {

		try {
			return dateTimeFormat.parse(date);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 时间格式化
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateTimeHHMM(String date) {

		try {
			return yyyy_mm_dd_hh_mm.parse(date);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当前日期(yyyy-MM-dd)
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNowDate() {

		return DateUtil.getDateFormat(dateFormat.format(new Date()));
	}

	/**
	 * 获取指定月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {

		gregorianCalendar.setTime(date);
		gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取指定月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {

		gregorianCalendar.setTime(date);
		gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
		gregorianCalendar.add(Calendar.MONTH, 1);
		gregorianCalendar.add(Calendar.DAY_OF_MONTH, -1);
		return gregorianCalendar.getTime();
	}

	/**
	 * 当前日期相减
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayBefore(Date date, int length) {

		gregorianCalendar.setTime(date);
		int day = gregorianCalendar.get(Calendar.DATE);
		gregorianCalendar.set(Calendar.DATE, day - length);
		return gregorianCalendar.getTime();
	}

	/**
	 * 当前日期相加
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayAfter(Date date, int length) {

		gregorianCalendar.setTime(date);
		int day = gregorianCalendar.get(Calendar.DATE);
		gregorianCalendar.set(Calendar.DATE, day + length);
		return gregorianCalendar.getTime();
	}

	/**
	 * 当前日期相加
	 * 
	 * @param 分钟
	 * @return
	 */
	public static Date getMinuteAfter(Date date, int length) {

		gregorianCalendar.setTime(date);
		int minute = gregorianCalendar.get(Calendar.MINUTE);
		gregorianCalendar.set(Calendar.MINUTE, minute + length);
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取当前年
	 * 
	 * @return
	 */
	public static int getNowYear(Date date) {

		Calendar d = Calendar.getInstance();
		d.setTime(date);
		return d.get(Calendar.YEAR);
	}

	/**
	 * 获取当前月份
	 * 
	 * @return
	 */
	public static int getNowMonth(Date date) {

		Calendar d = Calendar.getInstance();
		d.setTime(date);
		return d.get(Calendar.MONTH) + 1;

	}
	
	public static Date getMonthAfter(Date date,Integer length) {

		gregorianCalendar.setTime(date);
		int month = gregorianCalendar.get(Calendar.MONTH);
		gregorianCalendar.set(Calendar.MONTH, month + length);
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取当月天数
	 * 
	 * @return
	 */
	public static int getNowMonthDay(Date date) {

		Calendar d = Calendar.getInstance();
		d.setTime(date);
		return d.getActualMaximum(Calendar.DATE);
	}

	/**
	 * 获取时间段的每一天
	 * 
	 * @param 开始日期
	 * @param 结算日期
	 * @return 日期列表
	 */
	public static List<Date> getEveryDay(Date startDate, Date endDate) {

		if (startDate == null || endDate == null) {
			return null;
		}
		// 格式化日期(yy-MM-dd)
		startDate = DateUtil.getDateFormat(DateUtil.getDateFormat(startDate));
		endDate = DateUtil.getDateFormat(DateUtil.getDateFormat(endDate));
		List<Date> dates = new ArrayList<Date>();
		gregorianCalendar.setTime(startDate);
		dates.add(gregorianCalendar.getTime());
		while (gregorianCalendar.getTime().compareTo(endDate) < 0) {
			// 加1天
			gregorianCalendar.add(Calendar.DAY_OF_MONTH, 1);
			dates.add(gregorianCalendar.getTime());
		}
		return dates;
	}

	/**
	 * 判断时间是否在制定的时间段之类
	 * 
	 * @param date 需要判断的时间
	 * @param start 时间段的起始时间
	 * @param end 时间段的截止时间
	 * @param interModel 区间的模式
	 * 
	 *            <pre>
	 * 取值：
	 * LEFT_OPEN_RIGHT_OPEN
	 * LEFT_CLOSE_RIGHT_OPEN
	 * LEFT_OPEN_RIGHT_CLOSE
	 * LEFT_CLOSE_RIGHT_CLOSE
	 * </pre>
	 * @param compModel 比较的模式
	 * 
	 *            <pre>
	 * 取值：
	 * COMP_MODEL_DATE	只比较日期，不比较时间
	 * COMP_MODEL_TIME	只比较时间，不比较日期
	 * COMP_MODEL_DATETIME 比较日期，也比较时间
	 * </pre>
	 * @return
	 */
	public static boolean isBetween(Date date, Date start, Date end, int interModel, int compModel) {

		if (date == null || start == null || end == null) {
			throw new IllegalArgumentException("日期不能为空");
		}
		SimpleDateFormat format = null;
		switch (compModel) {
			case COMP_MODEL_DATE : {
				format = new SimpleDateFormat("yyyyMMdd");
				break;
			}
			case COMP_MODEL_TIME : {
				format = new SimpleDateFormat("HHmmss");
				break;
			}
			case COMP_MODEL_DATETIME : {
				format = new SimpleDateFormat("yyyyMMddHHmmss");
				break;
			}
			default : {
				throw new IllegalArgumentException(String.format("日期的比较模式[%d]有误", compModel));
			}
		}
		long dateNumber = Long.parseLong(format.format(date));
		long startNumber = Long.parseLong(format.format(start));
		long endNumber = Long.parseLong(format.format(end));
		switch (interModel) {
			case LEFT_OPEN_RIGHT_OPEN : {
				if (dateNumber <= startNumber || dateNumber >= endNumber) {
					return false;
				}
				else {
					return true;
				}
			}
			case LEFT_CLOSE_RIGHT_OPEN : {
				if (dateNumber < startNumber || dateNumber >= endNumber) {
					return false;
				}
				else {
					return true;
				}
			}
			case LEFT_OPEN_RIGHT_CLOSE : {
				if (dateNumber <= startNumber || dateNumber > endNumber) {
					return false;
				}
				else {
					return true;
				}
			}
			case LEFT_CLOSE_RIGHT_CLOSE : {
				if (dateNumber < startNumber || dateNumber > endNumber) {
					return false;
				}
				else {
					return true;
				}
			}
			default : {
				throw new IllegalArgumentException(String.format("日期的区间模式[%d]有误", interModel));
			}
		}
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date getTime() {

		Calendar calendar = Calendar.getInstance();

		return calendar.getTime();
	}

	/**
	 * 增加或减少多少秒后的时间
	 * 
	 * @param minute
	 * @return
	 */
	public static Date getTime(Date date, int second) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.SECOND, second);
		return calendar.getTime();
	}

	/**
	 * 增加或减少多少天后的日期
	 *
	 * @param dayCount
	 * @return
	 */
	public static Date getRecentDay(int dayCount, Date date) {

		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();

		LocalDate localDate = instant.atZone(zoneId).toLocalDate();
		localDate = localDate.plusDays(dayCount);

		ZonedDateTime zdt = localDate.atStartOfDay(zoneId);

		date = Date.from(zdt.toInstant());

		return date;

	}

	public static Date getDate(String dateString) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = null;

		try {

			date = sdf.parse(dateString);
		}
		catch (ParseException e) {
			logger.debug(e.getMessage(), e);
		}

		return date;
	}

	public static String getSdfDate(Date date, String sdf) {

		if (null != date && null != sdf) {

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sdf);

			return simpleDateFormat.format(date);
		}
		return null;
	}

	public static String toDateStringFromIso(String sdate) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		format.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));

		Date date = null;

		try {
			date = format.parse(sdate);
		}
		catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sdf.format(date);
	}

	public static Date getSpecifiedDayAfter(Date startDate, int days) {

		if (null != startDate) {
			Calendar c = Calendar.getInstance();
			c.setTime(startDate);
			int day = c.get(Calendar.DATE);
			c.set(Calendar.DATE, day + days);
			return c.getTime();
		}
		return null;
	}

	public static Date getSpecifiedHourBefore(Date now, int hours) {

		if (null != now) {
			Calendar c = Calendar.getInstance();
			c.setTime(now);
			int hour = c.get(Calendar.HOUR);
			c.set(Calendar.HOUR, hour - hours);
			return c.getTime();
		}
		return null;
	}

	/**
	 * 根据参数 查询月份的第一天
	 * 
	 * @param now
	 * @param month -1,0,1 上月,本月,下月的第一天
	 * @return
	 */
	public static Date getMonthStartDate(Date now, int month) {

		if (null != now) {

			Calendar c = Calendar.getInstance();
			c.setTime(now);
			c.add(Calendar.MONTH, month);
			c.set(Calendar.DAY_OF_MONTH, 1);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);

			return c.getTime();
		}

		return null;
	}

	/**
	 * 根据参数 查询月份的最后一天
	 * 
	 * @param now
	 * @param month -1,0,1 上月,本月,下月的最后一天
	 * @return
	 */
	public static Date getMonthEndDate(Date now, int month) {

		if (null != now) {

			Calendar c = Calendar.getInstance();
			c.setTime(now);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
			c.add(Calendar.MONTH, month);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			c.set(Calendar.HOUR_OF_DAY, 23);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
			c.set(Calendar.MILLISECOND, 999);

			return c.getTime();
		}

		return null;
	}

	/*
	 * 两日前相减获取小时
	 */
	public static BigDecimal getDiffHour(String beginTime, String endTime) {
		Date beginDate = getDateTimeFormat(beginTime);
		Date endDate = getDateTimeFormat(endTime);
		Double timeDiff = getMinutes(endDate, beginDate) / 60D;
		return new BigDecimal(timeDiff);
	}
	
	/*
	 * 两日前相减获取小时
	 */
	public static BigDecimal getDiffHour(Date beginTime, Date endTime) {
		Double timeDiff = getMinutes(endTime, beginTime) / 60D;
		return new BigDecimal(timeDiff);
	}
	
	/*
	 * 两日前相减获取天数
	 */
	public static int getDiffDay(String beginTime, String endTime) {
		Date beginDate = getDateTimeFormat(beginTime);
		Date endDate = getDateTimeFormat(endTime);
		int days = (int) ((endDate.getTime() - beginDate.getTime()) / (1000*3600*24));
	    return days;
	}
	
	/*
	 * 两日前相减获取天数
	 */
	public static int getDiffDay(Date beginTime, Date endTime) {
		int days = (int) ((endTime.getTime() - beginTime.getTime()) / (1000*3600*24));
	    return days;
	}
	
	// 获取两个日期间相差的分钟数
	public static long getMinutes(Date endDate, Date beginDate) {

		Calendar calendarOne = Calendar.getInstance();
		calendarOne.setTime(endDate);
		Calendar calendarTwo = Calendar.getInstance();
		calendarTwo.setTime(beginDate);
		return ( calendarOne.getTimeInMillis() - calendarTwo.getTimeInMillis() ) / ( 1000 * 60 );
	}	

	/**
	 * 计算指定某年某月的开始日期
	 */
	public static Date getBeginTime(int year, int month) {

		YearMonth yearMonth = YearMonth.of(year, month);
		LocalDate localDate = yearMonth.atDay(1);
		LocalDateTime startOfDay = localDate.atStartOfDay();
		ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.of("Asia/Shanghai"));

		return Date.from(zonedDateTime.toInstant());
	}

	/**
	 * 计算指定某年某月的结束日期
	 */
	public static Date getEndTime(int year, int month) {

		YearMonth yearMonth = YearMonth.of(year, month);
		LocalDate endOfMonth = yearMonth.atEndOfMonth();
		LocalDateTime localDateTime = endOfMonth.atTime(23, 59, 59, 999);
		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
		return Date.from(zonedDateTime.toInstant());
	}

	/**
	 * 获取指定年份第一天
	 */
	public static Date getCurrYearFirst(int year) {

		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取指定年份最后一天
	 */
	public static Date getCurrYearLast(int year) {

		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		return currYearLast;
	}

	/**
	 * 判断日期是否相等
	 * 
	 * @author cs
	 */
	public static boolean sameDate(Date d1, Date d2) {

		LocalDate localDate1 = ZonedDateTime.ofInstant(d1.toInstant(), ZoneId.systemDefault()).toLocalDate();
		LocalDate localDate2 = ZonedDateTime.ofInstant(d2.toInstant(), ZoneId.systemDefault()).toLocalDate();
		return localDate1.isEqual(localDate2);
	}

	/**
	 * Long 转日期
	 * 
	 * @author cs
	 */
	public static String transferLongToDate(String dateFormat, Long millSec) {

		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date(millSec);
		return sdf.format(date);
	}

	/**
	 * 日期拼接时间
	 * 
	 * @author cs
	 * @throws ParseException 
	 */
	public static Date combinDate(Date date, String time) throws ParseException {

		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		String datestr = DateUtil.getDateFormat(date);
		Date newDate = sf1.parse(datestr + " " + time);
		return newDate;
	}

	/**
	 * @param dateString
	 * @param formatStr
	 * @return —— 字符串转换成时间对象
	 */
	public static Date stringTodate(String dateString, String formatStr) {

		if (StringUtils.isEmpty(dateString)) {
			return null;
		}
		Date formateDate = null;
		DateFormat format = new SimpleDateFormat(formatStr);
		try {
			formateDate = format.parse(dateString);
		}
		catch (ParseException e) {
			return null;
		}
		return formateDate;
	}
	
	/**
	 * 
	 * @Title: dateToXmlDate 
	 * @Description: 中信保日期格式
	 * @param date
	 * @return XMLGregorianCalendar
	 * @throws
	 */
	public static XMLGregorianCalendar dateToXmlDate(Date date){  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        DatatypeFactory dtf = null;  
         try {  
            dtf = DatatypeFactory.newInstance();  
        } catch (DatatypeConfigurationException e) {  
        }  
        XMLGregorianCalendar dateType = dtf.newXMLGregorianCalendar();  
        dateType.setYear(cal.get(Calendar.YEAR));  
        //由于Calendar.MONTH取值范围为0~11,需要加1  
        dateType.setMonth(cal.get(Calendar.MONTH)+1);  
        dateType.setDay(cal.get(Calendar.DAY_OF_MONTH));  
        dateType.setHour(cal.get(Calendar.HOUR_OF_DAY));  
        dateType.setMinute(cal.get(Calendar.MINUTE));  
        dateType.setSecond(cal.get(Calendar.SECOND));  
        return dateType;  
    }
	
	/**
	 * 计算某年某周的结束日期
	 */
	public static Date getYearWeekEndDay(int yearNo, int weekNo) {

		Calendar cal = Calendar.getInstance();
		cal.setWeekDate(yearNo, weekNo + 1, Calendar.SUNDAY);
		if (cal.get(Calendar.YEAR) > yearNo) {
			cal.set(yearNo, 11, 31);
			return cal.getTime();
		}
		else {
			return cal.getTime();
		}
	}
	
	/**
	 * 计算某年某周的开始日期
	 */
	public static Date getYearWeekFirstDay(int yearNo, int weekNo) {

		Calendar cal = Calendar.getInstance();
		cal.setWeekDate(yearNo, weekNo, Calendar.MONDAY);
		if (cal.get(Calendar.YEAR) < yearNo) {
			return DateUtils.truncate(cal.getTime(), Calendar.YEAR);
		}
		else {
			return cal.getTime();
		}
	}
	
	/**
	 * @param quarterly
	 * @return 某个季度的开始月
	 */
	public static Integer staMonthNum(Integer quarterly) {

		return 3 * ( quarterly - 1 ) + 1;
	}
	
	/**
	 * @param quarterly
	 * @return 某个季度的结束月
	 */
	public static Integer endMonthNum(Integer quarterly) {

		return 3 * quarterly;
	}
	
	/**
	 * @param quarterly
	 * @return 某个半年度的开始月
	 */
	public static Integer staHalfyearNum(Integer quarterly) {

		return 6 * ( quarterly - 1 ) + 1;
	}

	/**
	 * @param quarterly
	 * @return 某个半年度的结束月
	 */
	public static Integer endHalfyearNum(Integer quarterly) {

		return 6 * quarterly;
	}
	
	//获取给定时间的结束时间
	public static Date toDate(String expiryDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(expiryDate);
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(date);
			calendar1.set(Calendar.HOUR_OF_DAY, 23);
			calendar1.set(Calendar.MINUTE, 59);
			calendar1.set(Calendar.SECOND, 59);
			calendar1.set(Calendar.MILLISECOND, 999);
			return calendar1.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	//与当前时间比较，是否还在有效期内
	public static Boolean isEffective(String expiryDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(expiryDate);
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(date);
			calendar1.set(Calendar.HOUR_OF_DAY, 23);
			calendar1.set(Calendar.MINUTE, 59);
			calendar1.set(Calendar.SECOND, 59);
			calendar1.set(Calendar.MILLISECOND, 999);
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(new Date());
			if(calendar1.getTimeInMillis()-calendar2.getTimeInMillis()>=0) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}	
	
	// 比较例外日期
	public static Boolean isExceptionDates(String exceptionDates, Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = sdf.format(date);
		if (StringUtils.isNotBlank(exceptionDates)) {
			for (String dateExp : StringUtils.split(exceptionDates, ",")) {
				if (dateExp.equals(dateString))
					return true;
			}
		}
		return false;
	}
	
	// 获取当天结束时间1
	public static Date getEndDate(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date date1 = new Date(calendar.getTimeInMillis() - 1000);
		return date1;
	}	

    // 获取当天结束时间2
	public static Date getEndDate2(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		Date date1 = calendar.getTime();
		return date1;
	}
	
	// 获取当前小时的结束时间
	public static Date getEndHour(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// calendar.add(Calendar.HOUR_OF_DAY, 1);
		Date date1 = new Date(calendar.getTimeInMillis() + 1000 * ( 1800 - 1 ));
		return date1;
	}

	// 获取当前小时的结束时间
	@SuppressWarnings("deprecation")
	public static Date getEndHour2(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// calendar.set(Calendar.HOUR_OF_DAY, 23);
		if (date.getMinutes() >= 30) {
			calendar.set(Calendar.MINUTE, 59);
		}
		else {
			calendar.set(Calendar.MINUTE, 29);
		}
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		Date date1 = calendar.getTime();
		return date1;
	}
		

}