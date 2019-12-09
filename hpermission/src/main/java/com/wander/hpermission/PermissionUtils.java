package com.wander.hpermission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 申请权限的工具类
 * @Author: liuhao
 * @CreateDate: 2019-12-09 13:59
 */

public class PermissionUtils {

    //判断版本是否是6.0
    public static boolean isVersionCodeM() {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.M;
    }

    /**
     * @param context
     * @param permissions
     * @return 获取未授权的集合
     */
    public static List<String> getDeniedList(Context context, String... permissions) {
        //创建一个未授权的集合
        List<String> deniedList = new ArrayList<>();
        //如果传入为null,返回空的集合
        if (permissions.length == 0) {
            return deniedList;
        }
        for (String permission : permissions) {
            int permissionCode = ContextCompat.checkSelfPermission(context, permission);
            if (permissionCode == PackageManager.PERMISSION_DENIED) {
                //将没有授权的添加到集合
                deniedList.add(permission);
            }
        }
        return deniedList;
    }
}
