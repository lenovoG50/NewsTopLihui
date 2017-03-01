package myapp.bwie.com.newstoplihui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import myapp.bwie.com.newstoplihui.R;

/**
 * 类的用途:
 *
 * @author 李辉
 * @date 2017/2/19 13:06.
 */

public class AttentionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attention, null);
        return view;
    }
}
