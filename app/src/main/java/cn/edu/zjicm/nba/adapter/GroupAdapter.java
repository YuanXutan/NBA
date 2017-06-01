package cn.edu.zjicm.nba.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cn.edu.zjicm.nba.R;
import cn.edu.zjicm.nba.model.Group;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    private List<Group> groupList;
    private Context context;

    public GroupAdapter(List<Group> key, Context context) {
        this.groupList = key;
        this.context = context;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_group_name ;
        TextView txt_scorebar_name_title;
        TextView txt_scorebar_w_title;
        TextView txt_scorebar_l_title;
        ListView listView ;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_group_name = (TextView) itemView.findViewById(R.id.txt_group_name);
            txt_scorebar_name_title = (TextView) itemView.findViewById(R.id.txt_scorebar_name_title);
            txt_scorebar_w_title = (TextView) itemView.findViewById(R.id.txt_scorebar_w_title);
            txt_scorebar_l_title = (TextView) itemView.findViewById(R.id.txt_scorebar_l_title);
            listView = (ListView) itemView.findViewById(R.id.elvScoreboard);



        }
    }

    public GroupAdapter(List<Group> groupList) {
        this.groupList = groupList;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_group, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Group group = groupList.get(position);
        holder.txt_group_name.setText(group.getName());
        holder.txt_scorebar_name_title.setText(R.string.sb_title_T);
        holder.txt_scorebar_w_title.setText(R.string.sb_title_W);
        holder.txt_scorebar_l_title.setText(R.string.sb_title_L);


//        根据innerlistview的高度机损parentlistview item的高度
        setListViewHeightBasedOnChildren(holder.listView);
        TeamAdapter adapter = new TeamAdapter(context, group.getTeams());
        holder.listView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    /**
     * @param  listView
     * 此方法是本次listview嵌套listview的核心方法：计算parentlistview item的高度。
     * 如果不使用此方法，无论innerlistview有多少个item，则只会显示一个item。
     **/
    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {        return;    }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}