package com.example.volleyhelper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;

/**
 * 网络请求工具类
 *
 * @author Javen
 */
public class VolleyUtils {
    private static String TAG = VolleyUtils.class.getSimpleName();

    public interface MyImageListener {
        void setImageBitmap(Bitmap bitmap);
    }

    public static void getImage(Context context, String url, final MyImageListener listener) {
        ImageLoader imageLoader = VolleyHelper.getInstance(context).getImageLoader();
        imageLoader.get(url, new ImageListener() {
            @Override
            public void onResponse(ImageContainer response, boolean arg) {
                if (response.getBitmap() != null) {
                    listener.setImageBitmap(response.getBitmap());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Image Error" + error.getMessage());
            }
        });
    }

    public static void getImage(Context context, String url, final MyImageListener listener, VolleyHelper.CacheType type) {
        ImageLoader imageLoader = VolleyHelper.getInstance(context).getImageLoader(type);
        imageLoader.get(url, new ImageListener() {
            @Override
            public void onResponse(ImageContainer response, boolean arg) {
                if (response.getBitmap() != null) {
                    listener.setImageBitmap(response.getBitmap());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Image Error" + error.getMessage());
            }
        });
    }

    /**
     * 获取ImageListener
     *
     * @param view
     * @param defaultImage
     * @param errorImage
     * @return
     */
    public static ImageListener getImageListener(final ImageView view, final Bitmap defaultImage, final Bitmap errorImage) {

        return new ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // 回调失败
                if (errorImage != null) {
                    view.setImageBitmap(errorImage);
                }
            }

            @Override
            public void onResponse(ImageContainer response, boolean isImmediate) {
                // 回调成功
                if (response.getBitmap() != null) {
                    view.setImageBitmap(response.getBitmap());
                } else if (defaultImage != null) {
                    view.setImageBitmap(defaultImage);
                }
            }
        };

    }

    /**
     * 提供给外部调用方法
     *
     * @param url
     * @param view
     * @param defaultImageId
     * @param errorImageId
     */
    public static void loadImage(Context context, String url, ImageView view, int defaultImageId, int errorImageId) {
        Bitmap defaultImage = BitmapFactory.decodeResource(context.getResources(), defaultImageId);
        Bitmap errorImage = BitmapFactory.decodeResource(context.getResources(), errorImageId);
        VolleyHelper.getInstance(context).getImageLoader().get(url, VolleyUtils.getImageListener(view, defaultImage, errorImage), 0, 0);
    }

    /**
     *
     * @param context
     * @param url
     * @param view
     * @param defaultImageId
     * @param errorImageId
     * @param type
     */
    public static void loadImage(Context context, String url, ImageView view, int defaultImageId, int errorImageId, VolleyHelper.CacheType type) {
        Bitmap defaultImage = BitmapFactory.decodeResource(context.getResources(), defaultImageId);
        Bitmap errorImage = BitmapFactory.decodeResource(context.getResources(), errorImageId);
        VolleyHelper.getInstance(context).getImageLoader(type).get(url, VolleyUtils.getImageListener(view, defaultImage, errorImage), 0, 0);
    }

    /**
     * 提供给外部调用方法
     *
     * @param url
     * @param view
     * @param defaultImageId
     * @param errorImageId
     */
    public static void loadImage(Context context, String url, ImageView view, int defaultImageId, int errorImageId, int maxWidth, int maxHeight) {
        Bitmap defaultImage = BitmapFactory.decodeResource(context.getResources(), defaultImageId);
        Bitmap errorImage = BitmapFactory.decodeResource(context.getResources(), errorImageId);
        VolleyHelper.getInstance(context).getImageLoader().get(url, VolleyUtils.getImageListener(view, defaultImage, errorImage), maxWidth, maxHeight);
    }

    /**
     *
     * @param context
     * @param url
     * @param view
     * @param defaultImageId
     * @param errorImageId
     * @param maxWidth
     * @param maxHeight
     * @param type
     */
    public static void loadImage(Context context, String url, ImageView view, int defaultImageId, int errorImageId, int maxWidth, int maxHeight,VolleyHelper.CacheType type) {
        Bitmap defaultImage = BitmapFactory.decodeResource(context.getResources(), defaultImageId);
        Bitmap errorImage = BitmapFactory.decodeResource(context.getResources(), errorImageId);
        VolleyHelper.getInstance(context).getImageLoader(type).get(url, VolleyUtils.getImageListener(view, defaultImage, errorImage), maxWidth, maxHeight);
    }

}
