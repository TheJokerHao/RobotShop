package jiubeirobot.com.robotshop.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.lzy.okhttputils.callback.FileCallback;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import jiubeirobot.com.robotshop.BuildConfig;
import jiubeirobot.com.robotshop.R;
import jiubeirobot.com.robotshop.base.Activitys;
import jiubeirobot.com.robotshop.base.BaseActivity;
import jiubeirobot.com.robotshop.base.BaseBean;
import jiubeirobot.com.robotshop.bean.ObjBean;
import jiubeirobot.com.robotshop.bean.VersionCodeBean;
import jiubeirobot.com.robotshop.data.net.APIManager;
import jiubeirobot.com.robotshop.data.net.Flags;
import jiubeirobot.com.robotshop.ui.dialog.AlertDialog;
import jiubeirobot.com.robotshop.utils.L;
import jiubeirobot.com.robotshop.utils.OkHttpHelper;
import jiubeirobot.com.robotshop.utils.T;
import okhttp3.Response;

/**
 * @author TheJoker丶豪
 *         主activity
 * @date 创建时间:2017/8/21
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.llyt_main_buyshop)
    LinearLayout ll_main_buyshop;

    @BindView(R.id.llyt_main_sys_set)
    LinearLayout ll_main_sysSet;

    @BindView(R.id.llyt_main_takegoods)
    LinearLayout ll_main_takegoods;


    @Override
    public int pageLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {
        detect();

    }


    /**
     * 检测是否需要更新版本的方法
     */
    public void detect() {
        APIManager.getInstance().getVersionCode(MainActivity.this);
    }


    /**
     * 获取当前的版本号 判断是否需要更新版本
     *
     * @param codeNum
     */
    private void getCodeThan(VersionCodeBean codeNum) {
        String XTcode = codeNum.getVersionCode();
        String appUrl = codeNum.getVersionUrl();
        int code = 0;
        Log.d("print", "getCodeThan: " + XTcode);

        if (!XTcode.equals("")) {
            code = Integer.parseInt(XTcode);
        }
        if (code > getCurrentVersionName()) {
            showDownloadDialog(appUrl, XTcode);
        }
    }


    /**
     * 提示更新版本的dialog
     *
     * @param appUrl 更新的版本所在的地址
     * @param intr   版本号
     */

    private void showDownloadDialog(final String appUrl, String intr) {
        AlertDialog dialog = new AlertDialog(this);
        AlertDialog.OnClickListenerAlertDialog listenerAlertDialog = new AlertDialog.OnClickListenerAlertDialog() {
            @Override
            public void onClick(View v, Dialog dialog) {
                downLoadApk(appUrl);
            }
        };
        dialog.builder().setTitle("版本更新").setMsg("版本：" + intr).setCancelable(false).setPositiveButton("下载", listenerAlertDialog)
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
    }

    // 下载apk文件
    private void downLoadApk(String appUrl) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setCancelable(false);
        pd.setMessage("正在下载更新");
        pd.show();
        OkHttpHelper.getInstance().downloadFile(this, appUrl, new FileCallback(getString(R.string.app_name) + ".apk") {
            @Override
            public void onSuccess(File file, okhttp3.Call call, Response response) {
                installApk(file);
                pd.dismiss();
            }

            @Override
            public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                super.downloadProgress(currentSize, totalSize, progress, networkSpeed);
                pd.setProgress((int) (progress * 100));
            }

            @Override
            public void onError(okhttp3.Call call, Response response, Exception e) {
                super.onError(call, response, e);
                T.show("下载失败", 2);
                pd.dismiss();
            }
        });
    }

    //安装apk
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
        finish();
    }

    /**
     * 请求失败回调
     *
     * @param error 错误信息
     * @param flag  错误值
     */

    @Override
    public void onError(String error, int flag) {
        super.onError(error, flag);
        Log.d("print", "MainActivity的onError: " + error + "flag" + flag);
    }


    @Override
    protected void onResume() {
        super.onResume();
        detect();
    }

    /**
     * 根据返回的值判断是否登录成功   根绝登录成功之后的值判端进入下一步系统设置页面
     *
     * @param bean
     * @param flag
     */
    @Override
    public void onSuccess(BaseBean bean, int flag) {

        Log.d("print", "onSuccess: " + bean.getMessage());

        switch (flag) {
            case Flags.FLAG_VERSION_CODE:
//                BaseBean<ObjBean<VersionCodeBean>> vCode = bean;
//                VersionCodeBean codeNum = vCode.getData().getObj();
//                getCodeThan(codeNum);
                break;
            case Flags.FLAG_TEST_CODE:
                L.i(bean.getCode());
                L.i(bean.getMessage());
                break;
        }
        dismissHUD();
    }

    @Override
    public void onBackPressed() {
    }

    @OnClick({R.id.llyt_main_buyshop, R.id.llyt_main_sys_set, R.id.llyt_main_takegoods})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llyt_main_buyshop:
                Activitys.toShoplist(MainActivity.this);
                break;
            case R.id.llyt_main_sys_set:
                Activitys.toLogin(MainActivity.this);
                break;
            case R.id.llyt_main_takegoods:
                Activitys.toTake(MainActivity.this);
//                Activitys.toQRPay(MainActivity.this);
                break;
            default:
                break;
        }
    }

    /**
     * @return 当前应用版本号
     */
    public int getCurrentVersionName() {
        return BuildConfig.VERSION_CODE;
    }

}