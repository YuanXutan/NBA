package cn.edu.zjicm.nba.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.edu.zjicm.nba.R;
import cn.edu.zjicm.nba.http.HttpUtil;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by acer on 2017/6/4.
 */

public class ChangePasswordActivity extends Activity {

    private EditText old_password_tx;
    private EditText new_password_tx;

    private Button change_password_btn;

    String old;
    String xin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        old_password_tx = (EditText) findViewById(R.id.old_password_tx);
        new_password_tx = (EditText) findViewById(R.id.new_password_tx);
        change_password_btn = (Button) findViewById(R.id.change_password_btn);

        change_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changePassword();
            }
        });

    }

    private void changePassword() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                old = old_password_tx.getText().toString().trim();
                xin = new_password_tx.getText().toString().trim();
                HttpUtil.postOKHttpRequest("http://121.199.40.253/nba/changePassword",
                        "oldPassword", old, "newPassword", xin, new okhttp3.Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                String responseData = response.body().string();
                                try {
                                    JSONObject jsonObject = new JSONObject(responseData);

                                    if (jsonObject.getString("message").equals("success")) {
                                        Intent intent = new Intent();
                                        intent.putExtra("username", jsonObject.getJSONObject("value").getString("username"));
                                        intent.putExtra("realname", jsonObject.getJSONObject("value").getString("realname"));
                                        intent.putExtra("money", jsonObject.getJSONObject("value").getString("money"));
                                        setResult(RESULT_OK, intent);
                                        finish();
                                        Looper.prepare();
                                        Toast.makeText(ChangePasswordActivity.this,
                                                "修改密码成功", Toast.LENGTH_SHORT).show();
                                        Looper.loop();
                                    } else {
                                        Looper.prepare();
                                        Toast.makeText(ChangePasswordActivity.this,
                                                "原密码错误或未登录", Toast.LENGTH_SHORT).show();
                                        Looper.loop();
                                    }
//

//
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        }).start();
    }
}
