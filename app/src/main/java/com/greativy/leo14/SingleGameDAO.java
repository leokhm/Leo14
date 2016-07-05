package com.greativy.leo14;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leokh on 5/2/2016.
 */

public class SingleGameDAO {

    public static final String TABLE_SGAME = "singlegameitem";
    private static final String KEY_ID = "_sgid";
    private static final String GAME_ID = "gameid";
    private static final String GAMETYPE = "gametype";
    private static final String ROUNDSCORETYPE = "roundscoretype";
    private static final String PLAYER1ROUNDSCORE = "p1rscore";
    private static final String PLAYER2ROUNDSCORE = "p2rscore";
    private static final String PLAYER3ROUNDSCORE = "p3rscore";
    private static final String PLAYER4ROUNDSCORE = "p4rscore";

    public static final String CREATE_TABLE2 =
            "CREATE TABLE " + TABLE_SGAME + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    GAME_ID + " INTEGER NOT NULL, " + GAMETYPE + " INTEGER NOT NULL, " +
                    ROUNDSCORETYPE + " INTEGER NOT NULL, " + PLAYER1ROUNDSCORE + " INTEGER NOT NULL, " +
                    PLAYER2ROUNDSCORE + " INTEGER NOT NULL, " + PLAYER3ROUNDSCORE + " INTEGER," +
                    PLAYER4ROUNDSCORE + " INTEGER)";

    private SQLiteDatabase db;

    public SingleGameDAO(Context context) {
        db = DBHelper.getDatabase(context);

    }

    public void close() {
        db.close();
    }

    public SingleGameItem insert(SingleGameItem item) {
        ContentValues cv = new ContentValues();
        cv.put(GAME_ID, item.getGameId());
        cv.put(GAMETYPE, item.getGameType());
        cv.put(ROUNDSCORETYPE, item.getScoreType());
        cv.put(PLAYER1ROUNDSCORE, item.getPlayer1RoundScore());
        cv.put(PLAYER2ROUNDSCORE, item.getPlayer2RoundScore());
        cv.put(PLAYER3ROUNDSCORE, item.getPlayer3RoundScore());
        cv.put(PLAYER4ROUNDSCORE, item.getPlayer4RoundScore());

        long id = db.insert(TABLE_SGAME, null, cv);
        item.setId(id);

        return item;
    }

    public boolean update(SingleGameItem item) {
        ContentValues cv = new ContentValues();
        cv.put(GAME_ID, item.getGameId());
        cv.put(GAMETYPE, item.getGameType());
        cv.put(ROUNDSCORETYPE, item.getScoreType());
        cv.put(PLAYER1ROUNDSCORE, item.getPlayer1RoundScore());
        cv.put(PLAYER2ROUNDSCORE, item.getPlayer2RoundScore());
        cv.put(PLAYER3ROUNDSCORE, item.getPlayer3RoundScore());
        cv.put(PLAYER4ROUNDSCORE, item.getPlayer4RoundScore());
        String location = KEY_ID + "=" + item.getId();

        return db.update(TABLE_SGAME, cv, location, null) > 0;
    }

    public boolean delete(long id) {
        String location = KEY_ID + "=" + id;
        return db.delete(TABLE_SGAME, location, null) > 0;
    }

    //define getRecord method that pulls the record from cursor and return the result
    public SingleGameItem getRecord(Cursor cursor) {
        SingleGameItem result = new SingleGameItem();
        result.setId(cursor.getLong(0));
        result.setGameId(cursor.getLong(1));
        result.setGameType(cursor.getInt(2));
        result.setScoreType(cursor.getInt(3));
        result.setPlayer1RoundScore(cursor.getInt(4));
        result.setPlayer2RoundScore(cursor.getInt(5));
        result.setPlayer3RoundScore(cursor.getInt(6));
        result.setPlayer4RoundScore(cursor.getInt(7));

        return result;

    }

    //db.rawQuery(SELECT * FROM todos, null);
    public List<SingleGameItem> getAll() {
        List<SingleGameItem> result = new ArrayList<>();
        Cursor cursor = db.query(TABLE_SGAME, null, null, null, null, null, null, null);
        cursor.moveToFirst();

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }
        cursor.close();
        return result;

    }
    //db.rawQuery(SELECT * FROM todos td, tags tg, todo_tags tt
    // WHERE tg.tag_name = ‘Watchlist’ AND tg.id = tt.tag_id AND td.id = tt.todo_id", null);

    public List<SingleGameItem> getAllByGameId(long gameId) {
        List<SingleGameItem> result = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SGAME + " WHERE " + GAME_ID + " = '" + gameId + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            cursor.moveToLast();

            //SingleGameItem mSingleGameItem = new SingleGameItem();
            do {
                result.add(getRecord(cursor));
            } while (cursor.moveToPrevious());
            cursor.close();
        }
        return result;
    }

    /**
     * TODO get all column score sum by player
     */
    public Integer getAllColumnScoreSum(String selectedColumn) {
        String selectQuery = "SELECT SUM(" + selectedColumn + ") As myTotal FROM " + TABLE_SGAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Integer total = cursor.getInt(cursor.getColumnIndex("myTotal"));
        Log.i("SingleGameDAO", "getAllColumnScoreSum total is " + total);
        cursor.close();
        return total;
    }

    public Integer getColScoreSumByGameId(String selectedColumn, long gameId) {
        String selectQuery = "SELECT SUM(" + selectedColumn + ") As myTotal FROM " + TABLE_SGAME + " where " + GAME_ID + " = '" + gameId + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        Integer total;
        if (cursor != null && cursor.moveToFirst()) {

            total = cursor.getInt(cursor.getColumnIndex("myTotal"));
            Log.i("SingleGameDAO", "getAllColumnScoreSum total is " + total);
            cursor.moveToFirst();
            cursor.close();
        } else {
            total = 0;
        }
        return total;
    }


    public SingleGameItem get(long id) {
        SingleGameItem item = null;
        String location = KEY_ID + "=" + id;
        Cursor cursor = db.query(TABLE_SGAME, null, location, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            item = getRecord(cursor);
        }
        cursor.close();
        return item;
    }

    public int getCount() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_SGAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }
        cursor.close();


        return result;
    }

    public void sample() {
        /**result.setId(cursor.getLong(0));
         result.setGameId(cursor.getLong(1));
         result.setGameType(cursor.getString(2));
         result.setScoreType(cursor.getInt(3));
         result.setPlayer1RoundScore(cursor.getInt(4));
         result.setPlayer2RoundScore(cursor.getInt(5));
         result.setPlayer3RoundScore(cursor.getInt(6));
         result.setPlayer4RoundScore(cursor.getInt(7));*/
        //    public SingleGameItem(long id, long gameId, integer gameType, Integer roundScoreType, Integer player1RoundScore, Integer player2RoundScore, Integer player3RoundScore, Integer player4RoundScore) {

        SingleGameItem item1 = new SingleGameItem(0, 1, 0, 0, 8, 0, 0, -8);
        SingleGameItem item2 = new SingleGameItem(1, 2, 0, 0, 16, 0, 0, -16);
        SingleGameItem item3 = new SingleGameItem(2, 3, 0, 0, 24, 0, 0, -24);
        SingleGameItem item4 = new SingleGameItem(3, 4, 0, 0, 36, 0, 0, -36);
        SingleGameItem item5 = new SingleGameItem(4, 1, 0, 0, 40, 0, 0, -40);
        SingleGameItem item6 = new SingleGameItem(5, 1, 0, 0, 48, 0, 0, -48);
        SingleGameItem item7 = new SingleGameItem(6, 5, 0, 0, 60, 0, 0, -60);
        SingleGameItem item8 = new SingleGameItem(7, 6, 0, 0, 64, 0, 0, -64);
        SingleGameItem item9 = new SingleGameItem(8, 7, 0, 0, 72, 0, 0, -72);
        SingleGameItem item10 = new SingleGameItem(9, 8, 0, 0, 96, 0, 0, -96);


        insert(item1);
        insert(item2);
        insert(item3);
        insert(item4);
        insert(item5);
        insert(item6);
        insert(item7);
        insert(item8);
        insert(item9);

        insert(item10);


    }

}
