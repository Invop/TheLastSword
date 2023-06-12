package com.lastsword.menu;

import javax.swing.*;

/**
 * The type Charater selection menu window.
 */
public class CharaterSelectionMenuWindow extends JFrame {

    /**
     * Instantiates a new Charater selection menu window.
     *
     * @param charaterSelectionPanel the charater selection panel
     */
    public CharaterSelectionMenuWindow(JPanel charaterSelectionPanel) {
        this.setTitle("TheLastSword");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(charaterSelectionPanel);
        setVisible(true);
    }
}
