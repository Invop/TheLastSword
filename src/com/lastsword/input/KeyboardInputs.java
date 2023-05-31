package com.lastsword.input;

import com.lastsword.game.Game;
import com.lastsword.game.GamePanel;
import com.lastsword.graphics.ButtonRenderer;
import com.lastsword.utilities.Letter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.lastsword.game.GamePanel.RenderRandomBtns;

public class KeyboardInputs implements KeyListener {

    private static String wordToMatch;
    private int currentIndex;
    private ButtonRenderer buttonRenderer;

    public void setCurrentIndex(){
        this.currentIndex = 0;
    }
    public void setWordToMatch(String word){
        wordToMatch = word;
    }
    public void setButtonRenderer(ButtonRenderer btnRenderer){
        buttonRenderer = btnRenderer;
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
                        GamePanel.isCarouselActive=true;
                        RenderRandomBtns();
                    }
                } else {
                    currentIndex = 0;
                    GamePanel.isCarouselActive=false;
                }

                return;
            }
        }
    }


}
