package com.example.project1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateProcessor {


    public static String dateToString(Date d){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz");
        return simpleDateFormat.format(d);
    }

    public static Date stringToDate(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz");
        return simpleDateFormat.parse(s);
    }

}
