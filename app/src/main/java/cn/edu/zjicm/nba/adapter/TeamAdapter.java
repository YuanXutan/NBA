package cn.edu.zjicm.nba.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import cn.edu.zjicm.nba.R;
import cn.edu.zjicm.nba.activity.TeamActivity;
import cn.edu.zjicm.nba.model.Team;

public class TeamAdapter extends BaseAdapter {
	private LayoutInflater inflater = null;
	public List<Team> _listData = null;
	private Context mContext = null;

	public TeamAdapter(Context context, List<Team> list) {
		mContext = context;
		_listData = list;
		inflater = LayoutInflater.from(mContext);
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return _listData == null ? 0 : _listData.size();
	}

	@Override
	public Team getItem(int position) {
		// TODO Auto-generated method stub
		return _listData == null ? null : _listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return _listData == null ? 0 : position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		final View view = convertView;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_item_team, null);
			holder.txt_team_rank = (TextView)convertView.findViewById(R.id.txt_rank);
			holder.img_team_flag =  (ImageView)convertView.findViewById(R.id.img_team_flag);
			holder.txt_team_name = (TextView)convertView.findViewById(R.id.txt_team_name);
			holder.txt_win = (TextView) convertView	.findViewById(R.id.txt_win);
			holder.txt_lost = (TextView) convertView.findViewById(R.id.txt_lost);

			convertView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(mContext, TeamActivity.class);
					intent.putExtra("team", _listData.get(position).getEnglishName());
					mContext.startActivity(intent);
				}
			});
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}

		final Team team = _listData.get(position);
		String[] strs = team.getEnglishName().split(" ");
		String teamName = strs[0];
		for(int i=1; i<strs.length;i++)
			teamName += "_" + strs[i];
		holder.img_team_flag.setImageResource(mContext.getResources().getIdentifier(
				teamName.toLowerCase(Locale.ENGLISH), "drawable", mContext.getPackageName()));
		holder.txt_team_rank.setText(position + 1 + "");
		holder.txt_team_name.setText(team.getName());
		holder.txt_win.setText(team.getWin()+"");
		holder.txt_lost.setText(team.getLost()+"");



		return convertView;
	}

	public class ViewHolder{
		private TextView txt_team_rank;
		private ImageView img_team_flag;
		private TextView txt_team_name;
		private TextView txt_win;
		private TextView txt_lost;
	}




}
