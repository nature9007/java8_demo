package com.java8;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class DateTest {
    public static void main(String[] args) throws ParseException, InterruptedException {

//    Date date = new Date(119,9,23);
//        System.out.println(date);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        for (int i = 0;i < 30; i++){
//            new Thread(() -> {
//                for ( int x = 0; x<100 ; x++){
//                    Date parseDate = null;
//                    try {
//                        parseDate = sdf.parse("20191023");
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("x:"+x+","+parseDate);
//                }
//            }).start();
//        }
//        Date parseDate =sdf.parse("20191023");
//        System.out.println(parseDate);

//    testLocalDaate();
//    testLocalTime();
//        combineLocalDateAndTime();
//        testInstant();
//        testDuration();
//        testPeriod();
//        testDateFormat();
        testDateParse();
    }

    private static void testLocalTime() {
        LocalTime time = LocalTime.now();
        System.out.println(time);
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());
    }

    private static void testLocalDaate() {
        LocalDate localDate = LocalDate.of(2019, 10, 23);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());

        localDate.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH);
    }


    private static void combineLocalDateAndTime(){
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        System.out.println(localDateTime.toString());
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
    }

    private static void testInstant() throws InterruptedException {
        Instant start = Instant.now();
        Thread.sleep(1000);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }

    private static void testDuration(){
        LocalTime start = LocalTime.now();
        LocalTime before = start.minusHours(1);
        Duration duration = Duration.between(start, before);
        System.out.println(duration.toHours());
    }

    private static void  testPeriod(){
        Period period = Period.between(LocalDate.of(2014,1,10),LocalDate.of(2019,10,23));
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        System.out.println(period.getYears());
    }

    private static void testDateFormat(){
        LocalDate localDate = LocalDate.now();
//        String format = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
//        String format1 = localDate.format(DateTimeFormatter.ISO_ORDINAL_DATE);
//        System.out.println(format);
//        System.out.println(format1);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = localDate.format(dateTimeFormatter);
        System.out.println(format);
    }

    private static void testDateParse(){
        String date1 = "20191023";
        LocalDate localDate = LocalDate.parse(date1, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date2 = "2016-11-13";
        LocalDate localDate1 = LocalDate.parse(date2,dateTimeFormatter);
        System.out.println(localDate1);
    }
}
