package com.greativy.leo14;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link TabBFragment.OnItemClickListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class RoundListRecyclerViewAdapter extends RecyclerView.Adapter<RoundListRecyclerViewAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void clickOnView(View v, int position);
    }
    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mListener = mItemClickListener;
    }
    private List<SingleGameItem> sGameItems;
    private GameListItem mGameListItem;
    //private final List<DummyItem> mValues;
    public OnItemClickListener mListener;

    public RoundListRecyclerViewAdapter(GameListItem item, List<SingleGameItem> items, OnItemClickListener listener) {

        sGameItems = items;
        mGameListItem = item;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_tab_b, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        SingleGameItem singleGameItem = sGameItems.get(position);
        //viewHolder.ScoreType.setText(mValues.get(position).id);
        //viewHolder.mContentView.setText(mValues.get(position).content);
        viewHolder.ScoreType.setText(singleGameItem.getScoreType().toString());
        viewHolder.p1rScore.setText(singleGameItem.getPlayer1RoundScore().toString());
        viewHolder.p2rScore.setText(singleGameItem.getPlayer2RoundScore().toString());
        viewHolder.p3rScore.setText(singleGameItem.getPlayer3RoundScore().toString());
        viewHolder.p4rScore.setText(singleGameItem.getPlayer4RoundScore().toString());
        viewHolder.tv_player1.setText(mGameListItem.getPlayer1());
        viewHolder.tv_player2.setText(mGameListItem.getPlayer2());
        viewHolder.tv_player3.setText(mGameListItem.getPlayer3());
        viewHolder.tv_player4.setText(mGameListItem.getPlayer4());

        /**viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            /**
            @Override
            public void onClick(View v) {
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
        return sGameItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //public final View mView;
        public TextView ScoreType;
        public TextView p1rScore;
        public TextView p2rScore;
        public TextView p3rScore;
        public TextView p4rScore;
        public TextView tv_player1;
        public TextView tv_player2;
        public TextView tv_player3;
        public TextView tv_player4;




        public ViewHolder(View view) {
            super(view);
            //mView = view;
            ScoreType = (TextView) view.findViewById(R.id.scoreType);
            p1rScore = (TextView) view.findViewById(R.id.p1rScore);
            p2rScore = (TextView) view.findViewById(R.id.p2rScore);
            p3rScore = (TextView) view.findViewById(R.id.p3rScore);
            p4rScore = (TextView) view.findViewById(R.id.p4rScore);
            tv_player1 = (TextView) view.findViewById(R.id.tv_DBplayer1);
            tv_player2 = (TextView) view.findViewById(R.id.tv_DBplayer2);
            tv_player3 = (TextView) view.findViewById(R.id.tv_DBplayer3);
            tv_player4 = (TextView) view.findViewById(R.id.tv_DBplayer4);


        }

        @Override
        public String toString() {
            return super.toString() + " '" + ScoreType.getText() + "'";
        }

    }
}
