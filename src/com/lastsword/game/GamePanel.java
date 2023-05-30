package com.lastsword.game;

import com.lastsword.entities.Enemy;
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
    private Animation attackAnimationPlayer,
            ultAnimationPlayer, arrowAnimationPlayer,
            walkAnimationPlayer, runAnimationPlayer,
            hurtAnimationPlayer, deadAnimationPlayer;
    private Animation attack1AnimationEnemy, attack2AnimationEnemy,
            ultAnimationEnemy, projectileAnimationEnemy,
            walkAnimationEnemy, runAnimationEnemy,
            hurtAnimationEnemy, deadAnimationEnemy,
            idleAnimationEnemy;
    private Timer animation_timer, arrow_timer;
    private WordGenerator wordGenerator;
    private ButtonRenderer buttonRenderer;
    private final KeyboardInputs keyboardInputs;
    private List<BufferedImage> attackFramesPlayer, ultFramesPlayer, walkFramesPlayer, runFramesPlayer, hurtFramesPlayer, deadFramesPlayer, idleFramesPlayer;
    private List<BufferedImage> attackFramesEnemy, walkFramesEnemy, hurtFramesEnemy, deadFramesEnemy, idleFramesEnemy;
    private List<List<BufferedImage>> enemyUltCollection;
    private BufferedImage arrow;
    private BufferedImage projectile;
    private static boolean attackAnimationStartPlayer = false,
            ultAnimationStartPlayer = false,
            arrowAnimationStartPlayer = false,
            walkAnimationStartPlayer = false,
            runAnimationStartPlayer = false,
            hurtAnimationStartPlayer = false,
            deadAnimationStartPlayer = false,
            isImageVisiblePlayer = false;
    private static final boolean attackAnimationStartEnemy = false;
    private static final boolean ultAnimationStartPlayerEnemy = false;
    private static final boolean arrowAnimationStartPlayerEnemy = false;
    private static final boolean walkAnimationStartPlayerEnemy = false;
    private static final boolean runAnimationStartPlayerEnemy = false;
    private static final boolean hurtAnimationStartPlayerEnemy = false;
    private static final boolean deadAnimationStartPlayerEnemy = false;
    private static final boolean isImageVisiblePlayerEnemy = false;
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
        InitPlayerFrames();
        AddPlayerAnimations();
        RenderRandomBtns();
        addKeyListener(keyboardInputs = new KeyboardInputs(wordGenerator.getWord(), buttonRenderer));
    }

    public void addCollection(List<BufferedImage> images) {
        enemyUltCollection.add(images);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 100;
        int y = 100;
        buttonRenderer.draw(g, x + 300, y + 300);
        if (arrowAnimationStartPlayer) {
            arrowAnimationPlayer.update();
            if (arrowAnimationPlayer.getCurrentFrameIndex() >= 0) {
                isImageVisiblePlayer = true; // Set the flag to true when the image is about to appear
                shiftX += 20; // Update the X-axis shift
            }
            if (isImageVisiblePlayer) arrowAnimationPlayer.draw(g, x + shiftX, y + 83);
        }
        if (attackAnimationStartPlayer) {
            attackAnimationPlayer.update();
            attackAnimationPlayer.draw(g, x, y);
        } else if (ultAnimationStartPlayer) {
            ultAnimationPlayer.update();
            ultAnimationPlayer.draw(g, x, y);
            // Check if ultAnimation has finished
            if (ultAnimationPlayer.getCurrentFrameIndex() == ultAnimationPlayer.getFrames().size() - 4 && player.getPlayerId() == 2) {
                arrowAnimationStartPlayer = true;
            }
        } else if (walkAnimationStartPlayer) {
            walkAnimationPlayer.update();
            walkAnimationPlayer.draw(g, x, y);
        } else if (runAnimationStartPlayer) {
            runAnimationPlayer.update();
            runAnimationPlayer.draw(g, x, y);
        } else if (hurtAnimationStartPlayer) {
            hurtAnimationPlayer.update();
            hurtAnimationPlayer.draw(g, x, y);
        } else if (deadAnimationStartPlayer) {
            deadAnimationPlayer.update();
            deadAnimationPlayer.draw(g, x, y);
        }
    }

    private void RenderRandomBtns() {
        wordGenerator = new WordGenerator(3);
        letterValues = wordGenerator.getLetterValues();
        buttonRenderer = new ButtonRenderer(letterValues);
    }

    //player
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

        attackFramesPlayer = new ArrayList<>();
        if (frames1 != null) {
            attackFramesPlayer.addAll(frames1);
        }
        if (frames2 != null) {
            attackFramesPlayer.addAll(frames2);
        }
        if (frames3 != null) {
            attackFramesPlayer.addAll(frames3);
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
        ultFramesPlayer = new ArrayList<>();
        if (frames1 != null) {
            ultFramesPlayer.addAll(frames1);
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

        walkFramesPlayer = new ArrayList<>();
        if (frames1 != null) {
            walkFramesPlayer.addAll(frames1);
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

        runFramesPlayer = new ArrayList<>();
        if (frames1 != null) {
            runFramesPlayer.addAll(frames1);
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

        hurtFramesPlayer = new ArrayList<>();
        if (frames1 != null) {
            hurtFramesPlayer.addAll(frames1);
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

        deadFramesPlayer = new ArrayList<>();
        if (frames1 != null) {
            deadFramesPlayer.addAll(frames1);
        }
    }

    private void AddPlayerArrowFrame() {
        GetFrames getFrame;
        getFrame = new GetFrames("src/res/images/sprites/player/samurai_archer/Arrow.png");
        arrow = scaleImage(getFrame.getFrame(), 2);
    }

    private void InitPlayerFrames() {

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

    //enemy
    private void AddEnemyAttackFrames() {
        GetFrames getFrames1, getFrames2;
        List<BufferedImage> frames1 = null, frames2 = null;
        switch (Enemy.getEnemyId()) {
            case 1 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/Attack1.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 2 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/not_bosses/juggernaut/Attack.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 3 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/not_bosses/hydra/Attack1.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
                getFrames2 = new GetFrames("src/res/images/sprites/enemy/not_bosses/hydra/Attack2.png",
                        null);
                frames2 = scaleImages(getFrames2.FramesToList(), 2);
            }
        }

        attackFramesEnemy = new ArrayList<>();
        if (frames1 != null) {
            attackFramesEnemy.addAll(frames1);
        }
        if (frames2 != null) {
            attackFramesEnemy.addAll(frames2);
        }
    }

    private void AddEnemyUltFrames() {
        GetFrames getFrames1;
        getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/Summon_Juggernaut.png",
                null);
        addCollection(scaleImages(getFrames1.FramesToList(), 2));
        getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/MagorixUltAttack1.png",
                null);
        addCollection(scaleImages(getFrames1.FramesToList(), 2));
        getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/MagorixUltAttack2.png",
                null);
        addCollection(scaleImages(getFrames1.FramesToList(), 2));
        getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/MagorixUltWalk.png",
                null);
        addCollection(scaleImages(getFrames1.FramesToList(), 2));
        getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/MagorixUltHurt.png",
                null);
        addCollection(scaleImages(getFrames1.FramesToList(), 2));
        getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/MagorixUltIdle.png",
                null);
        addCollection(scaleImages(getFrames1.FramesToList(), 2));

    }

    private void AddEnemyWalkFrames() {
        GetFrames getFrames1;
        List<BufferedImage> frames1 = null;
        switch (Enemy.getEnemyId()) {
            case 1 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/Walk.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 2 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/not_bosses/juggernaut/Walk.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 3 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/not_bosses/hydra/Walk.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
        }

        walkFramesEnemy = new ArrayList<>();
        if (frames1 != null) {
            walkFramesEnemy.addAll(frames1);
        }
    }

    private void AddEnemyHurtFrames() {
        GetFrames getFrames1;
        List<BufferedImage> frames1 = null;
        switch (Enemy.getEnemyId()) {
            case 1 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/Hurt.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 2 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/not_bosses/juggernaut/Hurt.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 3 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/not_bosses/hydra/Hurt.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
        }

        hurtFramesEnemy = new ArrayList<>();
        if (frames1 != null) {
            hurtFramesEnemy.addAll(frames1);
        }
    }

    private void AddEnemyDeadFrames() {
        GetFrames getFrames1;
        List<BufferedImage> frames1 = null;
        switch (Enemy.getEnemyId()) {
            case 1 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/Death.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 2 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/not_bosses/juggernaut/Death.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 3 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/not_bosses/hydra/Death.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
        }

        deadFramesEnemy = new ArrayList<>();
        if (frames1 != null) {
            deadFramesEnemy.addAll(frames1);
        }
    }

    private void AddEnemyIdleFrames() {
        GetFrames getFrames1;
        List<BufferedImage> frames1 = null;
        switch (Enemy.getEnemyId()) {
            case 1 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/Idle.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 2 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/not_bosses/juggernaut/Idle.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 3 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/not_bosses/hydra/Idle.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
        }

        idleFramesEnemy = new ArrayList<>();
        if (frames1 != null) {
            idleFramesEnemy.addAll(frames1);
        }
    }

    private void AddEnemyProjectileFrame() {
        GetFrames getFrame;
        getFrame = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/Projectile.png");
        projectile = scaleImage(getFrame.getFrame(), 2);
    }

    private void InitEnemyFrames() {
        AddEnemyAttackFrames();
        AddEnemyUltFrames();
        AddEnemyWalkFrames();
        AddEnemyHurtFrames();
        AddEnemyDeadFrames();
        AddEnemyIdleFrames();
        AddEnemyProjectileFrame();
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

    private void AddPlayerAnimations() {
        if (player.getPlayerId() == 3) {
            ultAnimationSpeed = 160;
        } else {
            ultAnimationSpeed = 100;
        }
        attackAnimationPlayer = new Animation(attackFramesPlayer, defaultAnimationSpeed, true);
        ultAnimationPlayer = new Animation(ultFramesPlayer, ultAnimationSpeed, false);
        if (player.getPlayerId() == 2) arrowAnimationPlayer = new Animation(arrow);
        walkAnimationPlayer = new Animation(walkFramesPlayer, defaultAnimationSpeed, false);
        runAnimationPlayer = new Animation(runFramesPlayer, defaultAnimationSpeed, false);
        hurtAnimationPlayer = new Animation(hurtFramesPlayer, defaultAnimationSpeed, false);
        deadAnimationPlayer = new Animation(deadFramesPlayer, defaultAnimationSpeed, false);
    }

    private void AddEnemyAnimation() {
        if (Enemy.isIsBossFight()) {
            attack1AnimationEnemy = new Animation(enemyUltCollection.get(1), defaultAnimationSpeed, false);
            attack2AnimationEnemy = new Animation(enemyUltCollection.get(2), defaultAnimationSpeed, false);
            projectileAnimationEnemy = new Animation(projectile);
            walkAnimationEnemy = new Animation(enemyUltCollection.get(3), defaultAnimationSpeed, false);
            hurtAnimationEnemy = new Animation(enemyUltCollection.get(4), defaultAnimationSpeed, false);
            idleAnimationEnemy = new Animation(enemyUltCollection.get(5), defaultAnimationSpeed, false);
        } else {
            attack1AnimationEnemy = new Animation(attackFramesEnemy, defaultAnimationSpeed, false);
            if (Enemy.getEnemyId() == 1) projectileAnimationEnemy = new Animation(projectile);
            walkAnimationEnemy = new Animation(walkFramesEnemy, defaultAnimationSpeed, false);
            hurtAnimationEnemy = new Animation(hurtFramesEnemy, defaultAnimationSpeed, false);
            deadAnimationEnemy = new Animation(deadFramesEnemy, defaultAnimationSpeed, false);
            idleAnimationEnemy = new Animation(idleFramesEnemy, defaultAnimationSpeed, false);
        }
    }

    public static void setAttackAnimationStartPlayer(boolean state) {
        attackAnimationStartPlayer = state;
    }

    public static void setUltAnimationStartPlayer(boolean state) {
        ultAnimationStartPlayer = state;
    }

    public static void setWalkAnimationStartPlayer(boolean state) {
        walkAnimationStartPlayer = state;
    }

    public static void setRunAnimationStartPlayer(boolean state) {
        runAnimationStartPlayer = state;
    }

    public static void setHurtAnimationStartPlayer(boolean state) {
        hurtAnimationStartPlayer = state;
    }

    public static void setDeadAnimationStartPlayer(boolean state) {
        deadAnimationStartPlayer = state;
    }
}

