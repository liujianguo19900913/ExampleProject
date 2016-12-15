package com.nengxin.example.exampleproject.model.application;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局控制Activity
 */
public class ActivityStackControlUtil {
    private static List<Activity> activityList = new ArrayList<Activity>();

    public static void remove(Activity activity) {

        activityList.remove(activity);

    }

    public static void add(Activity activity) {

        activityList.add(activity);

    }

    public static void finishProgram() {

        for (Activity activity : activityList) {

            activity.finish();

        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
