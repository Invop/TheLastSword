package com.lastsword.entities;

public class Player {
    private int score;
    private int playerHp;

    private static final int ultChance  = 15;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPlayerHp() {
        return playerHp;
    }

    public void setPlayerHp(int playerHp) {
        this.playerHp = playerHp;
    }

    public int getUltChance(){
        return ultChance;
    }
}
