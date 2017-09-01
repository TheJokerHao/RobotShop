package jiubeirobot.com.robotshop.data.net;

import android.content.Context;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import jiubeirobot.com.robotshop.R;
import jiubeirobot.com.robotshop.base.BaseBean;
import jiubeirobot.com.robotshop.common.core.HUDCallBack;
import jiubeirobot.com.robotshop.utils.L;
import jiubeirobot.com.robotshop.utils.OkHttpHelper;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/2/18.
 * 实现了网络数据加载的接口 进而对相应的返回数据进行判定
 */


public class NetLoadImpl implements INetLoad {

    /**
     * @param flag        请求返回数据的flag
     * @param relativeUrl 本地拼接的URL
     * @param params      参数
     * @param context     当前的上下文（分为activity  或者是 fragment）
     * @param iNetResult  返回数据监听的剪口 和上下文有相互联系
     * @param beanClz     TypeToken  返回需要的数据类型
     */
    @Override
    public void loadData(int flag, String relativeUrl, JSONObject params, Context context, final INetResult iNetResult, final Type beanClz) {

        //这里是定义的使用OKhttp来请求数据
        OkHttpHelper.getInstance().post(context, relativeUrl, params, new HUDCallBack<BaseBean>(context, flag) {
            @Override
            public void requestBefore(Request request) {
                iNetResult.requestBefore(flag);
                L.i("requestBefore");
            }

            @Override
            public void onFailure(Call call, IOException e) {
                iNetResult.onError(context.getString(R.string.error_call_back_no_error), flag);
                L.i("onFailure");
            }

            @Override
            public void onSuccess(Response response, BaseBean obj, int flag) {
                if (obj != null) {
                    L.i("obj!=null onSuccess");
                    iNetResult.onSuccess(obj, flag);
                } else {
                    L.i("obj==null onSuccess");
                    iNetResult.onError(context.getString(R.string.error_call_back_no_error), flag);
                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {
                String error = null;
                if (-1 == code) {
                    L.i("code == 900 state == 1");
                    try {
                        error = e.getMessage();
                    } catch (Exception e1) {
                        L.i("  line error " + e1);
                        error = context.getString(R.string.error_call_back_no_error);
                    }
                } else {
                    switch (response.code()) {
                        case -1:
                            error = context.getString(R.string.error_call_back_server_error);
                            break;
                        case 00000001:
                            error = context.getString(R.string.error_call_back_no_param);
                            break;
                        case 00000002:
                            error = context.getString(R.string.error_call_back_param_error);
                            break;
                        case 00000003:
                            error = context.getString(R.string.error_call_back_no_permission);
                            break;
                        case 00000004:  //保存的用户信息过期
                            error = context.getString(R.string.error_call_back_user_info_outtime);
                            break;
                        case 00000005:  //没有操作权限
                            error = context.getString(R.string.error_call_back_no_permission);
                            break;
                        case 00000006:  //没有操作权限
                            error = context.getString(R.string.error_call_back_no_user);
                            break;
                        case 00000007:  //没有操作权限
                            error = context.getString(R.string.error_call_back_no_permission);
                            break;
//                        case 00000008:
//                            error = context.getString(R.string.error_call_back_no_permission);
//                            break;
//                        case 00000009:  //没有操作权限
//                            error = context.getString(R.string.error_call_back_no_permission);
//                            break;
                        case 00000010:  //没有操作权限
                            error = context.getString(R.string.error_call_back_not_rule);
                            break;
                        default:
                            error = context.getString(R.string.error_call_back_no_error);
                            break;
                    }
                }
                iNetResult.onError(error, flag);
            }

            @Override
            public void onResponse(Response response) {
                L.i("onResponse");
            }
        }, beanClz);

    }


}
