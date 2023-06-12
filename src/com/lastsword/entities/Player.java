package com.lastsword.entities;

/**
 * The type Player.
 */
public class Player {
    private static int score;
    private static int playerHp;
    private static String info;
    private static int playerId;
    private static final int ultChance = 8;

    /**
     * Instantiates a new Player.
     *
     * @param playerIndx the player indx
     */
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

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score) {
        Player.score = score;
    }

    /**
     * Gets player hp.
     *
     * @return the player hp
     */
    public int getPlayerHp() {
        return playerHp;
    }

    /**
     * Sets player hp.
     *
     * @param playerHp the player hp
     */
    public void setPlayerHp(int playerHp) {
        Player.playerHp = playerHp;
    }

    /**
     * Gets ult chance.
     *
     * @return the ult chance
     */
    public int getUltChance() {
        return ultChance;
    }

    /**
     * Gets info.
     *
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Gets player id.
     *
     * @return the player id
     */
    public int getPlayerId() {
        return playerId;
    }
}
