package com.example.volleyhelper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * 图片缓存帮助类
 * 包含内存缓存LruCache和volley磁盘缓存
 *
 * @author Javen
 */
public class TPBDoubleCache implements ImageCache {

    private String TAG = TPBDoubleCache.this.getClass().getSimpleName();

    //缓存类
    private static LruCache<String, Bitmap> mLruCache;

    //磁盘缓存大小
    private static final int DISKMAXSIZE = 10 * 1024 * 1024;
    private String strTag = "--->***";
    protected final Cache cache;

    public TPBDoubleCache(Context context) {
        // 获取应用可占内存的1/8作为缓存
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        // 实例化LruCaceh对象
        mLruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };

        cache = VolleyHelper.getInstance(context).getRequestQueue().getCache();
    }

    /**
     * 从缓存（内存缓存，磁盘缓存）中获取Bitmap
     * 1,volley处理缓存的时候对url进行了处理,以下是处理的代码.
     * http://pic1.win4000.com/wallpaper/0/5152b6a0562e6.jpg#W0#H0#SCENTER_INSIDE
     * private static String getCacheKey(String url, int maxWidth, int maxHeight, ScaleType scaleType, ImageRequest.Transformation transformation) {
     * final StringBuilder key = new StringBuilder();
     * key.append(url);
     * key.append("#W").append(maxWidth);
     * key.append("#H").append(maxHeight);
     * key.append("#S").append(scaleType.toString());
     * <p>
     * if (transformation != null)
     * key.append("#T").append(transformation.key());
     * <p>
     * return key.toString();
     * }
     * 2,由此可见我们使用url,要对url进行还原处理.
     */
    @Override
    public Bitmap getBitmap(String url) {
        if (mLruCache.get(url) != null) {
            // 从LruCache缓存中取
            Log.i(TAG, strTag + "从LruCahce获取");
            return mLruCache.get(url);
        } else {
            String key = getUrlKey(url);
            if (cache.get(key) != null) {
                // 从volley缓存取
                Cache.Entry entry = cache.get(key);
                if (entry != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(entry.data, 0, entry.data.length);
                    // 存入LruCache缓存
                    mLruCache.put(url, bitmap);
                    Log.i(TAG, strTag + "从volley缓存获取");
                    return bitmap;
                }
            }
        }
        return null;
    }

    private String getUrlKey(String url) {
        String regex = "#";
        int i = url.indexOf(regex);
        String subSequence = url.subSequence(0, i).toString();
        return subSequence;
    }

    /**
     * 存入缓存（内存缓存，磁盘缓存）
     */
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        // 存入LruCache缓存
        mLruCache.put(url, bitmap);
        //volley已经做了磁盘缓存,无须重复添加缓存.
    }

    /*
    volley处理缓存的时候对url进行了处理,以下是处理的代码.
    http://pic1.win4000.com/wallpaper/0/5152b6a0562e6.jpg#W0#H0#SCENTER_INSIDE
    private static String getCacheKey(String url, int maxWidth, int maxHeight, ScaleType scaleType, ImageRequest.Transformation transformation) {
        final StringBuilder key = new StringBuilder();
        key.append(url);
        key.append("#W").append(maxWidth);
        key.append("#H").append(maxHeight);
        key.append("#S").append(scaleType.toString());

        if (transformation != null)
            key.append("#T").append(transformation.key());

        return key.toString();
    }
    */

}