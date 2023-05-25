package com.lastsword.entities;

public class Player {
    private int score;
    private int playerHp;
    private String info;
    private static final int ultChance  = 15;

    public Player(int playerIndx){
        switch (playerIndx){
            case 1:{
                playerHp = 100;
                info="HeroInfo1";
                break;}
            case 2:{
                playerHp = 120;
                info="HeroInfo2";
                break;}
            case 3:{
                playerHp = 150;
                info="HeroInfo3";
                break;}
            default:break;
        }
    }



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

    public String getInfo() {
        return info;
    }
}
