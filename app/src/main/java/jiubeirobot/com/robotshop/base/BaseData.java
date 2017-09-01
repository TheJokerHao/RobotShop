package jiubeirobot.com.robotshop.base;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.provider.SyncStateContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jiubeirobot.com.robotshop.bean.StockBean;
import jiubeirobot.com.robotshop.data.constans.Constants;
import jiubeirobot.com.robotshop.utils.Arith;
import jiubeirobot.com.robotshop.utils.DetectTool;
import jiubeirobot.com.robotshop.utils.L;
import jiubeirobot.com.robotshop.utils.SPUtil;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/22.
 * 创建内容：数据总类
 * ==========================
 */

public class BaseData {
    private static BaseData baseData = new BaseData();

    private double totalMoney;



    public static BaseData getBaseData() {
        return baseData;
    }

    public static void setBaseData(BaseData baseData) {
        BaseData.baseData = baseData;
    }

    private String ROBOT_SIGN;//机器人UUID
    //购物车
    private List<StockBean> cardShops = new ArrayList<>();

    public int getCardNums() {
        return cardNums;
    }

    public void setCardNums(int cardNums) {
        this.cardNums = cardNums;
    }

    private int cardNums;

    public List<StockBean> getCardShops() {
        return cardShops;
    }

    public void setCardShops(List<StockBean> cardShops) {
        this.cardShops = cardShops;
    }

    public void clearCartShops() {
        cardShops.clear();
        cardNums = 0;
    }


    public String getTotalMoney() {
        return Arith.get2Decimal(totalMoney + "");
    }

    public int getCardItemNum() {
        return cardShops.size();
    }

    public int queryShopNum(String sid) {
        Iterator<StockBean> iterator = cardShops.iterator();
        while (iterator.hasNext()) {
            StockBean nextBean = iterator.next();
            if (nextBean.getId().equals(sid)) {
                return nextBean.getNum();
            }
        }
        return 0;
    }


    /**
     * 添加商品到购物车
     * @param bean
     * @param num
     */

    public void addShop(StockBean bean, int num) {
        L.i("addShop：" + bean.toString());
        totalMoney = 0;
        cardNums = 0;
        boolean isAdd = true;
        Iterator<StockBean> iterator = cardShops.iterator();
        while (iterator.hasNext()) {
            StockBean nextBean = iterator.next();
            if (nextBean.getId().equals(bean.getId())) {
                nextBean.setNum(nextBean.getNum() + num);
                isAdd = false;
            }
            cardNums += nextBean.getNum();
            totalMoney += nextBean.getNum() * Double.parseDouble(nextBean.getLsPrice());
        }
        if (isAdd) {
            bean.setNum(num);
            cardNums += bean.getNum();
            totalMoney += num * Double.parseDouble(bean.getLsPrice());
            cardShops.add(bean);
        }
    }




    /**
     * 获取机器人的Mac地址
     * @param application
     * @return
     */
    public String getROBOT_SIGN(BaseApplication application) {//取Mac地址
        ROBOT_SIGN = (String) SPUtil.get(application, Constants.ROBOT_SIGN_UUID_KEY, "");
        if (ROBOT_SIGN == null || ROBOT_SIGN.length() == 0) {
            WifiManager wm = (WifiManager) application.getSystemService(Context.WIFI_SERVICE);
            String macAddress = wm.getConnectionInfo().getMacAddress();
            ROBOT_SIGN = macAddress;
            if (ROBOT_SIGN == null || ROBOT_SIGN.length() == 0) {//从SP获取失败？
                ROBOT_SIGN = DetectTool.getUUID();
            }
            SPUtil.put(application, Constants.ROBOT_SIGN_UUID_KEY, ROBOT_SIGN);
        }
        Log.d("print", "getROBOT_SIGN: " + ROBOT_SIGN.toLowerCase());
        return ROBOT_SIGN.toLowerCase();
    }

}
