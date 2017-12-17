package com.qualitest.utils.java;

import java.text.DateFormat;
import java.util.Date;

public class Time {


    public static String getDateAndTime(){

        Date curDate = new Date();

        String DateToStr = DateFormat.getInstance().format(curDate);

        return DateToStr;

    }
}
