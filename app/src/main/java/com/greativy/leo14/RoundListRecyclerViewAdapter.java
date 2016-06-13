package com.greativy.leo14;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import static java.security.AccessController.getContext;


public class RoundListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 1;
    private static final int TYPE_SCORE = 2;
    List<SingleGameItem> list = Collections.emptyList();
    private List<SingleGameItem> sGameItems;
    private GameListItem mGameListItem;
    public OnItemClickListener mListener;

    public interface OnItemClickListener {
        void clickOnView(View v, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mListener = mItemClickListener;

    }



    public RoundListRecyclerViewAdapter(GameListItem item, List<SingleGameItem> items, OnItemClickListener listener) {

        sGameItems = items;
        mGameListItem = item;
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);
        }
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder {
        public TextView ScoreType;
        public TextView p1rScore;
        public TextView p2rScore;
        public TextView p3rScore;
        public TextView p4rScore;
        public TextView tv_player1;
        public TextView tv_player2;
        public TextView tv_player3;
        public TextView tv_player4;

        public ScoreViewHolder(View view) {
            super(view);
            p1rScore = (TextView) view.findViewById(R.id.p1rScore);
            p2rScore = (TextView) view.findViewById(R.id.p2rScore);
            p3rScore = (TextView) view.findViewById(R.id.p3rScore);
            p4rScore = (TextView) view.findViewById(R.id.p4rScore);


        }
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        public TextView player1;
        public TextView player2;
        public TextView player3;
        public TextView player4;


        public PlayerViewHolder(View view) {
            super(view);
            this.player1 = (TextView) view.findViewById(R.id.tv_tbPlayer1);
            player2 = (TextView) view.findViewById(R.id.tv_tbPlayer2);
            player3 = (TextView) view.findViewById(R.id.tv_tbPlayer3);
            player4 = (TextView) view.findViewById(R.id.tv_tbPlayer4);

        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tab_b_players, parent, false);
            viewHolder = new PlayerViewHolder(view);
            return viewHolder;
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_tab_b_scores, parent, false);
            viewHolder = new ScoreViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        switch (viewHolder.getItemViewType()) {
            case TYPE_HEADER:
                PlayerViewHolder holder = (PlayerViewHolder) viewHolder;
                holder.player1.setText(mGameListItem.getPlayer1());
                holder.player2.setText(mGameListItem.getPlayer2());
                holder.player3.setText(mGameListItem.getPlayer3());
                holder.player4.setText(mGameListItem.getPlayer4());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("RoundListAdapter", " onBindView playerviewholder in position " + position);

                    }
                });
                break;
            case TYPE_SCORE:
                SingleGameItem singleGameItem = sGameItems.get(position - 1);
                ScoreViewHolder holder2 = (ScoreViewHolder) viewHolder;
                holder2.p1rScore.setText(String.valueOf(singleGameItem.getPlayer1RoundScore()));
                holder2.p2rScore.setText(String.valueOf(singleGameItem.getPlayer2RoundScore()));
                holder2.p3rScore.setText(String.valueOf(singleGameItem.getPlayer3RoundScore()));
                holder2.p4rScore.setText(String.valueOf(singleGameItem.getPlayer4RoundScore()));
                holder2.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("RoundListAdapter", " onBindView ScoreViewHolder in position " + position);
                    }
                });

                break;
        }
    }

    @Override
    public int getItemCount() {
        return sGameItems.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {

        return position == 0 ? TYPE_HEADER : TYPE_SCORE;
    }



}
