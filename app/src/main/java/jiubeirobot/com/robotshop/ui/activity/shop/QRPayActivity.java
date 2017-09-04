package jiubeirobot.com.robotshop.ui.activity.shop;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import jiubeirobot.com.robotshop.R;
import jiubeirobot.com.robotshop.base.Activitys;
import jiubeirobot.com.robotshop.base.BaseActivity;
import jiubeirobot.com.robotshop.base.BaseBean;
import jiubeirobot.com.robotshop.utils.L;
import jiubeirobot.com.robotshop.utils.T;

public class QRPayActivity extends BaseActivity {

    @BindView(R.id.tv_back_txt)
    TextView tvBackTxt;
    @BindView(R.id.iv_search_icon)
    ImageView ivSearchIcon;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.llyt_back)
    LinearLayout llytBack;
    @BindView(R.id.iv_pay_qr)
    ImageView ivpay;
    @BindView(R.id.iv_pay_icon)
    ImageView ivpay_icon;
    @BindView(R.id.tv_pay_des)
    TextView tvpay_des;
    private long millisInFuture = 1800000;//30分钟 = 1800000毫秒
    private long countDownInterval = 1000;
    private CountDownTimer countDownTime; //计时类

    @Override
    public int pageLayout() {
        return R.layout.activity_qrpay;
    }

    @Override
    public void initTitle() {
        ivSearchIcon.setVisibility(View.GONE);
        tvBackTxt.setVisibility(View.GONE);
        tvTitle.setText(getString(R.string.shopping_pay_type));
    }

    @Override
    public void initView() {
        requestPayInfo();
        countDownTime = new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                L.i("millisUntilFinished：" + millisUntilFinished);
            }

            @Override
            public void onFinish() {
                T.showToCenter(getString(R.string.common_timeout_pay));
                Activitys.toShoplist(QRPayActivity.this);
                countDownTime.start();
            }
        };
    }

    /**
     * 定义请求数据的方法
     */
    private void requestPayInfo() {
    }

    @Override
    public void onSuccess(BaseBean bean, int flag) {
        switch (flag) {
            default:
                break;
        }
    }

    @OnClick({R.id.llyt_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llyt_back:
                finish();
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (countDownTime != null) {
            countDownTime.cancel();
        }
    }
}
