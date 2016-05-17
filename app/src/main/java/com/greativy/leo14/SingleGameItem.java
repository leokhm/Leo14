package com.greativy.leo14;

/**
 * Created by leokh on 5/2/2016.
 */
public class SingleGameItem implements java.io.Serializable {

    private long Id;
    private long gameId;


    private String gameType;
    private Integer roundScoreType;
    private Integer player1RoundScore;
    private Integer player2RoundScore;
    private Integer player3RoundScore;
    private Integer player4RoundScore;


    public SingleGameItem() {
    }

    public SingleGameItem(long id, long gameId, String gameType, Integer roundScoreType, Integer player1RoundScore, Integer player2RoundScore, Integer player3RoundScore, Integer player4RoundScore) {
        Id = id;
        this.gameId = gameId;
        this.gameType = gameType;
        this.roundScoreType = roundScoreType;
        this.player1RoundScore = player1RoundScore;
        this.player2RoundScore = player2RoundScore;
        this.player3RoundScore = player3RoundScore;
        this.player4RoundScore = player4RoundScore;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public Integer getRoundScoreType() {
        return roundScoreType;
    }

    public void setRoundScoreType(Integer roundScoreType) {
        this.roundScoreType = roundScoreType;
    }

    public Integer getPlayer1RoundScore() {
        return player1RoundScore;
    }

    public void setPlayer1RoundScore(Integer player1RoundScore) {
        this.player1RoundScore = player1RoundScore;
    }

    public Integer getPlayer2RoundScore() {
        return player2RoundScore;
    }

    public void setPlayer2RoundScore(Integer player2RoundScore) {
        this.player2RoundScore = player2RoundScore;
    }

    public Integer getPlayer3RoundScore() {
        return player3RoundScore;
    }

    public void setPlayer3RoundScore(Integer player3RoundScore) {
        this.player3RoundScore = player3RoundScore;
    }

    public Integer getPlayer4RoundScore() {
        return player4RoundScore;
    }

    public void setPlayer4RoundScore(Integer player4RoundScore) {
        this.player4RoundScore = player4RoundScore;
    }

}
