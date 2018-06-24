package moon.volley.api;

import com.android.volley.Request;
import com.google.gson.reflect.TypeToken;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import moon.volley.entity.SearchResult;
import moon.volley.form.FormText;
import moon.volley.network.Constant;
import moon.volley.network.DataResponseListener;
import moon.volley.network.GetObjectRequest;
import moon.volley.network.PostFormRequest;
import moon.volley.network.PostObjectRequest;
import moon.volley.network.ResponseListener;
import moon.volley.network.VolleyUtil;

/**
 * Created by moon.zhong on 2015/2/28.
 */
public class KugouApi {

    /**
     * 酷狗音乐搜索音乐get网络请求接口
     * @param keyWord 要搜索的关键字
     * @param listener 回调接口，包含错误回调和正确的数据回调
     */
    public static void getObjectSearchApi(String keyWord,ResponseListener listener){
        String url ;
        try {
            url = Constant.KugouHost +"?keyword="+ URLEncoder.encode(keyWord,"utf-8") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            url = Constant.KugouHost +"?keyword="+ URLEncoder.encode(keyWord) ;
        }
        Request request = new GetObjectRequest(url,new TypeToken<SearchResult>(){}.getType(),listener) ;
        VolleyUtil.getRequestQueue().add(request) ;
    }

    /**
     * 酷狗音乐搜索音乐post网络请求接口
     * @param keyword 要搜索的关键字
     * @param listener 回调接口，包含错误回调和正确的数据回调
     */
    public static void postObjectSearchApi(String keyword,ResponseListener listener){
        Map<String,String> param = new HashMap<String,String>() ;
        param.put("keyword",keyword) ;
        Request request = new PostObjectRequest(Constant.KugouHost,param,new TypeToken<SearchResult>(){}.getType(),listener);
        VolleyUtil.getRequestQueue().add(request) ;
    }

    /**
     * 酷狗音乐搜索音乐post表单提交接口
     * @param keyword 要搜索的关键字
     * @param listener 回调接口，包含错误回调和正确的数据回调
     */
    public static void postFormSearchApi(String keyword,DataResponseListener listener){
        List<FormText> formTextList = new ArrayList<FormText>() ;
        formTextList.add(new FormText("keyword",keyword));
        formTextList.add(new FormText("page","1"));
        Request request = new PostFormRequest(Constant.KugouHost,formTextList,new TypeToken<SearchResult>(){}.getType(),listener) ;
        VolleyUtil.getRequestQueue().add(request) ;
    }
}
