package com.lastsword.game;

import com.lastsword.entities.Player;
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
import java.util.Random;

import static com.lastsword.utilities.GetFrames.scaleImages;


public class GamePanel extends JPanel {
    private Animation attackAnimation;
    private Animation ultAnimation;
    private Timer animation_timer;
    private WordGenerator wordGenerator;
    private ButtonRenderer buttonRenderer;
    private KeyboardInputs keyboardInputs;

    private List<BufferedImage> attackFrames;
    private List<BufferedImage> ultFrames;

    private static boolean attackAnimationStart = false;
    private static boolean ultAnimationStart = false;
    private static  int animationSpeed = 100;
    private int animation_delay = 100;

    private static Player player;
    private int[] letterValues;
    private Random random;


    public GamePanel() {
        player = Game.getSelectedPlayer();
        System.out.println(player.getInfo());
        setBackground(Color.PINK);
        setSize(1280, 720);
        setFocusable(true);
        CreateTimers();
        AddAttackFrames();
        AddUltFrames();
        AddAnimations();
        RenderRandomBtns();
        addKeyListener(keyboardInputs = new KeyboardInputs(wordGenerator.getWord(), buttonRenderer));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 100;
        int y = 100;
        buttonRenderer.draw(g, x + 300, y + 300);

        if (attackAnimationStart) {
            attackAnimation.update();
            attackAnimation.draw(g, x, y);
        }
        if (ultAnimationStart){
            ultAnimation.update();
            ultAnimation.draw(g,x,y);
        }
    }

    private void RenderRandomBtns() {
        wordGenerator = new WordGenerator(3);
        letterValues = wordGenerator.getLetterValues();
        buttonRenderer = new ButtonRenderer(letterValues);
    }

    private void AddAttackFrames() {
        GetFrames getFrames1, getFrames2, getFrames3;
        List<BufferedImage> frames1 = null, frames2 = null, frames3 = null;
        switch (player.getPlayerId()) {
            case 1: {
                getFrames1 = new GetFrames("src/res/images/sprites/player/fire_vizard/Attack_1.png",
                        "src/res/images/sprites/player/fire_vizard/Idle.png");
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
                getFrames2 = new GetFrames("src/res/images/sprites/player/fire_vizard/Attack_2.png",
                        "src/res/images/sprites/player/fire_vizard/Idle.png");
                frames2 = scaleImages(getFrames2.FramesToList(), 2);
                break;
            }
            case 2: {
                getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_archer/Attack_1.png",
                        "src/res/images/sprites/player/samurai_archer/Idle.png");
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
                getFrames2 = new GetFrames("src/res/images/sprites/player/samurai_archer/Attack_2.png",
                        "src/res/images/sprites/player/samurai_archer/Idle.png");
                frames2 = scaleImages(getFrames2.FramesToList(), 2);
                getFrames3 = new GetFrames("src/res/images/sprites/player/samurai_archer/Attack_3.png",
                        "src/res/images/sprites/player/samurai_archer/Idle.png");
                frames3 = scaleImages(getFrames3.FramesToList(), 2);
                break;
            }
            case 3: {
                getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_commander/Attack_1.png",
                        "src/res/images/sprites/player/samurai_commander/Idle.png");
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
                getFrames2 = new GetFrames("src/res/images/sprites/player/samurai_commander/Attack_2.png",
                        "src/res/images/sprites/player/samurai_commander/Idle.png");
                frames2 = scaleImages(getFrames2.FramesToList(), 2);
                getFrames3 = new GetFrames("src/res/images/sprites/player/samurai_commander/Attack_3.png",
                        "src/res/images/sprites/player/samurai_commander/Idle.png");
                frames3 = scaleImages(getFrames3.FramesToList(), 2);
                break;
            }
        }

        attackFrames = new ArrayList<>();
        if (frames1 != null) {
            attackFrames.addAll(frames1);
        }
        if (frames2 != null) {
            attackFrames.addAll(frames2);
        }
        if (frames3 != null) {
            attackFrames.addAll(frames3);
        }
    }

    private void AddUltFrames() {
        GetFrames getFrames1;
        List<BufferedImage> frames1 = null, frames2 = null, frames3 = null;
        switch (player.getPlayerId()) {
            case 1: {
                getFrames1 = new GetFrames("src/res/images/sprites/player/fire_vizard/Flame_jet.png",
                        "src/res/images/sprites/player/fire_vizard/Idle.png");
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
                break;
            }
            case 2: {
                getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_archer/Shot.png",
                        "src/res/images/sprites/player/samurai_archer/Idle.png");
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
                break;
            }
            case 3: {
                getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_commander/Protect.png",
                        "src/res/images/sprites/player/samurai_commander/Idle.png");
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
                break;
            }
        }

        ultFrames = new ArrayList<>();
        if (frames1 != null) {
            ultFrames.addAll(frames1);
        }
        if (frames2 != null) {
            ultFrames.addAll(frames2);
        }
    }

    private void CreateTimers() {
        animation_timer = new Timer(animation_delay, e -> {
            repaint();
        });
        animation_timer.start();
    }

    private void AddAnimations() {
        attackAnimation = new Animation(attackFrames, animationSpeed, false);
        if(player.getPlayerId()==3){animationSpeed = 160; animation_delay=160;}
        else{animationSpeed = 100; animation_delay=100;}
        ultAnimation = new Animation(ultFrames, animationSpeed, false);
    }

    public static void SetAttackAnimationStart(boolean state) {
        attackAnimationStart = state;
    }

    public static void SetUltAnimationStart(boolean state) {
        ultAnimationStart = state;
    }
}

