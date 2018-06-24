package moon.volley;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import moon.volley.adapter.SongAdapter;
import moon.volley.api.KugouApi;
import moon.volley.api.MiNongApi;
import moon.volley.entity.SearchResult;
import moon.volley.entity.SongDetail;


public class PostRequestActivity extends ActionBarActivity {

    private ListView mListView ;
    private SongAdapter mAdapter ;
    private List<SongDetail> mSongList ;
    private ProgressDialog mDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        mSongList = new ArrayList<SongDetail>() ;
        mListView = (ListView) findViewById(R.id.id_list_view);
        mAdapter = new SongAdapter(this,mSongList) ;
        mListView.setAdapter(mAdapter);
        mDialog = new ProgressDialog(this) ;
        mDialog.setMessage("post请求中...");
        mDialog.show();
        /*请求网络获取数据*/
        MiNongApi.postObjectWeatherApi("beijing", new moon.volley.network.ResponseListener<SearchResult>() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("zgy", "======onErrorResponse=====" + error);
                mDialog.dismiss();
            }

            @Override
            public void onResponse(SearchResult response) {
                Log.v("zgy", "======onResponse=====" + response);
                mSongList.addAll(response.getData().getInfo());
                mAdapter.notifyDataSetChanged();
                mDialog.dismiss();
            }
        });
    }

}
