package com.dou.server.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author yangjd
 */
public class DateUtils {
    public static Date changeMinuteDate(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 获取两个日期差
     * @param endDate 结束时间
     * @param startDate 开始时间
     * @param format 格式
     * @return string
     */
    public static String getDatePoor(Date endDate, Date startDate, String format) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        return String.format(format,day,hour,min,sec);
    }

    /**
     * 获取两个日期差（天）
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 日期集合
     */
    public static List<Date> getDatePoorDay(Date startDate, Date endDate) {
        Date startDateTemp = getFirstHrMinSec(startDate);
        Date endDateTemp = getLastHrMinSec(endDate);
        long startTime = startDateTemp.getTime();
        long endTime = endDateTemp.getTime();
        long oneDay = 1000 * 60 * 60 * 24L;
        long time = startTime;
        List<Date> dates = new ArrayList<>();
        while (time <= endTime) {
            Date d = new Date(time);
            dates.add(d);
            time += oneDay;
        }
        return dates;
    }

    /**
     * 获取日期0点0分0秒
     * @param date 日期
     * @return
     */
    public static Date getFirstHrMinSec(Date date) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取日期23点59分59秒
     * @param date 日期
     * @return
     */
    public static Date getLastHrMinSec(Date date) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date getFirstDayOfMonth(final Date date) {
        Date dateTemp = getFirstHrMinSec(date);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(dateTemp);
        final int last = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        return cal.getTime();
    }

    public static Date getLastDayOfMonth(final Date date) {
        Date dateTemp = getLastHrMinSec(date);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(dateTemp);
        final int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        return cal.getTime();
    }

    public static Date getFirstDayOfYear(final Date date) {
        Date dateTemp = getFirstHrMinSec(date);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(dateTemp);
        final int last = cal.getActualMinimum(Calendar.DAY_OF_YEAR);
        cal.set(Calendar.DAY_OF_YEAR, last);
        return cal.getTime();
    }

    public static Date getLastDayOfYear(final Date date) {
        Date dateTemp = getLastHrMinSec(date);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(dateTemp);
        final int last = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
        cal.set(Calendar.DAY_OF_YEAR, last);
        return cal.getTime();
    }

    /**
     * 获取上个月的日期
     * @date: 2019/9/25
    */
    public static String getLastMonthToFormatStr(Date date,String format) {
        LocalDate localDate = dateToLocalDate(date);
        localDate = localDate.minusMonths(1);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern(format);
        return formatters.format(localDate);
    }

    /**
     * date转换LocalDate
     * @date: 2019/9/25
    */
    public static LocalDate dateToLocalDate(Date date) {
        if(null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 日期字符串按照格式转换对象
     * @date: 2019/9/3
     */
    public static Date strToDate(String dateStr, String DateFormat){
        try {
            return new SimpleDateFormat(DateFormat).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 日期对象按照格式转换字符串
     * @date: 2019/9/3
     */
    public static String dateToFormatStr(Date date, String DateFormat){
        return new SimpleDateFormat(DateFormat).format(date);
    }
}
