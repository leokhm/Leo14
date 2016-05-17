package com.greativy.leo14;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
   // private String mParam1;
    //private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TabAFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * //@param param1 Parameter 1.
     * //@param param2 Parameter 2.
     * @return A new instance of fragment TabAFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TabAFragment newInstance() {
        TabAFragment fragment = new TabAFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_a, container, false);

        Intent intent = getActivity().getIntent();
        GameListItem item = (GameListItem) intent.getExtras().getSerializable("com.greativy.leo14.GamelistItem");
        TextView tv_gameTitle = (TextView) view.findViewById(R.id.tv_DGameTitle);
        TextView tv_player1 = (TextView) view.findViewById(R.id.tv_DPlayer1);
        TextView tv_player2 = (TextView) view.findViewById(R.id.tv_DPlayer2);
        TextView tv_player3 = (TextView) view.findViewById(R.id.tv_DPlayer3);
        TextView tv_player4 = (TextView) view.findViewById(R.id.tv_DPlayer4);
        TextView tv_player1FinalScore = (TextView) view.findViewById(R.id.tv_DPlayer1FinalScore);
        TextView tv_player2FinalScore = (TextView) view.findViewById(R.id.tv_DPlayer2FinalScore);
        TextView tv_player3FinalScore = (TextView) view.findViewById(R.id.tv_DPlayer3FinalScore);
        TextView tv_player4FinalScore = (TextView) view.findViewById(R.id.tv_DPlayer4FinalScore);


        tv_gameTitle.setText(item.getGameTitle());
        tv_player1.setText(item.getPlayer1());
        tv_player2.setText(item.getPlayer2());
        tv_player3.setText(item.getPlayer3());
        tv_player4.setText(item.getPlayer4());

        tv_player1FinalScore.setText(String.valueOf(0));
        tv_player2FinalScore.setText(String.valueOf(0));
        tv_player3FinalScore.setText(String.valueOf(item.getPlayer3FinalScore()));
        tv_player4FinalScore.setText(String.valueOf(item.getPlayer4FinalScore()));
        /**
        tv_player1FinalScore.setText(item.getPlayer1FinalScore());
        tv_player2FinalScore.setText(item.getPlayer2FinalScore());
        tv_player3FinalScore.setText(item.getPlayer3FinalScore());
        tv_player4FinalScore.setText(item.getPlayer4FinalScore());

        */


        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
        void onFragmentInteraction(Uri uri);
    }
}
