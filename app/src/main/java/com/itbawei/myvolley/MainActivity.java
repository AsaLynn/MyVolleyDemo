package com.itbawei.myvolley;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.volleyhelper.VolleyHelper;
import com.example.volleyhelper.VolleyUtils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String itemName;
    private String TAG = this.getClass().getSimpleName();
    private String GET_DISKLRUCACHE_URL = "http://p1.so.qhmsg.com/t01a3afe2ec7905b296.jpg";
    private String GET_VOLLEY_URL = "http://upload.cankaoxiaoxi.com/2017/0324/1490342017810.png";
    private String LOAD_DISKLRUCACHE_B_URL = "http://img2.3lian.com/2014/f3/51/d/2.jpg";
//    private String LOAD_VOLLEY_URL = "http://www.ishowx.com/uploads/allimg/150410/43-1504101H048-50.jpg";
    private String LOAD_VOLLEY_URL = "http://dingyue.nosdn.127.net/TEYYKxSDXlboSMsXXzGleZA46ykgS0cbeomNS2IkF=xCj1501565499165.jpg";

    private void show() {
        final String[] items = {
                "0加载网络图片内存缓存",
                "1加载网络图片disk缓存",
                "2加载网络图片volley缓存",
                "3占位加载网络图片内存缓存",
                "4占位加载网络图片disk缓存",
                "5占位加载网络图片volley缓存",
        };
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("volley封装后的操作")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itemName = items[which];
                        switch (which) {
                            case 0:
                                //加载网络图片内存缓存
                                getImage();
                                break;
                            case 1:
                                //加载网络图片disk缓存
                                getImage1();
                                break;
                            case 2:
                                //加载网络图片volley缓存
                                getImage2();
                                break;
                            case 3:
                                //占位加载网络图片内存缓存
                                loadImage();
                                break;
                            case 4:
                                //占位加载网络图片disk缓存
                                loadImage1();
                                break;
                            case 5:
                                //占位加载网络图片volley缓存
                                loadImage2();
                                break;
                        }
                    }
                }).create().show();
    }

    private void loadImage2() {
        VolleyUtils.loadImage(this, LOAD_VOLLEY_URL, imageView, R.drawable.loading, R.drawable.load_error, VolleyHelper.CacheType.VOLLEYCACHE_C);
    }

    private void loadImage1() {
        VolleyUtils.loadImage(this, LOAD_DISKLRUCACHE_B_URL, imageView, R.drawable.loading, R.drawable.load_error, VolleyHelper.CacheType.DISKLRUCACHE_B);
    }

    private void getImage2() {
        VolleyUtils.getImage(this, /*GET_VOLLEY_URL*/LOAD_VOLLEY_URL, new VolleyUtils.MyImageListener() {

            @Override
            public void setImageBitmap(final Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
                showResult("getImage2");
            }
        }, VolleyHelper.CacheType.VOLLEYCACHE_C);
    }

    private void getImage1() {
        VolleyUtils.getImage(this, GET_DISKLRUCACHE_URL, new VolleyUtils.MyImageListener() {

            @Override
            public void setImageBitmap(final Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
                showResult("getImage1");
            }
        }, VolleyHelper.CacheType.DISKLRUCACHE_B);
    }


    TextView textView;
    ImageView imageView;
    private String GET_IMAGE_URL = "http://dingyue.nosdn.127.net/TEYYKxSDXlboSMsXXzGleZA46ykgS0cbeomNS2IkF=xCj1501565499165.jpg";
//    private String GET_IMAGE_URL = "http://uploadfile.bizhizu.cn/2014/0219/20140219044835421.jpg";
    private String LOAD_IMAGE_URL = "http://pic1.win4000.com/wallpaper/0/5152b6a0562e6.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_show).setOnClickListener(this);
        textView = (TextView) findViewById(R.id.content);
        imageView = (ImageView) findViewById(R.id.id_imgView);
        VolleyHelper.getInstance(this).getRequestQueue().add(new ImageRequest("", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }));
    }

    public void loadImage() {
        VolleyUtils.loadImage(this, LOAD_IMAGE_URL, imageView, R.drawable.loading, R.drawable.load_error);
    }

    public void getImage() {//GET_IMAGE_URL
        VolleyUtils.getImage(this, GET_IMAGE_URL, new VolleyUtils.MyImageListener() {

            @Override
            public void setImageBitmap(final Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
                showResult("getImage");
            }
        });
    }

    @Override
    protected void onDestroy() {
        //    	//取消Request
//    	VolleyHelper.getInstance(this).getRequestQueue().cancelAll("My Tag");

//    	//或者我们前面实现的方法
//    	VolleyHelper.getInstance(this).cancelPendingRequests("My Tag");
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_show) {
            show();
        }
    }

    private void showResult(String result) {
        String mResult = itemName + result;
        textView.setText(mResult);
        Toast.makeText(this, mResult, Toast.LENGTH_SHORT).show();
        Log.i(TAG, mResult);
    }


}
