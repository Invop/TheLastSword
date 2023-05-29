package com.lastsword.menu;

import javax.swing.*;

public class CharaterSelectionMenuWindow extends JFrame {

    public CharaterSelectionMenuWindow(JPanel charaterSelectionPanel) {
        this.setTitle("TheLastSword");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(charaterSelectionPanel);
        setVisible(true);
    }
}
