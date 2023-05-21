package com.lastsword.game;

import com.lastsword.audio.AudioPlayer;
import com.lastsword.graphics.Animation;

import java.awt.image.BufferedImage;

public class Game {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private AudioPlayer audioPlayer;
    private Animation animation;
    private boolean isRunning;
    private int frameRate;


    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        audioPlayer = new AudioPlayer("src/res/music/MainTheme.wav");
        audioPlayer.loop();
    }

}
