package jiubeirobot.com.robotshop.base;

import android.app.Application;
import android.content.Intent;

import com.lzy.okhttputils.OkHttpUtils;

import butterknife.ButterKnife;
import jiubeirobot.com.robotshop.BuildConfig;
import jiubeirobot.com.robotshop.service.ScannerService;
import jiubeirobot.com.robotshop.utils.L;

/**
 * 类描述    :应用上下文，应用生命周期管理，在这里初始化等
 * 包名      : com.fecmobile.jiubeirobot.base
 * 类名称    : BaseApplication
 * 创建时间  : 2017/2/21 19:59
 * 修改人    :
 * 修改时间  :
 * 修改备注  :
 */
public class BaseApplication extends Application {
    private ActivityControl activityControl;
    private static BaseApplication baseApplication;

    public static BaseApplication getInstance() {
        return baseApplication;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        activityControl = ActivityControl.getActivityControl();

        ButterKnife.setDebug(BuildConfig.DEBUG);

        //启动检测扫码枪的后台服务
        start();
        OkHttpUtils.init(this);
        if (BuildConfig.DEBUG) {
            L.i(BuildConfig.SERVER_IP);
        }

    }

    public void start() {
        Intent intent = new Intent(this, ScannerService.class);
        startService(intent);

    }

    public void stop() {
        Intent intent = new Intent(this, ScannerService.class);
        stopService(intent);
    }



    @Override
    public void onTerminate() {
        super.onTerminate();
        exitApp();
        stop();

    }

    /**
     * 描述      : 退出APP
     * 方法名    :  exitApp
     * param    :   无
     * 返回类型  : void
     * 创建人    : ghy
     * 创建时间  : 2017/2/21 20:00
     * 修改人    :
     * 修改时间
     * 修改备注
     * throws
     */
    public void exitApp() {
        activityControl.finishiAll();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

}