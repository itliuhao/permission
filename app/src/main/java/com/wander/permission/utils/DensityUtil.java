package com.wander.permission.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * @Description: 像素转化工具类
 * @Author: liuhao
 * @CreateDate: 2019-12-10 10:39
 */

public class DensityUtil {
    private DensityUtil() {
        throw new UnsupportedOperationException("DensityUtil cannot be instantiated");
    }

    public int dip2px(Context context, int dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static float px2sp(Context context, float pxValue) {
        return (pxValue / context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static int sp2px(Context context, int spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spValue, context.getResources().getDisplayMetrics());
    }
}
