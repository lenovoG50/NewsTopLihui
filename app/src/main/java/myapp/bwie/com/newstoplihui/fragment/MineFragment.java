package myapp.bwie.com.newstoplihui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;
import myapp.bwie.com.newstoplihui.R;
import myapp.bwie.com.newstoplihui.activity.PhoneLogin;
import myapp.bwie.com.newstoplihui.utils.MyEventBus;

/**
 * 类的用途:
 *
 * @author 李辉
 * @date 2017/2/19 13:06.
 */

public class MineFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView phonelogin;
    private ImageView qqlogin;
    private ImageView sinalogin;
    private ImageView arrowconlogin;
    private LinearLayout collect;
    private LinearLayout history;
    private LinearLayout night;
    private RelativeLayout alerts;
    private RelativeLayout topshow;
    private RelativeLayout jingdong;
    private RelativeLayout shared;
    private RelativeLayout feedback;
    private RelativeLayout setting;

    private final String APPID = "1105602574";
    private Tencent mTencent;
    private IUiListener loginListener;
    private TextView ts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, null);
        //初始化控件
        initControl();

        return view;
    }

    private void initControl() {
        phonelogin = (ImageView) view.findViewById(R.id.phonelogin);
        qqlogin = (ImageView) view.findViewById(R.id.qqlogin);
        ts = (TextView) view.findViewById(R.id.textshow);
        sinalogin = (ImageView) view.findViewById(R.id.sinalogin);
        arrowconlogin = (ImageView) view.findViewById(R.id.arrowcon);
        collect = (LinearLayout) view.findViewById(R.id.collect);
        history = (LinearLayout) view.findViewById(R.id.history);
        night = (LinearLayout) view.findViewById(R.id.night);
        alerts = (RelativeLayout) view.findViewById(R.id.alerts);
        topshow = (RelativeLayout) view.findViewById(R.id.topshop);
        jingdong = (RelativeLayout) view.findViewById(R.id.jingdong);
        shared = (RelativeLayout) view.findViewById(R.id.shared);
        feedback = (RelativeLayout) view.findViewById(R.id.feedback);
        setting = (RelativeLayout) view.findViewById(R.id.setting);

        phonelogin.setOnClickListener(this);
        qqlogin.setOnClickListener(this);
        sinalogin.setOnClickListener(this);
        ts.setOnClickListener(this);
        collect.setOnClickListener(this);
        history.setOnClickListener(this);
        night.setOnClickListener(this);
        alerts.setOnClickListener(this);
        topshow.setOnClickListener(this);
        jingdong.setOnClickListener(this);
        shared.setOnClickListener(this);
        feedback.setOnClickListener(this);
        setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.phonelogin:
                Intent intent = new Intent(getActivity(), PhoneLogin.class);
                startActivity(intent);
                break;
            case R.id.qqlogin:
                qqlogin();
                break;
            case R.id.sinalogin:

                break;
            case R.id.collect:

                break;
            case R.id.alerts:

                break;
            case R.id.history:

                break;
            case R.id.night:
                EventBus.getDefault().post(new MyEventBus("dayOrNight"));
                break;
            case R.id.topshop:

                break;
            case R.id.jingdong:

                break;
            case R.id.shared:

                break;
            case R.id.feedback:

                break;
            case R.id.setting:

                break;
        }
    }

    private void qqlogin() {
        mTencent = Tencent.createInstance(APPID, getActivity());

        mTencent.login(this, "all", loginListener);

        loginListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                //登录成功后回调该方法,可以跳转相关的页面

                Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();

                JSONObject object = (JSONObject) o;

                try {

                    String accessToken = object.getString("access_token");

                    String expires = object.getString("expires_in");

                    String openID = object.getString("openid");

                    mTencent.setAccessToken(accessToken, expires);

                    mTencent.setOpenId(openID);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }

        };
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Constants.REQUEST_LOGIN) {

            if (resultCode == -1) {

                Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);

                Tencent.handleResultData(data, loginListener);

                UserInfo info = new UserInfo(getActivity(), mTencent.getQQToken());

                info.getUserInfo(new IUiListener() {

                    @Override

                    public void onComplete(Object o) {

                        try {

                            JSONObject info = (JSONObject) o;

                            String nickName = info.getString("nickname");//获取用户昵称

                            String iconUrl = info.getString("figureurl_qq_2");//获取用户头像的url

                            Toast.makeText(getActivity(), "昵称：" + nickName, Toast.LENGTH_SHORT).show();
                            ts.setText(nickName);
                            ts.setTextColor(Color.WHITE);
                            DisplayImageOptions options = DisplayImageOptions.createSimple();
                            ImageLoader.getInstance().displayImage(iconUrl, qqlogin, options);
                            phonelogin.setVisibility(View.GONE);
                            sinalogin.setVisibility(View.GONE);
                            arrowconlogin.setVisibility(View.GONE);
                        } catch (JSONException e) {

                            e.printStackTrace();

                        }

                    }

                    @Override
                    public void onError(UiError uiError) {

                    }


                    @Override

                    public void onCancel() {

                    }

                });
            }
        }
    }
}