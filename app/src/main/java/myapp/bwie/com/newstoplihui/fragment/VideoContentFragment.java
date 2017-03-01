package myapp.bwie.com.newstoplihui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import myapp.bwie.com.newstoplihui.R;
import myapp.bwie.com.newstoplihui.adapter.VideoContentAdapter;
import myapp.bwie.com.newstoplihui.bean.videoContentBean.VideoBean;
import myapp.bwie.com.newstoplihui.utils.XUtilsLoadData;

/**
 * 类的用途:
 *
 * @author 李辉
 * @date 2017/2/20 10:53.
 */
public class VideoContentFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2 {

    int page = 10;
    String videoUrlHeader = "http://c.3g.163.com/nc/video/list/";
    String videoUrlcenter = "/n/";
    String videoUrlFooter = "-10.html";
    private View view;
    private PullToRefreshListView plv;
    ArrayList<VideoBean> video = new ArrayList<VideoBean>();
    private VideoContentAdapter adapter;
    private String videoUrl_id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        if (view != null) {
            ViewGroup group = (ViewGroup) view.getParent();
            if (group != null) {
                group.removeView(view);
            }
        } else {
            view = inflater.inflate(R.layout.fragment_homecontent, null);
            //得到传值
            Bundle bundle = getArguments();
            videoUrl_id = bundle.getString("videoUrl_id");
            //初始化控件
            initView();
            //请求数据
            initData(videoUrlHeader + videoUrl_id + videoUrlcenter + page + videoUrlFooter);
            //初始化适配器
            initAdapter();
            //刷新加载
            plv.setOnRefreshListener(this);
            plv.setMode(PullToRefreshBase.Mode.BOTH);
        }
        return view;
    }


    private void initAdapter() {
        adapter = new VideoContentAdapter(getActivity());
        plv.setAdapter(adapter);

    }

    private void initData(String s) {
        XUtilsLoadData.loadData(s, new XUtilsLoadData.CallBack() {
            @Override
            public void callBack(String s) {
                Gson gson = new Gson();
                try {
                    JSONObject job = new JSONObject(s);
                    JSONArray joa = job.optJSONArray(videoUrl_id);
                    for (int i = 0; i < joa.length(); i++) {
                        JSONObject jsonObject = joa.optJSONObject(i);
                        VideoBean videoBean = gson.fromJson(jsonObject.toString(), VideoBean.class);
                        video.add(videoBean);
                    }

                    adapter.addData(video);
                    adapter.notifyDataSetChanged();
                    plv.onRefreshComplete();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initView() {
        plv = (PullToRefreshListView) view.findViewById(R.id.plv);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        video.clear();
        page = 10;
        initData(videoUrlHeader + videoUrl_id + videoUrlcenter + page + videoUrlFooter);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page += 10;
        initData(videoUrlHeader + videoUrl_id + videoUrlcenter + page + videoUrlFooter);

    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
