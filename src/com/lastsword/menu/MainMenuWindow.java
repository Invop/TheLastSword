package com.lastsword.menu;

import javax.swing.*;

public class MainMenuWindow extends JFrame {

    public MainMenuWindow(JPanel mainMenuPanel) {
        this.setTitle("TheLastSword");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainMenuPanel);
        setVisible(true);
    }
}
