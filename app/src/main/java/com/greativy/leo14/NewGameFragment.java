package com.greativy.leo14;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import static com.greativy.leo14.R.id.fab;
import static com.greativy.leo14.R.id.recycler_view_main;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewGameFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewGameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewGameFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Button Button_Submit, Button_Cancel;
    private EditText GameTitle, Player1, Player2, Player3, Player4;
    private GameListItem gameListItem;
    private int entryType;
    private Bundle bundle;
    private RecyclerView recyclerView;
    private GameListRecyclerAdapter gameListRecyclerAdapter;
    private GameListDAO gameListDAO;


    public NewGameFragment() {
        // Required empty public constructor
    }


    public static NewGameFragment newInstance(String param1, String param2) {
        NewGameFragment fragment = new NewGameFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bundle = getArguments();
             entryType = bundle.getInt("type");
        }


        if(entryType == 0) {
            gameListItem = (GameListItem) bundle.getSerializable("com.greativy.leo14.GameListItem");
            GameTitle.setText(gameListItem.getGameTitle());
            Player1.setText(gameListItem.getPlayer1());
            Player2.setText(gameListItem.getPlayer2());
            Player3.setText(gameListItem.getPlayer3());
            Player4.setText(gameListItem.getPlayer4());
            //toolbar.setTitle("EDIT ITEM");
        } else {
            gameListItem = new GameListItem();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_game, container, false);
        findView(view);
        onSubmit();
        onCancel();
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

    private void onCancel() {
        Button_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder d = new AlertDialog.Builder(getActivity());
                d.setTitle(R.string.confirm_cancel)
                        .setMessage(R.string.confirm_cancel_content)
                        .setPositiveButton(android.R.string.yes,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        FragmentManager fm = getFragmentManager();
                                        fm.popBackStackImmediate();

                                    }
                                });
                d.setNegativeButton(android.R.string.no, null);
                d.show();
            }
        });
    }

    public void onSubmit() {
        Button_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation(v)) {
                    //TODO add sharedpreference to save the player names
                    gameListItem.setGameTitle(GameTitle.getText().toString());
                    gameListItem.setPlayer1(Player1.getText().toString());
                    gameListItem.setPlayer2(Player2.getText().toString());
                    gameListItem.setPlayer3(Player3.getText().toString());
                    gameListItem.setPlayer4(Player4.getText().toString());
                    gameListItem.setPlayer1FinalScore(0);
                    gameListItem.setPlayer2FinalScore(0);
                    gameListItem.setPlayer3FinalScore(0);
                    gameListItem.setPlayer4FinalScore(0);


                    if(entryType == 1) {
                        gameListItem.setCreateTime(new Date().getTime());
                        gameListItem.setModTime(new Date().getTime());


                    } else {
                        gameListItem.setModTime(new Date().getTime());
                    }
                    bundle.putSerializable("com.greativy.leo14.GameListItem", gameListItem);
                    gameListDAO = new GameListDAO(getContext());
                    gameListDAO.insert(gameListItem);
                    //gameListRecyclerAdapter.notifyDataSetChanged();
                    //recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_main);
                    //recyclerView.setAdapter(gameListRecyclerAdapter);
                    FragmentManager fm = getFragmentManager();
                    MainListFragment mainListFragment = new MainListFragment();
                    mainListFragment.setArguments(bundle);
                    fm.popBackStack();
                    //setResult(Activity.RESULT_OK, result);
                    //finish();

                }
            }
        });

    }


    private Boolean validation(View v) {
        EditText[] stg = {GameTitle, Player1, Player2};
        EditText[] stg2 = {GameTitle, Player1, Player2, Player3, Player4};
        boolean BL_error = true;

        for (EditText t : stg) {
            TextInputLayout parent = (TextInputLayout) t.getParent();
            if (t.getText().toString().isEmpty()) {
                CharSequence hint = parent.getHint();
                Snackbar.make(v, hint + " is Empty", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                parent.setError(hint + " " + getString(R.string.err_msg_name));
                BL_error = false;
            } else {
                parent.setError(null);
            }
        }

        /**
         for (EditText t: stg2) {
         TextInputLayout parent = (TextInputLayout) t.getParent();
         if (!t.getText().toString().isEmpty() && t.getText().toString().length() >= 20) {
         CharSequence hint = parent.getHint();
         Snackbar.make(v, hint + " must be less than 20 chars ", Snackbar.LENGTH_LONG)
         .setAction("Action", null).show();
         parent.setError(hint + " " + getString(R.string.err_maxlength));
         BL_error = false;
         } else {
         parent.setError(null);
         }

         }*/


        return BL_error;

    }


    public void findView(View view) {
        Button_Submit = (Button) view.findViewById(R.id.button_Submit);
        Button_Cancel = (Button) view.findViewById(R.id.button_Cancel);
        GameTitle = (EditText) view.findViewById(R.id.editText_GameTitle);
        Player1 = (EditText) view.findViewById(R.id.editText_Player1);
        Player2 = (EditText) view.findViewById(R.id.editText_Player2);
        Player3 = (EditText) view.findViewById(R.id.editText_Player3);
        Player4 = (EditText) view.findViewById(R.id.editText_Player4);


    }
}
