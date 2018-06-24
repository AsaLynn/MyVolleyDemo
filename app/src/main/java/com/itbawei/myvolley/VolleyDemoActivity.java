package com.itbawei.myvolley;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ClearCacheRequest;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/*
1,创建请求队列.(不要重复创建队列,只要一个.)
2,创建请求
3,将请求加入队列.
 */
public class VolleyDemoActivity extends AppCompatActivity implements View.OnClickListener, Response.ErrorListener {

    private TextView tv_content;
    private RequestQueue requestQueue;
    private String TAG = this.getClass().getSimpleName();
    private String url;
    private ImageView iv_volley;
    private ImageLoader imageLoader;
    private NetworkImageView iv_volley1;
    private String itemName;
    protected String strTag = "--->***";
    private String IMAGE_REQUEST_URL = "http://uploadfile.bizhizu.cn/2014/0219/20140219044835421.jpg";
    private String IMAGE_LOADER_URL = "http://img.67.com/upload/images/2014/05/23/1400814736_1896877012.jpg";
    private String IMAGE_NET_WORK_URL = "http://pic1.win4000.com/wallpaper/e/557957b6c512f.jpg";
    private String STRING_REQUEST_URL = "http://litchiapi.jstv.com/api/GetFeeds?column=3&PageSize=5&pageIndex=5&val=100511D3BE5301280E0992C73A9DEC41";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_demo);

        initView();

        //创建请求队列.
        requestQueue = Volley.newRequestQueue(this);

    }

    private void initView() {
        iv_volley = (ImageView) findViewById(R.id.iv_volley);
        iv_volley1 = (NetworkImageView) findViewById(R.id.iv_volley1);
        tv_content = (TextView) findViewById(R.id.tv_content);
        Button btn_volley = (Button) findViewById(R.id.btn_volley);
        btn_volley.setOnClickListener(this);
        findViewById(R.id.btn_cache).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_volley) {
            show();
        } else if (v.getId() == R.id.btn_cache) {
            showCache();
        }
    }

    private void showCache() {
        final String[] items = {
                "0读取ImageRequest的缓存",
                "1读取ImageLoader的缓存",
                "2读取NetImageView的缓存",
                "3删除ImageRequest的缓存",
                "4手动添加ImageRequest缓存",
                "5清空缓存",
                "6清空缓存方式2",
                "7失效缓存",
                "8读取StringRequest的缓存",
        };
        new AlertDialog.Builder(this)
                .setTitle("Volley的基本使用")
                .setIcon(R.mipmap.ic_launcher)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        itemName = items[i] + strTag;
                        //i就是数组的索引.
                        switch (i) {
                            case 0:
                                //读取ImageRequest的缓存
                                getImageCache(IMAGE_REQUEST_URL);
                                break;
                            case 1:
                                //读取ImageLoader的缓存
                                getImageCache(IMAGE_LOADER_URL);
                                break;
                            case 2:
                                //2读取NetImageView的缓存
                                getImageCache(IMAGE_NET_WORK_URL);
                                break;
                            case 3:
                                //删除ImageRequest的缓存
//                                removeImageCache(IMAGE_REQUEST_URL);
                                removeImageCache(IMAGE_LOADER_URL);
                                break;
                            case 4:
                                //4手动添加ImageRequest缓存
                                //重新加载数据,添加缓存数据
                                putCache();
                                break;
                            case 5:
                                //清空缓存的数据后
                                requestQueue.getCache().clear();
                                showResult("清空缓存的数据");
                                break;
                            case 6:
                                //6清空缓存方式2
                                //使用ClearCacheRequest,清空缓存!
                                clearCache2();
                                break;
                            case 7:
                                //7失效缓存
                                //使得缓存数据失效,但是不会删除,
                                requestQueue.getCache().initialize();
                                showResult("使得缓存数据失效过期");
                                break;
                            case 8:
                                //9读取StringRequest的缓存
                                getStringCache(STRING_REQUEST_URL);
                                break;
                        }
                    }
                }).create().show();
    }

    private void getStringCache(String str) {
        Cache.Entry entry = requestQueue.getCache().get(str);
        if (null != entry) {
            showResult(new String(entry.data));
        }else {
            showResult("没有读取到缓存!");
        }
    }


    private void show() {
        final String[] items = {"0发送StringRequestGET",
                "1发送StringRequestPOST",
                "2发送ImageRequest加入缓存",
                "3发送ImageRequest不加缓存",
                "4ImageLoader使用",
                "5NetworkImageView的用法",
                "6发送JsonObjectRequest",
                "7发送JsonArrayRequest",
                "8发送GsonRequset",
        };
        new AlertDialog.Builder(this)
                .setTitle("Volley的基本使用")
                .setIcon(R.mipmap.ic_launcher)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        itemName = items[i] + strTag;
                        //i就是数组的索引.
                        switch (i) {
                            case 0:
                                //发送StringRequest请求.
                                sendGetStringRequest();
                                break;
                            case 1:
                                //发送stringrequest,post请求.
                                sendPostStringRequest();
                                break;
                            case 2:
                                //imageRequest使用,缓存
                                sendImageRequest(true);
                                break;
                            case 3:
                                //imageRequest使用,不缓存.
                                sendImageRequest(false);
                                break;
                            case 4:
                                //imageloader的使用.
                                sendImageLoader();
                                break;
                            case 5:
                                //NetworkImageView的用法
                                sendNetworkImageView();
                                break;
                            case 6:
                                // sendJsonObjectRequest
                                sendJsonObjectRequest();
                                break;
                            case 7:
                                // sendJsonArrayRequest
                                sendJsonArrayRequest();
                                break;
                            case 8:
                                //GsonRequset.
                                sendGsonRequset();
                                break;
                        }
                    }
                }).create().show();
    }

    private void clearCache2() {
        ClearCacheRequest ccr = new ClearCacheRequest(requestQueue.getCache(),
                new Runnable() {
                    @Override
                    public void run() {
                        showResult("ClearCacheRequest清除缓存!");
                    }
                });
        ccr.setTag(this);
        requestQueue.add(ccr);
    }

    private void putCache() {
//        url = "http://uploadfile.bizhizu.cn/2014/0219/20140219044835421.jpg";
        //参数:1,地址.2,成功回调.3,宽4,高5,决定图片质量.6,失败回调.
        Request imageRequest = new ImageRequest(IMAGE_REQUEST_URL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                iv_volley.setImageBitmap(response);
                showResult("图片重新加载成功!");
                Cache.Entry entry = new Cache.Entry();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                response.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                entry.data = baos.toByteArray();
                requestQueue.getCache().put(IMAGE_REQUEST_URL, entry);
            }
        }, 0, 0, Bitmap.Config.RGB_565, VolleyDemoActivity.this);
        imageRequest.setShouldCache(false);//关闭缓存.
        //加入队列.
        requestQueue.add(imageRequest);
    }

    private void removeImageCache(String imageUrl) {
//        String imageUrl = "http://uploadfile.bizhizu.cn/2014/0219/20140219044835421.jpg";
        requestQueue.getCache().remove(imageUrl);
        showResult("删除了" + imageUrl);
    }

    private void sendJsonArrayRequest() {
        url = "http://172.21.3.24/jsonarry.json";
        Request request1 = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    showResult(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, VolleyDemoActivity.this);
        requestQueue.add(request1);
    }

    private void sendJsonObjectRequest() {
        url = "http://www.weather.com.cn/data/sk/101010100.html";
        final Request request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject weatherinfo = response.getJSONObject("weatherinfo");
                    String city = weatherinfo.getString("city");
                    String cityid = weatherinfo.getString("cityid");
                    String result = "onResponse: 城市名称:" + city + "城市id" + cityid;
                    showResult(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, VolleyDemoActivity.this);
        requestQueue.add(request);
    }

    private void sendGsonRequset() {
        url = "http://www.weather.com.cn/data/sk/101010100.html";
        GsonRequest<Weather> gsonRequest = new GsonRequest<>(url, Weather.class, new Response.Listener<Weather>() {
            @Override
            public void onResponse(Weather response) {
                showResult(response.getWeatherinfo().toString());
            }
        }, VolleyDemoActivity.this);
        requestQueue.add(gsonRequest);
    }

    private void sendNetworkImageView() {

//                            1. 创建一个RequestQueue对象。
//                            2. 创建一个ImageLoader对象。
        if (null == imageLoader) {
            imageLoader = new ImageLoader(requestQueue, new MyImageCache());
        }
//                            3. 在布局文件中添加一个NetworkImageView控件。
//                            4. 在代码中获取该控件的实例。
//                            5. 设置要加载的图片地址。
        //默认图片
        iv_volley1.setDefaultImageResId(R.mipmap.ic_launcher);
        //加载失败后显示的图片.
        iv_volley1.setErrorImageResId(R.mipmap.over_fail_iv);
        //参数:1,图片地址.2,
        iv_volley1.setImageUrl(IMAGE_NET_WORK_URL, imageLoader);
    }

    private void sendImageLoader() {

//                            1. 创建一个RequestQueue对象(已经有了)
//                            2. 创建一个ImageLoader对象。
        //ImageLoader(RequestQueue queue, ImageCache imageCache)
        if (null == imageLoader) {
            imageLoader = new ImageLoader(requestQueue, new MyImageCache() {
                @Override
                protected Bitmap getBtimapFormDisk(String url) {
                    Cache.Entry entry = requestQueue.getCache().get(IMAGE_LOADER_URL);
                    if (null != entry) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(entry.data, 0, entry.data.length);
                        return bitmap;
                    }
                    return super.getBtimapFormDisk(url);
                }
            });
        }
//                            3. 获取一个ImageListener对象。
        //参数:1,imageview,2,默认图片.3,加载失败图片.
        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(iv_volley, R.mipmap.id_over, R.mipmap.over_fail_iv);
//                            4. 调用ImageLoader的get()方法加载网络上的图片。
        //内部封装:当缓存中有数据,就取缓存数据,否则网络加载,其具体缓存操作对外暴漏接口
        imageLoader.get(IMAGE_LOADER_URL, imageListener);
    }

    private void sendImageRequest(boolean shouldCache) {
        //参数:1,地址.2,成功回调.3,宽4,高5,决定图片质量.6,失败回调.
        Request imageRequest = new ImageRequest(IMAGE_REQUEST_URL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                iv_volley.setImageBitmap(response);
                showResult("图片加载成功!");
            }
        }, 0, 0, Bitmap.Config.RGB_565, VolleyDemoActivity.this);
        imageRequest.setShouldCache(shouldCache);
        /*if (!imageRequest.shouldCache()) {//判断缓存是否开启,true为开启
            imageRequest.setShouldCache(true);
        }*/
        //加入队列.
        requestQueue.add(imageRequest);
    }

    private void getImageCache(String cacheUrl) {
//        String url = IMAGE_REQUEST_URL;
//        String url = IMAGE_LOADER_URL;
        Cache cache = requestQueue.getCache();
        Cache.Entry entry = cache.get(cacheUrl);
        if (null != entry) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(entry.data, 0, entry.data.length);
            iv_volley.setImageBitmap(bitmap);
            showResult("缓存读取成功!");
        } else {
            showResult("没有缓存!");
        }

    }

    private void sendGetStringRequest() {
        //创建get请求.
        //加入队列.
        //参数:1,url地址.2,成功回调.3失败回调.
        Request request = new StringRequest(STRING_REQUEST_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //主线程运行的.
                showResult(response);
            }
        }, VolleyDemoActivity.this);
        //设置标记
        request.setTag(this);
        request.setShouldCache(true);
        requestQueue.add(request);
    }

    private void sendPostStringRequest() {
        //创建队列
        //创建请求
        url = "http://www.kuaidi100.com/query";
        //add("type", "yuantong").add("postid", "500379523313").build();
        Request request1 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showResult(response);
            }
        }, VolleyDemoActivity.this) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("type", "yuantong");
                map.put("postid", "500379523313");
                return map;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String parsed;
                try {
                    parsed = new String(response.data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    parsed = new String(response.data);
                }
                return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
            }

           /* @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = super.getHeaders();
                headers.put("","");
                return headers;
            }*/
        };

        //加入队列.
        requestQueue.add(request1);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i(TAG, "onErrorResponse: --->***" + error);
    }

    private void showResult(String result) {
        String mResult = itemName + result;
        tv_content.setText(mResult);
        Toast.makeText(VolleyDemoActivity.this, mResult, Toast.LENGTH_SHORT).show();
        Log.i(TAG, mResult);
    }

    @Override
    protected void onDestroy() {
        requestQueue.cancelAll(this);
        super.onDestroy();
    }
}
