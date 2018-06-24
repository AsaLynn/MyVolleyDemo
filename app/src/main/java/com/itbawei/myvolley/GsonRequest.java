package com.itbawei.myvolley;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

/**
 * 1,继承request
 * 2,重写抽象方法
 * 3,定义好要返回java对象.
 * 4,构造方法传入对象.class.
 */

public class GsonRequest<T> extends Request<T> {

    private final Response.Listener<T> mListener;
    private Gson gson;
    private Class<T> mClazz;

    //构造方法
    public GsonRequest(int method, String url,Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
        gson = new Gson();
        mClazz = clazz;
    }

    //默认get请求.
    public GsonRequest(String url,Class<T> clazz,Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this(Method.GET, url,clazz, listener, errorListener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            //解析处服务器编码格式.
            String parsed = new String(response.data, "UTF-8");
            return Response.success(gson.fromJson(parsed, mClazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new VolleyError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
