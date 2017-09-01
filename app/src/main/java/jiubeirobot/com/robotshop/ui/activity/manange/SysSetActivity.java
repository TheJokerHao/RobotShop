package jiubeirobot.com.robotshop.ui.activity.manange;

import android.app.Dialog;
import android.util.Log;
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
import jiubeirobot.com.robotshop.ui.dialog.CommomDialog;
import jiubeirobot.com.robotshop.utils.CacheUtil;

/**
 * @author TheJoker丶豪
 *         <p>
 *         系统设置的activity
 * @date 创建时间:2017/8/21
 */
public class SysSetActivity extends BaseActivity {
    @BindView(R.id.btn_version_up)
    Button btn_upVersion;
    @BindView(R.id.btn_clear_cache)
    Button btn_clearCache;
    @BindView(R.id.btn_Initia_set)
    Button btn_InitiaSet;
    @BindView(R.id.btn_about_us)
    Button btn_Aboutus;
    @BindView(R.id.btn_order_manage)
    Button btn_ordermanage;

    @BindView(R.id.llyt_back)
    LinearLayout llytback;
    @BindView(R.id.tv_title)
    TextView tvtitle;
    @BindView(R.id.iv_search_icon)
    ImageView ivsearch;

    @Override
    public int pageLayout() {
        return R.layout.activity_sys_set;
    }

    @Override
    public void initTitle() {
        tvtitle.setText(getString(R.string.system_title_text));
        ivsearch.setVisibility(View.GONE);
    }

    @Override
    public void initView() {

    }

    @Override
    public void onSuccess(BaseBean bean, int flag) {

    }

    @OnClick({R.id.btn_version_up, R.id.btn_clear_cache, R.id.btn_Initia_set, R.id.btn_about_us,R.id.btn_order_manage,R.id.llyt_back})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.llyt_back:
                finish();
                break;
            case R.id.btn_order_manage:
                Activitys.toShoporder(SysSetActivity.this);
                break;
            case R.id.btn_version_up:
                //在这里请求服务端版本数据  根据返回值来进行判定是否需要更新
                new CommomDialog(SysSetActivity.this, R.style.dialog, "是否更新最新版本？", new CommomDialog.OnCloseListener() {

                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            //获取服务端的数据更新版本
                            dialog.dismiss();

                        }

                    }
                }).setTitle("版本更新").show();
                break;
            case R.id.btn_clear_cache:
                //获取缓存信息  然后清楚缓存
                new CommomDialog(SysSetActivity.this, R.style.dialog, "确定清楚缓存吗？", new CommomDialog.OnCloseListener() {

                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            //获取系统缓存来清除
                            try {
                                String size = CacheUtil.getTotalCacheSize(getApplicationContext());
                                Log.d("print", "onClick: " + size);
                                CacheUtil.clearAllCache(getApplicationContext());
                                dialog.dismiss();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }).setTitle("清除缓存").show();

                break;
            case R.id.btn_Initia_set:
                Activitys.toShowSet(SysSetActivity.this);
                break;
            case R.id.btn_about_us:
                Activitys.toAbout(SysSetActivity.this);
                break;
            default:
                break;
        }
    }
}
