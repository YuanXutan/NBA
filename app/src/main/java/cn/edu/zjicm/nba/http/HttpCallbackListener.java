package cn.edu.zjicm.nba.http;

/**
 * Created by acer on 2017/5/31.
 */
public interface HttpCallbackListener {

    void onFinish(String response);

    void  onError(Exception e);
}
