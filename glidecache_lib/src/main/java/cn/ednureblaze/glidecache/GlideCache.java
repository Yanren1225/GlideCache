package cn.ednureblaze.glidecache;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;

/**
 * 操作 Glide 缓存的工具类
 */
public class GlideCache {
    private static Bitmap glideBitmap;

    /**
     * 判断是否有缓存
     * @param context 传入一个上下文
     * @param url   判断是否有缓存的图片链接
     * @return 返回 boolean 类型，有缓存返回 true，无缓存返回 false
     */
    public static boolean isHaveGlideCache(Context context, String url){
        FutureTarget<Bitmap> bitmap = Glide.with(context)
                .asBitmap()
                .load(url)
                .submit();
        try{
            glideBitmap = bitmap.get();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 获取 Glide 的缓存
     * @param context 传入上下文
     * @param url 在 Glide 添加图片时使用的 URL (一般为网络地址)
     * @return 返回 Bitmap 类型的缓存
     */
    public static Bitmap getGlideBitmap(Context context, String url) {
        FutureTarget<Bitmap> bitmap = Glide.with(context)
                .asBitmap()
                .load(url)
                .submit();
        try{
            glideBitmap = bitmap.get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return glideBitmap;
    }

    /**
     * 给图片设置缓存里的图片
     * @param activity 传入一个 Activity
     * @param image 需要设置缓存图片的 ImageView
     * @param imageUrl 在 Glide 添加图片时使用的 URL (一般为网络地址)
     */
    public static void setNormalImageViaGlideCache(final Activity activity, final ImageView image, final String imageUrl) {
        new Thread(() -> {
            try {
                final Bitmap glideBitmap=GlideCache.getGlideBitmap(activity, imageUrl);

                activity.runOnUiThread(() -> image.setImageBitmap(glideBitmap));
            } catch (Exception ignored) {}
        }).start();
    }

    /**
     *
     * @param activity 传入一个 Activity
     * @param blurImage 需要设置缓存图片的 ImageView
     * @param imageUrl 在 Glide 添加图片时使用的 URL (一般为网络地址)
     * @param pattern 模糊程度
     */
    public static void setBlurImageViaGlideCache(final Activity activity, final ImageView blurImage, final String imageUrl, final String pattern) {

        new Thread(() -> {
            try{
                Bitmap glideBitmap=GlideCache.getGlideBitmap(activity, imageUrl);
                int scaleRatio = 0;
                if (TextUtils.isEmpty(pattern)) {
                    scaleRatio = 0;
                } else if (scaleRatio < 0) {
                    scaleRatio = 10;
                } else {
                    scaleRatio = Integer.parseInt(pattern);
                }
                //下面的这个方法必须在子线程中执行
                final Bitmap blurBitmap2 = FastBlur.toBlur(glideBitmap, scaleRatio);

                //刷新ui必须在主线程中执行
                activity.runOnUiThread(() -> {
                    blurImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    blurImage.setImageBitmap(blurBitmap2);
                });
            } catch (Exception ignored) {}
        }).start();
    }
}