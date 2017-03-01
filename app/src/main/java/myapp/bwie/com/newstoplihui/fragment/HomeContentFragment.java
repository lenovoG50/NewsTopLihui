package myapp.bwie.com.newstoplihui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import myapp.bwie.com.newstoplihui.R;
import myapp.bwie.com.newstoplihui.activity.NewsDetails;
import myapp.bwie.com.newstoplihui.adapter.HomeAdapter;
import myapp.bwie.com.newstoplihui.bean.homeContentBean.SortBean;
import myapp.bwie.com.newstoplihui.utils.XUtilsLoadData;

/**
 * 类的用途:
 *
 * @author 李辉
 * @date 2017/2/19 16:24.
 */
public class HomeContentFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2 {

    int page = 0;
    String urlhead = "http://c.m.163.com/nc/article/headline/";
    String urlfooter = "-20.html";
    private View view;
    private String url_id;

    private PullToRefreshListView plv;
    ArrayList<SortBean> list = new ArrayList<SortBean>();
    private HomeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        } else {

            //获取bundle传值
            Bundle bundle = getArguments();
            url_id = bundle.getString("url_ID");
            //初始化视图
            view = inflater.inflate(R.layout.fragment_homecontent, null);
            plv = (PullToRefreshListView) view.findViewById(R.id.plv);
            //初始化适配器
            initAdapter();
            //请求数据
            initData(urlhead + url_id + "/" + page + urlfooter);
            //条目监听
            initClick();
            //设置刷新加载
            initRefreshListener();

        }
        return view;
    }

    private void initRefreshListener() {
        plv.setOnRefreshListener(this);
        plv.setMode(PullToRefreshBase.Mode.BOTH);
    }

    private void initClick() {
        plv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list.get(position).getUrl() != null) {
                    Intent intent = new Intent(getActivity(), NewsDetails.class);
                    intent.putExtra("newsDetail", list.get(position - 1).getUrl());
                    startActivity(intent);
                }
            }
        });
    }


    public void initAdapter() {
        adapter = new HomeAdapter(getActivity());
        plv.setAdapter(adapter);
    }

    private void initData(String url) {

        XUtilsLoadData.loadData(url, new XUtilsLoadData.CallBack() {
            @Override
            public void callBack(String s) {
                Gson gson = new Gson();
                try {
                    JSONObject job = new JSONObject(s);
                    JSONArray joa = job.optJSONArray(url_id);
                    for (int i = 0; i < joa.length(); i++) {
                        JSONObject jsonObject = joa.optJSONObject(i);
                        SortBean sortBean = gson.fromJson(jsonObject.toString(), SortBean.class);
                        list.add(sortBean);
                    }
                    adapter.addData(list);
                    adapter.notifyDataSetChanged();
                    plv.onRefreshComplete();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        list.clear();
        page = 0;
        initData(urlhead + url_id + "/" + page + urlfooter);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        page += 5;
        initData(urlhead + url_id + "/" + page + urlfooter);
        Log.d("zzz", page + "");
    }
}
