package com.ea.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String dateLongToString(long longDate) {
        Date date = new Date(longDate);
        SimpleDateFormat df2 = new SimpleDateFormat("MM.dd.yyyy HH:mm:ss");
        return df2.format(date);
    }
}
