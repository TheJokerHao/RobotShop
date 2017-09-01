package jiubeirobot.com.robotshop.ui.activity;

import jiubeirobot.com.robotshop.base.BaseActivity;
import jiubeirobot.com.robotshop.base.BaseBean;
import jiubeirobot.com.robotshop.data.constans.Constants;
import jiubeirobot.com.robotshop.utils.L;

/**
 * @date 创建时间:2017/8/23
 * 监测扫码枪扫描数据返回的接收的activity
 * @author TheJoker丶豪
 */

public class ScanResultActivity extends BaseActivity {
    String barcode;//扫描的商品的码的值

    @Override
    public int pageLayout() {
        return -1;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {
        barcode = getIntent().getStringExtra(Constants.INTENT_SHOP_BARCODE);

        L.i(barcode);
    }

    @Override
    public void onSuccess(BaseBean bean, int flag) {

    }

    @Override
    public void onError(String error, int flag) {
        super.onError(error, flag);
        finish();
    }
}
