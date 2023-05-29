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

import static com.lastsword.utilities.GetFrames.scaleImage;
import static com.lastsword.utilities.GetFrames.scaleImages;


public class GamePanel extends JPanel {
    private Animation attackAnimation,
            ultAnimation, arrowAnimation,
            walkAnimation, runAnimation,
            hurtAnimation, deadAnimation;
    private Timer animation_timer, arrow_timer;
    private WordGenerator wordGenerator;
    private ButtonRenderer buttonRenderer;
    private final KeyboardInputs keyboardInputs;
    private List<BufferedImage> attackFrames, ultFrames, walkFrames, runFrames, hurtFrames, deadFrames;
    private BufferedImage arrow;
    private static boolean attackAnimationStart = false,
            ultAnimationStart = false,
            arrowAnimationStart = false,
            walkAnimationStart = false,
            runAnimationStart = false,
            hurtAnimationStart = false,
            deadAnimationStart = false,
            isImageVisible = false;
    private static final int defaultAnimationSpeed = 100;
    private static int ultAnimationSpeed = 100;
    private final int attackAnimation_delay = 100;
    private final int arrowAnimation_delay = 90;
    private static Player player;
    private int[] letterValues;
    private Random random;
    private int shiftX = 50;


    public GamePanel() {
        player = Game.getSelectedPlayer();
        System.out.println(player.getInfo());
        setBackground(Color.PINK);
        setSize(1280, 720);
        setFocusable(true);
        CreateTimer();
        InitFrames();
        AddAnimations();
        RenderRandomBtns();
        addKeyListener(keyboardInputs = new KeyboardInputs(wordGenerator.getWord(), buttonRenderer));
    }

    private void InitFrames() {

        //move
        AddPlayerWalkFrames();
        AddPlayerRunFrames();

        //dmg out
        AddPlayerAttackFrames();
        AddPlayerUltFrames();
        if (player.getPlayerId() == 2) {
            AddPlayerArrowFrame();
        }

        //dmg in
        AddPlayerHurtFrames();
        AddPlayerDeadFrames();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 100;
        int y = 100;
        buttonRenderer.draw(g, x + 300, y + 300);
        if (arrowAnimationStart) {
            arrowAnimation.update();
            if (arrowAnimation.getCurrentFrameIndex() >= 0) {
                isImageVisible = true; // Set the flag to true when the image is about to appear
                shiftX += 20; // Update the X-axis shift
            }
            if (isImageVisible) arrowAnimation.draw(g, x + shiftX, y + 83);
        }
        if (attackAnimationStart) {
            attackAnimation.update();
            attackAnimation.draw(g, x, y);
        } else if (ultAnimationStart) {
            ultAnimation.update();
            ultAnimation.draw(g, x, y);
            // Check if ultAnimation has finished
            if (ultAnimation.getCurrentFrameIndex() == ultAnimation.getFrames().size() - 4 && player.getPlayerId() == 2) {
                arrowAnimationStart = true;
            }
        } else if (walkAnimationStart) {
            walkAnimation.update();
            walkAnimation.draw(g, x, y);
        } else if (runAnimationStart) {
            runAnimation.update();
            runAnimation.draw(g, x, y);
        } else if (hurtAnimationStart) {
            hurtAnimation.update();
            hurtAnimation.draw(g, x, y);
        } else if (deadAnimationStart) {
            deadAnimation.update();
            deadAnimation.draw(g, x, y);
        }
    }

    private void RenderRandomBtns() {
        wordGenerator = new WordGenerator(3);
        letterValues = wordGenerator.getLetterValues();
        buttonRenderer = new ButtonRenderer(letterValues);
    }

    private void AddPlayerAttackFrames() {
        GetFrames getFrames1, getFrames2, getFrames3;
        List<BufferedImage> frames1 = null, frames2 = null, frames3 = null;
        switch (player.getPlayerId()) {
            case 1 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/fire_vizard/Attack_1.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
                getFrames2 = new GetFrames("src/res/images/sprites/player/fire_vizard/Attack_2.png",
                        null);
                frames2 = scaleImages(getFrames2.FramesToList(), 2);
            }
            case 2 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_archer/Attack_1.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
                getFrames2 = new GetFrames("src/res/images/sprites/player/samurai_archer/Attack_2.png",
                        null);
                frames2 = scaleImages(getFrames2.FramesToList(), 2);
                getFrames3 = new GetFrames("src/res/images/sprites/player/samurai_archer/Attack_3.png",
                        null);
                frames3 = scaleImages(getFrames3.FramesToList(), 2);
            }
            case 3 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_commander/Attack_1.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
                getFrames2 = new GetFrames("src/res/images/sprites/player/samurai_commander/Attack_2.png",
                        null);
                frames2 = scaleImages(getFrames2.FramesToList(), 2);
                getFrames3 = new GetFrames("src/res/images/sprites/player/samurai_commander/Attack_3.png",
                        null);
                frames3 = scaleImages(getFrames3.FramesToList(), 2);
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

    private void AddPlayerUltFrames() {
        GetFrames getFrames1;
        List<BufferedImage> frames1 = null;
        switch (player.getPlayerId()) {
            case 1 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/fire_vizard/Flame_jet.png",
                        "src/res/images/sprites/player/fire_vizard/Idle.png");
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 2 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_archer/Shot.png",
                        "src/res/images/sprites/player/samurai_archer/Idle.png");
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 3 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_commander/Protect.png",
                        "src/res/images/sprites/player/samurai_commander/Idle.png");
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
        }

        ultFrames = new ArrayList<>();
        if (frames1 != null) {
            ultFrames.addAll(frames1);
        }
    }

    private void AddPlayerWalkFrames() {
        GetFrames getFrames1;
        List<BufferedImage> frames1 = null;
        switch (player.getPlayerId()) {
            case 1 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/fire_vizard/Walk.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 2 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_archer/Walk.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 3 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_commander/Walk.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
        }

        walkFrames = new ArrayList<>();
        if (frames1 != null) {
            walkFrames.addAll(frames1);
        }
    }

    private void AddPlayerRunFrames() {
        GetFrames getFrames1;
        List<BufferedImage> frames1 = null;
        switch (player.getPlayerId()) {
            case 1 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/fire_vizard/Run.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 2 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_archer/Run.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 3 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_commander/Run.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
        }

        runFrames = new ArrayList<>();
        if (frames1 != null) {
            runFrames.addAll(frames1);
        }
    }

    private void AddPlayerHurtFrames() {
        GetFrames getFrames1;
        List<BufferedImage> frames1 = null;
        switch (player.getPlayerId()) {
            case 1 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/fire_vizard/Hurt.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 2 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_archer/Hurt.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 3 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_commander/Hurt.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
        }

       hurtFrames = new ArrayList<>();
        if (frames1 != null) {
            hurtFrames.addAll(frames1);
        }
    }

    private void AddPlayerDeadFrames() {
        GetFrames getFrames1;
        List<BufferedImage> frames1 = null;
        switch (player.getPlayerId()) {
            case 1 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/fire_vizard/Dead.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 2 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_archer/Dead.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 3 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_commander/Dead.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
        }

        deadFrames = new ArrayList<>();
        if (frames1 != null) {
            deadFrames.addAll(frames1);
        }
    }

    private void AddPlayerArrowFrame() {
        GetFrames getFrame;
        getFrame = new GetFrames("src/res/images/sprites/player/samurai_archer/Arrow.png");
        arrow = scaleImage(getFrame.getFrame(), 2);
    }

    private void CreateTimer() {
        animation_timer = new Timer(attackAnimation_delay, e -> {
            repaint();
        });
        animation_timer.start();

        if (player.getPlayerId() == 2) {
            arrow_timer = new Timer(arrowAnimation_delay, e -> {
                repaint();

            });
            arrow_timer.start();
        }
    }

    private void AddAnimations() {
        if (player.getPlayerId() == 3) {
            ultAnimationSpeed = 160;
        } else {
            ultAnimationSpeed = 100;
        }
        attackAnimation = new Animation(attackFrames, defaultAnimationSpeed, true);
        ultAnimation = new Animation(ultFrames, ultAnimationSpeed, false);
        if (player.getPlayerId() == 2) arrowAnimation = new Animation(arrow);
        walkAnimation = new Animation(walkFrames, defaultAnimationSpeed, false);
        runAnimation = new Animation(runFrames, defaultAnimationSpeed, false);
        hurtAnimation = new Animation(hurtFrames, defaultAnimationSpeed, false);
        deadAnimation = new Animation(deadFrames, defaultAnimationSpeed, false);
    }

    public static void setAttackAnimationStart(boolean state) {
        attackAnimationStart = state;
    }

    public static void setUltAnimationStart(boolean state) {
        ultAnimationStart = state;
    }

    public static void setWalkAnimationStart(boolean state) {
        walkAnimationStart = state;
    }

    public static void setRunAnimationStart(boolean state) {
        runAnimationStart = state;
    }

    public static void setHurtAnimationStart(boolean state) {
        hurtAnimationStart = state;
    }

    public static void setDeadAnimationStart(boolean state) {
        deadAnimationStart = state;
    }
}

