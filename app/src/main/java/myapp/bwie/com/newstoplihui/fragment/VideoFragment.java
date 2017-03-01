package myapp.bwie.com.newstoplihui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCMediaManager;
import myapp.bwie.com.newstoplihui.R;
import myapp.bwie.com.newstoplihui.adapter.VideoFragmentAdapter;
import myapp.bwie.com.newstoplihui.utils.VideoType;

/**
 * 类的用途:
 *
 * @author 李辉
 * @date 2017/2/19 13:06.
 */

public class VideoFragment extends Fragment {

    private View view;
    private TabLayout sortTab;
    private ImageButton seachForVideo;
    private ViewPager vp;
    ArrayList<Fragment> list = new ArrayList<Fragment>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_video, null);
        //初始化控件
        initView();

        return view;
    }

    private void initView() {
        sortTab = (TabLayout) view.findViewById(R.id.sortTab);
        seachForVideo = (ImageButton) view.findViewById(R.id.seachForVideo);
        vp = (ViewPager) view.findViewById(R.id.vp);

        list.clear();
        for (int i = 0; i < new VideoType().getVideoTypeId().length; i++) {
            VideoContentFragment fragment = new VideoContentFragment();
            Bundle bundle = new Bundle();
            bundle.putString("videoUrl_id", new VideoType().getVideoTypeId()[i]);
            fragment.setArguments(bundle);
            list.add(fragment);
        }

        vp.setAdapter(new VideoFragmentAdapter(getChildFragmentManager(), getActivity(), new VideoType().getVideoType(), list));

        sortTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        sortTab.setupWithViewPager(vp);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden == true) {
            JCMediaManager.instance().releaseMediaPlayer();
        }
    }
}