package jiubeirobot.com.robotshop.utils.glide;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import jiubeirobot.com.robotshop.R;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/21.
 * 创建内容：加载图片定义接口常量类
 * ==========================
 */

public interface IImageLoad {
    /**
     * 加载中
     */
    int IMG_LOADING = R.drawable.defualt_img;
    /**
     * 加载失败
     */
    int IMG_ERROR = R.drawable.defualt_img;

    /**
     * 加载失败
     */
    int PLACEHOLDER = R.drawable.defualt_img;

    void load(Activity activity, Object path, ImageView imageView);

    void load(Fragment fragment, Object path, ImageView imageView);

    void load(Context context, Object path, ImageView imageView);


    /**
     * 清除内存中的缓存 必须在UI线程中调用
     */
    void clearMemory();

    /**
     * 清除磁盘中的缓存 必须在后台线程中调用，建议同时clearMemory()
     */
    void clearDiskCache();
}
