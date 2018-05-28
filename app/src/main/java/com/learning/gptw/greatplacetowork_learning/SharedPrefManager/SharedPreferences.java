package com.learning.gptw.greatplacetowork_learning.SharedPrefManager;

import android.app.Activity;
import android.content.Context;

import com.learning.gptw.greatplacetowork_learning.Constans.Constans;


public class SharedPreferences {

   /* public static long getLastOnLineTime() {

        return getSharedPreferenceLong(R.string.last_online_time_key);
    }

    public static void setLastOnLineTime(long lastOnlineTime) {

        setSharedPreferenceLong(R.string.last_online_time_key, lastOnlineTime);
    }

    public static String getLastMenu() {

        return getSharedPreferenceString(R.string.last_menu_key, R.string.defaul_last_menu);
    }

    public static void setLastMenu(String lastMenu) {

        setSharedPreferenceString(R.string.last_menu_key, lastMenu);
    }*/

    private static void setSharedPreferenceString(int key, String value) {

        android.content.SharedPreferences sharedPref = Constans.context.getSharedPreferences("CODEOriginationValues", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constans.context.getString(key), value);
        editor.commit();
    }

    private static String getSharedPreferenceString(int key, int defaultValueKey) {

        android.content.SharedPreferences sharedPref = Constans.context.getSharedPreferences("CODEOriginationValues", Context.MODE_PRIVATE);
        String defaultValue = Constans.context.getResources().getString(defaultValueKey);
        String value = sharedPref.getString(Constans.context.getResources().getString(key), defaultValue);
        return value;
    }

    private static void setSharedPreferenceInt(int key, int value) {

        Activity activity = (Activity) Constans.context;

        android.content.SharedPreferences sharedPref = activity.getSharedPreferences("CODEOriginationValues", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(activity.getString(key), value);
        editor.commit();
    }

    private static int getSharedPreferenceInt(int key, int defaultValueKey) {

        Activity activity = (Activity) Constans.context;

        android.content.SharedPreferences sharedPref = activity.getSharedPreferences("CODEOriginationValues", Context.MODE_PRIVATE);
        int defaultValue = activity.getResources().getInteger(defaultValueKey);
        int value = sharedPref.getInt(activity.getResources().getString(key), defaultValue);
        return value;
    }

    private static void setSharedPreferenceLong(int key, long value) {

        android.content.SharedPreferences sharedPref = Constans.context.getSharedPreferences("CODEOriginationValues", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(Constans.context.getString(key), value);
        editor.commit();
    }

    private static long getSharedPreferenceLong(int key) {

        android.content.SharedPreferences sharedPref = Constans.context.getSharedPreferences("CODEOriginationValues", Context.MODE_PRIVATE);
        long defaultValue = 0;
        long value = sharedPref.getLong(Constans.context.getResources().getString(key), defaultValue);
        return value;
    }
}
