package jiubeirobot.com.robotshop.ui.fragment;

import com.fecmobile.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import jiubeirobot.com.robotshop.R;
import jiubeirobot.com.robotshop.base.BaseBean;
import jiubeirobot.com.robotshop.base.BaseFragment;
import jiubeirobot.com.robotshop.utils.PageUtils;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/29.
 * 创建内容：提货订单的fragment
 * ==========================
 */

public class ExtractOrderFragment extends BaseFragment implements XRecyclerView.LoadingListener {
    @BindView(R.id.xrv_extract_order)
    XRecyclerView xrv_extractorder;
    private PageUtils pageUtils = new PageUtils();



    @Override
    public int pageLayout() {
        return R.layout.fragment_extract_order;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {
        xrv_extractorder.setPullRefreshEnabled(true);//设置下拉刷新为TRUE
        xrv_extractorder.setLoadingMoreEnabled(false);//设置加载更多
        xrv_extractorder.setLoadingListener(this);//设置加载监听
        pageUtils.setPageSize(8);
    }

    @Override
    public void onSuccess(BaseBean bean, int flag) {

    }

    /**
     * recyclerview 加载监听
     */
    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
