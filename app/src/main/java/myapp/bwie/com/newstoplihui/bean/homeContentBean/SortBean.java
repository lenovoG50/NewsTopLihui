package myapp.bwie.com.newstoplihui.bean.homeContentBean;

import java.util.List;

/**
 * 类的用途:
 *
 * @author 李辉
 * @date 2017/2/16 09:43.
 */

public class SortBean {
    private String title;
    private String source;
    private String ptime;
    private String tname;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private List<Img> imgextra;


    public List<Img> getImgextra() {
        return imgextra;
    }

    public void setImgextra(List<Img> imgextra) {
        this.imgextra = imgextra;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTname() {
        return tname;
    }

    @Override
    public String toString() {
        return "SortBean{" +
                "imgextra=" + imgextra +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", ptime='" + ptime + '\'' +
                ", tname='" + tname + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

}
