package com.wander.permission.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.wander.phonesafe.R;

/**
 * @Description: toast工具类
 * @Author: liuhao
 * @CreateDate: 2019-12-10 08:44
 */

public class ToastUtils {


    private static Context context;

    // Toast对象
    private static Toast toast;

    private static TextView contentView;

    // 字体颜色
    private static int textColor = R.color.white;

    // 字体大小
    private static int textSize = 14;

    // 背景资源
    private static int backgroudId = R.drawable.toast_frame_style;

    // 错误的背景资源
    private static int errorBackgroudId = R.drawable.toast_frame_style_erroe;

    private ToastUtils() {
        throw new RuntimeException("u cannot be instantiated");
    }

    /**
     * 在Application中初始化ToastUtils.init(this)
     *
     * @param context
     */
    public static void init(Context context) {
        ToastUtils.context = context.getApplicationContext();
    }

    /**
     * 发送Toast,默认LENGTH_SHORT
     *
     * @param resId
     */
    public static void show(int resId) {
        showToast(context, context.getString(resId), Toast.LENGTH_SHORT, false);
    }

    public static void show(String msg) {
        showToast(context, msg, Toast.LENGTH_SHORT, false);
    }

    public static void showError(int resId) {
        showToast(context, context.getString(resId), Toast.LENGTH_SHORT, true);
    }

    public static void showError(String errorMsg) {
        showToast(context, errorMsg, Toast.LENGTH_SHORT, true);
    }

    private static void showToast(Context context, String message, int duration, boolean isError) {

        if (context == null) {
            return;
        }

        if (toast == null || contentView == null) {
            contentView = new TextView(context);
            toast = new Toast(context);
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 200);
            toast.setView(contentView);
            toast.setDuration(duration);
        }
        contentView.setTextColor(context.getResources().getColor(textColor));
        contentView.setTextSize(textSize);
        if (isError) {
            contentView.setBackgroundResource(errorBackgroudId);
        } else {
            contentView.setBackgroundResource(backgroudId);
        }
        contentView.setText(message);
        toast.show();
    }

    /**
     * 在UI界面隐藏或者销毁前取消Toast显示
     */
    public static void cancel() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
    }

    /**
     * build模式修改toast配置
     */
    public static class Builder {

        public Builder textColor(int textColor) {
            ToastUtils.textColor = textColor;
            return this;
        }

        public Builder textSize(int textSize) {
            ToastUtils.textSize = textSize;
            return this;
        }

        public Builder backgroudId(int backgroudId) {
            ToastUtils.backgroudId = backgroudId;
            return this;
        }

        public Builder errorBackgroudId(int errorBackgroudId) {
            ToastUtils.errorBackgroudId = errorBackgroudId;
            return this;
        }

        public void build(Context context) {
            ToastUtils.init(context);
        }
    }
}
