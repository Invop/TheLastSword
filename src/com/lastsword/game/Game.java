package com.lastsword.game;

import com.lastsword.entities.Player;
import com.lastsword.input.KeyboardInputs;
import com.lastsword.menu.CharacterSelectionMenuPanel;


/**
 * The type Game.
 */
public class Game {
    private GameWindow gameWindow;
    private static GamePanel gamePanel;
    private static Player selectedPlayer;

    /**
     * Reset game.
     */
    public static void resetGame() {
        selectedPlayer = null;
        gamePanel = null;
    }

    /**
     * Reset flags player.
     */
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

    /**
     * Reset flags enemy.
     */
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

    /**
     * Start game.
     */
    public void StartGame() {
        KeyboardInputs.setCnt();
        if (gamePanel != null) {
            gamePanel.resetVariables();
            gamePanel = null;
        }
        gamePanel = new GamePanel();
        gamePanel.setDifficultyLevel(CharacterSelectionMenuPanel.getDifficultyLevel());
        gameWindow = new GameWindow(gamePanel);
    }

    /**
     * Player move to the point.
     *
     * @param point the point
     */
    public static void PlayerMoveToThePoint(int point) {
        resetFlagsPlayer();
        GamePanel.isPlayerWalk = true;
        gamePanel.PlayerMove(point);
    }

    /**
     * Enemy move to the point.
     *
     * @param point the point
     */
    public static void EnemyMoveToThePoint(int point) {
        resetFlagsEnemy();
        GamePanel.isEnemyWalk = true;
        gamePanel.EnemyMove(point);
    }

    /**
     * Player attack.
     */
    public static void PlayerAttack() {
        resetFlagsPlayer();
        GamePanel.isPlayerAttack = true;

    }

    /**
     * Enemy attack.
     */
    public static void EnemyAttack() {
        resetFlagsEnemy();
        GamePanel.isEnemyAttack1 = true;

    }

    /**
     * Gets selected player.
     *
     * @return the selected player
     */
    public static Player getSelectedPlayer() {
        return selectedPlayer;
    }

    /**
     * Sets selected player.
     *
     * @param selectedPlayer the selected player
     */
    public void setSelectedPlayer(Player selectedPlayer) {
        Game.selectedPlayer = selectedPlayer;
    }
}
