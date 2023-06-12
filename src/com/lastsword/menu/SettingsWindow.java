package com.lastsword.menu;

import javax.swing.*;

/**
 * The type Settings window.
 */
public class SettingsWindow extends JFrame {

    /**
     * Instantiates a new Settings window.
     *
     * @param settingsPanel the settings panel
     */
    public SettingsWindow(JPanel settingsPanel) {
        this.setTitle("TheLastSword");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(settingsPanel);
        setVisible(true);
    }
}
