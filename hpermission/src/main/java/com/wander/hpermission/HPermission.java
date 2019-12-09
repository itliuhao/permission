package com.wander.hpermission;

import android.app.Activity;
import android.app.FragmentManager;

import java.util.List;

/**
 * @Description: 申请权限的帮助类
 * @Author: liuhao
 * @CreateDate: 2019-12-09 14:00
 */

public class HPermission {

    private Activity mActivity;
    private PermissionFragment mPermissionFragment;
    private static final String TAG = "HPermission";
    private String[] mPermissions;

    public HPermission(Activity activity) {
        this.mActivity = activity;
        mPermissionFragment = getPermissionFragment(activity);
    }

    private PermissionFragment getPermissionFragment(Activity activity) {
        //获取权限的fragment
        PermissionFragment permissionFragment = findPermissionFragment(activity);
        boolean isNewInstance = (permissionFragment == null);
        if (isNewInstance) {
            permissionFragment = new PermissionFragment();
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager.beginTransaction()
                    .add(permissionFragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();

        }
        return permissionFragment;
    }

    /**
     * 获取权限fragment
     *
     * @param activity
     * @return
     */
    private PermissionFragment findPermissionFragment(Activity activity) {
        return (PermissionFragment) activity.getFragmentManager().findFragmentByTag(TAG);
    }

    /**
     * 要申请的权限组
     *
     * @param permissions
     * @return
     */
    public HPermission permissions(String... permissions) {
        mPermissions = permissions;
        return this;
    }

    /**
     * 申请权限
     *
     * @param permissionListener
     */
    public void request(PermissionListener permissionListener) {
        mPermissionFragment.setPermissionListener(permissionListener);
        // 1.判断版本
        if (!PermissionUtils.isVersionCodeM()) {
            // 版本6.0一下，直接回调成功方法
            permissionListener.onSucceed();
            return;
        }
        // 版本6.0及以上
        // 2.获取未授权的列表
        List<String> deniedList = PermissionUtils.getDeniedList(mActivity, mPermissions);
        if (deniedList.size() > 0) {
            // 3.去申请权限
            mPermissionFragment.requestPermissions(mActivity, deniedList.toArray(new String[deniedList.size()]));
        } else {
            permissionListener.onSucceed();
        }


    }

}
