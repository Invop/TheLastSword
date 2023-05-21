package com.lastsword.game;

import javax.swing.*;

public class GameWindow{


    private JFrame jFrame;

    public GameWindow(GamePanel gamePanel){
        jFrame = new JFrame("TheLastSword");
        jFrame.setSize(1280,720);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.setVisible(true);
    }
}
