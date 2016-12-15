package com.nengxin.example.exampleproject.model.https;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class HttpUrlClient {
    private static HashMap<String, String> CookieContiner = new HashMap<String, String>();
    static String PREFS_NAME = "PREFS_NAME";

    public static String post(String url, List<NameValuePair> params, Context context) {
        String result = null;
        HttpClient client = new DefaultHttpClient();
        ((AbstractHttpClient) client).setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
        // 读取超时
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
        HttpPost httpPost = new HttpPost(url);
        AddCookies(httpPost, context);
        // httpPost.setHeader("Cookie", "JSESSIONID=" +
        // UserUtils.getUsersessionIdFromLocalSharedPrefenrese(context));
        // 设置HTTP POST请求参数必须用NameValuePair对象
        HttpResponse httpResponse = null;
        try {
            // 设置httpPost请求参数
//            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            httpResponse = new DefaultHttpClient().execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                SaveCookies(context, httpResponse);
            }
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                // 第三步，使用getEntity方法活得返回结果
                result = EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String post(String url, Context context) {
        String result = null;
        HttpClient client = new DefaultHttpClient();
        ((AbstractHttpClient) client).setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
        // 读取超时
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);
        HttpPost httpPost = new HttpPost(url);
        AddCookies(httpPost, context);
        // httpPost.setHeader("Cookie", "JSESSIONID=" +
        // UserUtils.getUsersessionIdFromLocalSharedPrefenrese(context));
        // 设置HTTP POST请求参数必须用NameValuePair对象
        HttpResponse httpResponse = null;
        try {
            // 设置httpPost请求参数
            // httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            httpResponse = new DefaultHttpClient().execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                SaveCookies(context, httpResponse);
            }
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                // 第三步，使用getEntity方法活得返回结果
                result = EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 保存Cookie
     */
    public static void SaveCookies(Context context, HttpResponse httpResponse) {
        Header[] headers = httpResponse.getHeaders("Set-Cookie");
        StringBuilder sb = new StringBuilder();
        String headerstr = headers.toString();
        Log.i("info", "headerstr=========" + headerstr);
        if (headers == null)
            return;
        // CookieContiner.clear();
        for (int i = 0; i < headers.length; i++) {
            String cookie = headers[i].getValue();
            String[] cookievalues = cookie.split(";");
            for (int j = 0; j < cookievalues.length; j++) {
                String[] keyPair = cookievalues[j].split("=");
                String key = keyPair[0].trim();
                String value = keyPair.length > 1 ? keyPair[1].trim() : "";
                sb.append(key);
                sb.append("=");
                sb.append(value);
                sb.append(";");
            }
        }
        String cookies = sb.toString();
        if (cookies != null && cookies.length() > 0 && cookies.contains("JSESSIONID=")) {
            String cookieNew = cookies.replace("cookies=", "");
//			UserUtils.setUserCookieslSharedPrefenrese(context, cookieNew);
        }

        Log.i("info", "SaveCookies==" + sb.toString());
    }

    /**
     * 增加Cookie
     *
     * @param request
     */
    public static void AddCookies(HttpPost request, Context context) {
        SharedPreferences prefs = context.getSharedPreferences("cookies", context.MODE_PRIVATE);

        for (Entry<String, ?> entry : prefs.getAll().entrySet())
            CookieContiner.put(entry.getKey(), entry.getValue().toString());
        StringBuilder sb = new StringBuilder();
        Iterator iter = CookieContiner.entrySet().iterator();
        while (iter.hasNext()) {
            Entry entry = (Entry) iter.next();
            String key = entry.getKey().toString();
            String val = entry.getValue().toString();
            sb.append(key);
            sb.append("=");
            sb.append(val);
            sb.append(";");
        }

        if (CookieContiner.size() != 0) {
            String addCookie = sb.toString();
            if (addCookie != null && addCookie.contains("cookies=") && (!addCookie.contains("cookies=;"))) {
                String cookieNew = addCookie.replace("cookies=", "cookies=;");
                request.addHeader("cookie", cookieNew);
                Log.i("info", "AddCookies sb.toString()=========" + cookieNew);
            } else {
                request.addHeader("cookie", sb.toString());
                Log.i("info", "AddCookies sb.toString()=========" + sb.toString());
            }
        }
    }

    /**
     * 增加Cookie
     */
    public static void AddCookies(HttpGet gets, Context context) {
        SharedPreferences prefs = context.getSharedPreferences("cookies", context.MODE_PRIVATE);

        for (Entry<String, ?> entry : prefs.getAll().entrySet())
            CookieContiner.put(entry.getKey(), entry.getValue().toString());
        StringBuilder sb = new StringBuilder();
        Iterator iter = CookieContiner.entrySet().iterator();
        while (iter.hasNext()) {
            Entry entry = (Entry) iter.next();
            String key = entry.getKey().toString();
            String val = entry.getValue().toString();
            sb.append(key);
            sb.append("=");
            sb.append(val);
            sb.append(";");
        }

        if (CookieContiner.size() != 0) {
            String addCookie = sb.toString();
            if (addCookie != null && addCookie.contains("cookies=") && (!addCookie.contains("cookies=;"))) {
                String cookieNew = addCookie.replace("cookies=", "cookies=;");
                gets.addHeader("cookie", cookieNew);
                Log.i("info", "AddCookies sb.toString()=========" + cookieNew);
            } else {
                gets.addHeader("cookie", sb.toString());
                Log.i("info", "AddCookies sb.toString()=========" + sb.toString());
            }
        }
    }

    public static String get(String url, Context context) {
        String data = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet gets = new HttpGet(url);
            AddCookies(gets, context);
            HttpResponse response = client.execute(gets);
            if (response.getStatusLine().getStatusCode() == 200) {
                SaveCookies(context, response);
                HttpEntity entity = response.getEntity();
                data = EntityUtils.toString(entity);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 使用GET访问去访问网络
     *
     * @return 服务器返回的结果
     */
    public static String Get(String path) {
        HttpURLConnection conn = null;
        String state = null;
        try {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(5000);
            conn.connect();
            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream is = conn.getInputStream();
                state = getStringFromInputStream(is);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return state;
    }

    /**
     * 根据输入流返回一个字符串
     *
     * @param is
     * @return
     * @throws Exception
     */
    private static String getStringFromInputStream(InputStream is) throws Exception {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len = -1;
        while ((len = is.read(buff)) != -1) {
            baos.write(buff, 0, len);
        }
        is.close();
        String html = baos.toString();
        baos.close();
        return html;
    }
}
