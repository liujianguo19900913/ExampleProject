package com.nengxin.example.exampleproject.model.application;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.nengxin.example.exampleproject.model.util.LogUtil;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;

public class ApplicationData extends Application {
    ApplicationData instance;//当前进程实例
    public static boolean isLogin = false;//是否已经登陆
    public static int screenWidth = 0;
    public static int screenHeight = 0;
    public static boolean isFirstLogin = true;
    public static boolean isCheckInsideCammer = true;
    String falg = null;

    @Override
    public void onCreate() {
        this.instance = ApplicationData.this;//当前这个实例等于自己
        super.onCreate();//回调自身
        LogUtil.isDebug = false;
        initImageLoader(getApplicationContext());
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.getDefaultDisplay().getMetrics(metric);
        screenWidth = metric.widthPixels; // 屏幕宽度（像素）
        screenHeight = metric.heightPixels; // 屏幕宽度（像素）
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.MAX_PRIORITY).denyCacheImageMultipleSizesInMemory()
                .imageDecoder(new BaseImageDecoder(false))
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);
    }
}
