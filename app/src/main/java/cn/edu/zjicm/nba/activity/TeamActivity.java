package cn.edu.zjicm.nba.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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


public class TeamActivity extends Activity {


	private String team;
	private String team_english_name;
	private List<Game> gameList=new ArrayList<>();
	private Handler mHandler;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.single_listview);


		Intent intent = getIntent();
		team = intent.getStringExtra("team");
		HttpUtil.sendOKHttpRequest("http://121.199.40.253/nba/game?team="+team, new okhttp3.Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {

				String responseData = response.body().string();
				parseJSONWithJSONObject(responseData);

			}
		});

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listview);
		LinearLayoutManager layoutManager = new LinearLayoutManager(TeamActivity.this);
		recyclerView.setLayoutManager(layoutManager);
		GameAdapter adapter = new GameAdapter(gameList, TeamActivity.this);
		recyclerView.setAdapter(adapter);
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
