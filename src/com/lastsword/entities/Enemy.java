package com.lastsword.entities;

/**
 * The type Enemy.
 */
public class Enemy {
    private static int enemyHp;
    private static int enemyId;
    private static int enemyDmg;
    private static boolean isBossFight = false;

    /**
     * Gets enemy hp.
     *
     * @return the enemy hp
     */
    public static int getEnemyHp() {
        return enemyHp;
    }

    /**
     * Gets enemy id.
     *
     * @return the enemy id
     */
    public static int getEnemyId() {
        return enemyId;
    }

    /**
     * Sets enemy id.
     *
     * @param enemyId the enemy id
     */
    public static void setEnemyId(int enemyId) {
        Enemy.enemyId = enemyId;
    }

    /**
     * Gets enemy dmg.
     *
     * @return the enemy dmg
     */
    public static int getEnemyDmg() {
        return enemyDmg;
    }

    /**
     * Is boss fight boolean.
     *
     * @return the boolean
     */
    public static boolean isBossFight() {
        return isBossFight;
    }

    /**
     * Sets is boss fight.
     *
     * @param isBossFight the is boss fight
     */
    public static void setIsBossFight(boolean isBossFight) {
        Enemy.isBossFight = isBossFight;
    }

    /**
     * Sets enemy hp.
     *
     * @param enemyHp the enemy hp
     */
    public static void setEnemyHp(int enemyHp) {
        Enemy.enemyHp = enemyHp;
    }


    //hydra - double dmg id 3
    //jug - 2x hp id 2
    //magorix - 1st stage (1 hit ) 2nd (4-5?) id 1
}
