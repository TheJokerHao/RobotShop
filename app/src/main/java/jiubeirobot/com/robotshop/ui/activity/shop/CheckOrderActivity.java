package jiubeirobot.com.robotshop.ui.activity.shop;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import jiubeirobot.com.robotshop.R;
import jiubeirobot.com.robotshop.base.Activitys;
import jiubeirobot.com.robotshop.base.BaseActivity;
import jiubeirobot.com.robotshop.base.BaseBean;
import jiubeirobot.com.robotshop.base.BaseRecyclerAdapter;

public class CheckOrderActivity extends BaseActivity {
    @BindView(R.id.iv_search_icon)
    ImageView ivsearch;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.llyt_back)
    LinearLayout llback;
    @BindView(R.id.btn_submit_order)
    Button btnsuborder;


    @Override
    public int pageLayout() {
        return R.layout.activity_check_order;
    }

    @Override
    public void initTitle() {
        ivsearch.setVisibility(View.GONE);
        tvTitle.setText(getString(R.string.check_order));
    }

    @Override
    public void initView() {

    }

    @Override
    public void onSuccess(BaseBean bean, int flag) {

    }

    @OnClick({R.id.llyt_back,R.id.btn_submit_order})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.llyt_back:
                finish();
                break;
            case R.id.btn_submit_order:
                Activitys.toSelectType(CheckOrderActivity.this);
                break;
            default:
                break;
        }
    }

}
