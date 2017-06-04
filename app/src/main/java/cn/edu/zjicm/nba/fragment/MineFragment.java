package cn.edu.zjicm.nba.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.edu.zjicm.nba.R;
import cn.edu.zjicm.nba.activity.ChangePasswordActivity;
import cn.edu.zjicm.nba.activity.LoginActivity;
import cn.edu.zjicm.nba.http.HttpUtil;
import okhttp3.Call;
import okhttp3.Response;


public class MineFragment extends Fragment {

    TextView user_name_tx;
    TextView real_name_tx;
    TextView money_tx;

    Button login_btn;
    Button change_password_btn;
    Button logout_btn;

    LinearLayout login_ll;
    LinearLayout user_info_ll;

    private Handler handler=null;

    private JSONObject object;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建属于主线程的handler
        handler=new Handler();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        user_name_tx = (TextView) view.findViewById(R.id.user_name_tx);
        real_name_tx = (TextView) view.findViewById(R.id.real_name_tx);
        money_tx = (TextView) view.findViewById(R.id.money_tx);

        login_btn = (Button) view.findViewById(R.id.login_btn);
        change_password_btn = (Button) view.findViewById(R.id.change_password_btn);
        logout_btn = (Button) view.findViewById(R.id.logout_btn);

        login_ll = (LinearLayout) view.findViewById(R.id.login_ll);
        user_info_ll = (LinearLayout) view.findViewById(R.id.user_info_ll);

        HttpUtil.sendOKHttpRequest("http://121.199.40.253/nba/member", new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseData = response.body().string();
                try {

                    JSONObject jsonObject = new JSONObject(responseData);
                    if (jsonObject.getString("message").equals("success")) {
                        object = jsonObject;
                        handler.post(runnableUi2);

                    }else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        change_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivityForResult(intent2, 2);
            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logout();
            }
        });
        return view;
    }


    private void logout() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtil.logoutOKHttpRequest("http://121.199.40.253/nba/logout", new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String responseData = response.body().string();
                        try {
                            JSONObject jsonObject = new JSONObject(responseData);

                            if (jsonObject.getString("message").equals("success")) {

                                handler.post(runnableUi);

                                Looper.prepare();
                                Toast.makeText(getActivity(),
                                        "退出当前帐号成功", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            } else {
                                Looper.prepare();
                                Toast.makeText(getActivity(),
                                        "未登录", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == getActivity().RESULT_OK) {
                    String username = data.getStringExtra("username");
                    String realname = data.getStringExtra("realname");
                    String money = data.getStringExtra("money");

                    login_ll.setVisibility(View.GONE);
                    user_info_ll.setVisibility(View.VISIBLE);
                    user_name_tx.setText(username);
                    real_name_tx.setText(realname);
                    money_tx.setText(money);
                }
                break;
            case 2:
                if (resultCode == getActivity().RESULT_OK) {
                    String username = data.getStringExtra("username");
                    String realname = data.getStringExtra("realname");
                    String money = data.getStringExtra("money");

                    login_ll.setVisibility(View.GONE);
                    user_info_ll.setVisibility(View.VISIBLE);
                    user_name_tx.setText(username);
                    real_name_tx.setText(realname);
                    money_tx.setText(money);
                }
                break;
            default:
        }
    }

    // 构建Runnable对象，在runnable中更新界面
    Runnable   runnableUi=new  Runnable(){
        @Override
        public void run() {
            //更新界面
            login_ll.setVisibility(View.VISIBLE);
            user_info_ll.setVisibility(View.GONE);
        }

    };

    Runnable   runnableUi2=new  Runnable(){
        @Override
        public void run() {
            //更新界面
            login_ll.setVisibility(View.GONE);
            user_info_ll.setVisibility(View.VISIBLE);

            try {
                user_name_tx.setText(object.getJSONObject("value").getString("username"));
                real_name_tx.setText(object.getJSONObject("value").getString("realname"));
                money_tx.setText(object.getJSONObject("value").getString("money"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    };
}