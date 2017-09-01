package jiubeirobot.com.robotshop.ui.activity.shop;

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

public class SelectOrderPayTypeActivity extends BaseActivity {
    @BindView(R.id.llyt_alipay)
    LinearLayout llyt_alipay;
    @BindView(R.id.llyt_wechat_pay)
    LinearLayout llyt_wechatpay;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back_txt)
    TextView tvBackTxt;
    @BindView(R.id.iv_search_icon)
    ImageView ivSearchIcon;

    @Override
    public int pageLayout() {
        return R.layout.activity_select_order_pay_type;
    }

    @Override
    public void initTitle() {
        ivSearchIcon.setVisibility(View.GONE);
        tvTitle.setText(getString(R.string.pay_type_title));
    }

    @Override
    public void initView() {

    }

    @Override
    public void onSuccess(BaseBean bean, int flag) {

    }


    @OnClick({R.id.llyt_back, R.id.llyt_alipay, R.id.llyt_wechat_pay})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.llyt_back:
                finish();
                break;
            case R.id.llyt_wechat_pay:
                Activitys.toQRPay(SelectOrderPayTypeActivity.this);
                break;
            case R.id.llyt_alipay:
                Activitys.toQRPay(SelectOrderPayTypeActivity.this);
                break;
            default:
                break;
        }
    }

}
