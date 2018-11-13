package com.thenightswatch.smartkitchentableremote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

class Utils {
    private static int sTheme;
    final static int THEME_DARK = 0;
    final static int THEME_WHITE = 1;
    /**
     * Set the theme of the Activity, and restart it by creating a new Activity of the same type
     */
    static void changeToTheme(AppCompatActivity activity, int theme){
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }
    /**
     * Set the theme of the activity, according to the configuration.
     */
    static void onActivityCreateSetTheme(AppCompatActivity activity){
        if (sTheme == 0){
            activity.setTheme(R.style.Theme_AppCompat_NoActionBar);
        }
        else{
            activity.setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        }
    }
}
