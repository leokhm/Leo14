package com.greativy.leo14;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabAFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabAFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabAFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Bundle bundle;
    private GameListItem mGameListItem;
    private String KEY_GAMELISTITEM = "GameListItem";
    private String gameTitle;
    private String player1;
    private String player2;
    private String player3;
    private String player4;


    public TabAFragment() {

    }

    public static TabAFragment newInstance() {
        TabAFragment fragment = new TabAFragment();
        //Bundle args = new Bundle();
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            bundle = getArguments();
            mGameListItem = (GameListItem) bundle.getSerializable(KEY_GAMELISTITEM);
            gameTitle = mGameListItem.getGameTitle();
            player1 = mGameListItem.getPlayer1();
            player2 = mGameListItem.getPlayer2();
            player3 = mGameListItem.getPlayer3();
            player4 = mGameListItem.getPlayer4();

            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_a, container, false);

        TextView tv_gameTitle = (TextView) view.findViewById(R.id.tv_DGameTitle);
        TextView tv_player1 = (TextView) view.findViewById(R.id.tv_DPlayer1);
        TextView tv_player2 = (TextView) view.findViewById(R.id.tv_DPlayer2);
        TextView tv_player3 = (TextView) view.findViewById(R.id.tv_DPlayer3);
        TextView tv_player4 = (TextView) view.findViewById(R.id.tv_DPlayer4);
        TextView tv_player1FinalScore = (TextView) view.findViewById(R.id.tv_DPlayer1FinalScore);
        TextView tv_player2FinalScore = (TextView) view.findViewById(R.id.tv_DPlayer2FinalScore);
        TextView tv_player3FinalScore = (TextView) view.findViewById(R.id.tv_DPlayer3FinalScore);
        TextView tv_player4FinalScore = (TextView) view.findViewById(R.id.tv_DPlayer4FinalScore);


        tv_gameTitle.setText(gameTitle);
        tv_player1.setText(player1);
        tv_player2.setText(player2);
        tv_player3.setText(player3);
        tv_player4.setText(player4);
        tv_player1FinalScore.setText(String.valueOf(0));
        tv_player2FinalScore.setText(String.valueOf(0));
        tv_player3FinalScore.setText(String.valueOf(mGameListItem.getPlayer3FinalScore()));
        tv_player4FinalScore.setText(String.valueOf(mGameListItem.getPlayer4FinalScore()));


        /**
        tv_player1FinalScore.setText(mGameListItem.getPlayer1FinalScore());
        tv_player2FinalScore.setText(mGameListItem.getPlayer2FinalScore());
        tv_player3FinalScore.setText(mGameListItem.getPlayer3FinalScore());
        tv_player4FinalScore.setText(mGameListItem.getPlayer4FinalScore());

        */


        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction();
    }
}
