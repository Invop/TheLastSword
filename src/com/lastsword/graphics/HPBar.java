package com.lastsword.graphics;

import javax.swing.*;

public class HPBar {
    private final int maxHP;
    private int currentHP;
    private JLabel hpLabel;

    public HPBar(int maxHP) {
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
    private void updateHpBar(){
        if (currentHP==maxHP){
            //for(...) ?
            hpLabel.setIcon(new ImageIcon(""));
        } else if (currentHP>=maxHP/2&&currentHP<currentHP) {
            hpLabel.setIcon(new ImageIcon(""));
        } else if (currentHP<=maxHP/2&&currentHP>0) {
            hpLabel.setIcon(new ImageIcon(""));
        }
        else if (currentHP==0) {
            hpLabel.setIcon(new ImageIcon(""));
        }
    }


    public double getPercentage() {
        return (double) currentHP / maxHP * 100;
    }

}
