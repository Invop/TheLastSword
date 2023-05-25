package com.lastsword.game;

import com.lastsword.audio.AudioPlayer;
import com.lastsword.graphics.Animation;
import com.lastsword.menu.CharacterSelectionMenu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Game {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private AudioPlayer audioPlayer;

    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
//        audioPlayer = new AudioPlayer("src/res/music/MainTheme.wav");
//        audioPlayer.loop();
    }

}
