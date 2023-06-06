package com.lastsword;

import com.lastsword.game.Game;
import com.lastsword.menu.CharacterSelectionMenuPanel;
import com.lastsword.menu.CharaterSelectionMenuWindow;
import com.lastsword.menu.MainMenuPanel;
import com.lastsword.menu.MainMenuWindow;

import javax.swing.*;


public class Main {
    private static MainMenuPanel MainMenuPanel;
    public static JPanel getMainMenuPanel(){return MainMenuPanel;}
    public static void main(String[] args) {
        MainMenuPanel = new MainMenuPanel();
        MainMenuWindow window = new MainMenuWindow(MainMenuPanel);
    }

}