package myapp.bwie.com.newstoplihui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的用途:
 *
 * @author 李辉
 * @date 2017/2/20 13:53.
 */
public class VideoFragmentAdapter extends FragmentPagerAdapter {
    private Context context;
    private String[] videoTitle;
    private List<Fragment> fragment;

    public VideoFragmentAdapter(FragmentManager fragmentManager, FragmentActivity activity, String[] videoType, ArrayList<Fragment> list) {
        super(fragmentManager);
        context = activity;
        videoTitle = videoType;
        fragment = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragment.get(position);
    }

    @Override
    public int getCount() {
        return fragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return videoTitle[position];
    }
}
