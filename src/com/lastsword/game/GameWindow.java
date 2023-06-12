package com.lastsword.game;

import javax.swing.*;

/**
 * The type Game window.
 */
public class GameWindow extends JFrame {

    /**
     * Instantiates a new Game window.
     *
     * @param gamePanel the game panel
     */
    public GameWindow(JPanel gamePanel) {
        this.setTitle("TheLastSword");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gamePanel);
        setVisible(true);

    }
}
