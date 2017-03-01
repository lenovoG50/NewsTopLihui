package myapp.bwie.com.newstoplihui.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import myapp.bwie.com.newstoplihui.R;
import myapp.bwie.com.newstoplihui.fragment.AttentionFragment;
import myapp.bwie.com.newstoplihui.fragment.HomeFragment;
import myapp.bwie.com.newstoplihui.fragment.MineFragment;
import myapp.bwie.com.newstoplihui.fragment.VideoFragment;
import myapp.bwie.com.newstoplihui.utils.MyEventBus;
import myapp.bwie.com.newstoplihui.utils.ThemeManager;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ThemeManager.OnThemeChangeListener {

    private ImageButton person;
    private ImageButton refresh;
    private TabLayout sortTab;
    private ViewPager vp;
    private RadioGroup rg;
    private LinearLayout fragment;
    private ArrayList<Fragment> list = new ArrayList<Fragment>();
    private ActionBar actionBar;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);


        //初始化控件
        initView();
        initFrament();
        selectFragment(0);

        ThemeManager.registerThemeChangeListener(this);
        actionBar = getSupportActionBar();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void getMessageFromMineFragment(MyEventBus event) {
        ThemeManager.setThemeMode(ThemeManager.getThemeMode() == ThemeManager.ThemeMode.DAY ? ThemeManager.ThemeMode.NIGHT : ThemeManager.ThemeMode.DAY);
    }

    public void initTheme() {

        relativeLayout.setBackgroundColor(getResources().getColor(ThemeManager.getCurrentThemeRes(MainActivity.this, R.color.backgroundColor)));
        // 设置标题栏颜色
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(ThemeManager.getCurrentThemeRes(MainActivity.this, R.color.colorPrimary))));
        }
        // 设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(getResources().getColor(ThemeManager.getCurrentThemeRes(MainActivity.this, R.color.colorPrimary)));
        }
    }

    @Override
    public void onThemeChanged() {
        initTheme();
    }

    private void initFrament() {
        list.add(new HomeFragment());
        list.add(new VideoFragment());
        list.add(new AttentionFragment());
        list.add(new MineFragment());
    }

    private void initView() {
        //推送


        rg = (RadioGroup) findViewById(R.id.rg);
        fragment = (LinearLayout) findViewById(R.id.fragment);
        rg.setOnCheckedChangeListener(this);
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
    }

    private void selectFragment(int i) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, list.get(i));
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.home:
                selectFragment(0);
                break;
            case R.id.video:
                selectFragment(1);
                break;
            case R.id.attention:
                selectFragment(2);
                break;
            case R.id.mine:
                selectFragment(3);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ThemeManager.unregisterThemeChangeListener(this);
    }
}
