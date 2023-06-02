package com.lastsword.game;

import com.lastsword.audio.AudioPlayer;
import com.lastsword.entities.Player;
import com.lastsword.menu.CharacterSelectionMenuPanel;
import com.lastsword.menu.CharaterSelectionMenuWindow;


public class Game {
    private final CharacterSelectionMenuPanel selectionMenuPanel;
    private final CharaterSelectionMenuWindow charaterSelectionMenuWindow;
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private AudioPlayer audioPlayer;
    private int victoryBattleCounter;
    private int playerScore;
    private static Player selectedPlayer;

    public Game() {

        selectionMenuPanel = new CharacterSelectionMenuPanel();
        charaterSelectionMenuWindow = new CharaterSelectionMenuWindow(selectionMenuPanel);
//
//        audioPlayer = new AudioPlayer("src/res/music/MainTheme.wav");
//        audioPlayer.loop();
    }

    public void StartGame() {
        charaterSelectionMenuWindow.dispose();
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
    }


    public static Player getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(Player selectedPlayer) {
        Game.selectedPlayer = selectedPlayer;
    }
}
