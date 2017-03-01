package myapp.bwie.com.newstoplihui.utils;


import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


/**
 * 类的用途:解析数据
 *
 * @author 李辉
 * @date 2017/2/16 08:06.
 */

public class XUtilsLoadData {


    public interface CallBack<T> {
        void callBack(String s);
    }

    public static <T> void loadData(String url, final CallBack callback) {
        RequestParams entity = new RequestParams(url);
        x.http().get(entity, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {

                        callback.callBack(result);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                }

        );
    }
}
