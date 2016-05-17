package com.greativy.leo14;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greativy.leo14.dummy.DummyContent.DummyItem;

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
    //private final List<DummyItem> mValues;
    public OnItemClickListener mListener;

    public RoundListRecyclerViewAdapter(List<SingleGameItem> items, OnItemClickListener listener) {
        sGameItems = items;
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
        SingleGameItem item = sGameItems.get(position);
        //viewHolder.mIdView.setText(mValues.get(position).id);
        //viewHolder.mContentView.setText(mValues.get(position).content);
        viewHolder.mIdView.setText(item.getGameType());
        viewHolder.p1rScore.setText(item.getPlayer1RoundScore().toString());
        viewHolder.p2rScore.setText(item.getPlayer2RoundScore().toString());
        viewHolder.p3rScore.setText(item.getPlayer3RoundScore().toString());
        viewHolder.p4rScore.setText(item.getPlayer4RoundScore().toString());

        //viewHolder.mContentView.setText(item.getPlayer1RoundScore());
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
        public TextView mIdView;
        public TextView mContentView;
        public TextView p1rScore;
        public TextView p2rScore;
        public TextView p3rScore;
        public TextView p4rScore;


        public ViewHolder(View view) {
            super(view);
            //mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            p1rScore = (TextView) view.findViewById(R.id.p1rScore);
            p2rScore = (TextView) view.findViewById(R.id.p2rScore);
            p3rScore = (TextView) view.findViewById(R.id.p3rScore);
            p4rScore = (TextView) view.findViewById(R.id.p4rScore);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

    }
}
