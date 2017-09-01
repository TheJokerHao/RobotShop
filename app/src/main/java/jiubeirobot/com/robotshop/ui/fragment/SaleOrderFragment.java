package jiubeirobot.com.robotshop.ui.fragment;

import com.fecmobile.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import jiubeirobot.com.robotshop.R;
import jiubeirobot.com.robotshop.base.BaseBean;
import jiubeirobot.com.robotshop.base.BaseFragment;
import jiubeirobot.com.robotshop.data.net.APIManager;
import jiubeirobot.com.robotshop.data.net.Flags;
import jiubeirobot.com.robotshop.utils.PageUtils;
import jiubeirobot.com.robotshop.utils.SharedPreferencesUtils;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/29.
 * 创建内容：销售订单的fragment
 * ==========================
 */

public class SaleOrderFragment extends BaseFragment implements XRecyclerView.LoadingListener {
    @BindView(R.id.xrv_sale_order)
    XRecyclerView xrv_saleorder;
    private PageUtils pageUtils = new PageUtils();


    @Override
    public int pageLayout() {
        return R.layout.fragment_sale_order;
    }

    @Override
    public void initTitle() {

    }


    @Override
    public void initView() {
        xrv_saleorder.setPullRefreshEnabled(true);//设置下拉刷新为TRUE
        xrv_saleorder.setLoadingMoreEnabled(false);//设置加载更多
        xrv_saleorder.setLoadingListener(this);//设置加载监听
        pageUtils.setPageSize(8);//设置每页的大小

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onSuccess(BaseBean bean, int flag) {
    }

    /**
     * recyclerview加载监听
     */
    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
