package myapp.bwie.com.newstoplihui.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的用途:
 *
 * @author 李辉
 * @date 2017/2/15 19:45.
 */
public class VpAdapter extends FragmentPagerAdapter {

    private Context context;
    private String[] sort;
    private List<Fragment> list;

    public VpAdapter(FragmentManager childFragmentManager, Context context, String[] title, ArrayList<Fragment> list) {
        super(childFragmentManager);
        this.context = context;
        sort = title;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return sort[position];
    }
}
