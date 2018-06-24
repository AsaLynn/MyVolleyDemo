package moon.volley.api;

import com.android.volley.Request;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import moon.volley.entity.SearchResult;
import moon.volley.entity.TestBean;
import moon.volley.network.Constant;
import moon.volley.network.GetObjectRequest;
import moon.volley.network.PostObjectRequest;
import moon.volley.network.ResponseListener;
import moon.volley.network.VolleyUtil;

/**
 * Created by gyzhong on 15/3/3.
 */
public class MiNongApi {
    /**
     * Minong 测试数据get网络请求接口
     * @param value 测试数据
     * @param listener 回调接口，包含错误回调和正确的数据回调
     */
    public static void getObjectMiNongApi(String value,ResponseListener listener){
        String url ;
        try {
            url = Constant.MinongHost +"?test="+ URLEncoder.encode(value, "utf-8") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            url = Constant.MinongHost +"?test="+ URLEncoder.encode(value) ;
        }
        Request request = new GetObjectRequest(url,new TypeToken<TestBean>(){}.getType(),listener) ;
        VolleyUtil.getRequestQueue().add(request) ;
    }

    /*
    * *Minong 测试数据post网络请求接口
    * @param value 测试数据
    * @param listener 回调接口，包含错误回调和正确的数据回调
    */
    public static void postObjectMinongApi(String value,ResponseListener listener){
        Map<String,String> param = new HashMap<String,String>() ;
        param.put("test",value) ;
        Request request = new PostObjectRequest(Constant.MinongHost,param,new TypeToken<TestBean>(){}.getType(),listener);
        VolleyUtil.getRequestQueue().add(request) ;
    }
    public static void postObjectWeatherApi(String value,ResponseListener listener){
        Map<String,String> param = new HashMap<String,String>() ;
        param.put("q",value) ;
        Request request = new PostObjectRequest(Constant.WeatherHost,param,new TypeToken<TestBean>(){}.getType(),listener);
        VolleyUtil.getRequestQueue().add(request) ;
    }
}
