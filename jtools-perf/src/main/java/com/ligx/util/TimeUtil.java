package com.ligx.util;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Author: ligongxing.
 * Date: 2018/09/20.
 */
public class TimeUtil {

    public static final String YMDHMS_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date, String format) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(format);
    }
}
