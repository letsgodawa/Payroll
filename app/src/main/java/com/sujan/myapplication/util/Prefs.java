package com.sujan.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("Pref Token", Context.MODE_PRIVATE);
    }

    public void saveString(String key, String value) {
        editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void saveInteger(String key, int value) {
        editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public String getString(String key) {
        return preferences.getString(key, "");
    }

    public void saveBoolean(String key, Boolean value) {
        editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public Boolean getBool(String key) {
        return preferences.getBoolean(key, false);

    }
}
