package com.wander.permission.utils;

import android.text.TextUtils;
import android.util.Log;

import com.wander.permission.BuildConfig;

/**
 * author：liuhao
 * time  ：2019-12-09
 * desc  ：日志打印工具类
 */

public class LogUtils {
    private static boolean showDebugLog = BuildConfig.DEBUG;

    public static void i(Object objMsg) {
        if (showDebugLog){
            String msg = (objMsg == null || TextUtils.isEmpty(objMsg.toString())) ? "null" : objMsg.toString();
            Log.i(getTag(), getMsgFormat(msg));
        }

    }

    public static void d(Object objMsg) {
        if (showDebugLog) {
            String msg = (objMsg == null || TextUtils.isEmpty(objMsg.toString())) ? "null" : objMsg.toString();
            Log.d(getTag(), getMsgFormat(msg));
        }
    }

    public static void w(Object objMsg) {
        if (showDebugLog){
            String msg = (objMsg == null || TextUtils.isEmpty(objMsg.toString())) ? "null" : objMsg.toString();
            Log.w(getTag(), getMsgFormat(msg));
        }

    }

    public static void e(Object objMsg) {

        if (showDebugLog){
            String msg = (objMsg == null || TextUtils.isEmpty(objMsg.toString())) ? "null" : objMsg.toString();
            Log.e(getTag(), getMsgFormat(msg));
        }

    }

    /**
     * 获取类名,方法名,行号
     */
    private static String getFunctionName() {
        try {
            StackTraceElement[] sts = Thread.currentThread().getStackTrace();
            if (sts != null) {
                for (StackTraceElement st : sts) {
                    if (st.isNativeMethod()) {
                        continue;
                    }
                    if (st.getClassName().equals(Thread.class.getName())) {
                        continue;
                    }
                    if (st.getClassName().equals(LogUtils.class.getName())) {
                        continue;
                    }
                    return "Thread:" + Thread.currentThread().getName() + ",at " + st.getClassName() + "." + st.getMethodName()
                            + "(" + st.getFileName() + ":" + st.getLineNumber() + ")";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getTag() {
        try {
            StackTraceElement[] sts = Thread.currentThread().getStackTrace();
            if (sts != null) {
                for (StackTraceElement st : sts) {
                    if (st.isNativeMethod()) {
                        continue;
                    }
                    if (st.getClassName().equals(Thread.class.getName())) {
                        continue;
                    }
                    if (st.getClassName().equals(LogUtils.class.getName())) {
                        continue;
                    }
                    return st.getFileName();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return LogUtils.class.getName();
        }
        return LogUtils.class.getName();
    }

    private static String getMsgFormat(String msg) {
        return msg + "----" + getFunctionName();
    }
}
