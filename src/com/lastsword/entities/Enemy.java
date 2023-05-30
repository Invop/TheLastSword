package com.lastsword.entities;

public class Enemy {
    private static int enemyHp;
    private static int enemyId;
    private static int enemyDmg;
    private static boolean isBossFight=false;

    public static int getEnemyHp() {
        return enemyHp;
    }

    public static void setEnemyHp(int enemyHp) {
        Enemy.enemyHp = enemyHp;
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

    public static void setEnemyDmg(int enemyDmg) {
        Enemy.enemyDmg = enemyDmg;
    }

    public static boolean isIsBossFight() {
        return isBossFight;
    }

    public static void setIsBossFight(boolean isBossFight) {
        Enemy.isBossFight = isBossFight;
    }


    //hydra - double dmg
    //jug - 2x hp
    //magorix - 1st stage (1 hit ) 2nd (4-5?)
}
