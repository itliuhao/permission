package com.wander.hpermission;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.List;

/**
 * @Description: 申请权限的帮助类 (无界面的fragment)
 * @Author: liuhao
 * @CreateDate: 2019-12-09 13:59
 */

public class PermissionFragment extends Fragment {
    private static final int REQUESTCODE = 66;
    private static final int REQUEST_PERMISSION_SETTING = 55;
    private PermissionListener mPermissionListener;
    private Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  Fragment具有属性retainInstance，默认值为false。当设备旋转时，fragment会随托管activity一起销毁并重建。调用setRetainInstance(true)方法可保留fragment，
        setRetainInstance(true);
    }

    public void setPermissionListener(PermissionListener permissionListener) {
        this.mPermissionListener = permissionListener;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestPermissions(Activity activity, String[] permission) {
        this.mActivity = activity;
        requestPermissions(permission, REQUESTCODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUESTCODE) {
            // 获取未申请的权限列表
            List<String> deniedPermissions = PermissionUtils.getDeniedList(mActivity, permissions);
            if (deniedPermissions.size() > 0) {
                // 执行失败的方法
                onFailed(permissions);
            } else {
                // 执行成功的方法
                onSucceed();
            }
        }

    }

    /**
     * 成功时回调方法
     */
    private void onSucceed() {
        if (mPermissionListener != null) {
            mPermissionListener.onSucceed();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void onFailed(String[] permissions) {
        for (int i = 0; i < permissions.length; i++) {
            // 用户拒绝是true   用户选择不再提示是：false
            if (!shouldShowRequestPermissionRationale(permissions[i])) {
                new AlertDialog.Builder(mActivity)
                        .setTitle("权限被拒绝")
                        .setMessage("权限管理-->打开拒绝的权限")
                        .setPositiveButton("去打开", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                openSetting();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                mPermissionListener.onFiled();
                            }
                        })
                        .show();
                return;
            }
        }
        mPermissionListener.onFiled();
    }

    /**
     * 打开应用设置页面
     */
    private void openSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", mActivity.getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
    }

    @Override
    public void onDestroy() {
        // 销毁对象，防止内存泄漏
        mActivity = null;
        mPermissionListener = null;
        super.onDestroy();

    }
}
