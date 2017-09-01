package jiubeirobot.com.robotshop.ui.fragment;

import android.view.View;
import android.view.WindowManager;

import jiubeirobot.com.robotshop.R;
import jiubeirobot.com.robotshop.base.BaseBean;
import jiubeirobot.com.robotshop.base.BaseFragment;
import jiubeirobot.com.robotshop.data.constans.Constants;
import jiubeirobot.com.robotshop.data.net.APIManager;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/22.
 * 创建内容：
 * ==========================
 */

public class ShopDetailsFragment extends BaseFragment {
    private String id;

    @Override
    public int pageLayout() {
        id = getArguments().getString(Constants.INTENT_SHOP_ID);
        return R.layout.fragment_shop_details;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void initTitle() {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (id != null && id.length() > 0) {
            //TODO  这里获取本地购买页面的详情
//            APIManager.getInstance().shopDetails(getContext(), this, id);
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onSuccess(BaseBean bean, int flag) {

    }

    public void setCard() {
//        if (tvShopNumber == null) {
//            return;
//        }
//        int size = BaseData.getBaseData().getCardNums();
//        if (size == 0) {
//            tvShopNumber.setVisibility(View.INVISIBLE);
//        } else if (size > 99) {
//            tvShopNumber.setVisibility(View.VISIBLE);
//            tvShopNumber.setText("99");
//        } else {
//            tvShopNumber.setVisibility(View.VISIBLE);
//            tvShopNumber.setText(size + "");
//        }
    }

}
