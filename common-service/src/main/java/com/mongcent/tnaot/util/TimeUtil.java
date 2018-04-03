package com.mongcent.tnaot.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    private static String ymd = "yyyy-MM-dd";
    private static String ymdhms = "yyyy-MM-dd hh:mm:ss";
    private static String ymdHms = "yyyy-MM-dd HH:mm:ss";

    public static String ymd2str() {
        SimpleDateFormat sdf = new SimpleDateFormat(ymd);
        return sdf.format(new Date());
    }

    public static String ymdhms2str() {
        SimpleDateFormat sdf = new SimpleDateFormat(ymdhms);
        return sdf.format(new Date());
    }

    public static String ymdHms2str() {
        SimpleDateFormat sdf = new SimpleDateFormat(ymdHms);
        return sdf.format(new Date());
    }
}
