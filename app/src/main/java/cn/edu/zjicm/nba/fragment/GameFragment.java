package cn.edu.zjicm.nba.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import cn.edu.zjicm.nba.adapter.GameAdapter;
import cn.edu.zjicm.nba.http.HttpUtil;
import cn.edu.zjicm.nba.model.Game;
import okhttp3.Call;
import okhttp3.Response;

public class GameFragment extends Fragment {

    private List<Game> gameList = new ArrayList<>();

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game,container,false);

        HttpUtil.sendOKHttpRequest("http://121.199.40.253/nba/game", new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseData = response.body().string();
                parseJSONWithJSONObject(responseData);
            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_game);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        GameAdapter adapter = new GameAdapter(gameList, getContext());
        recyclerView.setAdapter(adapter);

        return view;

    }


    private void parseJSONWithJSONObject(String jsonData) {
       try {

           JSONObject jsonObject = new JSONObject(jsonData);

           JSONArray jsonArray = jsonObject.getJSONArray("value");


           for (int i=0; i<jsonArray.length(); i++) {
               JSONObject json = jsonArray.getJSONObject(i);
               Game game = new Game(json.getInt("id"),json.getString("name"),
                       json.getString("away_team_name"),json.getString("away_team_english_name"),
                       json.getString("home_team_name"),json.getString("home_team_english_name"),
                       json.getInt("home_team_score"),
                       json.getInt("away_team_score"),
                       json.getLong("start_time"),
                       json.getInt("status"));
               gameList.add(game);

           }
       } catch (JSONException e) {
           e.printStackTrace();
       }
    }

}