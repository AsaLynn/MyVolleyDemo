package moon.volley.entity;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by gyzhong on 15/3/1.
 */
public class ListSong {
    @Expose
    private long timestamp ;
    @Expose
    private int total ;
    @Expose
    private List<SongDetail> info ;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<SongDetail> getInfo() {
        return info;
    }

    public void setInfo(List<SongDetail> info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ListSong{" +
                "timestamp=" + timestamp +
                ", total=" + total +
                ", info=" + info +
                '}';
    }
}
