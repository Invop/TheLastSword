package com.lastsword.utilities;

public class HPBarLogic {
    private int maxHP;
    private int currentHP;

    public HPBarLogic(int maxHP) {
        this.maxHP = maxHP;
        this.currentHP = maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public void decreaseHP(int amount) {
        currentHP -= amount;
        if (currentHP < 0) {
            currentHP = 0;
        }
    }

    public void increaseHP(int amount) {
        currentHP += amount;
        if (currentHP > maxHP) {
            currentHP = maxHP;
        }
    }

    public double getPercentage() {
        return (double) currentHP / maxHP * 100;
    }

}
