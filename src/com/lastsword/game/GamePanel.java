package com.lastsword.game;

import com.lastsword.input.KeyboadInputs;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {

    public GamePanel(){
        this.setSize(1280,720);
        addKeyListener(new KeyboadInputs());
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawRect(100,100,200,80);
    }
}
