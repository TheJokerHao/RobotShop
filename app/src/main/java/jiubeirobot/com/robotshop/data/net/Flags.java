package jiubeirobot.com.robotshop.data.net;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/18.
 * 创建内容：定义的常量  根据这些返回值的常量来对请求数据的判定
 * ==========================
 */

public interface Flags {
    /**
     * 关于商品的flag开始
     */
    int FLAG_SHOP_LIST = 1001;//商品列表
    int FLAG_LOGIN_SYSTEM = 50001;//登录系统
    int FLAG_SALES_ORDER = 60001;//销售订单
    int FLAG_TAKE_GODS = 20001;//提货申请
    int FLAG_GET_JIUBEI = 3001;




    int FLAG_VERSION_CODE = 40001;//版本更新
    int FLAG_TEST_CODE = 40002;//测试
    int FLAG_PUT_ROBOT_INFO = 40003;//测试

}
