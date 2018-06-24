package moon.volley.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import moon.volley.R;
import moon.volley.entity.SongDetail;

/**
 * Created by gyzhong on 15/3/1.
 */
public class SongAdapter  extends RequestAdapter<SongDetail,SongAdapter.ViewHolder>{
    public SongAdapter(Context context, List<SongDetail> dataList) {
        super(context, dataList);
    }

    @Override
    public View createView(LayoutInflater inflater, int position) {
        View view = inflater.inflate(R.layout.song_item,null) ;
        return view;
    }

    @Override
    public ViewHolder createHolder(View view, int position) {
        ViewHolder viewHolder ;
        viewHolder = new ViewHolder() ;
        viewHolder.songText = findView(view,R.id.id_song) ;
        return viewHolder;
    }

    @Override
    public void showData(ViewHolder viewHolder, int position) {
        viewHolder.songText.setText(getItem(position).getFilename());
    }

    protected class ViewHolder{
        TextView songText ;
    }
}
