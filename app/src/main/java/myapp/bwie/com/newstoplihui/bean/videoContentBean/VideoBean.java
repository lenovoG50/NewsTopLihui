package myapp.bwie.com.newstoplihui.bean.videoContentBean;

/**
 * 类的用途:
 *
 * @author 李辉
 * @date 2017/2/20 16:24.
 */

public class VideoBean {
    private String cover;
    private String mp4_url;
    private String playCount;
    private String ptime;
    private String title;
    private String topicImg;
    private String topicName;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getMp4_url() {
        return mp4_url;
    }

    public void setMp4_url(String mp4_url) {
        this.mp4_url = mp4_url;
    }

    public String getPlayCount() {
        return playCount;
    }

    public void setPlayCount(String playCount) {
        this.playCount = playCount;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "VideoBean{" +
                "cover='" + cover + '\'' +
                ", mp4Hd_url='" + mp4_url + '\'' +
                ", playCount='" + playCount + '\'' +
                ", ptime='" + ptime + '\'' +
                ", title='" + title + '\'' +
                ", topicImg='" + topicImg + '\'' +
                ", topicName='" + topicName + '\'' +
                '}';
    }

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
