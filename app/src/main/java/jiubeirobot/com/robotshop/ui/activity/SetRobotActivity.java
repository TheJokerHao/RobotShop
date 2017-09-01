package jiubeirobot.com.robotshop.ui.activity;


import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.OnClick;
import jiubeirobot.com.robotshop.R;
import jiubeirobot.com.robotshop.base.Activitys;
import jiubeirobot.com.robotshop.base.BaseActivity;
import jiubeirobot.com.robotshop.base.BaseBean;
import jiubeirobot.com.robotshop.bean.RobotInfoBean;
import jiubeirobot.com.robotshop.common.view.AddressSelectedView;
import jiubeirobot.com.robotshop.data.net.APIManager;
import jiubeirobot.com.robotshop.utils.L;
import jiubeirobot.com.robotshop.utils.RegexUtils;
import jiubeirobot.com.robotshop.utils.SPUtil;
import jiubeirobot.com.robotshop.utils.SharedPreferencesUtils;
import jiubeirobot.com.robotshop.utils.T;

/**
 * @author TheJoker丶豪
 *         这是初始化APP的时候需要设置资料的activity
 * @date 创建时间:2017/8/18
 */

public class SetRobotActivity extends BaseActivity {
    //经度
    @BindView(R.id.et_longitude)
    EditText etlongitude;
    //纬度
    @BindView(R.id.et_latitude)
    EditText etlatitude;
    //地址选择器
    AddressSelectedView llytaddress;
    //账户名
    @BindView(R.id.et_number)
    EditText etnumber;

    @BindView(R.id.btn_setfinish)
    Button btnSetfinish;
    @BindView(R.id.llyt_address_item)
    LinearLayout llytAddressItem;

    RobotInfoBean robotInfoBean;
    String longitude;
    String latitude;
    String province;
    String city;
    String district;
    String username;


    @Override
    public int pageLayout() {
        return R.layout.activity_set_robot;
    }

    @Override
    public void initTitle() {
        boolean isFirst = SharedPreferencesUtils.getboolean(SetRobotActivity.this, "isfrist");
        L.d(isFirst);
        if (isFirst) {
            Activitys.toMain(SetRobotActivity.this);
            finish();
        } else {
            Activitys.toSetAct(SetRobotActivity.this);
        }

    }

    @Override
    public void initView() {
        llytaddress = (AddressSelectedView) findViewById(R.id.llyt_address);

        llytaddress.setCancelClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llytaddress.setVisibility(View.GONE);
            }
        });

        llytaddress.setOptionsSelectListener(new AddressSelectedView.OptionsSelectListener() {
            @Override
            public void onOptionsSelect(String province, String city, String district, View v) {
                //设置地区
                SetRobotActivity.this.province = province;
                SetRobotActivity.this.city = city;
                SetRobotActivity.this.district = district;
                llytaddress.setVisibility(View.GONE);
            }
        });


    }

    @Override
    public void onSuccess(BaseBean bean, int flag) {

    }

    @OnClick({R.id.btn_setfinish, R.id.llyt_address_item})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_setfinish:
                username = etnumber.getText().toString();
                if (!TextUtils.isEmpty(username)) {
                    if (RegexUtils.checkMobile(username)) {
                        Activitys.toMain(SetRobotActivity.this);
                        SharedPreferencesUtils.putBoolean(SetRobotActivity.this, "isfrist", true);
                        robotInfoBean = new RobotInfoBean();
                        robotInfoBean.setCity(city);
                        robotInfoBean.setDistrict(district);
                        robotInfoBean.setLatitude(latitude);
                        robotInfoBean.setLongitude(longitude);
                        robotInfoBean.setProvince(province);
                        robotInfoBean.setNumber(username);
//                        APIManager.getInstance().putRobotInfoToServer(SetRobotActivity.this,robotInfoBean);
                        finish();
                    } else {
                        T.showToCenter(getString(R.string.mobile_number_error));
                    }
                } else {
                    T.showToCenter(getString(R.string.mobile_number_error));
                }

                if (!TextUtils.isEmpty(username) && RegexUtils.checkMobile(username)) {
                    SharedPreferencesUtils.putString(SetRobotActivity.this, "username", username);
                }

                break;
            case R.id.llyt_address_item:
                if (llytaddress.getVisibility() == View.VISIBLE) {
                    llytaddress.setVisibility(View.GONE);
                } else {
                    llytaddress.setVisibility(View.VISIBLE);
                }
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
