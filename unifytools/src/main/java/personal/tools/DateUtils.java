package personal.tools;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 刘晨
 * @create 2018-04-19 14:58
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class DateUtils extends org.apache.commons.lang3.time.DateUtils{

    private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" };

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null){
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime()-date.getTime();
        return t/(24*60*60*1000);
    }


    public static Date getDateStart(Date date) {
        if(date==null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date= sdf.parse(formatDate(date, "yyyy-MM-dd")+" 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getDateEnd(Date date) {
        if(date==null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date= sdf.parse(formatDate(date, "yyyy-MM-dd") +" 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 判断字符串是否是日期
     * @param timeString
     * @return
     */
    public static boolean isDate(String timeString){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        try{
            format.parse(timeString);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    /**
     * 格式化时间
     * @param timestamp
     * @return
     */
    public static String dateFormat(Date timestamp){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(timestamp);
    }

    /**
     * 获取系统时间Timestamp
     * @return
     */
    public static Timestamp getSysTimestamp(){
        return new Timestamp(new Date().getTime());
    }

    /**
     * 获取系统时间Date
     * @return
     */
    public static Date getSysDate(){
        return new Date();
    }

    /**
     * 生成时间随机数
     * @return
     */
    public static String getDateRandom(){
        String s=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        return s;
    }

    public static boolean isCurrentDate(java.util.Date date, String patten) {
        return formatDate(new java.util.Date(), patten).compareTo(formatDate(date, patten)) == 0;
    }

    public static String getBetweenTime(java.util.Date beforeTime, java.util.Date afterTime) {
        long ms = afterTime.getTime() - beforeTime.getTime();
        long ss = 1000L;
        long mi = ss * 60L;
        long hh = mi * 60L;
        long dd = hh * 24L;
        String result = "";
        if(ms % dd > 0L && ms > 0L) {
            result = result + (ms - ms % dd) / dd + "天";
            ms %= dd;
        }

        if(ms % hh > 0L && ms > 0L) {
            result = result + (ms - ms % hh) / hh + "时";
            ms %= hh;
        }

        if(ms % mi > 0L && ms > 0L) {
            result = result + (ms - ms % mi) / mi + "分";
            ms %= mi;
        }

        if(ms % ss > 0L && ms > 0L) {
            result = result + (ms - ms % ss) / ss + "秒";
            long var10000 = ms % ss;
        }

        return result;
    }


}
