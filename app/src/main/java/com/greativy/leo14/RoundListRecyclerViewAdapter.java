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

import java.util.List;

import static java.security.AccessController.getContext;


public class RoundListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 1;
    private static final int TYPE_SCORE = 2;



    public interface OnItemClickListener {
        void clickOnView(View v, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mListener = mItemClickListener;
    }

    private List<SingleGameItem> sGameItems;
    private GameListItem mGameListItem;
    public OnItemClickListener mListener;

    public RoundListRecyclerViewAdapter(GameListItem item, List<SingleGameItem> items ,OnItemClickListener listener) {

        sGameItems = items;
        mGameListItem = item;
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        /* depreciate to try multiple view
        public TextView ScoreType;
        public TextView p1rScore;
        public TextView p2rScore;
        public TextView p3rScore;
        public TextView p4rScore;
        public TextView tv_player1;
        public TextView tv_player2;
        public TextView tv_player3;
        public TextView tv_player4;
        */


        public ViewHolder(View view) {
            super(view);
            /* depreciate to try multiple view
                        //ScoreType = (TextView) view.findViewById(R.id.scoreType);
            p1rScore = (TextView) view.findViewById(R.id.p1rScore);
            p2rScore = (TextView) view.findViewById(R.id.p2rScore);
            p3rScore = (TextView) view.findViewById(R.id.p3rScore);
            p4rScore = (TextView) view.findViewById(R.id.p4rScore);
            tv_player1 = (TextView) view.findViewById(R.id.tv_DBplayer1);
            tv_player2 = (TextView) view.findViewById(R.id.tv_DBplayer2);
            tv_player3 = (TextView) view.findViewById(R.id.tv_DBplayer3);
            tv_player4 = (TextView) view.findViewById(R.id.tv_DBplayer4);

             */
        }

        /*@Override
        public String toString() {
            return super.toString() + " '" + ScoreType.getText() + "'";
        } */

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
        /*depreciate to try multiple view
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_tab_b_scores, parent, false);
        return new ViewHolder(view);
        */
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

        switch(viewHolder.getItemViewType()) {
            case TYPE_HEADER:
                PlayerViewHolder holder = (PlayerViewHolder) viewHolder;
                holder.player1.setText(mGameListItem.getPlayer1());
                holder.player2.setText(mGameListItem.getPlayer2());
                holder.player3.setText(mGameListItem.getPlayer3());
                holder.player4.setText(mGameListItem.getPlayer4());
                Log.i("Round List Adapter", " onBindView playerviewholder in position " + position);
                break;
            case TYPE_SCORE:
                SingleGameItem singleGameItem = sGameItems.get(position-1);
                ScoreViewHolder holder2 = (ScoreViewHolder) viewHolder;
                holder2.p1rScore.setText(String.valueOf(singleGameItem.getPlayer1RoundScore()));
                holder2.p2rScore.setText(String.valueOf(singleGameItem.getPlayer2RoundScore()));
                holder2.p3rScore.setText(String.valueOf(singleGameItem.getPlayer3RoundScore()));
                holder2.p4rScore.setText(String.valueOf(singleGameItem.getPlayer4RoundScore()));


                Log.i("Round List Adapter", " onBindView scoreviewholder in position " + position);
                break;
        }


        //viewHolder.ScoreType.setText(mValues.get(position).id);
        //viewHolder.mContentView.setText(mValues.get(position).content);
        //viewHolder.ScoreType.setText(singleGameItem.getScoreType().toString());
        /*depreciate to try multiple view type
        viewHolder.p1rScore.setText(singleGameItem.getPlayer1RoundScore().toString());
        viewHolder.p2rScore.setText(singleGameItem.getPlayer2RoundScore().toString());
        viewHolder.p3rScore.setText(singleGameItem.getPlayer3RoundScore().toString());
        viewHolder.p4rScore.setText(singleGameItem.getPlayer4RoundScore().toString());
        viewHolder.tv_player1.setText(mGameListItem.getPlayer1());
        viewHolder.tv_player2.setText(mGameListItem.getPlayer2());
        viewHolder.tv_player3.setText(mGameListItem.getPlayer3());
        viewHolder.tv_player4.setText(mGameListItem.getPlayer4());
        */
        /**viewHolder.mView.setOnClickListener(new View.OnClickListener() {
         /**
         @Override public void onClick(View v) {
         if (null != mListener) {
         // Notify the active callbacks interface (the activity, if the
         // fragment is attached to one) that an item has been selected.
         mListener.onListFragmentInteraction(viewHolder.mItem);
         }
         }

         }); */
    }

    @Override
    public int getItemCount() {
        return sGameItems.size()+1;
    }

    @Override
    public int getItemViewType(int position) {

        return position ==  0 ? TYPE_HEADER : TYPE_SCORE;
    }



}
