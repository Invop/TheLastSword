package com.lastsword.input;

import com.lastsword.game.Game;
import com.lastsword.game.GamePanel;
import com.lastsword.graphics.ButtonRenderer;
import com.lastsword.utilities.Letter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.lastsword.game.GamePanel.isPlayerAttack;

/**
 * The type Keyboard inputs.
 */
public class KeyboardInputs implements KeyListener {

    private static String wordToMatch;
    private static int currentIndex;
    private static ButtonRenderer buttonRenderer;
    private static int cnt = 0;
    private static Timer timer;

    /**
     * Instantiates a new Keyboard inputs.
     */
    public KeyboardInputs() {
    }

    /**
     * Sets cnt.
     */
    public static void setCnt() {
        cnt = 0;
    }

    /**
     * Sets current index.
     */
    public static void setCurrentIndex() {
        currentIndex = 0;
    }

    /**
     * Sets word to match.
     *
     * @param word the word
     */
    public static void setWordToMatch(String word) {
        wordToMatch = word;
    }

    /**
     * Sets button renderer.
     *
     * @param btnRenderer the btn renderer
     */
    public static void setButtonRenderer(ButtonRenderer btnRenderer) {
        buttonRenderer = btnRenderer;
    }

    /**
     * Update timer.
     */
    public static void UpdateTimer() {
        timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentIndex != wordToMatch.length()) {
                    if (cnt != 0 && !isPlayerAttack) {
                        Game.EnemyAttack();
                    }
                    currentIndex = 0;
                }
            }
        });

        if (timer != null) {
            timer.restart();
        } else {
            timer.start();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

        char keyChar = Character.toUpperCase(e.getKeyChar());

        for (int i = 0; i < Letter.values().length; i++) {
            Letter letter = Letter.values()[i];
            if (letter.getCharacter() == keyChar) {
                if (letter.getCharacter() == wordToMatch.charAt(currentIndex)) {
                    currentIndex++;
                    buttonRenderer.updateImage(currentIndex - 1);
                    if (currentIndex == wordToMatch.length()) {
                        currentIndex = 0;
                        if (cnt == 0) {
                            GamePanel.isCarouselActive = true;
                        } else {
                            Game.PlayerAttack();
                        }
                        cnt++;
                        if (timer != null) {
                            timer.stop();
                        }
                    }
                } else {
                    if (cnt != 0 && !isPlayerAttack) {
                        Game.EnemyAttack();
                    }
                    currentIndex = 0;
                }
                return;
            }
        }

    }


}
