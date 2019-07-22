package com.zfr.aaron.spring.project.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.format.DateParser;
import cn.hutool.core.util.ArrayUtil;
import org.apache.commons.lang.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zfr
 * 日期工具类, 通过静态继承的方式, 扩展common-lang3中的DateUtils
 */
public class MyDateUtils extends DateUtil {

    /**
     * 日期规则定义
     */
    public static final String[] DATE_PATTERN = new String[]{
            "yyyyMMdd", //编号0
            "yyyyMMddHHmmss", //编号1
            "yyyyMMddHHmm", //编号2
            "yyyy-MM-dd HH:mm:ss", //编号3
            "yyyyMMdd HH:mm", //编号4
            "yyyy-MM-dd", //编号5
            "mm:ss",//编号6
            "yyyy-MM-dd HH:mm",//编号7
            "yyyy/MM/dd", //编号8
    };

    /**
     * 最强验证日期的正则表达式,添加了闰年的验证
     * YYYYMMDD
     * YYYY-MM-DD
     * YYYY/MM/DD
     * YYYY_MM_DD
     * YYYY.MM.DD的形式
     *
     * match : 2008-2-29 2008/02/29
     * not match : 2008-2-30   2007-2-29
     */
    public static final String YYYY_MM_DD_REGEX = "^(?:(?!0000)[0-9]{4}([-/.]?)(?:(?:0?[1-9]|1[0-2])\\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\\1(?:29|30)|(?:0?[13578]|1[02])\\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-/.]?)0?2\\2(?:29))$";
    public static final String YYYY_MM_DD = "^(?:(?!0000)[0-9]{4}([-])(?:(?:0?[1-9]|1[0-2])\\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\\1(?:29|30)|(?:0?[13578]|1[02])\\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-])0?2\\2(?:29))$";
    /**
     * 获取今天的日期, 不包括时间. 如yyyy-MM-dd 00:00:00.000
     *
     * @return yyyy-MM-dd 00:00:00.000
     */
    public static Date todayDate() {
        Calendar result = Calendar.getInstance();
        result.set(Calendar.HOUR_OF_DAY, 0);
        result.set(Calendar.MINUTE, 0);
        result.set(Calendar.SECOND, 0);
        result.set(Calendar.MILLISECOND, 0);
        return result.getTime();
    }

    /**
     * 根据开始日期和结束日期, 计算日期差
     *
     * @param from 开始日期
     * @param to   结束日期
     * @return 两个日期之间的天数
     */
    public static int getDays(Date from, Date to) {
        int days = 0;
        if (from.before(to)) {
            while (from.before(to)) {
                days++;
                from = addDays(from, 1);
            }
        } else {
            days = -1;
        }
        return days;
    }


    /**
     * 获取时间段(闭区间)内的每一天
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 日期段内的每一天
     */
    public static Set<Date> getEveryDay(Date start, Date end) {
        Set<Date> result = new HashSet<>();
        while (start.getTime() <= end.getTime()) {
            result.add(start);
            start = addDays(start, 1);
        }
        return result;
    }

    /**
     * 根据今天的时间 添加 numDay 天的时间
     *
     * @param numDay 要改变的 天数
     * @return
     */
    public static Date addDays(Date date,int numDay) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, numDay);//把日期往后增加一天.整数往后推,负数往前移动
        return calendar.getTime();
    }

    /**
     * 根据今天的时间 添加 numDay 天的时间
     *
     * @param numDay 要改变的 天数
     * @return
     */
    public static Date addDays(int numDay) {
        Date todayDate = MyDateUtils.todayDate();//取时间
        return addDays(todayDate,numDay);
    }

    /**
     * 将日期对象格式化为yyyy-MM-dd格式字符串
     *
     * @param date 日期对象
     * @return yyyy-MM-dd格式字符串
     */
    public static String format(Date date) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, DATE_PATTERN[5]);
    }

    /**
     * 将日期对象格式化为yyyy-MM-dd HH:mm:ss格式字符串
     *
     * @param date 日期对象
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, DATE_PATTERN[3]);
    }

    /**
     * 将日期格式化为指定格式字符串
     *
     * @param date    日期对象
     * @param pattern 字符串格式
     * @return 日期字符串
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, pattern);
    }

    public static Boolean isDate(String dateStr) {
        return checkTimePattern(dateStr, YYYY_MM_DD_REGEX);
    }
    public static Boolean checkTimePattern(String dateStr, String pattern) {
        Pattern p=Pattern.compile(pattern);
        Matcher m=p.matcher(dateStr);
        return m.find();
    }

    /**
     * 将下列格式日期转成 日期格式
     * 采用parseDate(String dateString) --》YYYYMMDD 转换不了
     * YYYYMMDD
     * YYYY-MM-DD
     * YYYY/MM/DD
     *
     *  TODO1 YYYY_MM_DD YYYY.MM.DD的形式
     */
    public static Date parseDateAll(String dateString) {
        if(dateString == null){
            return null;
        }
        Date date;
        try {
            DateTime dateTime = parseDate(dateString);
            date = dateTime.toJdkDate();
        } catch (Exception e) {
            try {
                DateTime dateTime = parse(dateString, (DateParser)DatePattern.PURE_DATE_FORMAT);
                date = dateTime.toJdkDate();
            } catch (Exception e1) {
                throw new RuntimeException(e);
            }
        }

        return date;
    }

    /**
     *  根据传入的 日期字符串 选出最大日期。例子（"2018-01-01,2018-02-01" --> 2018-02-01）
     * @param dateStr
     * @return
     */
    public static Date getMaxDateStr(String dateStr) {
        if(MyStringUtils.isEmpty(dateStr)){
            return null;
        }
        String[] dateArr = dateStr.split(MyStringUtils.COMMA);
        ArrayList<Date> dateList = new ArrayList<>();
        for(String dateS : dateArr){
            dateList.add(MyDateUtils.parseDateAll(dateS));
        }
        Date[] dateNewArr = new Date[dateArr.length];
        return ArrayUtil.max(dateList.toArray(dateNewArr));
    }

    public static boolean effectiveDate(Date preconsignDate) {
        String yyyyMMdd = DateUtil.format(preconsignDate, "yyyyMMdd");
        DateTime parse = DateUtil.parse(yyyyMMdd);

        DateTime now = DateUtil.parse(DateUtil.format(todayDate(), "yyyyMMdd"));
        if (parse.compareTo(now) < 0){
            return false;
        }
        return true;
    }

    /**
     * 比较日期 > more
     * @param newDate 新日期
     * @param oldDate 旧日期
     * @return
     */
    public static boolean afterDate(String newDate, String oldDate) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {

            Date dt1 =df.parse(newDate);

            Date dt2 =df.parse(oldDate);

            if(dt1.after(dt2)){
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /**
     * 比较日期 >=
     * @param newDate 新日期
     * @param oldDate 旧日期
     * @return
     */
    public static boolean equalsOrAfterDate(String newDate, String oldDate) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {

            Date dt1 =df.parse(newDate);

            Date dt2 =df.parse(oldDate);

            if(dt1.after(dt2) || dt1.equals(dt2)){
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }
}