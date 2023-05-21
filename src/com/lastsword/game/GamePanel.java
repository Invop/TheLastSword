package com.lastsword.game;

import com.lastsword.graphics.Animation;
import com.lastsword.input.KeyboadInputs;
import com.lastsword.utilities.GetFrames;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.lastsword.utilities.GetFrames.scaleImages;


public class GamePanel extends JPanel implements ActionListener {
    private Animation animation;
    private Timer timer;
     private static final int animationSpeed = 100;

    public GamePanel() {
        setBackground(Color.PINK);
        setSize(1280, 720);
        setFocusable(true);
        addKeyListener(new KeyboadInputs());

        GetFrames getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_archer/Attack_1.png");
        List<BufferedImage> frames1 = scaleImages(getFrames1.FramesToList(),2);


        GetFrames getFrames2 = new GetFrames("src/res/images/sprites/player/samurai_archer/Attack_2.png");
        List<BufferedImage> frames2 = scaleImages(getFrames2.FramesToList(),2);

        GetFrames getFrames3 = new GetFrames("src/res/images/sprites/player/samurai_archer/Attack_3.png");
        List<BufferedImage> frames3 = scaleImages(getFrames3.FramesToList(),2);

        List<BufferedImage> allFrames = new ArrayList<>();
        allFrames.addAll(frames1);
        allFrames.addAll(frames2);
        allFrames.addAll(frames3);

        animation = new Animation(allFrames, animationSpeed, true);
        int delay = 16;
        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 100;
        int y = 100;

        animation.draw(g, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        animation.update();

        repaint();
    }
}

