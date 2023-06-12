package com.lastsword.game;

import com.lastsword.audio.AudioPlayer;
import com.lastsword.entities.Player;
import com.lastsword.input.KeyboardInputs;
import com.lastsword.menu.CharacterSelectionMenuPanel;


public class Game {
    private GameWindow gameWindow;
    private static GamePanel gamePanel;
    private static Player selectedPlayer;

    public static void resetGame(){
        selectedPlayer = null;
        gamePanel=null;
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

    public static void resetFlagsEnemy() {
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
        KeyboardInputs.setCnt();
        if (gamePanel != null) {
            gamePanel.resetVariables();
            gamePanel=null;
        }
        gamePanel = new GamePanel();
        gamePanel.setDifficultyLevel(CharacterSelectionMenuPanel.getDifficultyLevel());
        gameWindow = new GameWindow(gamePanel);
    }

    public static void PlayerMoveToThePoint(int point) {
        resetFlagsPlayer();
        GamePanel.isPlayerWalk = true;
        gamePanel.PlayerMove(point);
    }

    public static void EnemyMoveToThePoint(int point) {
        resetFlagsEnemy();
        GamePanel.isEnemyWalk = true;
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
