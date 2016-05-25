package com.greativy.leo14;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.Toast;

public class NewRoundDialogFragment extends DialogFragment {
    private NumberPicker numberPicker_score;
    private NumberPicker numberPicker_name;
    private NumberPicker numberPicker_name2;
    private NumberPicker numberPicker_roundtype;
    private String KEY_GAMELISTITEM = "GameListItem";
    private GameListItem mGameListItem;
    private String player1;
    private String player2;
    private String player3;
    private String player4;
    private String[] players;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_round, container);
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
        numberPicker_name.setWrapSelectorWheel(true);

        /** findView for numberPicker 2nd name and setup */

        numberPicker_name2 = (NumberPicker) view.findViewById(R.id.numberPicker_name2);
        numberPicker_name2.setMinValue(0);
        numberPicker_name2.setMaxValue(players.length - 1);
        numberPicker_name2.setDisplayedValues(players);

        /** findView for numberPicker Round Type and setup */
        final String[] scoretype = new String[]{"自摸", "食", "出", "包"};
        numberPicker_roundtype = (NumberPicker) view.findViewById(R.id.numberPicker_roundType);
        numberPicker_roundtype.setMinValue(0);
        numberPicker_roundtype.setMaxValue(3); /**自膜，食糊 */
        numberPicker_roundtype.setDisplayedValues(scoretype);
        numberPicker_roundtype.setWrapSelectorWheel(true);
        numberPicker_roundtype.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Toast.makeText(getContext(), "from " + String.valueOf(oldVal) + " to " + String.valueOf(newVal), Toast.LENGTH_SHORT).show();
                if (numberPicker_name2.getValue() == 0) {
                    String[] NA = {" ", "", "", ""};
                    numberPicker_name2.setMinValue(0);
                    numberPicker_name2.setMaxValue(NA.length - 1);
                    numberPicker_name2.setDisplayedValues(NA);
                    numberPicker_name2.setWrapSelectorWheel(true);

                }
                if (numberPicker_name2.getValue() != 0) {
                    players = new String[]{player1, player2, player3, player4};

                    numberPicker_name2.setMinValue(0);
                    numberPicker_name2.setMaxValue(players.length - 1);
                    numberPicker_name2.setDisplayedValues(players);
                }
                /*if (newVal == 0) {
                    String [] NA = {" "};
                    numberPicker_name2.setMinValue(0);
                    numberPicker_name2.setMaxValue(players.length-1);
                    numberPicker_name2.setDisplayedValues(NA);

                }
                if (newVal != 0){
                    //String[] players2 = new String[]{player1, player2, player3, player4};

                    int log = players.length -1;
                    Log.i("players2 length", String.valueOf(log));

                    numberPicker_name2.setDisplayedValues(players);



                } */
            }
        });


        /** findView for numberPicker Fan and setup */
        String[] scoreLevel = new String[]{"3", "4", "5", "6", "7", "8", "9", "10"};
        numberPicker_score = (NumberPicker) view.findViewById(R.id.numberPicker_score);
        numberPicker_score.setMaxValue(scoreLevel.length - 1);
        numberPicker_score.setMinValue(0);
        numberPicker_score.setDisplayedValues(scoreLevel);
        numberPicker_score.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()

                                                     {
                                                         @Override
                                                         public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                                         }

                                                     }

        );
        return view;


    }

}