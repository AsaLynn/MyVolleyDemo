package moon.volley;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import moon.volley.adapter.SongAdapter;
import moon.volley.api.KugouApi;
import moon.volley.api.MiNongApi;
import moon.volley.entity.SearchResult;
import moon.volley.entity.SongDetail;
import moon.volley.entity.TestBean;
import moon.volley.network.ResponseListener;


public class GetRequestActivity extends ActionBarActivity {

    /*数据显示的View*/
    private TextView mIdTxt,mNameTxt,mDownloadTxt,mLogoTxt,mVersionTxt ;
    /*弹出等待对话框*/
    private ProgressDialog mDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);
        mIdTxt = (TextView) findViewById(R.id.id_id) ;
        mNameTxt = (TextView) findViewById(R.id.id_name) ;
        mDownloadTxt = (TextView) findViewById(R.id.id_download) ;
        mLogoTxt = (TextView) findViewById(R.id.id_logo) ;
        mVersionTxt = (TextView) findViewById(R.id.id_version) ;
        mDialog = new ProgressDialog(this) ;
        mDialog.setMessage("get请求中...");
        mDialog.show();
        /*请求网络获取数据*/
        MiNongApi.getObjectMiNongApi("minongbang",new ResponseListener<TestBean>() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mDialog.dismiss();
            }

            @Override
            public void onResponse(TestBean response) {
                mDialog.dismiss();
                /*显示数据*/
                mIdTxt.setText(response.getId()+"");
                mNameTxt.setText(response.getName());
                mDownloadTxt.setText(response.getDownload()+"");
                mLogoTxt.setText(response.getLogo());
                mVersionTxt.setText(response.getVersion()+"");
            }
        });
    }

}
