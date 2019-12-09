package com.wander.hpermission;

/**
 * @Description: java类作用描述 权限申请结果回调接口类
 * @Author: liuhao
 * @CreateDate: 2019-12-09 13:58
 */

public interface PermissionListener {

    // 申请成功
    void onSucceed();
    // 申请失败
    void onFiled();
}
