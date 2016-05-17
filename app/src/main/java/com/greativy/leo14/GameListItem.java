package com.greativy.leo14;

/**
 * Created by leokh on 4/4/2016.
 */
public class GameListItem implements java.io.Serializable {

    private long Id;
    private String gameTitle;
    private long createTime;
    private long modTime;
    private String player1;
    private String player2;
    private String player3;
    private String player4;
    private Integer player1FinalScore;
    private Integer player2FinalScore;
    private Integer player3FinalScore;
    private Integer player4FinalScore;


    public GameListItem() {
        gameTitle = "";
    }

    public GameListItem(long Id, String gameTitle, long createTime, long modTime, String player1, String player2, String player3, String player4, Integer player1FinalScore, Integer player2FinalScore, Integer player3FinalScore, Integer player4FinalScore) {
        this.Id = Id;
        this.gameTitle = gameTitle;
        this.createTime = createTime;
        this.modTime = modTime;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.player1FinalScore = player1FinalScore;
        this.player2FinalScore = player2FinalScore;
        this.player3FinalScore = player3FinalScore;
        this.player4FinalScore = player4FinalScore;

    }


    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getModTime() {
        return modTime;
    }

    public void setModTime(long modTime) {
        this.modTime = modTime;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getPlayer3() {
        return player3;
    }

    public void setPlayer3(String player3) {
        this.player3 = player3;
    }

    public String getPlayer4() {
        return player4;
    }

    public void setPlayer4(String player4) {
        this.player4 = player4;
    }


    public Integer getPlayer1FinalScore() {
        return player1FinalScore;
    }

    public void setPlayer1FinalScore(Integer player1FinalScore) {
        this.player1FinalScore = player1FinalScore;
    }

    public Integer getPlayer2FinalScore() {
        return player2FinalScore;
    }

    public void setPlayer2FinalScore(Integer player2FinalScore) {
        this.player2FinalScore = player2FinalScore;
    }

    public Integer getPlayer3FinalScore() {
        return player3FinalScore;
    }

    public void setPlayer3FinalScore(Integer player3FinalScore) {
        this.player3FinalScore = player3FinalScore;
    }

    public Integer getPlayer4FinalScore() {
        return player4FinalScore;
    }

    public void setPlayer4FinalScore(Integer player4FinalScore) {
        this.player4FinalScore = player4FinalScore;
    }


}