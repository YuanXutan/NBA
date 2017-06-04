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

public class LoginActivity extends Activity {

    private EditText username_tx;
    private EditText password_tx;

    private Button login_btn;


    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_btn = (Button) findViewById(R.id.login_btn);
        username_tx = (EditText) findViewById(R.id.username_tx);
        password_tx = (EditText) findViewById(R.id.password_tx);



        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                username = username_tx.getText().toString().trim();
                password = password_tx.getText().toString().trim();
                HttpUtil.postOKHttpRequest("http://121.199.40.253/nba/login",
                        "username",username,"password",password, new okhttp3.Callback() {
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
                                        Looper.prepare();

                                        finish();
                                        Toast.makeText(LoginActivity.this,
                                                "登陆成功", Toast.LENGTH_SHORT).show();
                                        Looper.loop();
                                    } else {
                                        Looper.prepare();
                                        Toast.makeText(LoginActivity.this,
                                                "用户名或密码错误", Toast.LENGTH_SHORT).show();
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
