package com.lastsword.game;

import javax.swing.*;

public class GameWindow extends JFrame{

    public GameWindow(JPanel gamePanel){
        this.setTitle("TheLastSword");
        setSize(1280,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gamePanel);
        setVisible(true);

    }
}
