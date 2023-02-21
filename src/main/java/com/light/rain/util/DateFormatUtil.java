package com.light.rain.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Author: LightRain
 * @Description: 日期处理工具
 * @DateTime: 2023-02-21 17:18
 * @Version：1.0
 **/
public class DateFormatUtil {

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 4:15
     * @Param: [msValue, pattern] 毫秒值 日期表达式
     * @Return: java.lang.String
     * @Description: 获取时间
     */
    public static String getTime(long msValue) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date(msValue));
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 4:15
     * @Param: [msValue, pattern] 毫秒值 日期表达式
     * @Return: java.lang.String
     * @Description: 获取时间
     * 毫秒值推荐使用：System.currentTimeMillis()</br>
     * 日期表达式：&nbsp yyyy-MM-dd hh:mm:ss</br>
     * 24小时表达式：&nbsp yyyy-MM-dd HH:mm:ss
     */
    public static String getTime(long msValue, String pattern) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date(msValue));
        return new SimpleDateFormat(pattern).format(calendar.getTime());
    }

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 4:29
     * @Param: [day] int 日期表达式
     * @Return: java.lang.String
     * @Description: 获取指定默认24小时格式日期 0:今日,+1:今日+1天,-1:今日-1天
     */
    public static String getTime(int day) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, day);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
    }

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 4:30
     * @Param: [day, pattern] int 日期表达式
     * @Return: java.lang.String
     * @Description: 获取指定日期格式</ br>
     * 日期表达式：&nbsp yyyy-MM-dd hh:mm:ss</br>
     * 24小时表达式：&nbsp yyyy-MM-dd HH:mm:ss
     */
    public static String getTime(int day, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, day);
        return new SimpleDateFormat(pattern).format(cal.getTime());
    }

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 5:04
     * @Param: [date, pattern] 偏移时间 日期表达式
     * @Return: long
     * @Description: ISO偏移日期时间转换成时间戳</ br>
     * 例:</br>
     * date='2022-11-25T18:45:47+00:00'</br>
     * pattern='yyyy-MM-dd HH:mm:ss'
     * 2022-11-25T06:45:47+00:00→1669329947000
     * 转换为正常24小时格式的时间戳
     */
    public static long getTime(String date) {
        return getTimestamp(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 5:04
     * @Param: [date, pattern] 偏移时间 日期表达式
     * @Return: long
     * @Description: ISO偏移日期时间转换成时间戳</ br>
     * 例:</br>
     * date='2022-11-25T18:45:47+00:00'</br>
     * pattern='yyyy-MM-dd HH:mm:ss'
     * 2022-11-25T06:45:47+00:00→1669329947000
     * 转换为正常24小时格式的时间戳
     */
    public static long getTime(String date, String pattern) {
        return getTimestamp(date, pattern);
    }

    /**
     * @Author: LightRain
     * @Date: 21/2/2023 下午 5:04
     * @Param: [date, pattern] 偏移时间 日期表达式
     * @Return: long
     * @Description: ISO偏移日期时间转换成时间戳</ br>
     * 例:</br>
     * date='2022-11-25T18:45:47+00:00'</br>
     * pattern='yyyy-MM-dd HH:mm:ss'
     * 2022-11-25T06:45:47+00:00→1669329947000
     * 转换为正常24小时格式的时间戳
     */
    private static long getTimestamp(String date, String pattern) {
        try {
            String format = LocalDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME).format(DateTimeFormatter.ofPattern(pattern));
            return new SimpleDateFormat(pattern).parse(format).getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
