package com.nengxin.example.exampleproject.model.https;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AllNetBiz {
    // 线程池 使用线程池开启线程 不可使用Thread.currentThread().join();休眠线程
    ExecutorService pool = Executors.newSingleThreadExecutor();

    /********************************
     * serchdata
     ************************************/
    public void GetSearchHotDatas(final String url, final Context context) {
//        final SerchActivity activity = (SerchActivity) context;
        Thread t = new Thread() {
            @Override
            public void run() {
                String result = HttpUrlClient.get(url, context);
                if (result == "" || result == null) {
//                    activity.handler.sendEmptyMessage(0);
                    return;
                } else {
                    try {

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        pool.execute(t);
    }
}
