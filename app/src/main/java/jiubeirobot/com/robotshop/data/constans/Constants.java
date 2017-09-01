package jiubeirobot.com.robotshop.data.constans;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/22.
 * 创建内容：一些常量参数的识别设置
 * ==========================
 */

public interface Constants {
    String ROBOT_SIGN_UUID_KEY = "ROBOT_SIGN_UUID_KEY";//机器人识别码   Mac 地址
    String INTENT_SHOP_BARCODE = "INTENT_SHOP_BARCODE"; // 商品条码    扫码显示商品详情
    String TAKE_GOODS_BARCODE = "TAKE_GOODS_BARCODE";  //提货的商品码  一般是二维码
    String SEARCH_HISTORY_RECORD = "SEARCH_HISTORY_RECORD";//历史搜索记录
    String INTENT_SHOP_SEARCH_RESULT = "INTENT_SHOP_SEARCH_RESULT";//商品搜索结果
    String INTENT_SHOP_LIST_TYPE = "INTENT_SHOP_LIST_TYPE";//跳转到商品列表，根据这个类型显示具体内容
    String INTENT_SHOP_DETAILS = "INTENT_SHOP_DETAILS";//商品详情
    String INTENT_SHOP_ID = "INTENT_SHOP_ID";//商品ID


}
