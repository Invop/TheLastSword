package com.lastsword.game;

import com.lastsword.audio.AudioPlayer;

public class Game {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private AudioPlayer audioPlayer;

    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        audioPlayer = new AudioPlayer("src/res/music/Battle_3_Choir_Loop.wav");
    }
}
