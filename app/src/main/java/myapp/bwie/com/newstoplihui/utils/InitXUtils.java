package myapp.bwie.com.newstoplihui.utils;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

/**
 * 类的用途:
 *
 * @author 李辉
 * @date 2017/2/16 08:11.
 */

public class InitXUtils extends Application {
    @Override
    public void onCreate() {

        x.Ext.init(this);
        x.Ext.setDebug(true);

        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
    }
}
