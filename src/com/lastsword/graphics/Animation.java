package com.lastsword.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {

    private final List<BufferedImage> frames;
    private int currentFrameIndex;
    private final long animationSpeed;
    private long lastFrameTime;
    private final boolean loop;

    public Animation(List<BufferedImage> frames, long animationSpeed, boolean loop) {
        this.frames = frames;
        this.animationSpeed = animationSpeed;
        this.loop = loop;
        currentFrameIndex = 0;
        lastFrameTime = 0;
    }

    public Animation(BufferedImage frame) {
        this.frames = new ArrayList<>();
        this.frames.add(frame);
        this.animationSpeed = 0; // Set animation speed to 0 as there's only one frame
        this.loop = false; // Disable looping for single frame
        currentFrameIndex = 0;
        lastFrameTime = 0;
    }

    public void update() {
        if (animationSpeed <= 0) {
            return; // No need to update if animation speed is 0 or less
        }

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

    public void drawEn(Graphics g, int x, int y) {
        BufferedImage currentFrame = frames.get(currentFrameIndex);
        g.drawImage(currentFrame, x - 40, y, null);

    }

    public int getCurrentFrameIndex() {
        return currentFrameIndex;
    }

    public List<BufferedImage> getFrames() {
        return frames;
    }

    public Rectangle getBounds(int x, int y) {
        BufferedImage currentFrame = frames.get(currentFrameIndex);
        int width = currentFrame.getWidth();
        int height = currentFrame.getHeight();
        return new Rectangle(x, y, width, height);
    }

}
