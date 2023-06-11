package com.lastsword.entities;

public class Player {
    private static int score;
    private static int playerHp;
    private static String info;
    private static int playerId;
    private static final int ultChance = 8;

    public Player(int playerIndx) {
        switch (playerIndx) {
            case 1: {
                playerHp = 3;
                playerId = 1;
                break;
            }
            case 2: {
                playerHp = 3;
                playerId = 2;
                info = "HeroInfo2";
                break;
            }
            case 3: {
                playerHp = 3;
                playerId = 3;
                info = "HeroInfo3";
                break;
            }
            default:
                break;
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        Player.score = score;
    }

    public int getPlayerHp() {
        return playerHp;
    }

    public void setPlayerHp(int playerHp) {
        Player.playerHp = playerHp;
    }

    public int getUltChance() {
        return ultChance;
    }

    public String getInfo() {
        return info;
    }

    public int getPlayerId() {
        return playerId;
    }
}
