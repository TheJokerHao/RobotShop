package jiubeirobot.com.robotshop.ui.activity.manange;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import jiubeirobot.com.robotshop.R;
import jiubeirobot.com.robotshop.base.BaseActivity;
import jiubeirobot.com.robotshop.base.BaseBean;
import jiubeirobot.com.robotshop.common.view.MyWebView;
import jiubeirobot.com.robotshop.data.net.APIManager;
import jiubeirobot.com.robotshop.data.net.Flags;

/**
 * @date 创建时间:2017/8/22
 * @author TheJoker丶豪
 * 平台介绍页面
 */
public class AboutUSActivity extends BaseActivity {
    @BindView(R.id.iv_search_icon)
    ImageView ivsearch;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.llyt_back)
    LinearLayout llback;
    @BindView(R.id.wv_about)
    MyWebView webView;


    @Override
    public int pageLayout() {
        return R.layout.activity_about_us;
    }

    @Override
    public void initTitle() {
        ivsearch.setVisibility(View.GONE);
        tvTitle.setText("久碑商城");

    }

    @Override
    public void initView() {
        // 定义方法获取数据
        APIManager.getInstance().getJiuBei(AboutUSActivity.this);
    }

    @Override
    public void onSuccess(BaseBean bean, int flag) {
        switch (flag) {
            case Flags.FLAG_GET_JIUBEI:
                break;
            default:
                break;
        }
        dismissHUD();
    }

    @OnClick({R.id.llyt_back})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.llyt_back:
                finish();
                break;
            default:
                break;
        }
    }
}