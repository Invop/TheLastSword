package com.lastsword.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Animation.
 */
public class Animation {

    private final List<BufferedImage> frames;
    private int currentFrameIndex;
    private final long animationSpeed;
    private long lastFrameTime;
    private final boolean loop;

    /**
     * Instantiates a new Animation.
     *
     * @param frames         the frames
     * @param animationSpeed the animation speed
     * @param loop           the loop
     */
    public Animation(List<BufferedImage> frames, long animationSpeed, boolean loop) {
        this.frames = frames;
        this.animationSpeed = animationSpeed;
        this.loop = loop;
        currentFrameIndex = 0;
        lastFrameTime = 0;
    }

    /**
     * Instantiates a new Animation.
     *
     * @param frame the frame
     */
    public Animation(BufferedImage frame) {
        this.frames = new ArrayList<>();
        this.frames.add(frame);
        this.animationSpeed = 0; // Set animation speed to 0 as there's only one frame
        this.loop = false; // Disable looping for single frame
        currentFrameIndex = 0;
        lastFrameTime = 0;
    }

    /**
     * Update.
     */
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

    /**
     * Draw.
     *
     * @param g the g
     * @param x the x
     * @param y the y
     */
    public void draw(Graphics g, int x, int y) {
        BufferedImage currentFrame = frames.get(currentFrameIndex);
        g.drawImage(currentFrame, x, y, null);
    }

    /**
     * Draw en.
     *
     * @param g the g
     * @param x the x
     * @param y the y
     */
    public void drawEn(Graphics g, int x, int y) {
        BufferedImage currentFrame = frames.get(currentFrameIndex);
        g.drawImage(currentFrame, x - 40, y, null);

    }

    /**
     * Gets current frame index.
     *
     * @return the current frame index
     */
    public int getCurrentFrameIndex() {
        return currentFrameIndex;
    }

    /**
     * Gets frames.
     *
     * @return the frames
     */
    public List<BufferedImage> getFrames() {
        return frames;
    }

    /**
     * Gets bounds.
     *
     * @param x the x
     * @param y the y
     * @return the bounds
     */
    public Rectangle getBounds(int x, int y) {
        BufferedImage currentFrame = frames.get(currentFrameIndex);
        int width = currentFrame.getWidth();
        int height = currentFrame.getHeight();
        return new Rectangle(x, y, width, height);
    }

}
