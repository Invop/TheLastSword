package com.lastsword.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.lastsword.game.GamePanel;
import com.lastsword.graphics.ButtonRenderer;
import com.lastsword.utilities.Letter;

public class KeyboardInputs implements KeyListener {

    private String wordToMatch;
    private int currentIndex;
    private ButtonRenderer buttonRenderer;

    public KeyboardInputs(String wordToMatch, ButtonRenderer buttonRenderer) {
        this.wordToMatch = wordToMatch;
        this.buttonRenderer=buttonRenderer;
        this.currentIndex = 0;

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
                        GamePanel.SetAnimationStart(true);
                        currentIndex = 0;
                    }
                } else {
                    System.out.println("Неправильно");
                    currentIndex = 0;
                }
                return;
            }
        }
    }



}
