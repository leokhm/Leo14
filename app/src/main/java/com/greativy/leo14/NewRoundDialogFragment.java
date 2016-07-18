package com.greativy.leo14;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class NewRoundDialogFragment extends DialogFragment {
    OnItemSubmitListener mListener;
    private NumberPicker numberPicker_score;
    private NumberPicker numberPicker_name;
    private NumberPicker numberPicker_name2;
    private NumberPicker numberPicker_scoreType;
    private Button button_RoundSubmit;
    private Button button_Cancel;
    private String KEY_GAMELISTITEM = "GameListItem";
    private GameListItem mGameListItem;
    private SingleGameItem mSingleGameItem;
    private SingleGameDAO mSingleGameDAO;
    private GetRoundScore mGetRoundScore;
    private Bundle bundle;
    private String player1;
    private String player2;
    private String player3;
    private String player4;
    private String[] players;
    private int NameValue;
    private int Name2Value;
    private int ScoreTypeValue;
    private int ScoreValue;
    private long gameId;
    private String scoreLevelUnit= "fan";
    private TextView tv_2ndplayer;
    final String[] scoreType = new String[]{"食糊", "自摸", "包"};
    String[] scoreLevel = new String[]{
            "3" + scoreLevelUnit,
            "4" + scoreLevelUnit,
            "5" + scoreLevelUnit,
            "6" + scoreLevelUnit,
            "7" + scoreLevelUnit,
            "8" + scoreLevelUnit,
            "9" + scoreLevelUnit,
            "10" + scoreLevelUnit};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("onCreate", "logC");
        if (getArguments() != null) {
            Log.i("log", "logD");
            bundle = getArguments();
            mGameListItem = (GameListItem) bundle.getSerializable(KEY_GAMELISTITEM);
            gameId = mGameListItem.getId();
            Log.i("gameId", "game Id is " + String.valueOf(gameId));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle(R.string.new_round);
        scoreLevelUnit = getResources().getString(R.string.score_level_unit);

        View view = inflater.inflate(R.layout.fragment_new_round, container);
        tv_2ndplayer = (TextView) view.findViewById(R.id.tv_dialog2ndPlayer);
        button_Cancel = (Button) view.findViewById(R.id.button_RoundCancel);
        button_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        button_RoundSubmit = (Button) view.findViewById(R.id.button_RoundSubmit);
        button_RoundSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSingleGameItem = new SingleGameItem();
                mSingleGameDAO = new SingleGameDAO(getContext());
                NameValue = numberPicker_name.getValue();
                Name2Value = numberPicker_name2.getValue();
                ScoreTypeValue = numberPicker_scoreType.getValue();
                ScoreValue = numberPicker_score.getValue();
                Toast.makeText(getContext(), "Player" + NameValue + 1 + " " + scoreType[ScoreTypeValue]  + " Player " + Name2Value+1+ " with Score "+ scoreLevel[ScoreValue], Toast.LENGTH_LONG).show();
                mGetRoundScore = new GetRoundScore();
                mSingleGameItem = mGetRoundScore.getResult(gameId, ScoreTypeValue, NameValue, Name2Value, ScoreValue, mSingleGameDAO);
                mListener.onDialogFragmentInteraction(mSingleGameItem);
                //mRoundListRecyclerViewAdapter.notifyItemInserted(mSingleGameDAO.getAll().size()+1);
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext());
                String maxPoint = sharedPreferences.getString("maxPoint","10");
                String minPoint = sharedPreferences.getString("minPoint","1");
                Toast.makeText(getContext(), "maxpoint is "+maxPoint+"and minpoint is "+ minPoint, Toast.LENGTH_LONG).show();


                dismiss();

            }


        });


        /**get GameListItem from argument & assign to variables */
        mGameListItem = (GameListItem) getArguments().getSerializable(KEY_GAMELISTITEM);
        player1 = mGameListItem.getPlayer1();
        player2 = mGameListItem.getPlayer2();
        player3 = mGameListItem.getPlayer3();
        player4 = mGameListItem.getPlayer4();
        players = new String[]{player1, player2, player3, player4};
        /** findview for numberpick name and setup*/
        numberPicker_name = (NumberPicker) view.findViewById(R.id.numberPicker_name);
        numberPicker_name.setMinValue(0);
        numberPicker_name.setMaxValue(players.length - 1);
        numberPicker_name.setDisplayedValues(players);
        numberPicker_name.setWrapSelectorWheel(false);

        numberPicker_name.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                /* error occur with this code
                List<String> Lplayers2 = new ArrayList<String>();

                String[] Aplayers2 = new String[4];
                Lplayers2.clear();
                    for (int i=0; i < 4; i++) {
                        if (!players[i].equals(players[newVal])) {
                            Lplayers2.add(players[i]);
                        } else {};
                }
                Lplayers2.add(" ");
                Log.i("show", Aplayers2[0]+ Aplayers2[1]+ Aplayers2[2]+Aplayers2[3]);
                Lplayers2.toArray(Aplayers2);
                numberPicker_name2.setDisplayedValues(Aplayers2);
                */


            }
        });

        /** findView for numberPicker 2nd name and setup */

        numberPicker_name2 = (NumberPicker) view.findViewById(R.id.numberPicker_name2);
        numberPicker_name2.setMinValue(0);
        numberPicker_name2.setMaxValue(players.length - 1);
        numberPicker_name2.setDisplayedValues(players);
        numberPicker_name2.setWrapSelectorWheel(false);


        /** findView for numberPicker Round Type and setup */
        numberPicker_scoreType = (NumberPicker) view.findViewById(R.id.numberPicker_roundType);
        numberPicker_scoreType.setMinValue(0);
        numberPicker_scoreType.setMaxValue(scoreType.length - 1); /**自膜，食糊 */
        numberPicker_scoreType.setDisplayedValues(scoreType);
        numberPicker_scoreType.setWrapSelectorWheel(false);
        numberPicker_scoreType.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Toast.makeText(getContext(), "from " + String.valueOf(oldVal) + " to " + String.valueOf(newVal), Toast.LENGTH_SHORT).show();
                if (newVal == 1) {
                    numberPicker_name2.setVisibility(View.GONE);
                    tv_2ndplayer.setVisibility(View.GONE);

                    /*Depreciate to use hide view instead
                    String[] NA = {" ", "", "", ""};
                    numberPicker_name2.setDisplayedValues(NA);
                    */


                } else {
                    numberPicker_name2.setVisibility(View.VISIBLE);
                    tv_2ndplayer.setVisibility(View.VISIBLE);

                    /*Depreciate to use hide view instead
                    players = new String[]{player1, player2, player3, player4};
                    numberPicker_name2.setDisplayedValues(players);
                    */
                }
            }
        });


        /** findView for numberPicker Fan and setup */
        numberPicker_score = (NumberPicker) view.findViewById(R.id.numberPicker_score);
        numberPicker_score.setMaxValue(scoreLevel.length - 1);
        numberPicker_score.setMinValue(0);
        numberPicker_score.setDisplayedValues(scoreLevel);
        numberPicker_score.setWrapSelectorWheel(false);

        numberPicker_score.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                                                         @Override

                                                         public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                                         }

                                                     }

        );
        return view;


    }


    public interface OnItemSubmitListener {
        // TODO: Update argument type and name
        void onDialogFragmentInteraction(SingleGameItem item);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        /** important to add to avoid interface listener's NPE */
        mListener = (OnItemSubmitListener) context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle("My Title");
        return dialog;
    }



}