package com.lastsword.game;

import com.lastsword.graphics.Animation;
import com.lastsword.input.KeyboadInputs;
import com.lastsword.utilities.GetFrames;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class GamePanel extends JPanel {

    private Animation animation;

    public GamePanel(){
        setBackground(Color.PINK);
        setSize(1280,720);
        setFocusable(true);
        addKeyListener(new KeyboadInputs());

        GetFrames getFrames = new GetFrames("src/res/images/sprites/player/samurai_archer/Attack_1.png");
        List<BufferedImage> frames = getFrames.FramesToList();
        long animationSpeed = 100;
        animation = new Animation(frames, animationSpeed);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int x = 100;
        int y = 100;

        animation.update();
        animation.draw(g, x, y);

        repaint();
    }
}
