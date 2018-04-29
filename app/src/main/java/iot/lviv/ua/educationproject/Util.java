package iot.lviv.ua.educationproject;

import android.text.format.Time;

import java.util.Calendar;

/**
 * Created by Volodymyr on 08.04.2018.
 */

public class Util {

    public static String getTime(){
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();

        String hour = String.valueOf(today.hour);
        String minute = String.valueOf(today.minute);
        String second = String.valueOf(today.second);

        if (hour.length() < 2){
            hour = "0" + hour;
        }
        if (minute.length() < 2){
            minute = "0" + minute;
        }
        if (second.length() < 2){
            second = "0" + second;
        }

        String time = hour + ":" + minute + ":" + second;

        return time;
    }

    public static String getDate(){
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
        String date = day + "." + month + "." + today.year;

        return date;
    }
}
