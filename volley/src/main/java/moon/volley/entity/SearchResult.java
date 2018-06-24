package moon.volley.entity;

import com.google.gson.annotations.Expose;

/**
 * Created by gyzhong on 15/3/1.
 */
public class SearchResult {
    @Expose
    private int status ;
    @Expose
    private int errcode ;
    @Expose
    private String error ;
    @Expose
    private ListSong data ;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ListSong getData() {
        return data;
    }

    public void setData(ListSong data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "status=" + status +
                ", errcode=" + errcode +
                ", error='" + error + '\'' +
                ", data=" + data +
                '}';
    }
}
