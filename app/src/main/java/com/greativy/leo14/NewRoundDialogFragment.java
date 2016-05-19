package com.greativy.leo14;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

public class NewRoundDialogFragment extends DialogFragment
{
    NumberPicker numberPicker_fan = null;
    NumberPicker numberPicker_name = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_round, container);
        String player1 = getArguments().getString("player1");
        String player2 = getArguments().getString("player2");
        String player3 = getArguments().getString("player3");
        String player4 = getArguments().getString("player4");
        String[] players = {player1,player2,player3,player4};

        numberPicker_name = (NumberPicker) view.findViewById(R.id.numberPicker_name);
        numberPicker_fan.setMaxValue(3);
        numberPicker_fan.setMinValue(0);
        numberPicker_fan.setDisplayedValues(players);

        numberPicker_fan = (NumberPicker) view.findViewById(R.id.numberPicker_score);
        numberPicker_fan.setMaxValue(2);
        numberPicker_fan.setMinValue(0);
        numberPicker_fan.setDisplayedValues(new String[] {"2","4","10"});
        return view;


    }

}