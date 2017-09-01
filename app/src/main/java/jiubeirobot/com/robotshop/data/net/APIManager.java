package jiubeirobot.com.robotshop.data.net;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import jiubeirobot.com.robotshop.base.BaseBean;
import jiubeirobot.com.robotshop.base.ListBean;
import jiubeirobot.com.robotshop.bean.ObjBean;
import jiubeirobot.com.robotshop.bean.RobotInfoBean;
import jiubeirobot.com.robotshop.bean.ShopListBean;
import jiubeirobot.com.robotshop.bean.VersionCodeBean;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/18.
 * 创建内容：定义请求数据的基类
 * ==========================
 */

public class APIManager {
    private static APIManager apiManager;
    private INetLoad iNetLoad = new NetLoadImpl();
    private Gson gson = new Gson();

    public static APIManager getInstance() {
        if (apiManager == null) {
            synchronized (APIManager.class) {
                if (apiManager == null) {
                    apiManager = new APIManager();
                }
            }
        }
        return apiManager;
    }

    /**
     * 提交初始设置信息到服务器
     */
    public void getRobotNumberForInfo(Context context) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("shop_id", "");
            jsonObject.put("beginCount", "0");
            jsonObject.put("selectCount", "20");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        iNetLoad.loadData(Flags.FLAG_TEST_CODE, URLS.URL_TEST_CANSHU, jsonObject, context, (INetResult) context, new TypeToken<BaseBean<ObjBean<VersionCodeBean>>>() {
        }.getType());

    }

    /**
     * 商品列表
     */
    public void shopList(Context context, String productname, int pageIndex, int pageSize) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productname", productname);
            jsonObject.put("pageIndex", pageIndex);
            jsonObject.put("pageSize", pageSize);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        iNetLoad.loadData(Flags.FLAG_SHOP_LIST, URLS.URL_SHOP_LIST, jsonObject, context, (INetResult) context, new TypeToken<BaseBean<ListBean<ShopListBean>>>() {
        }.getType());


    }


    /**
     * 提交机器人信息到服务端
     */
    public void putRobotInfoToServer(Context context, RobotInfoBean robotInfoBean) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(gson.toJson(robotInfoBean));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("print", "putRobotInfoToServer: " + jsonObject);
        iNetLoad.loadData(Flags.FLAG_PUT_ROBOT_INFO, URLS.URL_PUT_INFO, jsonObject, context, (INetResult) context, new TypeToken<BaseBean<ObjBean<RobotInfoBean>>>() {
        }.getType());
    }


    /**
     * 获取版本号更新版本
     */
    public void getVersionCode(Context context) {
        JSONObject jsonObject = new JSONObject();
        iNetLoad.loadData(Flags.FLAG_VERSION_CODE, URLS.URL_VERSION_CODE, jsonObject, context, (INetResult) context, new TypeToken<BaseBean<ObjBean<VersionCodeBean>>>() {
        }.getType());

    }

    /**
     * 登录管理系统
     */
    public void loginSys(Context context, String username, String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("", "");
            jsonObject.put("", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        iNetLoad.loadData(Flags.FLAG_LOGIN_SYSTEM, URLS.URL_LOGIN_SYSTEM, jsonObject, context, (INetResult) context, new TypeToken<BaseBean<ObjBean<VersionCodeBean>>>() {
        }.getType());

    }

    /**
     * 获取销售订单
     */
    public void getSalesOrder(Context context, INetResult iNetResult, String username) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        iNetLoad.loadData(Flags.FLAG_SALES_ORDER, URLS.URL_SALES_ORDER, jsonObject, context, iNetResult, new TypeToken<BaseBean<ListBean<VersionCodeBean>>>() {
        }.getType());
    }

    /**
     * 提交提货申请
     * username 当前店主账户
     */
    public void takeGods(Context context, String code, String username) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("code", code);
            jsonObject.put("username", username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        iNetLoad.loadData(Flags.FLAG_TAKE_GODS, URLS.URL_TAKE_GODS, jsonObject, context, (INetResult) context, new TypeToken<BaseBean<ObjBean<VersionCodeBean>>>() {
        }.getType());
    }

    /**
     * 关于久碑
     */
    public void getJiuBei(Context context) {
        JSONObject jsonObject = new JSONObject();
        iNetLoad.loadData(Flags.FLAG_GET_JIUBEI, URLS.URL_GET_JIUBEI, jsonObject, context, (INetResult) context, new TypeToken<BaseBean<ObjBean<VersionCodeBean>>>() {
        }.getType());
    }


}
