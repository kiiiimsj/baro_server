package com.wantchu.wantchu_server2.business;

import java.time.LocalDateTime;

public class DateConverter {

    private static StringBuilder builder = new StringBuilder();
    LocalDateTime localDateTime;
    public DateConverter(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public static String convertDateWithoutTime(LocalDateTime localDateTime) {
        builder.delete(0, builder.toString().length())
        .append(localDateTime.getYear()).append("년 ")
        .append(localDateTime.getMonthValue() + "월 ")
        .append(localDateTime.getDayOfMonth() + "일 ");
        return builder.toString();
    }

    public static String convertDateWithTime(LocalDateTime localDateTime) {
        builder.delete(0, builder.toString().length())
                .append(localDateTime.getYear()).append("년 ")
                .append(localDateTime.getMonthValue() + "월 ")
                .append(localDateTime.getDayOfMonth() + "일 ")
                .append(localDateTime.getHour() + "시 ")
                .append(localDateTime.getMinute() + "분 ")
                .append(localDateTime.getSecond() + "초");
        return builder.toString();
    }

    public static String convertDateOnlyHour(LocalDateTime localDateTime) {
        if(localDateTime.getHour() > 12) {
            builder.delete(0, builder.toString().length())
                    .append(localDateTime.getHour() + ":")
                    .append(localDateTime.getMinute() + " PM");
            return builder.toString();
        }
        else {
            builder.delete(0, builder.toString().length())
                    .append(localDateTime.getHour() + ":")
                    .append(localDateTime.getMinute() + " AM");
            return builder.toString();
        }
    }
}
