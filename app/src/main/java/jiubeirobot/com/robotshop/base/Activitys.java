package jiubeirobot.com.robotshop.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import jiubeirobot.com.robotshop.data.constans.Constants;
import jiubeirobot.com.robotshop.ui.activity.ScanResultActivity;
import jiubeirobot.com.robotshop.ui.activity.manange.AboutUSActivity;
import jiubeirobot.com.robotshop.ui.activity.manange.LoginManagerActivity;
import jiubeirobot.com.robotshop.ui.activity.MainActivity;
import jiubeirobot.com.robotshop.ui.activity.SetRobotActivity;
import jiubeirobot.com.robotshop.ui.activity.manange.ShowSetActivity;
import jiubeirobot.com.robotshop.ui.activity.shop.CheckOrderActivity;
import jiubeirobot.com.robotshop.ui.activity.shop.QRPayActivity;
import jiubeirobot.com.robotshop.ui.activity.shop.SearchShopActivity;
import jiubeirobot.com.robotshop.ui.activity.shop.SelectOrderPayTypeActivity;
import jiubeirobot.com.robotshop.ui.activity.shop.ShopBuyListActivity;
import jiubeirobot.com.robotshop.ui.activity.shop.ShopOrderActivity;
import jiubeirobot.com.robotshop.ui.activity.manange.SysSetActivity;
import jiubeirobot.com.robotshop.ui.activity.manange.TakeGoodsActivity;
import jiubeirobot.com.robotshop.ui.activity.shop.ShoppingCartActivity;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/21.
 * 创建内容：定义的所有跳转的工具类
 * ==========================
 */

public class Activitys {
    /**
     * 跳转主页
     */
    public static void toMain(Activity aty) {
        Intent intent = new Intent(aty, MainActivity.class);
        aty.startActivity(intent);
    }
    /**
     * 跳转初始设置页面
     */
    public static void toSetAct(Activity aty) {
        Intent intent = new Intent(aty, SetRobotActivity.class);
        aty.startActivity(intent);
    }

    /**
     * 跳转至商品展示列表
     */
    public static void toShoplist(Activity aty) {
        Intent intent = new Intent(aty, ShopBuyListActivity.class);
        aty.startActivity(intent);
    }

    /**
     * 跳转至商品订单显示
     */
    public static void toShoporder(Activity aty) {
        Intent intent = new Intent(aty, ShopOrderActivity.class);
        aty.startActivity(intent);
    }
    /**
     * 跳转至系统设置
     */
    public static void toSetSys(Activity aty) {
        Intent intent = new Intent(aty, SysSetActivity.class);
        aty.startActivity(intent);
    }
    /**
     * 跳转至登录
     */
    public static void toLogin(Activity aty) {
        Intent intent = new Intent(aty, LoginManagerActivity.class);
        aty.startActivity(intent);
    }

    /**
     * 跳转至提货
     */
    public static void toTake(Activity aty) {
        Intent intent = new Intent(aty, TakeGoodsActivity.class);
        aty.startActivity(intent);
    }
    /**
     * 跳转至扫码结果
     */
    public static void toScanResult(Context context, String code){
        Intent intent = new Intent(context, ScanResultActivity.class);
        intent.putExtra(Constants.INTENT_SHOP_BARCODE, code);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    /**
     * 跳转至提货
     */
    public static void toExtract(Context context, String code){
        Intent intent = new Intent(context, TakeGoodsActivity.class);
        intent.putExtra(Constants.TAKE_GOODS_BARCODE, code);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    /**
     * 跳转至关于我们
     */
    public static void toAbout(Activity aty) {
        Intent intent = new Intent(aty, AboutUSActivity.class);
        aty.startActivity(intent);
    }

    /**
     * 跳转至关于我们
     */
    public static void toShowSet(Activity aty) {
        Intent intent = new Intent(aty, ShowSetActivity.class);
        aty.startActivity(intent);
    }

    /**
     * 跳转至购物车
     * @param aty
     */
    public static void toShopCard(Activity aty){
        Intent intent = new Intent(aty, ShoppingCartActivity.class);
        aty.startActivity(intent);
    }

    /**
     * 跳转搜索商品
     */
    public static void toSearchShop(Activity aty, boolean isShoList) {
        Intent intent;
        if (!isShoList) {
            intent = new Intent(aty, ShopBuyListActivity.class);
            aty.startActivity(intent);
        }
        intent = new Intent(aty, SearchShopActivity.class);
        aty.startActivityForResult(intent, 0);
    }

    /**
     * 跳转至核算订单
     * @param aty
     */

    public static void toCheckOrder(Activity aty){
        Intent intent = new Intent(aty, CheckOrderActivity.class);
        aty.startActivity(intent);
    }

    /**
     * 跳转至支付
     */
    public static void toSelectType(Activity aty) {
        Intent intent = new Intent(aty, SelectOrderPayTypeActivity.class);
        aty.startActivity(intent);
    }

    /**
     * 跳转至支付
     */
    public static void toQRPay(Activity aty){
        Intent intent = new Intent(aty, QRPayActivity.class);
        aty.startActivity(intent);
    }
}
