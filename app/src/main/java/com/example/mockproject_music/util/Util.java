package com.example.mockproject_music.util;

import android.content.Context;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

import com.example.mockproject_music.R;

public class Util {

    public static void transparentStatusbar(Context context, Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(context, R.color.bg_app));
        }
        window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
    }

    public static String convertMilToMinutes(long mili) {
        long minutes = (mili / 1000)  / 60;
        int seconds = (int)((mili / 1000) % 60);

        return minutes + ":" + seconds;
    }
}
