package com.lastsword.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import com.lastsword.utilities.Letter;

public class KeyboardInputs implements KeyListener {

    private String wordToMatch;
    private int currentIndex;

    public KeyboardInputs(String wordToMatch) {
        this.wordToMatch = wordToMatch;
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

        char keyChar = e.getKeyChar();
        for (Letter letter : Letter.values()) {
            if (letter.getCharacter() == Character.toUpperCase(keyChar)) {
                if (letter.getCharacter() == wordToMatch.charAt(currentIndex)) {
                    currentIndex++;
                    if (currentIndex == wordToMatch.length()) {
                        System.out.println("correct");
                        currentIndex = 0;
                    }
                } else {
                    System.out.println("wrong");
                    currentIndex = 0;
                }
                return;
            }
        }
    }



}
