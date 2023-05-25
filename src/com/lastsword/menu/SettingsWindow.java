package com.lastsword.menu;

import javax.swing.*;

public class SettingsWindow extends JFrame {

    public SettingsWindow(JPanel settingsPanel){
        this.setTitle("TheLastSword");
        setSize(1280,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(settingsPanel);
        setVisible(true);
    }
}
