package com.nengxin.example.exampleproject.model.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

/**
 * 本地缓存的用户信息的 get Set 方法
 */
public class UserUtils {
    private static UserUtils util;

    private UserUtils() {

    }

    public static UserUtils getInstance() {
        if (util == null) {
            util = new UserUtils();
        }
        return util;
    }

    // 删除登陆文件
    public void deleteLoginInfo(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("loginUserInfo", Context.MODE_PRIVATE);
        if (preferences != null) {
            preferences.edit().clear().commit();
        }
    }

    /**
     * 设置用户的登录状态
     */
    public static void setUserIsloginSharedPrefenrese(Context context, boolean islogin) {

        SharedPreferences saveLoginInfo = context.getSharedPreferences("loginUserInfo", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = saveLoginInfo.edit();
        editor.putBoolean("isLogin", islogin);
        editor.commit();
    }

    /**
     * 得到用户的登录状态
     */
    public static boolean getUserIsloginSharedPrefenrese(Context context) {

        SharedPreferences saveLoginInfo = context.getSharedPreferences("loginUserInfo", context.MODE_PRIVATE);
        return saveLoginInfo.getBoolean("isLogin", false);
    }

    /**
     * 设置用户的引导状态
     */
    public static void setUserYDSharedPrefenrese(Context context, boolean yd) {

        SharedPreferences saveLoginInfo = context.getSharedPreferences("loginUserInfo", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = saveLoginInfo.edit();
        editor.putBoolean("isYD", yd);
        editor.commit();
    }

    /**
     * 得到用户的引导状态
     */
    public static boolean getUserYDSharedPrefenrese(Context context) {

        SharedPreferences saveLoginInfo = context.getSharedPreferences("loginUserInfo", context.MODE_PRIVATE);
        return saveLoginInfo.getBoolean("isYD", false);
    }

    /**
     * 得到用户的cookies
     *
     * @param context
     */

    public static String getUserCookieslSharedPrefenrese(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("cookies", context.MODE_PRIVATE);
        return preferences.getString("cookies", "");
    }

    /**
     * 设置用户cookies
     */
    public static void setUserCookieslSharedPrefenrese(Context context, String cookies) {
        SharedPreferences saveLoginInfo = context.getSharedPreferences("cookies", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = saveLoginInfo.edit();
        editor.putString("cookies", cookies);
        editor.commit();
    }

    /**
     * 清除用户cookies
     */
    public static void clearUserCookieslSharedPrefenrese(Context context) {

        SharedPreferences saveLoginInfo = context.getSharedPreferences("cookies", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = saveLoginInfo.edit();
        editor.putString("cookies", "");
        editor.commit();
    }

    /**
     * 同步一下cookie
     */
    public static void synCookies(Context context, String url) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();// 移除
        cookieManager.setCookie(url, getUserCookieslSharedPrefenrese(context));// cookies是在HttpClient中获得的cookie
        CookieSyncManager.getInstance().sync();
    }
}
