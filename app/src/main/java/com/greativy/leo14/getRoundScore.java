package com.greativy.leo14;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;

import java.util.List;

import static java.lang.Integer.parseInt;


/**
 * Created by leokh on 6/8/2016.
 */

public class GetRoundScore extends Fragment {
    private SingleGameItem mSingleGameItem;
    private List<SingleGameItem> mSingleGameItems;


    private int[][] scoreLevel = new int[][]{{3, 4, 5, 6, 7, 8, 9, 10},
            {8, 16, 24, 36, 48, 64, 80, 128}, {4, 8, 12, 16, 24, 32, 48, 64}};


    public SingleGameItem getResult(long gameId, int ScoreTypeValue, int NameValue, int Name2Value, int ScoreValue, SingleGameDAO mSingleGameDAO) {


        /** TODO fix sharedpreference
        SharedPreferences sharedPreferences = PreferenceManager.getSharedPreferences(getActivity().getBaseContext());

        int maxPoint = parseInt(sharedPreferences.getString("maxPoint","10"));
        int minPoint = parseInt(sharedPreferences.getString("minPoint","3"));
        int point1 = parseInt(sharedPreferences.getString("point1","1"));
        int point2 = parseInt(sharedPreferences.getString("point2","4"));
        int point3 = parseInt(sharedPreferences.getString("point3","8"));
        int point4 = parseInt(sharedPreferences.getString("point4","16"));
        int point5 = parseInt(sharedPreferences.getString("point5","24"));
        int point6 = parseInt(sharedPreferences.getString("point6","36"));
        int point7 = parseInt(sharedPreferences.getString("point7","48"));
        int point8 = parseInt(sharedPreferences.getString("point8","64"));
        int point9 = parseInt(sharedPreferences.getString("point9","80"));
        int point10 = parseInt(sharedPreferences.getString("point10","128"));
        int point11 = parseInt(sharedPreferences.getString("point11","144"));
        int point12 = parseInt(sharedPreferences.getString("point12","192"));
        int point13 = parseInt(sharedPreferences.getString("point13","256"));
        Log.i("max point",String.valueOf(maxPoint));
        Log.i("min point",String.valueOf(minPoint));
        Log.i("point1", String.valueOf(point1));
        */





        mSingleGameItem = new SingleGameItem();
        mSingleGameItems = mSingleGameDAO.getAll();
        mSingleGameItem.setId(mSingleGameItems.size());
        mSingleGameItem.setGameType(0);
        mSingleGameItem.setGameId(gameId);
        mSingleGameItem.setScoreType(ScoreTypeValue);


        switch (ScoreTypeValue) {
            default:
                break;
            case 0:
                if (NameValue == 0 && Name2Value == 1) {
                    mSingleGameItem.setPlayer1RoundScore(scoreLevel[1][ScoreValue]);
                    mSingleGameItem.setPlayer2RoundScore(-scoreLevel[1][ScoreValue]);
                    mSingleGameItem.setPlayer3RoundScore(0);
                    mSingleGameItem.setPlayer4RoundScore(0);
                } else if (NameValue == 0 && Name2Value == 2) {
                    mSingleGameItem.setPlayer1RoundScore(scoreLevel[1][ScoreValue]);
                    mSingleGameItem.setPlayer2RoundScore(0);
                    mSingleGameItem.setPlayer3RoundScore(-scoreLevel[1][ScoreValue]);
                    mSingleGameItem.setPlayer4RoundScore(0);
                } else if (NameValue == 0 && Name2Value == 3) {
                    mSingleGameItem.setPlayer1RoundScore(scoreLevel[1][ScoreValue]);
                    mSingleGameItem.setPlayer2RoundScore(0);
                    mSingleGameItem.setPlayer3RoundScore(0);
                    mSingleGameItem.setPlayer4RoundScore(-scoreLevel[1][ScoreValue]);
                } else if (NameValue == 1 && Name2Value == 0) {
                    mSingleGameItem.setPlayer1RoundScore(-scoreLevel[1][ScoreValue]);
                    mSingleGameItem.setPlayer2RoundScore(scoreLevel[1][ScoreValue]);
                    mSingleGameItem.setPlayer3RoundScore(0);
                    mSingleGameItem.setPlayer4RoundScore(0);
                } else if (NameValue == 1 && Name2Value == 2) {
                    mSingleGameItem.setPlayer1RoundScore(0);
                    mSingleGameItem.setPlayer2RoundScore(scoreLevel[1][ScoreValue]);
                    mSingleGameItem.setPlayer3RoundScore(-scoreLevel[1][ScoreValue]);
                    mSingleGameItem.setPlayer4RoundScore(0);
                } else if (NameValue == 1 && Name2Value == 3) {
                    mSingleGameItem.setPlayer1RoundScore(0);
                    mSingleGameItem.setPlayer2RoundScore(scoreLevel[1][ScoreValue]);
                    mSingleGameItem.setPlayer3RoundScore(0);
                    mSingleGameItem.setPlayer4RoundScore(-scoreLevel[1][ScoreValue]);
                } else if (NameValue == 2 && Name2Value == 0) {
                    mSingleGameItem.setPlayer1RoundScore(-scoreLevel[1][ScoreValue]);
                    mSingleGameItem.setPlayer2RoundScore(0);
                    mSingleGameItem.setPlayer3RoundScore(scoreLevel[1][ScoreValue]);
                    mSingleGameItem.setPlayer4RoundScore(0);
                } else if (NameValue == 2 && Name2Value == 1) {
                    mSingleGameItem.setPlayer1RoundScore(0);
                    mSingleGameItem.setPlayer2RoundScore(-scoreLevel[1][ScoreValue]);
                    mSingleGameItem.setPlayer3RoundScore(scoreLevel[1][ScoreValue]);
                    mSingleGameItem.setPlayer4RoundScore(0);
                } else if (NameValue == 2 && Name2Value == 3) {
                    mSingleGameItem.setPlayer1RoundScore(0);
                    mSingleGameItem.setPlayer2RoundScore(0);
                    mSingleGameItem.setPlayer3RoundScore(scoreLevel[1][ScoreValue]);
                    mSingleGameItem.setPlayer4RoundScore(-scoreLevel[1][ScoreValue]);
                } else if (NameValue == 3 && Name2Value == 0) {
                    mSingleGameItem.setPlayer1RoundScore(-scoreLevel[1][ScoreValue]);
                    mSingleGameItem.setPlayer2RoundScore(0);
                    mSingleGameItem.setPlayer3RoundScore(0);
                    mSingleGameItem.setPlayer4RoundScore(scoreLevel[1][ScoreValue]);
                } else if (NameValue == 3 && Name2Value == 1) {
                    mSingleGameItem.setPlayer1RoundScore(0);
                    mSingleGameItem.setPlayer2RoundScore(-scoreLevel[1][ScoreValue]);
                    mSingleGameItem.setPlayer3RoundScore(0);
                    mSingleGameItem.setPlayer4RoundScore(scoreLevel[1][ScoreValue]);
                } else if (NameValue == 3 && Name2Value == 2) {
                    mSingleGameItem.setPlayer1RoundScore(0);
                    mSingleGameItem.setPlayer2RoundScore(0);
                    mSingleGameItem.setPlayer3RoundScore(-scoreLevel[1][ScoreValue]);
                    mSingleGameItem.setPlayer4RoundScore(scoreLevel[1][ScoreValue]);
                }
                //mSingleGameDAO.insert(mSingleGameItem);
                return mSingleGameItem;
            //mRoundListRecyclerViewAdapter.notifyItemInserted(mSingleGameDAO.getAll().size()+1);
            //break;

            case 1:
                Log.i("DialogFragment", "Case 1 Happen, scoreType is " + ScoreTypeValue);
                switch (NameValue) {
                    default:
                        Log.i("NameValue Exception", "default throwout");
                        break;
                    case 0:
                        mSingleGameItem.setPlayer1RoundScore(scoreLevel[2][ScoreValue] * 3);
                        mSingleGameItem.setPlayer2RoundScore(-scoreLevel[2][ScoreValue]);
                        mSingleGameItem.setPlayer3RoundScore(-scoreLevel[2][ScoreValue]);
                        mSingleGameItem.setPlayer4RoundScore(-scoreLevel[2][ScoreValue]);
                        //mSingleGameDAO.insert(mSingleGameItem);
                        break;
                    case 1:
                        mSingleGameItem.setPlayer1RoundScore(-scoreLevel[2][ScoreValue]);
                        mSingleGameItem.setPlayer2RoundScore(scoreLevel[2][ScoreValue] * 3);
                        mSingleGameItem.setPlayer3RoundScore(-scoreLevel[2][ScoreValue]);
                        mSingleGameItem.setPlayer4RoundScore(-scoreLevel[2][ScoreValue]);
                        //mSingleGameDAO.insert(mSingleGameItem);
                        break;
                    case 2:
                        mSingleGameItem.setPlayer1RoundScore(-scoreLevel[2][ScoreValue]);
                        mSingleGameItem.setPlayer2RoundScore(-scoreLevel[2][ScoreValue]);
                        mSingleGameItem.setPlayer3RoundScore(scoreLevel[2][ScoreValue] * 3);
                        mSingleGameItem.setPlayer4RoundScore(-scoreLevel[2][ScoreValue]);
                        //mSingleGameDAO.insert(mSingleGameItem);
                        break;
                    case 3:
                        mSingleGameItem.setPlayer1RoundScore(-scoreLevel[2][ScoreValue]);
                        mSingleGameItem.setPlayer2RoundScore(-scoreLevel[2][ScoreValue]);
                        mSingleGameItem.setPlayer3RoundScore(-scoreLevel[2][ScoreValue]);
                        mSingleGameItem.setPlayer4RoundScore(scoreLevel[2][ScoreValue] * 3);
                        //mSingleGameDAO.insert(mSingleGameItem);
                        return mSingleGameItem;

                }
        }
        return mSingleGameItem;


    }
}