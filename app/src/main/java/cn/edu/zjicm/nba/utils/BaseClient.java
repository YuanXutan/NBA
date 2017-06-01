package cn.edu.zjicm.nba.utils;


import android.app.Activity;
import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.HttpEntity;

import cn.edu.zjicm.nba.MyApplication;

public class BaseClient {
    private static final String BASE_URL = "http://121.199.40.253/nba/";

    private static AsyncHttpClient client = new AsyncHttpClient();
    static Context context = MyApplication.getInstance();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("token", context.getSharedPreferences("token", Activity.MODE_PRIVATE).getString("token", "null"));
        client.addHeader("appid", "cb_7jcelkr9yr82b");
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("token", context.getSharedPreferences("token", Activity.MODE_PRIVATE).getString("token", "null"));
        client.addHeader("appid", "cb_7jcelkr9yr82b");
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }
    public static void put(String url,RequestParams params,AsyncHttpResponseHandler responseHandler){
        client.addHeader("token", context.getSharedPreferences("token", Activity.MODE_PRIVATE).getString("token", "null"));
        client.addHeader("appid", "cb_7jcelkr9yr82b");
        client.put(getAbsoluteUrl(url), params, responseHandler);
    }
    public static void delete(String url,RequestParams params,AsyncHttpResponseHandler responseHandler){
        client.addHeader("token", context.getSharedPreferences("token", Activity.MODE_PRIVATE).getString("token", "null"));
        client.addHeader("appid", "cb_7jcelkr9yr82b");
        client.delete(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void delete1(Context context,String url,HttpEntity entity,String contentType,AsyncHttpResponseHandler responseHandler){
        client.addHeader("token", context.getSharedPreferences("token", Activity.MODE_PRIVATE).getString("token", "null"));
        client.addHeader("appid", "cb_7jcelkr9yr82b");
        client.delete(context, url, entity, contentType, responseHandler);
    }
    public static void postFile(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("token", context.getSharedPreferences("token", Activity.MODE_PRIVATE).getString("token", "null"));
        client.addHeader("appid", "cb_7jcelkr9yr82b");
        client.setConnectTimeout(600000);
        client.setMaxConnections(3);
        client.setResponseTimeout(600000);
        client.setTimeout(600000);
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
