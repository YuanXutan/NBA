package cn.edu.zjicm.nba.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.edu.zjicm.nba.R;
import cn.edu.zjicm.nba.model.Game;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    public List<Game> mGameList ;

    Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_game_time;
        TextView txt_game_name;
        TextView txt_home_team_name;
        TextView txt_score;
        TextView txt_away_team_name;
        ImageView img_home_team_flag;
        ImageView img_away_team_flag;


        public ViewHolder(View itemView) {
            super(itemView);

            txt_game_time = (TextView) itemView.findViewById(R.id.txt_game_time);
            txt_game_name = (TextView) itemView.findViewById(R.id.txt_game_name);
            txt_home_team_name = (TextView) itemView.findViewById(R.id.txt_home_team_name);
            txt_score = (TextView) itemView.findViewById(R.id.txt_score);
            txt_away_team_name = (TextView) itemView.findViewById(R.id.txt_away_team_name);
            img_home_team_flag = (ImageView) itemView.findViewById(R.id.img_home_team_flag);
            img_away_team_flag = (ImageView) itemView.findViewById(R.id.img_away_team_flag);

        }
    }

    public GameAdapter (List<Game> gameList ,Context context) {

        mContext = context;
        mGameList = gameList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_game,parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Game game = mGameList.get(position);

        SimpleDateFormat formatter = new SimpleDateFormat("MM.dd HH:mm");
        String time = formatter.format(new Date(game.getStart_time()* 1000));

        holder.txt_game_time.setText(time);
        holder.txt_game_name.setText(game.getName());
        holder.txt_home_team_name.setText(game.getHome_team_name());
        holder.txt_score.setText(game.getHome_team_score()+ ":" +game.getAway_team_score());
        holder.txt_away_team_name.setText(game.getAway_team_name());


        String[] strs = game.getAway_team_english_name().split(" ");
        String teamName = strs[0];
        String[] strs2 = game.getHome_team_english_name().split(" ");
        String teamName2 = strs2[0];
        for(int i=1; i<strs.length;i++) {
            teamName += "_" + strs[i];

        }
        for(int j=1; j<strs2.length;j++) {
            teamName2 += "_" + strs2[j];

        }
        holder.img_away_team_flag.setImageResource(mContext.getResources().getIdentifier(
                teamName.toLowerCase(Locale.ENGLISH), "drawable", mContext.getPackageName()));

        holder.img_home_team_flag.setImageResource(mContext.getResources().getIdentifier(
                teamName2.toLowerCase(Locale.ENGLISH), "drawable", mContext.getPackageName()));

    }

    @Override
    public int getItemCount() {
        return mGameList.size();
    }

}
