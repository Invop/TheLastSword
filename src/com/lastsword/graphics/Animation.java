package com.lastsword.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

public class Animation {

    private List<BufferedImage> frames;
    private int currentFrameIndex;
    private long animationSpeed;
    private long lastFrameTime;

    public Animation(List<BufferedImage> frames, long animationSpeed) {
        this.frames = frames;
        this.animationSpeed = animationSpeed;
        currentFrameIndex = 0;
        lastFrameTime = 0;
    }

    public void update() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastFrameTime > animationSpeed) {
            currentFrameIndex++;
            if (currentFrameIndex >= frames.size()) {
                currentFrameIndex = 0;
            }
            lastFrameTime = currentTime;
        }
    }

    public void draw(Graphics g, int x, int y) {
        BufferedImage currentFrame = frames.get(currentFrameIndex);
        g.drawImage(currentFrame, x, y, null);
    }
}
