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
import jiubeirobot.com.robotshop.common.view.CircularImageView;

/**
 * @date 创建时间:2017/8/22
 * @author TheJoker丶豪
 * 显示初始设置的页面
 */
public class ShowSetActivity extends BaseActivity {
    @BindView(R.id.iv_search_icon)
    ImageView ivsearch;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.llyt_back)
    LinearLayout llback;

    @BindView(R.id.civ_head_photo)
    CircularImageView headphoto;
    @BindView(R.id.tv_user_name)
    TextView username;
    @BindView(R.id.tv_account_name)
    TextView accountname;
    @BindView(R.id.tv_user_account)
    TextView useraccount;



    @Override
    public int pageLayout() {
        return R.layout.activity_show_set;
    }

    @Override
    public void initTitle() {
        ivsearch.setVisibility(View.GONE);
        tvTitle.setText(getString(R.string.inilite_set));
    }

    @Override
    public void initView() {

    }

    @Override
    public void onSuccess(BaseBean bean, int flag) {

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
