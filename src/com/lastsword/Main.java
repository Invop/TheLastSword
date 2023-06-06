package com.lastsword;

import com.lastsword.audio.AudioPlayer;
import com.lastsword.menu.MainMenuPanel;
import com.lastsword.menu.MainMenuWindow;

import javax.swing.*;


public class Main {
    private static AudioPlayer audioPlayer;
    private static MainMenuPanel MainMenuPanel;

    public static JPanel getMainMenuPanel() {
        return MainMenuPanel;
    }

    public static void main(String[] args) {
        audioPlayer = new AudioPlayer("src/res/music/MainTheme.wav");
        audioPlayer.setLoop(true);
        audioPlayer.play();
        MainMenuPanel = new MainMenuPanel();
        MainMenuWindow window = new MainMenuWindow(MainMenuPanel);
    }

}