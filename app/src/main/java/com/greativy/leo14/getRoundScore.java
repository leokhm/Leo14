package com.greativy.leo14;

import android.util.Log;

import java.util.List;

/**
 * Created by leokh on 6/8/2016.
 */

public class getRoundScore {
    private SingleGameItem mSingleGameItem;
    private List<SingleGameItem> mSingleGameItems;


    private int[][] scoreLevel = new int[][]{{3, 4, 5, 6, 7, 8, 9, 10},
            {8, 16, 24, 36, 48, 64, 80, 128}, {4, 8, 12, 16, 24, 32, 48, 64}};


    public SingleGameItem getResult(long gameId, int ScoreTypeValue, int NameValue, int Name2Value, int ScoreValue, SingleGameDAO mSingleGameDAO) {
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