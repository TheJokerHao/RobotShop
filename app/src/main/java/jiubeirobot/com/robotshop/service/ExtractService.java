package jiubeirobot.com.robotshop.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.serialport.scannerLib;
import android.support.annotation.Nullable;
import android.util.Log;

import jiubeirobot.com.robotshop.ui.activity.manange.TakeGoodsActivity;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/31.
 * 创建内容：提货
 * ==========================
 */

public class ExtractService extends Service {
    private scannerLib scanner;
    private TakeGoodsActivity.ExtractReceivers scan;

    @Override
    public void onCreate() {
        super.onCreate();
        scan = new TakeGoodsActivity.ExtractReceivers();
        //配置扫码器
        scanner = new scannerLib();
        scanner.initScanner();
        scanner.scannerHandler = new Handler() {
            public void handleMessage(Message msg) {
                RefreshDisplay(msg);
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(TakeGoodsActivity.ExtractReceivers.EXTRACTACTION);
        registerReceiver(scan, filter);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (scan != null) {
            unregisterReceiver(scan);
        }
    }

    /**
     * 刷新界面
     */
    private void RefreshDisplay(Message msg) {
        switch (msg.what) {
            case 0:
                String val = new String(scanner.scannerRx_Buffer);
                Log.d("print", "RefreshDisplay:提货码 " + val);
                Intent intent = new Intent();
                intent.setAction(TakeGoodsActivity.ExtractReceivers.EXTRACTACTION);
                intent.putExtra(TakeGoodsActivity.ExtractReceivers.DATA, val);
                sendBroadcast(intent);
                //msg.arg1 可以获取接收字符长度
                //Arrays.fill(scanner.scannerRx_Buffer, (byte) 0);
                break;
        }
    }
}
