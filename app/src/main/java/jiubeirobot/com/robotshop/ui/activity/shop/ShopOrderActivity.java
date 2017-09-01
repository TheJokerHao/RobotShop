package jiubeirobot.com.robotshop.ui.activity.shop;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import jiubeirobot.com.robotshop.R;
import jiubeirobot.com.robotshop.base.BaseActivity;
import jiubeirobot.com.robotshop.base.BaseBean;
import jiubeirobot.com.robotshop.data.net.APIManager;
import jiubeirobot.com.robotshop.ui.fragment.ExtractOrderFragment;
import jiubeirobot.com.robotshop.ui.fragment.SaleOrderFragment;
import jiubeirobot.com.robotshop.utils.L;
import jiubeirobot.com.robotshop.utils.SharedPreferencesUtils;

/**
 * @author TheJoker丶豪'
 *         商品订单管理的activity
 * @date 创建时间:2017/8/21
 */
public class ShopOrderActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_back_txt)
    TextView tvBackTxt;
    @BindView(R.id.iv_search_icon)
    ImageView ivSearchIcon;


    @BindView(R.id.rl_sale_order)
    RelativeLayout rlSaleordr;
    @BindView(R.id.rl_extract_order)
    RelativeLayout rlExtractorder;
    @BindView(R.id.fl_content)
    FrameLayout flcontent;

    @BindView(R.id.rb_sale_order)
    RadioButton rbsaleorder;
    @BindView(R.id.rb_extract_order)
    RadioButton rbextractorder;

    @BindView(R.id.v_sale_order)
    View vsaleorder;
    @BindView(R.id.v_extract_order)
    View vextractorder;

    SaleOrderFragment saleorderfragment;
    ExtractOrderFragment extractorderfragment;

    private String username;


    @Override
    public int pageLayout() {
        return R.layout.activity_shop_order;

    }

    @Override
    public void initTitle() {
        ivSearchIcon.setVisibility(View.GONE);
        tvTitle.setText("订单管理");

    }


    @Override
    public void initView() {
        username = SharedPreferencesUtils.getString(this, "username");
        APIManager.getInstance().getSalesOrder(this, this, username);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

    }

    @Override
    public void onSuccess(BaseBean bean, int flag) {

    }

    @OnClick({R.id.llyt_back, R.id.rl_sale_order, R.id.rl_extract_order})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.llyt_back:
                finish();
                break;
            case R.id.rl_sale_order:
                changeTab(0);
                break;
            case R.id.rl_extract_order:
                changeTab(1);
                break;
            default:
                break;
        }
    }

    private void changeTab(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (index == 0) {
            rbsaleorder.setChecked(true);
            rbextractorder.setChecked(false);
            vsaleorder.setVisibility(View.VISIBLE);
            vextractorder.setVisibility(View.INVISIBLE);

            if (saleorderfragment == null) {
                saleorderfragment = new SaleOrderFragment();
                transaction.add(R.id.fl_content, saleorderfragment);
            } else {
                transaction.show(saleorderfragment);
            }

            if (extractorderfragment != null) {
                extractorderfragment.onPause();
                transaction.hide(extractorderfragment);
            }

            transaction.commitAllowingStateLoss();

        } else if (index == 1) {
            L.i("index：" + index);
            rbsaleorder.setChecked(false);
            rbextractorder.setChecked(true);
            vsaleorder.setVisibility(View.INVISIBLE);
            vextractorder.setVisibility(View.VISIBLE);

            if (extractorderfragment == null) {
                extractorderfragment = new ExtractOrderFragment();
                transaction.add(R.id.fl_content, extractorderfragment);
            } else {
                transaction.show(extractorderfragment);
            }
            if (saleorderfragment != null) {
                saleorderfragment.onPause();
                transaction.hide(saleorderfragment);
            }
            transaction.commitAllowingStateLoss();

        }
    }
}
