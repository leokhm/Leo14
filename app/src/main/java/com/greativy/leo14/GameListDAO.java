package com.greativy.leo14;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by leokh on 4/30/2016.
 */
public class GameListDAO {
    public static final String TABLE_NAME = "gamelistitem";
    public static final String KEY_ID = "_id";
    public static final String GAME_TITLE = "gametitle";
    public static final String CREATE_TIME = "createtime";
    public static final String LAST_MODIFY = "lastmodify";
    public static final String PLAYER1NAME = "player1name";
    public static final String PLAYER2NAME = "player2name";
    public static final String PLAYER3NAME = "player3name";
    public static final String PLAYER4NAME = "player4name";
    public static final String PLAYER1SCORE = "player1score";
    public static final String PLAYER2SCORE = "player2score";
    public static final String PLAYER3SCORE = "player3score";
    public static final String PLAYER4SCORE = "player4score";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    GAME_TITLE + " TEXT NOT NULL, " +
                    CREATE_TIME + " INTEGER NOT NULL, " +
                    LAST_MODIFY + " INTEGER, " +
                    PLAYER1NAME + " TEXT NOT NULL, " +
                    PLAYER2NAME + " TEXT NOT NULL, " +
                    PLAYER3NAME + " TEXT, " +
                    PLAYER4NAME + " TEXT, " +
                    PLAYER1SCORE + " INTEGER NOT NULL, "+
                    PLAYER2SCORE + " INTEGER NOT NULL, "+
                    PLAYER3SCORE + " INTEGER, "+
                    PLAYER4SCORE + " INTEGER)";
    /**
     * "CREATE TABLE gamelistitem (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
     * "gametitle TEXT NOT NULL, createtime INTEGER NOT NULL, " +
     * "lastmodify INTEGER, player1 TEXT NOT NULL, " +
     * "player2 TEXT NOT NULL, player3 TEXT, player4 TEXT, player1score INTEGER NOT NULL, player2score INTEGER NOT NULL, player3score INTEGER, player4score INTEGER)";
     **/

    private SQLiteDatabase db;

    public GameListDAO(Context context) {
        db = DBHelper.getDatabase(context);
    }

    public void close() {
        db.close();
    }

    public GameListItem insert(GameListItem item) {
        ContentValues cv = new ContentValues();
        cv.put(GAME_TITLE, item.getGameTitle());
        cv.put(CREATE_TIME, item.getCreateTime());
        cv.put(LAST_MODIFY, item.getModTime());
        cv.put(PLAYER1NAME, item.getPlayer1());
        cv.put(PLAYER2NAME, item.getPlayer2());
        cv.put(PLAYER3NAME, item.getPlayer3());
        cv.put(PLAYER4NAME, item.getPlayer4());
        cv.put(PLAYER1SCORE, item.getPlayer1FinalScore());
        cv.put(PLAYER2SCORE, item.getPlayer2FinalScore());
        cv.put(PLAYER3SCORE, item.getPlayer3FinalScore());
        cv.put(PLAYER4SCORE, item.getPlayer4FinalScore());

        long id = db.insert(TABLE_NAME, null, cv);
        item.setId(id);

        return item;
    }

    public boolean update(GameListItem item) {
        ContentValues cv = new ContentValues();
        cv.put(GAME_TITLE, item.getGameTitle());
        cv.put(CREATE_TIME, item.getCreateTime());
        cv.put(LAST_MODIFY, item.getModTime());
        cv.put(PLAYER1NAME, item.getPlayer1());
        cv.put(PLAYER2NAME, item.getPlayer2());
        cv.put(PLAYER3NAME, item.getPlayer3());
        cv.put(PLAYER4NAME, item.getPlayer4());
        cv.put(PLAYER1SCORE, item.getPlayer1FinalScore());
        cv.put(PLAYER2SCORE, item.getPlayer2FinalScore());
        cv.put(PLAYER3SCORE, item.getPlayer3FinalScore());
        cv.put(PLAYER4SCORE, item.getPlayer4FinalScore());
        String location = KEY_ID + "=" + item.getId();

        return db.update(TABLE_NAME, cv, location, null) > 0;
    }

    public boolean delete(long id) {
        String location = KEY_ID + "=" + id;
        return db.delete(TABLE_NAME, location, null) > 0;
    }

    //define getRecord method that pulls the record from cursor and return the result
    public GameListItem getRecord(Cursor cursor) {
        GameListItem result = new GameListItem();
        result.setId(cursor.getLong(0));
        result.setGameTitle(cursor.getString(1));
        result.setCreateTime(cursor.getLong(2));
        result.setModTime(cursor.getLong(3));
        result.setPlayer1(cursor.getString(4));
        result.setPlayer2(cursor.getString(5));
        result.setPlayer3(cursor.getString(6));
        result.setPlayer4(cursor.getString(7));
        result.setPlayer1FinalScore(cursor.getInt(8));
        result.setPlayer2FinalScore(cursor.getInt(9));
        result.setPlayer3FinalScore(cursor.getInt(10));
        result.setPlayer4FinalScore(cursor.getInt(11));


        return result;

    }

    public List<GameListItem> getAll() {
        List<GameListItem> result = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);
        cursor.moveToLast();
        while (cursor.moveToPrevious()) {
            result.add(getRecord(cursor));
        }
        cursor.close();
        return result;

    }

    public GameListItem get(long id) {
        GameListItem item = null;
        String location = KEY_ID + "=" + id;
        Cursor cursor = db.query(TABLE_NAME, null, location, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            item = getRecord(cursor);
        }
        cursor.close();
        return item;
    }

    public int getCount() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }

    public void sample() {

        GameListItem item1 = new GameListItem(0, "Game Title 1", new Date().getTime(), new Date().getTime(), "G1Player1ddddddddddd", "Player2", "Player3", "player4", 0, 0, 0, 0);
        GameListItem item2 = new GameListItem(0, "Game Title 2", new Date().getTime(), new Date().getTime(), "G2Player1", "Player2ddddddddddddd", "Player3", "player4", 0, 0, 0, 0);
        GameListItem item3 = new GameListItem(0, "Game Title 3", new Date().getTime(), new Date().getTime(), "G3Player1", "Player2", "Player3", "player4", 0, 0, 0, 0);
        GameListItem item4 = new GameListItem(0, "Game Title 4", new Date().getTime(), new Date().getTime(), "G3Player1", "Player2", "Player3", "player4", 0, 0, 0, 0);
        GameListItem item5 = new GameListItem(0, "Game Title 5", new Date().getTime(), new Date().getTime(), "G3Player1", "Player2", "Player3", "player4", 0, 0, 0, 0);
        GameListItem item6 = new GameListItem(0, "Game Title 6", new Date().getTime(), new Date().getTime(), "G3Player1", "Player2", "Player3", "player4", 0, 0, 0, 0);
        GameListItem item7 = new GameListItem(0, "Game Title 7", new Date().getTime(), new Date().getTime(), "G3Player1", "Player2", "Player3", "player4", 0, 0, 0, 0);
        GameListItem item8 = new GameListItem(0, "Game Title 8", new Date().getTime(), new Date().getTime(), "G3Player1", "Player2", "Player3", "player4", 0, 0, 0, 0);
        GameListItem item9 = new GameListItem(0, "Game Title 9", new Date().getTime(), new Date().getTime(), "G3Player1", "Player2", "Player3", "player4", 0, 0, 0, 0);
        GameListItem item10 = new GameListItem(0, "Game Title 10", new Date().getTime(), new Date().getTime(), "G3Player1", "Player2", "Player3", "player4", 0, 0, 0, 0);
        GameListItem item11 = new GameListItem(0, "Game Title 11", new Date().getTime(), new Date().getTime(), "G3Player1", "Player2", "Player3", "player4", 0, 0, 0, 0);
        GameListItem item12 = new GameListItem(0, "Game Title 12", new Date().getTime(), new Date().getTime(), "G3Player1", "Player2", "Player3", "player4", 0, 0, 0, 0);

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
        insert(item11);
        insert(item12);



    }


}
