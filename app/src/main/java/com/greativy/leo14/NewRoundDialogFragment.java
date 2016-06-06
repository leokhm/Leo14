package com.greativy.leo14;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.List;

public class NewRoundDialogFragment extends DialogFragment {
    OnItemSubmitListener mListener;
    private NumberPicker numberPicker_score;
    private NumberPicker numberPicker_name;
    private NumberPicker numberPicker_name2;
    private NumberPicker numberPicker_scoreType;
    private Button button_RoundSubmit;
    private Button button_Cancel;
    private String KEY_GAMELISTITEM = "GameListItem";
    private List<SingleGameItem> mSingleGameItems;
    private GameListItem mGameListItem;
    private SingleGameItem mSingleGameItem;
    private SingleGameDAO mSingleGameDAO;
    private Bundle bundle;
    private RoundListRecyclerViewAdapter mRoundListRecyclerViewAdapter;
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
    final String[] scoreType = new String[]{"食", "自摸", "包"};
    String[] scoreLevel = new String[]{"3", "4", "5", "6", "7", "8", "9", "10"};
    int[][] scoreLevel2 = new int[][]{{3, 4, 5, 6, 7, 8, 9, 10},
            {8, 16, 24, 36, 48, 64, 80, 128}, {4, 8, 12, 16, 24, 32, 48, 64}};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("onCreate", "logC");

        if (getArguments() != null) {
            Log.i("log", "logD");

            bundle = getArguments();
            mGameListItem = (GameListItem) bundle.getSerializable(KEY_GAMELISTITEM);
            gameId = mGameListItem.getId();
            Log.i("gameId", "game Id is "+String.valueOf(gameId));


        }
    }
    @Override
    public void onStart(){
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_round, container);
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
                mSingleGameItems = mSingleGameDAO.getAll();
                mSingleGameItem.setId(mSingleGameItems.size());
                mSingleGameItem.setGameType(0);
                mSingleGameItem.setGameId(gameId);

                NameValue = numberPicker_name.getValue();
                Name2Value = numberPicker_name2.getValue();
                ScoreTypeValue = numberPicker_scoreType.getValue();
                ScoreValue = numberPicker_score.getValue();
                Toast.makeText(getContext(), NameValue + " " + ScoreValue + ScoreTypeValue + Name2Value, Toast.LENGTH_LONG).show();
                mSingleGameItem.setScoreType(ScoreTypeValue);

                switch (ScoreTypeValue) {
                    default:
                        break;
                    case 0:
                        if (NameValue == 0 && Name2Value == 1) {
                            mSingleGameItem.setPlayer1RoundScore(scoreLevel2[1][ScoreValue]);
                            mSingleGameItem.setPlayer2RoundScore(-scoreLevel2[1][ScoreValue]);
                            mSingleGameItem.setPlayer3RoundScore(0);
                            mSingleGameItem.setPlayer4RoundScore(0);
                        } else if (NameValue == 0 && Name2Value == 2) {
                            mSingleGameItem.setPlayer1RoundScore(scoreLevel2[1][ScoreValue]);
                            mSingleGameItem.setPlayer2RoundScore(0);
                            mSingleGameItem.setPlayer3RoundScore(-scoreLevel2[1][ScoreValue]);
                            mSingleGameItem.setPlayer4RoundScore(0);
                        } else if (NameValue == 0 && Name2Value == 3) {
                            mSingleGameItem.setPlayer1RoundScore(scoreLevel2[1][ScoreValue]);
                            mSingleGameItem.setPlayer2RoundScore(0);
                            mSingleGameItem.setPlayer3RoundScore(0);
                            mSingleGameItem.setPlayer4RoundScore(-scoreLevel2[1][ScoreValue]);
                        } else
                        if (NameValue == 1 && Name2Value == 0) {
                            mSingleGameItem.setPlayer1RoundScore(-scoreLevel2[1][ScoreValue]);
                            mSingleGameItem.setPlayer2RoundScore(scoreLevel2[1][ScoreValue]);
                            mSingleGameItem.setPlayer3RoundScore(0);
                            mSingleGameItem.setPlayer4RoundScore(0);
                        } else if (NameValue == 1 && Name2Value == 2) {
                            mSingleGameItem.setPlayer1RoundScore(0);
                            mSingleGameItem.setPlayer2RoundScore(scoreLevel2[1][ScoreValue]);
                            mSingleGameItem.setPlayer3RoundScore(-scoreLevel2[1][ScoreValue]);
                            mSingleGameItem.setPlayer4RoundScore(0);
                        } else if (NameValue == 1 && Name2Value == 3) {
                            mSingleGameItem.setPlayer1RoundScore(0);
                            mSingleGameItem.setPlayer2RoundScore(scoreLevel2[1][ScoreValue]);
                            mSingleGameItem.setPlayer3RoundScore(0);
                            mSingleGameItem.setPlayer4RoundScore(-scoreLevel2[1][ScoreValue]);
                        } else
                        if (NameValue == 2 && Name2Value == 0) {
                            mSingleGameItem.setPlayer1RoundScore(-scoreLevel2[1][ScoreValue]);
                            mSingleGameItem.setPlayer2RoundScore(0);
                            mSingleGameItem.setPlayer3RoundScore(scoreLevel2[1][ScoreValue]);
                            mSingleGameItem.setPlayer4RoundScore(0);
                        } else if (NameValue == 2 && Name2Value == 1) {
                            mSingleGameItem.setPlayer1RoundScore(0);
                            mSingleGameItem.setPlayer2RoundScore(-scoreLevel2[1][ScoreValue]);
                            mSingleGameItem.setPlayer3RoundScore(scoreLevel2[1][ScoreValue]);
                            mSingleGameItem.setPlayer4RoundScore(0);
                        } else if (NameValue == 2 && Name2Value == 3) {
                            mSingleGameItem.setPlayer1RoundScore(0);
                            mSingleGameItem.setPlayer2RoundScore(0);
                            mSingleGameItem.setPlayer3RoundScore(scoreLevel2[1][ScoreValue]);
                            mSingleGameItem.setPlayer4RoundScore(-scoreLevel2[1][ScoreValue]);
                        } else
                        if (NameValue == 3 && Name2Value == 0) {
                            mSingleGameItem.setPlayer1RoundScore(-scoreLevel2[1][ScoreValue]);
                            mSingleGameItem.setPlayer2RoundScore(0);
                            mSingleGameItem.setPlayer3RoundScore(0);
                            mSingleGameItem.setPlayer4RoundScore(scoreLevel2[1][ScoreValue]);
                        } else if (NameValue == 3 && Name2Value == 1) {
                            mSingleGameItem.setPlayer1RoundScore(0);
                            mSingleGameItem.setPlayer2RoundScore(-scoreLevel2[1][ScoreValue]);
                            mSingleGameItem.setPlayer3RoundScore(0);
                            mSingleGameItem.setPlayer4RoundScore(scoreLevel2[1][ScoreValue]);
                        } else if (NameValue == 3 && Name2Value == 2) {
                            mSingleGameItem.setPlayer1RoundScore(0);
                            mSingleGameItem.setPlayer2RoundScore(0);
                            mSingleGameItem.setPlayer3RoundScore(-scoreLevel2[1][ScoreValue]);
                            mSingleGameItem.setPlayer4RoundScore(scoreLevel2[1][ScoreValue]);
                        }
                        mSingleGameDAO.insert(mSingleGameItem);
                        mListener.onDialogFragmentInteraction(mSingleGameItem);
                        //mRoundListRecyclerViewAdapter.notifyItemInserted(mSingleGameDAO.getAll().size()+1);
                        break;

                    case 1:
                        Log.i("DialogFragment","Case 1 Happen, scoreType is "+ScoreTypeValue);
                        switch (NameValue) {
                            default:
                                Log.i("NameValue Exception","default throwout");
                                break;
                            case 0:
                                mSingleGameItem.setPlayer1RoundScore(scoreLevel2[2][ScoreValue]*3);
                                mSingleGameItem.setPlayer2RoundScore(-scoreLevel2[2][ScoreValue]);
                                mSingleGameItem.setPlayer3RoundScore(-scoreLevel2[2][ScoreValue]);
                                mSingleGameItem.setPlayer4RoundScore(-scoreLevel2[2][ScoreValue]);
                                mSingleGameDAO.insert(mSingleGameItem);
                                break;
                            case 1:
                                mSingleGameItem.setPlayer1RoundScore(-scoreLevel2[2][ScoreValue]);
                                mSingleGameItem.setPlayer2RoundScore(scoreLevel2[2][ScoreValue]*3);
                                mSingleGameItem.setPlayer3RoundScore(-scoreLevel2[2][ScoreValue]);
                                mSingleGameItem.setPlayer4RoundScore(-scoreLevel2[2][ScoreValue]);
                                mSingleGameDAO.insert(mSingleGameItem);
                                break;
                            case 2:
                                mSingleGameItem.setPlayer1RoundScore(-scoreLevel2[2][ScoreValue]);
                                mSingleGameItem.setPlayer2RoundScore(-scoreLevel2[2][ScoreValue]);
                                mSingleGameItem.setPlayer3RoundScore(scoreLevel2[2][ScoreValue]*3);
                                mSingleGameItem.setPlayer4RoundScore(-scoreLevel2[2][ScoreValue]);
                                mSingleGameDAO.insert(mSingleGameItem);
                                break;
                            case 3:
                                mSingleGameItem.setPlayer1RoundScore(-scoreLevel2[2][ScoreValue]);
                                mSingleGameItem.setPlayer2RoundScore(-scoreLevel2[2][ScoreValue]);
                                mSingleGameItem.setPlayer3RoundScore(-scoreLevel2[2][ScoreValue]);
                                mSingleGameItem.setPlayer4RoundScore(scoreLevel2[2][ScoreValue]*3);
                                mSingleGameDAO.insert(mSingleGameItem);
                                break;


                        }
                        mListener.onDialogFragmentInteraction(mSingleGameItem);

                        break;
                    case 2:
                        Log.i("DialogFragment","Case 2 happens, & scoreType is "+ScoreTypeValue);
                        if (NameValue == 0 && Name2Value == 1) {
                            mSingleGameItem.setPlayer1RoundScore(-scoreLevel2[2][ScoreValue]*3);
                            mSingleGameItem.setPlayer2RoundScore(scoreLevel2[2][ScoreValue]*3);
                            mSingleGameItem.setPlayer3RoundScore(0);
                            mSingleGameItem.setPlayer4RoundScore(0);
                        } else if (NameValue == 0 && Name2Value == 2) {
                            mSingleGameItem.setPlayer1RoundScore(-scoreLevel2[2][ScoreValue]*3);
                            mSingleGameItem.setPlayer2RoundScore(0);
                            mSingleGameItem.setPlayer3RoundScore(scoreLevel2[2][ScoreValue]*3);
                            mSingleGameItem.setPlayer4RoundScore(0);
                        } else if (NameValue == 0 && Name2Value == 3) {
                            mSingleGameItem.setPlayer1RoundScore(-scoreLevel2[2][ScoreValue]*3);
                            mSingleGameItem.setPlayer2RoundScore(0);
                            mSingleGameItem.setPlayer3RoundScore(0);
                            mSingleGameItem.setPlayer4RoundScore(scoreLevel2[2][ScoreValue]*3);
                        } else
                        if (NameValue == 1 && Name2Value == 0) {
                            mSingleGameItem.setPlayer1RoundScore(scoreLevel2[2][ScoreValue]*3);
                            mSingleGameItem.setPlayer2RoundScore(-scoreLevel2[2][ScoreValue]*3);
                            mSingleGameItem.setPlayer3RoundScore(0);
                            mSingleGameItem.setPlayer4RoundScore(0);
                        } else if (NameValue == 1 && Name2Value == 2) {
                            mSingleGameItem.setPlayer1RoundScore(0);
                            mSingleGameItem.setPlayer2RoundScore(-scoreLevel2[2][ScoreValue]*3);
                            mSingleGameItem.setPlayer3RoundScore(scoreLevel2[2][ScoreValue]*3);
                            mSingleGameItem.setPlayer4RoundScore(0);
                        } else if (NameValue == 1 && Name2Value == 3) {
                            mSingleGameItem.setPlayer1RoundScore(0);
                            mSingleGameItem.setPlayer2RoundScore(-scoreLevel2[2][ScoreValue]*3);
                            mSingleGameItem.setPlayer3RoundScore(0);
                            mSingleGameItem.setPlayer4RoundScore(scoreLevel2[2][ScoreValue]*3);
                        } else
                        if (NameValue == 2 && Name2Value == 0) {
                            mSingleGameItem.setPlayer1RoundScore(scoreLevel2[2][ScoreValue]*3);
                            mSingleGameItem.setPlayer2RoundScore(0);
                            mSingleGameItem.setPlayer3RoundScore(-scoreLevel2[2][ScoreValue]*3);
                            mSingleGameItem.setPlayer4RoundScore(0);
                        } else if (NameValue == 2 && Name2Value == 1) {
                            mSingleGameItem.setPlayer1RoundScore(0);
                            mSingleGameItem.setPlayer2RoundScore(scoreLevel2[2][ScoreValue]*3);
                            mSingleGameItem.setPlayer3RoundScore(-scoreLevel2[2][ScoreValue]*3);
                            mSingleGameItem.setPlayer4RoundScore(0);
                        } else if (NameValue == 2 && Name2Value == 3) {
                            mSingleGameItem.setPlayer1RoundScore(0);
                            mSingleGameItem.setPlayer2RoundScore(0);
                            mSingleGameItem.setPlayer3RoundScore(-scoreLevel2[2][ScoreValue]*3);
                            mSingleGameItem.setPlayer4RoundScore(scoreLevel2[2][ScoreValue]*3);
                        } else
                        if (NameValue == 3 && Name2Value == 0) {
                            mSingleGameItem.setPlayer1RoundScore(scoreLevel2[2][ScoreValue]*3);
                            mSingleGameItem.setPlayer2RoundScore(0);
                            mSingleGameItem.setPlayer3RoundScore(0);
                            mSingleGameItem.setPlayer4RoundScore(-scoreLevel2[2][ScoreValue]*3);
                        } else if (NameValue == 3 && Name2Value == 1) {
                            mSingleGameItem.setPlayer1RoundScore(0);
                            mSingleGameItem.setPlayer2RoundScore(scoreLevel2[2][ScoreValue]*3);
                            mSingleGameItem.setPlayer3RoundScore(0);
                            mSingleGameItem.setPlayer4RoundScore(-scoreLevel2[2][ScoreValue]*3);
                        } else if (NameValue == 3 && Name2Value == 2) {
                            mSingleGameItem.setPlayer1RoundScore(0);
                            mSingleGameItem.setPlayer2RoundScore(0);
                            mSingleGameItem.setPlayer3RoundScore(scoreLevel2[2][ScoreValue]*3);
                            mSingleGameItem.setPlayer4RoundScore(-scoreLevel2[2][ScoreValue]*3);
                        }
                        mSingleGameDAO.insert(mSingleGameItem);
                        mListener.onDialogFragmentInteraction(mSingleGameItem);
                        //mRoundListRecyclerViewAdapter.notifyItemInserted(mSingleGameDAO.getAll().size()+1);
                        break;



                }

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
        numberPicker_scoreType.setMaxValue(scoreType.length-1); /**自膜，食糊 */
        numberPicker_scoreType.setDisplayedValues(scoreType);
        numberPicker_scoreType.setWrapSelectorWheel(false);
        numberPicker_scoreType.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Toast.makeText(getContext(), "from " + String.valueOf(oldVal) + " to " + String.valueOf(newVal), Toast.LENGTH_SHORT).show();
                if (newVal == 1) {
                    numberPicker_name2.setVisibility(View.INVISIBLE);
                    /*Depreciate to use hide view instead
                    String[] NA = {" ", "", "", ""};
                    numberPicker_name2.setDisplayedValues(NA);
                    */


                } else {
                    numberPicker_name2.setVisibility(View.VISIBLE);
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

}