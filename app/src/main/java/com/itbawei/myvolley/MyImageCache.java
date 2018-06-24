package com.itbawei.myvolley;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * 实现图片缓存的具体操作.
 *
 */

public class MyImageCache implements ImageLoader.ImageCache{

    private LruCache<String, Bitmap> lruCache;
    private String TAG = this.getClass().getSimpleName();
    private String strTag = "--->***";

    public MyImageCache() {
        //获取应用可用最大内存
        int max = (int) Runtime.getRuntime().maxMemory()/1024;
        //手动分配内存用于缓存图片.
        int size = max/8;
        lruCache = new LruCache<String, Bitmap>(size) {
            //重写计算大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount()/1024;
            }
        };
    }


    @Override
    public Bitmap getBitmap(String url) {
        Bitmap bitmap = lruCache.get(url);
        if (null != bitmap){
            Log.i(TAG, strTag+"getBitmap: imageloader从lrucache获取到了bitmap"+bitmap);
        }else {
            Log.i(TAG, strTag+"getBitmap: imageloader从lrucache没有获取到bitmap"+bitmap);
            bitmap = getBtimapFormDisk(url);
            if (null != bitmap){
                Log.i(TAG, strTag+"getBtimapFormDisk: imageloader从Disk获取到bitmap"+bitmap);
                lruCache.put(url,bitmap);
            }
        }
        return bitmap;
    }

    protected Bitmap getBtimapFormDisk(String url) {
        return null;
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        Log.i(TAG, strTag+"putBitmap: imageloader往lrucache中存放了bitmap"+bitmap);
        lruCache.put(url,bitmap);
    }
}
