package jiubeirobot.com.robotshop.ui.activity.manange;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import jiubeirobot.com.robotshop.R;
import jiubeirobot.com.robotshop.base.Activitys;
import jiubeirobot.com.robotshop.base.BaseActivity;
import jiubeirobot.com.robotshop.base.BaseApplication;
import jiubeirobot.com.robotshop.base.BaseBean;
import jiubeirobot.com.robotshop.data.constans.Constants;
import jiubeirobot.com.robotshop.data.net.APIManager;
import jiubeirobot.com.robotshop.data.net.Flags;
import jiubeirobot.com.robotshop.service.ExtractService;
import jiubeirobot.com.robotshop.utils.L;
import jiubeirobot.com.robotshop.utils.SharedPreferencesUtils;

/**
 * @author TheJoker丶豪
 *         提货的activity
 * @date 创建时间:2017/8/22
 */
public class TakeGoodsActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back_txt)
    TextView tvBackTxt;
    @BindView(R.id.iv_search_icon)
    ImageView ivSearchIcon;

    @BindView(R.id.btn_extract)
    Button btnextract;
    @BindView(R.id.btn_takegods)
    Button btntakegods;

    static TextView tv_number;

    static String takeCode;//提货的二维码的值
    String username;
    BaseApplication baseApplication;

    @Override
    public int pageLayout() {
        return R.layout.activity_take_goods;
    }

    @Override
    public void initTitle() {
        ivSearchIcon.setVisibility(View.GONE);
        tvTitle.setText("提取货物");
    }

    @Override
    public void initView() {
        baseApplication = BaseApplication.getInstance();
        tv_number = (TextView) findViewById(R.id.tv_extract_numaber);
        baseApplication.stop();
        Intent intent = new Intent(TakeGoodsActivity.this, ExtractService.class);
        startService(intent);
    }

    @Override
    public void onSuccess(BaseBean bean, int flag) {
        switch (flag) {
            case Flags.FLAG_TAKE_GODS:
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.llyt_back, R.id.btn_takegods, R.id.btn_extract})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.llyt_back:
                Intent intent = new Intent(TakeGoodsActivity.this, ExtractService.class);
                stopService(intent);
                baseApplication.start();
                finish();
                break;
            case R.id.btn_extract:
            case R.id.btn_takegods:
                username = SharedPreferencesUtils.getString(TakeGoodsActivity.this, "username");
                if (!TextUtils.isEmpty(takeCode) && !TextUtils.isEmpty(username)) {
                    //两种方式的提货  但是提交的方法都一样
                    APIManager.getInstance().takeGods(TakeGoodsActivity.this, takeCode, username);
                }
                break;
            default:
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(TakeGoodsActivity.this, ExtractService.class);
        stopService(intent);
        baseApplication.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("print", "onKeyDown: ");
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(TakeGoodsActivity.this, ExtractService.class);
            stopService(intent);
            baseApplication.start();
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * 内部类
     */

    public static class ExtractReceivers extends BroadcastReceiver {

        public static final String EXTRACTACTION = "com.jiubeirobot.receivers.ExtractReceivers";
        public static final String DATA = "data";

        @Override
        public void onReceive(Context context, Intent intent) {
            takeCode = intent.getStringExtra(DATA);
            L.i("监测到的返回的值提货" + takeCode.trim().toString());
            Log.d("print", "initView: " + takeCode);
            if (takeCode != null) {
                tv_number.setText("您的提货码为：" + takeCode);
            }
        }
    }
}
