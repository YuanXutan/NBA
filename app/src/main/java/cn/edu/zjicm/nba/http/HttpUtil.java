package cn.edu.zjicm.nba.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by acer on 2017/5/31.
 */
public class HttpUtil {


    //访问网络的两种方法，推荐第二种

    public static void sendHttpRequest(final String address, final
    HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (listener != null) {
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();

    }

    public static void sendOKHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void postOKHttpRequest(String address, String key1,
                                         String value1,String key2,String value2, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        RequestBody body2 = new FormBody.Builder()
                .add(key1, value1)
                .add(key2, value2)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(body2)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void logoutOKHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        RequestBody body2 = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(body2)
                .build();
        client.newCall(request).enqueue(callback);
    }

}