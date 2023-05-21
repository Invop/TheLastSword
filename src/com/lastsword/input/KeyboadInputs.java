package com.lastsword.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboadInputs implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar() + " pressed ");
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
