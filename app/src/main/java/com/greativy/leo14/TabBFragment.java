package com.greativy.leo14;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnItemClickListener}
 * interface.
 */
public class TabBFragment extends Fragment {


    private OnItemClickListener mListener;
    private List<SingleGameItem> items;
    private SingleGameDAO singleGameDAO;
    private RoundListRecyclerViewAdapter roundListRecyclerViewAdapter;
    private GameListItem mGameListItem;
    private String KEY_GAMELISTITEM = "GameListItem";
    private Bundle bundle;
    private String gameTitle;
    private String player1;
    private String player2;
    private String player3;
    private String player4;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TabBFragment() {

    }

    /**
     * // TODO: Customize parameter initialization
     *
     * @SuppressWarnings("unused") public static TabBFragment newInstance() {
     * TabBFragment fragment = new TabBFragment();
     * //Bundle args = new Bundle();
     * //args.putInt(ARG_COLUMN_COUNT, columnCount);
     * //fragment.setArguments(args);
     * return fragment;
     * }
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("onCreate", "logC");

        if (getArguments() != null) {
            Log.i("log", "logD");

            bundle = getArguments();
            mGameListItem = (GameListItem) bundle.getSerializable(KEY_GAMELISTITEM);

            gameTitle = mGameListItem.getGameTitle();
            player1 = mGameListItem.getPlayer1();
            player2 = mGameListItem.getPlayer2();
            player3 = mGameListItem.getPlayer3();
            player4 = mGameListItem.getPlayer4();


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_b_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_sgame);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setItemAnimator(new DefaultItemAnimator());


            singleGameDAO = new SingleGameDAO(getActivity().getApplicationContext());
            if (singleGameDAO.getCount() == 0) {
                singleGameDAO.sample();
            }
            items = singleGameDAO.getAllByGameId(mGameListItem.getId());
            Log.i("Tab B Fragment"," in Game "+ mGameListItem.getId());
            Log.i("Tab B Fragment"," has items "+items.size());

            roundListRecyclerViewAdapter = new RoundListRecyclerViewAdapter(mGameListItem, items, new RoundListRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void clickOnView(View v, int position) {
                    SingleGameItem item = items.get(position);

                    Snackbar.make(v, "test", Snackbar.LENGTH_LONG).show();


                }
            });


            recyclerView.setAdapter(roundListRecyclerViewAdapter);
        }

        Boolean CreateNewRound = bundle.getBoolean("CreateNewRound");
        if(CreateNewRound == true) {
            roundListRecyclerViewAdapter.notifyItemInserted(singleGameDAO.getAll().size()+1);

        }


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
        // TODO: Update argument type and name
        void onListFragmentInteraction(SingleGameItem item);
    }
}
