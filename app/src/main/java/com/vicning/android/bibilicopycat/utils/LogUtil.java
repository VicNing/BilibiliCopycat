package com.vicning.android.bibilicopycat.utils;

import android.util.Log;

/**
 * Created by Neil on 2016/8/5.
 */
public class LogUtil {

    public static final boolean DEBUGABLE = false;

    public static void logError(String extra, String msg) {
        if (DEBUGABLE) {
            Log.e(extra, msg);
        }
    }
}
