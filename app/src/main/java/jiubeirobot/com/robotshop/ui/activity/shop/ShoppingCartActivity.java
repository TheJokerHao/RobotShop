package jiubeirobot.com.robotshop.ui.activity.shop;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import jiubeirobot.com.robotshop.R;
import jiubeirobot.com.robotshop.base.Activitys;
import jiubeirobot.com.robotshop.base.BaseActivity;
import jiubeirobot.com.robotshop.base.BaseBean;

/**
 * @date 创建时间:2017/8/22
 * @author TheJoker丶豪
 * 购物车的页面
 *
 */
public class ShoppingCartActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back_txt)
    TextView tvBackTxt;
    @BindView(R.id.iv_search_icon)
    ImageView ivSearchIcon;
    @BindView(R.id.btn_settlement)
    Button btnsettlement;


    @Override
    public int pageLayout() {
        return R.layout.activity_shopping_cart;
    }

    @Override
    public void initTitle() {
        tvBackTxt.setVisibility(View.GONE);
        ivSearchIcon.setVisibility(View.INVISIBLE);
        tvTitle.setText(getString(R.string.common_buy_card));
    }

    @Override
    public void initView() {

    }

    @Override
    public void onSuccess(BaseBean bean, int flag) {

    }

    @OnClick({R.id.llyt_back,R.id.btn_settlement})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.llyt_back:
                finish();
                break;
            case R.id.btn_settlement:
                //跳转至核算订单
                Activitys.toCheckOrder(ShoppingCartActivity.this);

                break;
            default:
                break;
        }
    }

}
