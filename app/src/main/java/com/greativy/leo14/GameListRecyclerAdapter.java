package com.greativy.leo14;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by leokh on 5/7/2016.
 */
public class GameListRecyclerAdapter extends RecyclerView.Adapter<GameListRecyclerAdapter.ViewHolder>{

    public interface OnItemClickListener {
        void clickOnView(View v, int position);
    }
    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mListener = mItemClickListener;
    }

    private List<GameListItem> mItems;
    public OnItemClickListener mListener;

    public GameListRecyclerAdapter(List<GameListItem> mItems, OnItemClickListener mListener) {
        this.mItems = mItems;
        this.mListener = mListener;
    }

    //onCreateViewHolder 建立 view，並將 view 轉成 ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i){
        View dataView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_game_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(dataView, new OnItemClickListener() {

            @Override
            public void clickOnView(View v, int position) {
                GameListItem item = mItems.get(position);

                Snackbar.make(v, item.getGameTitle(), Snackbar.LENGTH_LONG).show();
            }
        });

        return viewHolder;
    }

    //後者將 data 顯示在 view 中
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position)
    {
        //  获取当前item中显示的数据
        GameListItem item = mItems.get(position);
        //viewHolder.gameTitleView.setText(String.valueOf(position));
        viewHolder.gameTitleView.setText(item.getGameTitle());
        viewHolder.p1NameView.setText(item.getPlayer1());
        viewHolder.p2NameView.setText(item.getPlayer2());
        viewHolder.p3NameView.setText(item.getPlayer3());
        viewHolder.p4NameView.setText(item.getPlayer4());
        DateFormat df;
        df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.US);
        String datetime = df.format(item.getCreateTime());
        viewHolder.CreateTimeView.setText(datetime);


    }

    //先在 ContactsAdapter 中加入 ViewHolder;  ViewHolder pattern 在 ListView 時已有，不過可選擇不使用，而 RecyclerView.Adapter 強制使用。
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView gameTitleView;
        public TextView p1NameView;
        public TextView p2NameView;
        public TextView p3NameView;
        public TextView p4NameView;
        public TextView CreateTimeView;

        public ViewHolder(View itemView, OnItemClickListener mListener)
        {
            super(itemView);

            gameTitleView = (TextView) itemView.findViewById(R.id.tv_GameTitle);
            p1NameView = (TextView) itemView.findViewById(R.id.tv_Player1);
            p2NameView = (TextView) itemView.findViewById(R.id.tv_Player2);
            p3NameView = (TextView) itemView.findViewById(R.id.tv_Player3);
            p4NameView = (TextView) itemView.findViewById(R.id.tv_Player4);
            CreateTimeView = (TextView) itemView.findViewById(R.id.tv_Date);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.clickOnView(v, getPosition());
            }
        }


    }



    @Override
    public int getItemCount()
    {
        return mItems.size();
    }
    //  删除指定的Item
    public void removeData(int position)
    {
        //sampleData.remove(position);
        //  通知RecyclerView控件某个Item已经被删除
        notifyItemRemoved(position);

    }
    //  在指定位置添加一个新的Item
    public void addItem(int positionToAdd)
    {
        //ampleData.add(positionToAdd,new SampleModel("新的列表项" + new Random().nextInt(10000)));
        //  通知RecyclerView控件插入了某个Item
        notifyItemInserted(positionToAdd);
    }


}
