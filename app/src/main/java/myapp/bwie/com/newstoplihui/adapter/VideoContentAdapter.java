package myapp.bwie.com.newstoplihui.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import myapp.bwie.com.newstoplihui.R;
import myapp.bwie.com.newstoplihui.bean.videoContentBean.VideoBean;

/**
 * 类的用途:
 *
 * @author 李辉
 * @date 2017/2/20 18:42.
 */
public class VideoContentAdapter extends BaseAdapter {

    private Context context;
    private List<VideoBean> data = new ArrayList<VideoBean>();
    private static ViewHolder vh;

    public VideoContentAdapter(FragmentActivity activity) {
        context = activity;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public VideoBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.fragment_videocontent, null);
            vh = new ViewHolder();
            vh.jc = (JCVideoPlayerStandard) convertView.findViewById(R.id.jcVideoPlayer);
            vh.icon = (ImageView) convertView.findViewById(R.id.icon);
            vh.topicName = (TextView) convertView.findViewById(R.id.topicName);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.topicName.setText(getItem(position).getTopicName());

        vh.jc.setUp(getItem(position).getMp4_url(), JCVideoPlayer.SCREEN_LAYOUT_LIST, getItem(position).getTitle());
        DisplayImageOptions options = DisplayImageOptions.createSimple();
        ImageLoader.getInstance().displayImage(getItem(position).getTopicImg(), vh.icon, options);
        ImageLoader.getInstance().displayImage(getItem(position).getCover(), vh.jc.thumbImageView, options);

        return convertView;
    }

    public void addData(List<VideoBean> list) {
        data = list;
    }

    class ViewHolder {
        JCVideoPlayerStandard jc;
        ImageView icon;
        TextView topicName;
    }


}
