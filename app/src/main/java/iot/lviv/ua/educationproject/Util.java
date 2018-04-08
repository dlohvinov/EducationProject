package iot.lviv.ua.educationproject;

import android.text.format.Time;

import java.util.Calendar;

/**
 * Created by Volodymyr on 08.04.2018.
 */

public class Util {

    public static String getDateAndTime(){
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        String day = String.valueOf(today.monthDay);
        String month = String.valueOf(today.month);
        if (day.length() < 2){
            day = "0" + day;
        }
        if (month.length() < 2){
            month = "0" + month;
        }
        String dateAndTime = day + "." + month + "." + today.year + " " + today.hour
                + ":" + today.minute + ":" + today.second;

        return dateAndTime;
    }
}
