package moon.volley.entity;

import com.google.gson.annotations.Expose;

/**
 * Created by gyzhong on 15/3/3.
 */
public class TestBean {
    @Expose
    private int id ;
    @Expose
    private String name ;
    @Expose
    private int download ;
    @Expose
    private String logo ;
    @Expose
    private int version ;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
