package moon.volley;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import moon.volley.adapter.SongAdapter;
import moon.volley.api.KugouApi;
import moon.volley.entity.SearchResult;
import moon.volley.entity.SongDetail;
import moon.volley.network.DataResponseListener;
import moon.volley.network.ResponseListener;


public class PostFormActivity extends ActionBarActivity {

    private ListView mListView ;
    private SongAdapter mAdapter ;
    private List<SongDetail> mSongList ;
    private ProgressDialog mDialog ;
    private View mHeaderView ;
    private TextView mShowPostData ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_form);
        mSongList = new ArrayList<SongDetail>() ;
        mListView = (ListView) findViewById(R.id.id_list_view);
        mAdapter = new SongAdapter(this,mSongList) ;
        mHeaderView = getLayoutInflater().inflate(R.layout.list_header_item,null) ;
        mShowPostData = (TextView) mHeaderView.findViewById(R.id.id_post_data) ;
        mListView.addHeaderView(mHeaderView);
        mListView.setAdapter(mAdapter);
        mDialog = new ProgressDialog(this) ;
        mDialog.setMessage("数据提交中...");
        mDialog.show() ;
        /*请求网络获取数据*/
        KugouApi.postFormSearchApi("冰雨",new DataResponseListener<SearchResult>() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("zgy","======onErrorResponse====="+error);
                mDialog.dismiss();
            }

            @Override
            public void onResponse(SearchResult response) {
                Log.v("zgy","======onResponse====="+response);
                mSongList.addAll(response.getData().getInfo()) ;
                mAdapter.notifyDataSetChanged();
                mDialog.dismiss();
            }

            @Override
            public void postData(String data) {
                mShowPostData.setText(data);
            }
        });
    }

}
