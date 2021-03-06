package com.greativy.leo14;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnItemClickListener}
 * interface.
 */
public class RoundFragment extends Fragment {


    OnItemClickListener mListener;
    private List<SingleGameItem> items;
    private SingleGameDAO mSingleGameDAO;
    private RoundListRecyclerViewAdapter roundListRecyclerViewAdapter;
    private GameListItem mGameListItem;
    private String KEY_GAMELISTITEM = "GameListItem";
    private String KEY_SINGLEGAMEITEM = "SingleGameItem";
    private Bundle bundle;
    private String gameTitle;
    private String player1;
    private String player2;
    private String player3;
    private String player4;
    private RecyclerView recyclerView;
    private static final int PLAYER = 0;
    private static final int SCORE = 1;
    private int mDatasetTypes[] = {PLAYER, SCORE}; //view types

    public static RoundFragment newInstance(GameListItem mGameListItem) {
        RoundFragment roundFragment = new RoundFragment();
        Bundle args = new Bundle();
        args.putSerializable("GameListItem", mGameListItem);
        roundFragment.setArguments(args);
        Log.i("RoundFragment", "newInstance1");

        return roundFragment;
    }


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RoundFragment() {

    }

    /**
     * // TODO: Customize parameter initialization
     *
     * @SuppressWarnings("unused") public static RoundFragment newInstance() {
     * RoundFragment fragment = new RoundFragment();
     * //Bundle args = new Bundle();
     * //args.putInt(ARG_COLUMN_COUNT, columnCount);
     * //fragment.setArguments(args);
     * return fragment;
     * }
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("RoundFragment", "onCreate");

        if (getArguments() != null) {
            Log.i("get argument", "have argument");

            bundle = getArguments();
            mGameListItem = (GameListItem) bundle.getSerializable(KEY_GAMELISTITEM);
            gameTitle = mGameListItem.getGameTitle();
            player1 = mGameListItem.getPlayer1();
            player2 = mGameListItem.getPlayer2();
            player3 = mGameListItem.getPlayer3();
            player4 = mGameListItem.getPlayer4();
            Log.i("RoundFragment", "onCreate");


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_round_list, container, false);
        Log.i("RoundFragment", "onCreateView");

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_singleGame);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            mSingleGameDAO = new SingleGameDAO(getActivity().getApplicationContext());
            if (mSingleGameDAO.getCount() == 0) {
                mSingleGameDAO.sample();
            }

            Log.i("RoundFragment", "total game is " + String.valueOf(mSingleGameDAO.getCount()));
            items = mSingleGameDAO.getAllByGameId(mGameListItem.getId());
            Log.i("RoundFragment", " in Game " + mGameListItem.getId());
            Log.i("RoundFragment", " Game " + mGameListItem.getId() + " has items " + items.size());


            roundListRecyclerViewAdapter = new RoundListRecyclerViewAdapter(mGameListItem, items, mSingleGameDAO, new RoundListRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void clickOnView(View v, int position) {
                    SingleGameItem item = items.get(position);
                    Snackbar.make(v, "test on click from clickonView", Snackbar.LENGTH_LONG).show();
                }
            });
            mListener.onListFragmentInteraction(items);


            recyclerView.setAdapter(roundListRecyclerViewAdapter);


        }


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("RoundFragment", "onAttach");

        if (context instanceof OnItemClickListener) {
            mListener = (OnItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("RoundFragment", "onDetach");

        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnItemClickListener {
        void onListFragmentInteraction(List<SingleGameItem> items);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("RoundFragment", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("RoundFragment", "onPause");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            /**bundle = getArguments();
             SingleGameItem singleGameItem = (SingleGameItem) bundle.getSerializable(KEY_SINGLEGAMEITEM);

             if (singleGameItem != null) {
             //items.add(items.size()+1,singleGameItem);
             roundListRecyclerViewAdapter.notifyItemInserted(items.size()+1);
             recyclerView.scrollToPosition(items.size()+1);
             Log.i("SGitem Condition pass", "pass");

             } else {

             Log.i("SGitem Condition fail", "fail");
             } */
            Log.i("RoundFragment", "setUserVisibleHint Trigger");


        }
    }


}
