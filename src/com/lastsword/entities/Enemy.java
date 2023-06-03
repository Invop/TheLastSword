package com.lastsword.entities;

public class Enemy {
    private static int enemyHp;
    private static int enemyId;
    private static int enemyDmg;
    private static boolean isBossFight = false;

    public static int getEnemyHp() {
        return enemyHp;
    }

    public static int getEnemyId() {
        return enemyId;
    }

    public static void setEnemyId(int enemyId) {
        Enemy.enemyId = enemyId;
    }

    public static int getEnemyDmg() {
        return enemyDmg;
    }

    public static boolean isBossFight() {
        return isBossFight;
    }

    public static void setIsBossFight(boolean isBossFight) {
        Enemy.isBossFight = isBossFight;
    }

    public static void setEnemyHp(int enemyHp) {
        Enemy.enemyHp = enemyHp;
    }


    //hydra - double dmg id 3
    //jug - 2x hp id 2
    //magorix - 1st stage (1 hit ) 2nd (4-5?) id 1
}
