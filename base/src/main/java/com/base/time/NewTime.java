package com.base.time;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-06 15:01
 */
public class NewTime {
    public static void main(String[] args) {
        t1();
//        t2();
//        t3();
//        zoneTime();
//        formatter();
//        different();
    }

    /**
     * Period：处理两个日期之间的差值
     *  Duration：处理两个时间之间的差值
     */
    private static void different() {
        LocalDate date = LocalDate.of(2017,7,22);
        LocalDate date1 = LocalDate.now();
        Period period = Period.between(date,date1);
        System.out.println(period.getYears() + "年" +
                period.getMonths() + "月" +
                period.getDays() + "天");

        LocalTime time = LocalTime.of(20,30);
        LocalTime time1 = LocalTime.of(23,59);
        Duration duration = Duration.between(time,time1);
        System.out.println(duration.toHours() + "小时");
        System.out.println(duration.toMinutes() + "分钟");
        System.out.println(duration.toMillis() + "秒");
    }

    private static void formatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println( formatter.format(LocalDateTime.now()));
    }

    /**
     * 可以指定时区的时间
     */
    private static void zoneTime() {
        ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println(zdt);
    }

    /**
     *LocalTime 表示日期,使用默认时区
     */
    private static void t3() {
        LocalTime localDate = LocalTime.now();
        System.out.println(localDate);

        //
        System.out.println(LocalDateTime.now());
    }

    /**
     *LocalDate 表示日期 使用默认时区
     */
    private static void t2() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
    }


    /**
     * Instant 表示时刻
     */
    private static void t1() {
            //创建 Instant 实例
            Instant instant = Instant.now();
            System.out.println(instant);

            //根据1970 的偏移秒数创建
            Instant instant1 = Instant.ofEpochSecond(20);
            System.out.println(instant1);
        //根据1970 的偏移秒数，纳秒创建
            Instant instant2 = Instant.ofEpochSecond(30,100);
            System.out.println(instant2);
        //根据1970 的偏移毫秒数创建
            Instant instant3 = Instant.ofEpochMilli(1000);
            System.out.println(instant3);


            //从字符解析为时间点
        System.out.println(Instant.parse("1997-01-01T00:00:01Z"));

    }
}
