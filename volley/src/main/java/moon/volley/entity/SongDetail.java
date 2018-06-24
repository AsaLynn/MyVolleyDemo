package moon.volley.entity;

import com.google.gson.annotations.Expose;

/**
 * Created by gyzhong on 15/3/1.
 */
public class SongDetail {
    @Expose
    private String filename ;
    @Expose
    private String singername ;
    @Expose
    private long filesize ;
    @Expose
    private String extname ;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSingername() {
        return singername;
    }

    public void setSingername(String singername) {
        this.singername = singername;
    }

    public long getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public String getExtname() {
        return extname;
    }

    public void setExtname(String extname) {
        this.extname = extname;
    }

    @Override
    public String toString() {
        return "SongDetail{" +
                "filename='" + filename + '\'' +
                ", singername='" + singername + '\'' +
                ", filesize=" + filesize +
                ", extname='" + extname + '\'' +
                '}';
    }
}
