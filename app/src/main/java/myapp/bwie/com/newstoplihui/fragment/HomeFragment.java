package myapp.bwie.com.newstoplihui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

import myapp.bwie.com.newstoplihui.R;
import myapp.bwie.com.newstoplihui.activity.channelManager.ChannelManager;
import myapp.bwie.com.newstoplihui.activity.channelManager.SeachContent;
import myapp.bwie.com.newstoplihui.adapter.VpAdapter;
import myapp.bwie.com.newstoplihui.utils.Title;


/**
 * 类的用途:
 *
 * @author 李辉
 * @date 2017/2/15 19:51.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    private View view;
    private TabLayout sort;
    private ImageButton add;
    private ViewPager vp;
    private EditText seach;
    ArrayList<Fragment> list = new ArrayList<Fragment>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        } else {
            view = inflater.inflate(R.layout.fragment_home, null);
            //初始化控件
            initView();

        }
        return view;
    }

    private void initView() {
        sort = (TabLayout) view.findViewById(R.id.sortTab);
        add = (ImageButton) view.findViewById(R.id.add);
        vp = (ViewPager) view.findViewById(R.id.vp);
        seach = (EditText) view.findViewById(R.id.seach);

        add.setOnClickListener(this);
        seach.setOnClickListener(this);

        for (int i = 0; i < new Title().getTitle().length; i++) {
            HomeContentFragment fragment = new HomeContentFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url_ID", new Title().titleUrl_ID[i]);
            fragment.setArguments(bundle);
            list.add(fragment);
        }

        VpAdapter adapter = new VpAdapter(getFragmentManager(), getActivity(), new Title().getTitle(), list);
        vp.setAdapter(adapter);
        //实现联动
        sort.setupWithViewPager(vp);
        sort.setTabMode(TabLayout.MODE_SCROLLABLE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                Intent intent = new Intent(getActivity(), ChannelManager.class);
                startActivity(intent);
                break;

            case R.id.seach:
                Intent intent1 = new Intent(getActivity(), SeachContent.class);
                startActivity(intent1);
                break;
        }
    }
}
