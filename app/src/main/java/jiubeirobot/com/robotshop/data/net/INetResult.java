package jiubeirobot.com.robotshop.data.net;

import jiubeirobot.com.robotshop.base.BaseBean;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/18.
 * 创建内容：定义请求数据返回值判断的接口
 * ==========================
 */

public interface INetResult {
    void requestBefore(int flag);

    void onSuccess(BaseBean bean, int flag);

    void onError(String error, int flag);

}
