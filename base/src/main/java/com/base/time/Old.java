package com.base.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-06 10:07
 */
public class Old {
    public static void main(String[] args) {
        t1();
    }

    public static void  t1() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c2 = new GregorianCalendar();

        System.out.println(dateFormat.format(c2.getTime()));
        int firstDayOfWeek = c2.getFirstDayOfWeek();
        System.out.println(firstDayOfWeek);
        System.out.println(c2.getMinimalDaysInFirstWeek());
        System.out.println(c2.get(Calendar.WEEK_OF_YEAR));
        c2.set(Calendar.DATE,20);
        System.out.println(dateFormat.format(c2.getTime()));
       c2.setMinimalDaysInFirstWeek(3);
        int firstDayOfWeek2= c2.getFirstDayOfWeek();
        System.out.println(firstDayOfWeek2);
        System.out.println(c2.getMinimalDaysInFirstWeek());
        System.out.println(c2.get(Calendar.WEEK_OF_YEAR));
    }
}
