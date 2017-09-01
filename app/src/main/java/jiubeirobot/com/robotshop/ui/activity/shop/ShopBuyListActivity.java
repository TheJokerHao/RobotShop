package jiubeirobot.com.robotshop.ui.activity.shop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fecmobile.xrecyclerview.XRecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import jiubeirobot.com.robotshop.R;
import jiubeirobot.com.robotshop.base.Activitys;
import jiubeirobot.com.robotshop.base.BaseActivity;
import jiubeirobot.com.robotshop.base.BaseBean;
import jiubeirobot.com.robotshop.base.BaseData;
import jiubeirobot.com.robotshop.bean.ShopListBean;
import jiubeirobot.com.robotshop.bean.StockBean;
import jiubeirobot.com.robotshop.data.constans.Constants;
import jiubeirobot.com.robotshop.data.net.Flags;
import jiubeirobot.com.robotshop.ui.adapter.ShopListAdapter;
import jiubeirobot.com.robotshop.ui.fragment.ShopDetailsFragment;
import jiubeirobot.com.robotshop.utils.L;
import jiubeirobot.com.robotshop.utils.PageUtils;
import jiubeirobot.com.robotshop.utils.T;

/**
 * @author TheJoker丶豪
 *         此activity用于展示商品列表  并且在此activity中筛选 排序等都可以放在这里
 * @date 创建时间:2017/8/18
 */

public class ShopBuyListActivity extends BaseActivity implements XRecyclerView.LoadingListener, DrawerLayout.DrawerListener, ShopListAdapter.CellClick {
    //布局开始
    @BindView(R.id.rv_view)
    XRecyclerView shopRecyclerView;
    @BindView(R.id.btn_default)
    Button btndefault;
    @BindView(R.id.et_search_input)
    EditText etSearch;
    @BindView(R.id.rl_card)
    RelativeLayout rlCard;
    @BindView(R.id.dl_shop_conetn_and_details)
    DrawerLayout dL_shopdetails;
    @BindView(R.id.llyt_price)
    LinearLayout llyt_price;
    @BindView(R.id.btn_price)
    TextView tv_price;
    @BindView(R.id.iv_price)
    ImageView iv_price;
    @BindView(R.id.llyt_shop_list_filter_filter)
    LinearLayout llyt_filter;
    @BindView(R.id.btn_filter)
    TextView tv_filter;
    @BindView(R.id.dlt_filter_content)
    DrawerLayout dl_filtercontent;
    @BindView(R.id.frame_content)
    FrameLayout frame_content;
    @BindView(R.id.fl_shop_details)
    FrameLayout fl_shop_details;
    @BindView(R.id.llyt_back)
    LinearLayout ll_back;
    @BindView(R.id.btn_sales)
    Button btnsales;
    @BindView(R.id.tv_shop_number)
    TextView tv_shopnumber;


    private PageUtils pageUtils = new PageUtils();

    private ViewGroup anim_mask_layout;//动画层

    private ShopDetailsFragment shopDetailsFragment;
    private ShopListAdapter shopListAdapter;
    List<ShopListBean> datalist = new ArrayList<>();


    private String sort = "0";
    private int priceType;

    //各个过滤的选择的信息
    private String priceBegin;//价格开始
    private String priceEnd;//价格结束
    private String years;//年份

    @Override
    public int pageLayout() {
        return R.layout.activity_shop_buy_list;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {
        shopRecyclerView.setPullRefreshEnabled(true);//设置下拉刷新为TRUE
        shopRecyclerView.setLoadingMoreEnabled(false);//设置加载更多
        shopRecyclerView.setLoadingListener(this);//设置加载监听
        pageUtils.setPageSize(10);//设置每页的大小


        shopListAdapter = new ShopListAdapter(ShopBuyListActivity.this, datalist);


        dl_filtercontent.addDrawerListener(this);
        dL_shopdetails.addDrawerListener(this);

        shopRecyclerView.setLayoutManager(new GridLayoutManager(ShopBuyListActivity.this, 2));
        dL_shopdetails.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        shopListAdapter.setCellClick(this);
        //给recyclerview设置适配器
//        shopRecyclerView.setAdapter(shopListAdapter);


        etSearch.setKeyListener(null);
        etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activitys.toSearchShop(ShopBuyListActivity.this, true);
            }
        });

        /**
         * 设置购物车数字的监听
         */
        tv_shopnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (tv_shopnumber.getText().length() == 0) {
                    tv_shopnumber.setVisibility(View.INVISIBLE);
                } else {
                    tv_shopnumber.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        getBarcode();
        shopRecyclerView.refresh();
        L.i("initView");


    }

    /**
     * 获取传递过来的扫码值 显示商品详情
     */
    private void getBarcode() {

        String type = getIntent().getStringExtra(Constants.INTENT_SHOP_LIST_TYPE);
        if (Constants.INTENT_SHOP_DETAILS.equals(type)) {
            String shopId = getIntent().getStringExtra(Constants.INTENT_SHOP_BARCODE);
            L.i("----shopId：" + shopId);
//            添加显示商品详情的方法
            addShopDetailsFragment(shopId);
        }


    }

    /**
     * 根据传入的商品id 显示商品详情
     *
     * @param id
     */
    private void addShopDetailsFragment(String id) {

        if (shopDetailsFragment == null) {
            shopDetailsFragment = new ShopDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.INTENT_SHOP_ID, id);
            shopDetailsFragment.setArguments(bundle);
        } else {
            shopDetailsFragment.setId(id);
            shopDetailsFragment.onResume();
        }

        FragmentTransaction ftrans = getSupportFragmentManager().beginTransaction();
        ftrans.replace(R.id.fl_shop_details, shopDetailsFragment);
        ftrans.commitAllowingStateLoss();
        dL_shopdetails.openDrawer(Gravity.RIGHT);

    }

    @Override
    public void onSuccess(BaseBean bean, int flag) {
        switch (flag) {
            case Flags.FLAG_SHOP_LIST:
                break;

            default:
                break;
        }
    }

    /**
     * 描述      :关闭商品详情
     * 方法名    :  closeShopDetails
     * param    :   无
     * 创建时间  : 2017/3/9 11:22
     * 修改人    :
     * 修改时间
     * 修改备注
     * throws
     */
    public void closeShopDetails() {
        dL_shopdetails.closeDrawer(Gravity.RIGHT);
    }


    /**
     * 描述      : 关闭筛选
     * 方法名    :  closeFilter
     * param    : 无
     * 修改人    :
     * 修改时间
     * 修改备注
     * throws
     */
    public void closeFilter() {
        dl_filtercontent.closeDrawer(Gravity.RIGHT);
    }


    /**
     * 对点击搜索框之后的返回数据进行处理
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Activity.RESULT_OK) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<ShopListBean>>() {
            }.getType();
            String shopStrs = data.getStringExtra(Constants.INTENT_SHOP_SEARCH_RESULT);
            List<ShopListBean> shopList = gson.fromJson(shopStrs, type);
            if (shopList != null) {
                datalist.clear();
                datalist.addAll(shopList);
                shopListAdapter.notifyDataSetChanged();
            } else {
                submit();
            }
        }
    }

    /**
     * 描述      :返回上一个Fragment
     * 方法名    : backStack
     * param    :   无
     * 返回类型  : Void
     * 修改人    :
     * 修改时间
     * 修改备注
     * throws
     */
    public void backStack() {
        getSupportFragmentManager().popBackStack();
    }


    /**
     * 描述      : 重置
     * 方法名    :  submit
     * param    : 无
     * 返回类型  :void
     * 修改人    :
     * 修改时间
     * 修改备注
     * throws
     */
    public void reset() {
        priceBegin = null;
        priceEnd = null;
        years = null;
    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.rl_card, R.id.llyt_back, R.id.btn_default, R.id.btn_sales, R.id.llyt_price, R.id.llyt_shop_list_filter_filter})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.rl_card:
                //判断当前购物车是否存在有商品  有就跳转  没有就提示没有商品
                Activitys.toShopCard(ShopBuyListActivity.this);
                break;
            case R.id.llyt_back:
                finish();
                break;
            case R.id.btn_default:
                changeState(0);
                pageUtils.setCurentLoadMode(false);//刷新页面
                submit();
                break;
            case R.id.btn_sales:
                changeState(1);
                pageUtils.setCurentLoadMode(false);
                submit();
                break;
            case R.id.llyt_price:
                changeState(2);
                pageUtils.setCurentLoadMode(false);
                submit();
                break;
            case R.id.llyt_shop_list_filter_filter:
//                if (dltFilterContent.isDrawerOpen(Gravity.RIGHT)) {
//                    dltFilterContent.closeDrawer(Gravity.RIGHT);
//                } else {
//                    dltFilterContent.openDrawer(Gravity.RIGHT);
//                }
                changeState(3);
                break;
            default:
                break;
        }
    }

    /**
     * 点击改变状态的方法
     */
    private void changeState(int index) {
        btndefault.setTextColor(getResources().getColorStateList(R.color.color_33));
        btnsales.setTextColor(getResources().getColorStateList(R.color.color_33));
        tv_price.setTextColor(getResources().getColorStateList(R.color.color_33));
        tv_filter.setTextColor(getResources().getColorStateList(R.color.color_33));
        iv_price.setImageResource(R.mipmap.price_default);

        if (index == 0) {
            //默认按钮点击
            sort = "0";
            priceType = 0;
            btndefault.setTextColor(getResources().getColorStateList(R.color.color_main));
        } else if (index == 1) {
            //销量按钮
            sort = "1";
            priceType = 0;
            btnsales.setTextColor(getResources().getColorStateList(R.color.color_main));
        } else if (index == 2) {
            tv_price.setTextColor(getResources().getColorStateList(R.color.color_main));
            priceType++;
            sort = "3".equals(sort) ? "4" : "3";
            if (priceType > 2) {
                priceType = 1;
            }
        } else if (index == 3) {
            priceType = 0;
            tv_filter.setTextColor(getResources().getColorStateList(R.color.color_main));
        }

        if (priceType == 0) {
            iv_price.setImageResource(R.mipmap.price_default);
        } else if (priceType == 1) {
            iv_price.setImageResource(R.mipmap.price_down);
        } else {
            iv_price.setImageResource(R.mipmap.price_up);
        }

    }

    /**
     * 提交数据  请求数据的方法
     */
    public void submit() {

    }

    @Override
    public void onRefresh() {
        // 刷新加载数据
        submit();
    }

    @Override
    public void onLoadMore() {
        //上拉加载更多
        submit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /**
     * 测拉框的监听
     */
    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {
        switch (drawerView.getId()) {
            case R.id.frame_content:
                addShoplistFilterFragment();
                break;
            case R.id.fl_shop_details:
                dL_shopdetails.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
        if (shopDetailsFragment != null) {
            shopDetailsFragment.onResume();
        }
        setPrice();
    }

    /**
     * 设置价格
     */
    private void setPrice() {
        int size = BaseData.getBaseData().getCardNums();
        if (size == 0) {
            tv_shopnumber.setVisibility(View.INVISIBLE);
        } else if (size > 99) {
            tv_shopnumber.setVisibility(View.VISIBLE);
            tv_shopnumber.setText("99");
        } else {
            tv_shopnumber.setVisibility(View.VISIBLE);
            tv_shopnumber.setText(size + "");
        }
        if (shopDetailsFragment != null) {
            shopDetailsFragment.setCard();
        }


    }


    /**
     * 数据错误监听
     *
     * @param error
     * @param flag
     */
    @Override
    public void onError(String error, int flag) {
        super.onError(error, flag);

    }

    /**
     * 添加商品筛选到商品列表
     */
    private void addShoplistFilterFragment() {


    }

    @Override
    public void onDrawerClosed(View drawerView) {
        switch (drawerView.getId()) {
            case R.id.fl_shop_details:
                shopDetailsFragment.onDestroy();
                dL_shopdetails.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;
        }
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    /**
     * item 点击监听
     *
     * @param postion
     */
    @Override
    public void onItemClick(int postion) {

        //点击 item 显示当前点击position的商品详情


    }

    /**
     * 加入购物车按钮的监听
     *
     * @param view
     * @param startLocation
     * @param stockBean
     * @param shopNum
     */
    @Override
    public void addToCard(View view, int[] startLocation, StockBean stockBean, int shopNum) {
        if (stockBean == null) return;

        int stock = Integer.parseInt(stockBean.getProductStock());
        if (stock == 0) {
            T.showToCenter(getString(R.string.common_inventory));
            return;
        }

        int repertory = Integer.parseInt(stockBean.getProductStock());
        int purchaseNum = BaseData.getBaseData().queryShopNum(stockBean.getId());
        if (purchaseNum >= repertory) {
            T.showToCenter(getString(R.string.common_inventory));
            return;
        }
        if (stockBean != null) {
            BaseData.getBaseData().addShop(stockBean, shopNum);
        }
        L.i(BaseData.getBaseData().getCardShops());
        L.i(BaseData.getBaseData().getCardNums());

        setAnim(view, startLocation);

    }


    /**
     * 添加动画层
     *
     * @return
     */
    private ViewGroup createAnimLayout() {
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE - 1);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    /**
     * 将View添加动画图层
     *
     * @param parent
     * @param view
     * @param location
     * @return
     */
    private View addViewToAnimLayout(final ViewGroup parent, final View view, int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }



    /**
     * 开始动画
     *
     * @param v
     * @param startLocation
     */
    public void setAnim(final View v, int[] startLocation) {
        anim_mask_layout = null;
        anim_mask_layout = createAnimLayout();
        anim_mask_layout.addView(v);//把动画小球添加到动画层
        final View view = addViewToAnimLayout(anim_mask_layout, v, startLocation);
        int[] endLocation = new int[2];// 存储动画结束位置的X、Y坐标
        tv_shopnumber.getLocationInWindow(endLocation);

        L.i("endLocation：" + endLocation[0] + "  " + endLocation[1]);
        // 计算位移
        int endX = endLocation[0] - startLocation[0];// 动画位移的X坐标
        int endY = endLocation[1] - startLocation[1];// 动画位移的y坐标

        TranslateAnimation translateAnimationX = new TranslateAnimation(0, endX, 0, 0);
        translateAnimationX.setInterpolator(new LinearInterpolator());

        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0, 0, endY);
        translateAnimationY.setInterpolator(new AccelerateInterpolator());

        AnimationSet set = new AnimationSet(false);
        set.setFillAfter(false);
        set.addAnimation(translateAnimationY);
        set.addAnimation(translateAnimationX);
        set.setDuration(800);// 动画的执行时间
        view.startAnimation(set);
        // 动画监听事件
        set.setAnimationListener(new Animation.AnimationListener() {
            // 动画的开始
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
            // 动画的结束
            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.GONE);
                setPrice();
            }
        });
    }
}