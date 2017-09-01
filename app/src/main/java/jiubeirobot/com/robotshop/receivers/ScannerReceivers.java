package jiubeirobot.com.robotshop.receivers
        ;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import jiubeirobot.com.robotshop.base.Activitys;
import jiubeirobot.com.robotshop.utils.L;

/**
 * 类描述    :
 * 包名      : com.fecmobile.jiubeirobot.receivers
 * 类名称    : ScannerReceivers
 * 修改人    :
 * 修改时间  :
 * 修改备注  :
 */
public class ScannerReceivers extends BroadcastReceiver {
    public static final String ACTION = "com.jiubeirobot.receivers.ScannerReceivers";
    public static final String DATA = "data";


    @Override
    public void onReceive(Context context, Intent intent) {
        String reuslt = intent.getStringExtra(DATA);
        L.i("监测到的返回的值" + reuslt.trim().toString());
        Activitys.toScanResult(context, reuslt);
    }

}
