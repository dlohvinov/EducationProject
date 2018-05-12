package iot.lviv.ua.educationproject;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    public static final String APP_PREFERENCES = "myPrefferences";
    public static SharedPreferences mPreferencess;

    public SharedPreferencesManager(Context context) {
        mPreferencess = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }


    public void setAverageMark(TypeOfClass typeOfClass, float averageMarkForLecture) {
        SharedPreferences.Editor editor = mPreferencess.edit();
        editor.putFloat(String.valueOf(typeOfClass), averageMarkForLecture);
        editor.apply();
    }

    public float getAverageMark(TypeOfClass typeOfClass) {
        return mPreferencess.getFloat(String.valueOf(typeOfClass), 99999);
    }
}
