package com.lastsword.game;

import com.lastsword.audio.AudioPlayer;
import com.lastsword.entities.Enemy;
import com.lastsword.entities.Player;
import com.lastsword.graphics.HPBar;
import com.lastsword.menu.CharacterSelectionMenuPanel;
import com.lastsword.menu.CharaterSelectionMenuWindow;

import javax.swing.*;
import java.awt.*;

import static com.lastsword.game.GamePanel.currentPlayerPoint;


public class Game {
    private GameWindow gameWindow;
    private static GamePanel gamePanel;
    private AudioPlayer audioPlayer;
    private int victoryBattleCounter;
    private int playerScore;
    private static Player selectedPlayer;
    private HPBar playerHpBar , enemyHpBar;
    private int KilledCounter =0;

    public Game() {
//
//        audioPlayer = new AudioPlayer("src/res/music/MainTheme.wav");
//        audioPlayer.loop();
    }
    public static void resetFlagsPlayer() {
        GamePanel.isPlayerAttack = false;
        GamePanel.isPlayerWalk = false;
        GamePanel.isPlayerHurt = false;
        GamePanel.isPlayerDead = false;
        GamePanel.isPlayerIdle = false;
        GamePanel.isPlayerUlt = false;
        GamePanel.isPlayerRange = false;
        GamePanel.isEnemyRange = false;
    }
    public static void resetFlagsEnemy(){
        GamePanel.isEnemyAttack1 = false;
        GamePanel.isEnemyAttack2 = false;
        GamePanel.isEnemyWalk = false;
        GamePanel.isEnemyHurt = false;
        GamePanel.isEnemyDead = false;
        GamePanel.isEnemyIdle = false;
        GamePanel.isEnemyUlt = false;
        GamePanel.isEnemyRange = false;
    }

    public void StartGame() {
        resetFlagsPlayer();
        resetFlagsEnemy();
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
    }
    public static void PlayerMoveToThePoint(int point){
        resetFlagsPlayer();
        GamePanel.isPlayerWalk=true;
        gamePanel.PlayerMove(point);
    }
    public static void EnemyMoveToThePoint(int point){
        resetFlagsEnemy();
        GamePanel.isEnemyWalk=true;
        gamePanel.EnemyMove(point);
    }
    public static void PlayerAttack() {
        resetFlagsPlayer();
        GamePanel.isPlayerAttack = true;

    }
    public static void EnemyAttack() {
        resetFlagsEnemy();
        GamePanel.isEnemyAttack1 = true;

    }

    public static Player getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(Player selectedPlayer) {
        Game.selectedPlayer = selectedPlayer;
    }
}
