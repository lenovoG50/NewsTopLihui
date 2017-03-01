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

import myapp.bwie.com.newstoplihui.R;
import myapp.bwie.com.newstoplihui.bean.homeContentBean.SortBean;

/**
 * 类的用途:
 *
 * @author 李辉
 * @date 2017/2/16 19:24.
 */
public class HomeAdapter extends BaseAdapter {

    private Context context;
    private List<SortBean> list = new ArrayList<SortBean>();
    private final int ITEM_TYPE1 = 0;
    private final int ITEM_TYPE2 = 2;
    private ViewHolder1 vh1;
    private ViewHolder2 vh2;


    public HomeAdapter(FragmentActivity activity) {
        context = activity;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public SortBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case ITEM_TYPE1:
                    convertView = View.inflate(context, R.layout.item_home1, null);
                    vh1 = new ViewHolder1();
                    vh1.title = (TextView) convertView.findViewById(R.id.title);
                    vh1.source = (TextView) convertView.findViewById(R.id.source);
                    vh1.ptime = (TextView) convertView.findViewById(R.id.ptime);
                    vh1.img1 = (ImageView) convertView.findViewById(R.id.img1);
                    vh1.img2 = (ImageView) convertView.findViewById(R.id.img2);
                    convertView.setTag(vh1);
                    break;
                case ITEM_TYPE2:
                    convertView = View.inflate(context, R.layout.item_home2, null);
                    vh2 = new ViewHolder2();
                    vh2.title = (TextView) convertView.findViewById(R.id.title);
                    vh2.source = (TextView) convertView.findViewById(R.id.source);
                    vh2.ptime = (TextView) convertView.findViewById(R.id.ptime);
                    convertView.setTag(vh2);
                    break;

            }
        } else {
            switch (type) {
                case ITEM_TYPE1:
                    vh1 = (ViewHolder1) convertView.getTag();
                    break;
                case ITEM_TYPE2:
                    vh2 = (ViewHolder2) convertView.getTag();
                    break;

            }
        }

        DisplayImageOptions options = DisplayImageOptions.createSimple();
        //赋值
        switch (type) {
            case ITEM_TYPE1:
                vh1.title.setText(getItem(position).getTitle());
                vh1.source.setText(getItem(position).getSource());
                vh1.ptime.setText(getItem(position).getPtime());
                ImageLoader.getInstance().displayImage(getItem(position).getImgextra().get(0).getImgsrc(), vh1.img1, options);
                ImageLoader.getInstance().displayImage(getItem(position).getImgextra().get(1).getImgsrc(), vh1.img2, options);
                break;
            case ITEM_TYPE2:
                vh2.title.setText(getItem(position).getTitle());
                vh2.source.setText(getItem(position).getSource());
                vh2.ptime.setText(getItem(position).getPtime());

                break;

        }

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {

        if (getItem(position).getImgextra() != null && getItem(position).getImgextra().size() > 0) {

            return ITEM_TYPE1;
        } else

        {
            return ITEM_TYPE2;
        }
    }

    class ViewHolder1 {
        TextView title, source, ptime;
        ImageView img1, img2;
    }

    class ViewHolder2 {

        TextView title, source, ptime;

    }

    public void addData(List<SortBean> list) {

        this.list = list;
    }

    ;


}
