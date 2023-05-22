package com.lastsword.game;

import com.lastsword.graphics.Animation;
import com.lastsword.graphics.ButtonRenderer;
import com.lastsword.input.KeyboardInputs;
import com.lastsword.utilities.GetFrames;
import com.lastsword.utilities.WordGenerator;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static com.lastsword.utilities.GetFrames.scaleImages;


public class GamePanel extends JPanel {
    private Animation MainAnimation;
    private Timer MainAnimation_timer;
    private WordGenerator wordGenerator;
    private ButtonRenderer buttonRenderer;
    private  KeyboardInputs keyboardInputs;

    private static final int animationSpeed = 100;
    private int MainAnimation_delay = 100;


    private int[] letterValues;

    public GamePanel() {
        setBackground(Color.PINK);
        setSize(1280, 720);
        setFocusable(true);

        //Додавання анімацій
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

        //Виклик Анімацій
        MainAnimation = new Animation(allFrames, animationSpeed, true);
        MainAnimation_timer = new Timer(MainAnimation_delay, e -> {
            MainAnimation.update();
            repaint();
        });
        MainAnimation_timer.start();

        //Перевірка букв
        wordGenerator = new WordGenerator(3);
        letterValues = wordGenerator.getLetterValues();
        addKeyListener(keyboardInputs =new KeyboardInputs(wordGenerator.getWord()));
        buttonRenderer = new ButtonRenderer(letterValues);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 100;
        int y = 100;
        buttonRenderer.draw(g,x+300,y+300);
        MainAnimation.draw(g, x, y);
    }

}

