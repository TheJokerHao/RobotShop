package jiubeirobot.com.robotshop.data.net;


/**
 * Created by Administrator on 2017/2/18.
 */

public interface IUIBase {
    int pageLayout();

    void initTitle();

    void initView();

    void showHUD();

    void dismissHUD();
}
