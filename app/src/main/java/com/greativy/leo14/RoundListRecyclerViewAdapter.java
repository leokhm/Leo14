package com.greativy.leo14;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;


public class RoundListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 1;
    private static final int TYPE_SCORE = 2;
    List<SingleGameItem> list = Collections.emptyList();
    private List<SingleGameItem> sGameItems;
    private GameListItem mGameListItem;
    public OnItemClickListener mListener;
    private SingleGameDAO mSingleGameDAO;
    private long gameId;


    public interface OnItemClickListener {
        void clickOnView(View v, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mListener = mItemClickListener;

    }


    public RoundListRecyclerViewAdapter(GameListItem item, List<SingleGameItem> items, SingleGameDAO singleGameDAO, OnItemClickListener listener) {

        sGameItems = items;
        mGameListItem = item;
        mSingleGameDAO = singleGameDAO;
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);
        }
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder {
        public TextView ScoreType;
        public TextView tv_p1rScore;
        public TextView tv_p2rScore;
        public TextView tv_p3rScore;
        public TextView tv_p4rScore;
        public TextView tv_player1;
        public TextView tv_player2;
        public TextView tv_player3;
        public TextView tv_player4;

        public ScoreViewHolder(View view) {
            super(view);
            tv_p1rScore = (TextView) view.findViewById(R.id.p1rScore);
            tv_p2rScore = (TextView) view.findViewById(R.id.p2rScore);
            tv_p3rScore = (TextView) view.findViewById(R.id.p3rScore);
            tv_p4rScore = (TextView) view.findViewById(R.id.p4rScore);


        }
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        public TextView player1;
        public TextView player2;
        public TextView player3;
        public TextView player4;
        public TextView score1;
        public TextView score2;
        public TextView score3;
        public TextView score4;


        public PlayerViewHolder(View view) {
            super(view);
            player1 = (TextView) view.findViewById(R.id.tv_tbPlayer1);
            player2 = (TextView) view.findViewById(R.id.tv_tbPlayer2);
            player3 = (TextView) view.findViewById(R.id.tv_tbPlayer3);
            player4 = (TextView) view.findViewById(R.id.tv_tbPlayer4);
            score1 = (TextView) view.findViewById(R.id.tv_tbScore1);
            score2 = (TextView) view.findViewById(R.id.tv_tbScore2);
            score3 = (TextView) view.findViewById(R.id.tv_tbScore3);
            score4 = (TextView) view.findViewById(R.id.tv_tbScore4);


        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_round_players, parent, false);
            viewHolder = new PlayerViewHolder(view);
            return viewHolder;
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_round_scores, parent, false);
            viewHolder = new ScoreViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        int p1rScore, p2rScore, p3rScore, p4rScore;

        switch (viewHolder.getItemViewType()) {
            case TYPE_HEADER:
                PlayerViewHolder holder = (PlayerViewHolder) viewHolder;
                holder.player1.setText(mGameListItem.getPlayer1());
                holder.player2.setText(mGameListItem.getPlayer2());
                holder.player3.setText(mGameListItem.getPlayer3());
                holder.player4.setText(mGameListItem.getPlayer4());
                gameId = mGameListItem.getId();
                holder.score1.setText(String.valueOf(mSingleGameDAO.getScoreSumByPlayerByGameId("p1rscore", gameId)));
                holder.score2.setText(String.valueOf(mSingleGameDAO.getScoreSumByPlayerByGameId("p2rscore", gameId)));
                holder.score3.setText(String.valueOf(mSingleGameDAO.getScoreSumByPlayerByGameId("p3rscore", gameId)));
                holder.score4.setText(String.valueOf(mSingleGameDAO.getScoreSumByPlayerByGameId("p4rscore", gameId)));

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("RoundListAdapter", " onBindView playerviewholder in position " + position);

                    }
                });
                break;
            case TYPE_SCORE:
                SingleGameItem singleGameItem = sGameItems.get(position - 1);
                p1rScore = (singleGameItem.getPlayer1RoundScore());
                p2rScore = (singleGameItem.getPlayer2RoundScore());
                p3rScore = (singleGameItem.getPlayer3RoundScore());
                p4rScore = (singleGameItem.getPlayer4RoundScore());


                ScoreViewHolder holder2 = (ScoreViewHolder) viewHolder;
                holder2.tv_p1rScore.setText(String.valueOf(p1rScore));
                holder2.tv_p2rScore.setText(String.valueOf(p2rScore));
                holder2.tv_p3rScore.setText(String.valueOf(p3rScore));
                holder2.tv_p4rScore.setText(String.valueOf(p4rScore));
                int[] pScore = {p1rScore, p2rScore, p3rScore, p4rScore};
                TextView[] tv_pScore = {holder2.tv_p1rScore, holder2.tv_p2rScore, holder2.tv_p3rScore, holder2.tv_p4rScore};
                for (int i = 0; i < pScore.length; i++) {
                    if (pScore[i] >= 1) {
                        tv_pScore[i].setTextColor(Color.GREEN);
                    } else if (pScore[i] < -1) {
                        tv_pScore[i].setTextColor(Color.RED);
                    } else if (pScore[i] == 0) {
                        tv_pScore[i].setTextColor(Color.parseColor("#8e8e8e"));
                    }
                }


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
