package com.lastsword;

import com.lastsword.game.Game;
import com.lastsword.menu.CharacterSelectionMenuPanel;
import com.lastsword.menu.CharaterSelectionMenuWindow;

import javax.swing.*;


public class Main {
    private static CharacterSelectionMenuPanel selectionMenuPanel;
    public static CharaterSelectionMenuWindow charaterSelectionMenuWindow;
    public static JPanel getPanel(){
        return selectionMenuPanel;
    }
    public static void main(String[] args) {
        selectionMenuPanel = new CharacterSelectionMenuPanel();
        charaterSelectionMenuWindow = new CharaterSelectionMenuWindow(selectionMenuPanel);

    }
}