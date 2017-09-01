package jiubeirobot.com.robotshop.ui.activity.shop;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import jiubeirobot.com.robotshop.R;
import jiubeirobot.com.robotshop.base.BaseActivity;
import jiubeirobot.com.robotshop.base.BaseBean;
import jiubeirobot.com.robotshop.base.ListBean;
import jiubeirobot.com.robotshop.bean.ShopListBean;
import jiubeirobot.com.robotshop.data.constans.Constants;
import jiubeirobot.com.robotshop.data.net.APIManager;
import jiubeirobot.com.robotshop.data.net.Flags;
import jiubeirobot.com.robotshop.listener.ItemClick;
import jiubeirobot.com.robotshop.ui.adapter.SearchRecodeAdapter;
import jiubeirobot.com.robotshop.ui.dialog.AlertDialog;
import jiubeirobot.com.robotshop.ui.dialog.ConfirmDropPointDialog;
import jiubeirobot.com.robotshop.utils.L;
import jiubeirobot.com.robotshop.utils.SPUtil;

public class SearchShopActivity extends BaseActivity {
    @BindView(R.id.llyt_back)
    LinearLayout llytback;
    @BindView(R.id.et_search_input)
    EditText etSearch;
    @BindView(R.id.rl_card)
    RelativeLayout rlcard;
    @BindView(R.id.llyt_point)
    LinearLayout llytPoint;
    @BindView(R.id.llyt_history)
    LinearLayout llytHistory;
    @BindView(R.id.rv_hot_list)
    RecyclerView rvHotList;
    @BindView(R.id.rv_recent_search_list)
    RecyclerView rvRecentSearchList;
    @BindView(R.id.iv_clear_search_recode)
    ImageView ivClearSearchRecode;
    private SearchRecodeAdapter hotListAdapter;
    private SearchRecodeAdapter recentSearchAdapter;
    private ConfirmDropPointDialog confirmDropPointDialog = new ConfirmDropPointDialog();
    private List<String> histroys = new ArrayList<>();
    private List<String> hotlist = new ArrayList<>();
    private Gson gson = new Gson();
    private int pageSize = 8;


    @Override
    public int pageLayout() {
        return R.layout.activity_search_shop;
    }

    @Override
    public void initTitle() {
        rlcard.setVisibility(View.GONE);

    }

    @Override
    public void initView() {
        rvHotList.setLayoutManager(new GridLayoutManager(this, 2));
        rvRecentSearchList.setLayoutManager(new LinearLayoutManager(this));
//        String hotStr = BaseData.getBaseData().getProtocolsInfoBean().getSite_search_hot_words();
//        if (hotStr != null) {
//            String[] hotWords = hotStr.split("，");
//            if (hotStr != null) {
//                hotlist.addAll(Arrays.asList(hotWords));
//            }
//        }

        hotListAdapter = new SearchRecodeAdapter(this, hotlist);
        recentSearchAdapter = new SearchRecodeAdapter(this, histroys);
        rvHotList.setAdapter(hotListAdapter);
        rvRecentSearchList.setAdapter(recentSearchAdapter);

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //TODO  请求数据商品数据
                    APIManager.getInstance().shopList(SearchShopActivity.this, etSearch.getText() + "", 0, pageSize);
                    histroys.add(0, etSearch.getText() + "");
                    while (histroys.size() > 3) {
                        histroys.remove(histroys.size() - 1);
                    }
                    L.i("--------------" + histroys.size());
                    recentSearchAdapter.notifyDataSetChanged();
                    return true;
                }
                return false;
            }
        });

        confirmDropPointDialog.setClickLisener(new ConfirmDropPointDialog.ClickLisener() {

            @Override
            public void onLeftClick(AlertDialog dialog) {

            }

            @Override
            public void onRightClick(AlertDialog dialog) {
                SPUtil.put(SearchShopActivity.this, Constants.SEARCH_HISTORY_RECORD, "");
                histroys.clear();
                recentSearchAdapter.notifyDataSetChanged();
            }
        });

        final String histroy = SPUtil.get(this, Constants.SEARCH_HISTORY_RECORD, "") + "";
        L.i(histroy);
//        Type type = new TypeToken<List<String>>() {
//        }.getType();
        List<String> strs = gson.fromJson(histroy, new TypeToken<List<String>>() {
        }.getType());

        L.i("////////////////////////////////" + strs);

        if (strs != null) {
            histroys.addAll(strs);
            recentSearchAdapter.notifyDataSetChanged();
        }
        hotListAdapter.setItemClick(new ItemClick() {
            @Override
            public void onItemClick(int postion) {
                etSearch.setText(hotlist.get(postion));
                //请求服务端数据
                APIManager.getInstance().shopList(SearchShopActivity.this, hotlist.get(postion), 0, pageSize);
            }
        });
        recentSearchAdapter.setItemClick(new ItemClick() {
            @Override
            public void onItemClick(int postion) {
                etSearch.setText(histroys.get(postion));
                // 请求服务端数据
                APIManager.getInstance().shopList(SearchShopActivity.this, histroys.get(postion) + "", 0, pageSize);
            }
        });


    }

    @Override
    public void onSuccess(BaseBean bean, int flag) {
        switch (flag) {
            case Flags.FLAG_SHOP_LIST:
                BaseBean<ListBean<ShopListBean>> listBeanBaseBean = bean;
                int rowCount = listBeanBaseBean.getData().getRowCount();
                if (rowCount == 0) {
                    llytPoint.setVisibility(View.VISIBLE);
                    llytHistory.setVisibility(View.GONE);
                } else {
                    Intent intent = new Intent();
                    intent.putExtra(Constants.INTENT_SHOP_SEARCH_RESULT, gson.toJson(listBeanBaseBean.getData().getList()));
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            default:
                break;
        }
        dismissHUD();

    }

    @OnClick({R.id.llyt_back, R.id.iv_clear_search_recode})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.llyt_back:
                finish();
                break;
            case R.id.iv_clear_search_recode:
                confirmDropPointDialog.show(this, getString(R.string.shopping_list_delete_search_recode), getString(R.string.submit), getString(R.string.cancel));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        confirmDropPointDialog.dismiss();
    }


}

