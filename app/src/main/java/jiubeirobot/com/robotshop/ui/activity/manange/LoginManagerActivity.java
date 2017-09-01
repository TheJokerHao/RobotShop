package jiubeirobot.com.robotshop.ui.activity.manange;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import jiubeirobot.com.robotshop.R;
import jiubeirobot.com.robotshop.base.Activitys;
import jiubeirobot.com.robotshop.base.BaseActivity;
import jiubeirobot.com.robotshop.base.BaseBean;
import jiubeirobot.com.robotshop.data.net.APIManager;
import jiubeirobot.com.robotshop.data.net.Flags;
import jiubeirobot.com.robotshop.utils.SharedPreferencesUtils;

/**
 * @date 创建时间:2017/8/21
 * @author TheJoker丶豪
 * 登录系统设置管理的activity
 */

public class LoginManagerActivity extends BaseActivity {

    @BindView(R.id.et_manage_account)
    EditText etaccount;
    @BindView(R.id.et_manage_password)
    EditText etpassword;
    @BindView(R.id.btn_manage_login)
    Button btnlogin;

    private String username;
    private String password;

    @Override
    public int pageLayout() {
        return R.layout.activity_login_manager;
    }

    @Override
    public void initTitle() {
        etaccount.clearFocus();
        etpassword.requestFocus();
        username = SharedPreferencesUtils.getString(this,"username");
        if(!TextUtils.isEmpty(username)){
            etaccount.setText(username);
        }
    }

    @Override
    public void initView() {

        password =etpassword.getText().toString().trim();

    }

    @Override
    public void onSuccess(BaseBean bean, int flag) {
        switch (flag){
            case Flags.FLAG_LOGIN_SYSTEM:
                //根据返回信息判断是否应该登录成功
                break;
            default:
                break;
        }

    }
    @OnClick({R.id.btn_manage_login})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.btn_manage_login:
                APIManager.getInstance().loginSys(this,username,password);
                Activitys.toSetSys(LoginManagerActivity.this);
                finish();
                break;
            default:
                break;
        }
    }

}
