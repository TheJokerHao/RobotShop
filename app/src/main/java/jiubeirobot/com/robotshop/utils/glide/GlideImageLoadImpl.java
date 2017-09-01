package jiubeirobot.com.robotshop.utils.glide;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import jiubeirobot.com.robotshop.base.BaseApplication;

/**
 * ==========================
 * 创建人：TheJoker丶豪 on
 * 创建时间：2017/8/21.
 * 创建内容：定义加载图片的工具类
 * ==========================
 */

public class GlideImageLoadImpl implements IImageLoad {
    //单例模式
    private static IImageLoad instance;

    public static IImageLoad getInstance() {
        if (instance == null) {
            synchronized (GlideImageLoadImpl.class) {
                if (instance == null) {
                    instance = new GlideImageLoadImpl();
                }
            }
        }
        return instance;
    }


    @Override
    public void load(Activity activity, Object path, ImageView imageView) {
        setDefaultLoadType(Glide.with(activity).load(path)).into(imageView);
    }

    @Override
    public void load(Fragment fragment, Object path, ImageView imageView) {
        setDefaultLoadType(Glide.with(fragment).load(path)).into(imageView);
    }

    @Override
    public void load(Context context, Object path, ImageView imageView) {
        setDefaultLoadType(Glide.with(context).load(path)).into(imageView);
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    public void clearMemory() {
        Glide.get(BaseApplication.getInstance()).clearMemory();

    }

    @Override
    public void clearDiskCache() {
        Glide.get(BaseApplication.getInstance()).clearDiskCache();
    }

    private DrawableRequestBuilder setDefaultLoadType(DrawableTypeRequest d) {
        return d.placeholder(IMG_LOADING).//加载中显示的图片
                error(IMG_ERROR).//加载失败时显示的图片
                placeholder(PLACEHOLDER).
                crossFade().//淡入淡出
                diskCacheStrategy(DiskCacheStrategy.ALL);
    }

}
