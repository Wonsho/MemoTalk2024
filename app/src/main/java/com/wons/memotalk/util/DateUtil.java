package com.wons.memotalk.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
    public static long getDate() {
        return System.currentTimeMillis();
    }

    public static String dateFormat(String format, long date) {
        Date time = new Date(date);
        // SimpleDateFormat으로 포맷 정의
        SimpleDateFormat sdf = new SimpleDateFormat(format); // h: 12시간제, a: 오전/오후 표시
        return sdf.format(time);
    }
}
