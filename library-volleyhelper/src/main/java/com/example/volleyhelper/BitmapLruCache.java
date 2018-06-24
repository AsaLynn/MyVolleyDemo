package com.example.volleyhelper;


import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;


/**
 * 图片缓存类 LruCache内存缓存
 * @author Javen
 *
 */
public class BitmapLruCache extends LruCache<String,Bitmap> implements ImageCache {
	private final static String TAG = BitmapLruCache.class.getSimpleName();
    private String STR_TAG = "--->***"+TAG;

    public static int getDefaultLruCacheSize(){
        final int maxMemory= (int) (Runtime.getRuntime().maxMemory()/1024);
        final int cacheSize=maxMemory/8;
        return cacheSize;

    }

    public BitmapLruCache(){
        this(getDefaultLruCacheSize());
    }
    public BitmapLruCache(int sizeInKiloBytes) {
        super(sizeInKiloBytes);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight() / 1024;
    }
    @Override
    public Bitmap getBitmap(String url) {
    	L.e(TAG,STR_TAG+"getBitmap.....");
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
    	L.e(TAG,STR_TAG+"putBitmap.....");
        put(url, bitmap);
    }

}