package cn.edu.zjicm.nba.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zjicm.nba.R;
import cn.edu.zjicm.nba.adapter.GroupAdapter;
import cn.edu.zjicm.nba.http.HttpUtil;
import cn.edu.zjicm.nba.model.Group;
import cn.edu.zjicm.nba.model.Team;
import okhttp3.Call;
import okhttp3.Response;

public class MineFragment extends Fragment {


    private List<Group> groupList = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_group, container, false);

        HttpUtil.sendOKHttpRequest("http://121.199.40.253/nba/group", new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseData = response.body().string();
                parseJSONWithJSONObject(responseData);
            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_group);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        GroupAdapter adapter = new GroupAdapter(groupList, getContext());
        recyclerView.setAdapter(adapter);
        return view;

    }


    private void parseJSONWithJSONObject(String jsonData) {
        try {

            JSONObject jsonObject = new JSONObject(jsonData);

            JSONArray jsonArray = jsonObject.getJSONArray("value");

            List<Team> teamList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                JSONArray jsonArray1 = json.getJSONArray("teams");
                for (int j = 0; j<jsonArray1.length();j++) {

                    JSONObject jsonObject1 = jsonArray1.getJSONObject(j);

                    Team team = new Team(jsonObject1.getString("name"),
                            jsonObject1.getString("englishName"),
                            jsonObject1.getInt("win"),jsonObject1.getInt("lost"));
                    teamList.add(team);
                }

                Group group = new Group(json.getString("name"), teamList);
                groupList.add(group);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}