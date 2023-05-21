package com.lastsword.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Animation {

    private BufferedImage[] frames;  // Масив кадрів анімації
    private int currentFrame;        // Поточний кадр
    private int frameCount;          // Кількість кадрів
    private int frameDelay;          // Затримка між кадрами
    private int delayCounter;        // Лічильник затримки
    private int x;                   // Координата X для малювання анімації
    private int y;                   // Координата Y для малювання анімації
    private int frameSpacing;        // Відстань між кадрами
    private int frameHeight;         // Висота кадрів

    public Animation(int frameDelay, int x, int y, int frameSpacing, int frameHeight) {
        this.frameDelay = frameDelay;
        this.x = x;
        this.y = y;
        this.frameSpacing = frameSpacing;
        this.frameHeight = frameHeight;
        currentFrame = 0;
        delayCounter = 0;
    }

    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
        frameCount = frames.length;
    }

    public void update() {
        if (frameCount > 1) {  // Якщо анімація складається з кількох кадрів
            delayCounter++;
            if (delayCounter >= frameDelay) {
                currentFrame++;
                if (currentFrame >= frameCount) {
                    currentFrame = 0;
                }
                delayCounter = 0;
            }
        }
    }

    public void draw(Graphics g) {
        if (frameCount > 0) {
            int frameX = x;
            for (int i = 0; i < frameCount; i++) {
                g.drawImage(frames[i], frameX, y, null);
                frameX += frames[i].getWidth() + frameSpacing;
            }
        }
    }
}
