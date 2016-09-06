package com.xyh.bestnews.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;

import com.xyh.bestnews.app.MyApp;

/**
 * Created by Administrator on 2016/9/5.
 */
public class UIUtils {
    public static Context getContext() {
        return MyApp.getApp();
    }

    public static Resources getResources() {
        return getContext().getResources();
    }

    public static int getColor(int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getResources().getColor(colorId, null);
        } else {
            return getResources().getColor(colorId);
        }

    }

}
