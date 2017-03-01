package myapp.bwie.com.newstoplihui.utils;

/**
 * 类的用途:
 *
 * @author 李辉
 * @date 2017/2/22 15:00.
 */

public class VideoType {
    private String[] videoType = new String[]{
            "热点视频",
            "娱乐视频",
            "搞笑视频",
            "精品视频"
    };
    private String[] videoTypeId = new String[]{
            "V9LG4B3A0",
            "V9LG4CHOR",
            "V9LG4E6VR",
            "00850FRB"
    };

    public String[] getVideoType() {
        return videoType;
    }

    public void setVideoType(String[] videoType) {
        this.videoType = videoType;
    }

    public String[] getVideoTypeId() {
        return videoTypeId;
    }

    public void setVideoTypeId(String[] videoTypeId) {
        this.videoTypeId = videoTypeId;
    }
}
