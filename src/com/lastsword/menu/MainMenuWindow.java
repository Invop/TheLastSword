package com.lastsword.menu;

import javax.swing.*;

/**
 * The type Main menu window.
 */
public class MainMenuWindow extends JFrame {

    /**
     * Instantiates a new Main menu window.
     *
     * @param mainMenuPanel the main menu panel
     */
    public MainMenuWindow(JPanel mainMenuPanel) {
        this.setTitle("TheLastSword");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainMenuPanel);
        setVisible(true);
    }
}
