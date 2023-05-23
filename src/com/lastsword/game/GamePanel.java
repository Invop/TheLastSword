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
    private Animation attackAnimation;
    private Timer attackAnimation_timer;
    private WordGenerator wordGenerator;
    private ButtonRenderer buttonRenderer;
    private  KeyboardInputs keyboardInputs;

    private List<BufferedImage> attackFrames;

    private static boolean AnimationStart = false;

    private static final int animationSpeed = 100;
    private int MainAnimation_delay = 100;


    private int[] letterValues;

    public GamePanel() {
        setBackground(Color.PINK);
        setSize(1280, 720);
        setFocusable(true);
        AddAttackFrames();
        AddAnimations();
        CreateTimers();
        RenderRandomBtns();
        addKeyListener(keyboardInputs =new KeyboardInputs(wordGenerator.getWord(),buttonRenderer));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 100;
        int y = 100;
        buttonRenderer.draw(g,x+300,y+300);

        if(AnimationStart) {
            attackAnimation.update();
            attackAnimation.draw(g, x, y);
        }
    }
    private void RenderRandomBtns(){
        wordGenerator = new WordGenerator(3);
        letterValues = wordGenerator.getLetterValues();
        buttonRenderer = new ButtonRenderer(letterValues);
    }
    private void AddAttackFrames(){
        GetFrames getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_archer/Attack_1.png");
        List<BufferedImage> frames1 = scaleImages(getFrames1.FramesToList(),2);

        GetFrames getFrames2 = new GetFrames("src/res/images/sprites/player/samurai_archer/Attack_2.png");
        List<BufferedImage> frames2 = scaleImages(getFrames2.FramesToList(),2);

        GetFrames getFrames3 = new GetFrames("src/res/images/sprites/player/samurai_archer/Attack_3.png");
        List<BufferedImage> frames3 = scaleImages(getFrames3.FramesToList(),2);

        attackFrames = new ArrayList<>();
        attackFrames.addAll(frames1);
        attackFrames.addAll(frames2);
        attackFrames.addAll(frames3);
    }
    private void CreateTimers(){
        attackAnimation_timer = new Timer(MainAnimation_delay, e -> {
            repaint();
        });
        attackAnimation_timer.start();
    }
    private void AddAnimations(){
        attackAnimation = new Animation(attackFrames, animationSpeed, false);
    }
    public static void SetAnimationStart(boolean state){
        AnimationStart=state;
    }


}

