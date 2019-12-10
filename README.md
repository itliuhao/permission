# Permission

说明：可以下载HPermission，添加到本地项目中、添加好依赖就可以直接使用，不需要添加多余的操作，直接调用。

## 执行代码

在需要申请权限的界面，直接调用以下代码：


```
 new HPermission(this).permissions(Manifest.permission.READ_PHONE_STATE,Manifest.permission.CAMERA)
                .request(new PermissionListener() {
                    @Override
                    public void onSucceed() {
                       // 申请成功回调
                    }

                    @Override
                    public void onFiled() {
			// 申请失败回调
                    }
                });
```
