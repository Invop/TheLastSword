package com.lastsword.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

public class Animation {

    private List<BufferedImage> frames;
    private int currentFrameIndex;
    private long animationSpeed;
    private long lastFrameTime;
    private boolean loop;

    public Animation(List<BufferedImage> frames, long animationSpeed, boolean loop) {
        this.frames = frames;
        this.animationSpeed = animationSpeed;
        this.loop = loop;
        currentFrameIndex = 0;
        lastFrameTime = 0;
    }

    public void update() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastFrameTime > animationSpeed) {
            currentFrameIndex++;
            if (currentFrameIndex >= frames.size()) {
                if (loop) {
                    currentFrameIndex = 0;
                } else {
                    currentFrameIndex = frames.size() - 1;
                }
            }
            lastFrameTime = currentTime;
        }
    }

    public void draw(Graphics g, int x, int y) {
        BufferedImage currentFrame = frames.get(currentFrameIndex);
        g.drawImage(currentFrame, x, y, null);
    }
}
