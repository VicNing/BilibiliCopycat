package com.vicning.android.bibilicopycat.utils;

import android.content.Context;

/**
 * Created by Neil on 2016/8/7.
 */
public class DensityUtil {
    public static int dip2px(Context context, float dip) {
        float density = context.getResources().getDisplayMetrics().density;
        return  (int)(dip * density + 0.5f);
    }

    public static float px2dip(Context context, float px) {
        float density = context.getResources().getDisplayMetrics().density;
        return px / density;
    }
}
