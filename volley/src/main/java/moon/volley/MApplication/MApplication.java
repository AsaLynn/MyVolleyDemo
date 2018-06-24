package moon.volley.MApplication;

import android.app.Application;
import android.content.Context;

import moon.volley.network.VolleyUtil;

/**
 * Created by gyzhong on 15/3/1.
 */
public class MApplication extends Application {
    private Context mContext ;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext() ;
        initialize() ;

    }

    private void initialize(){
        initRequestQueue();
    }

    private void initRequestQueue(){
        VolleyUtil.initialize(mContext);
    }
}
