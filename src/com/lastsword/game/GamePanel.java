package com.lastsword.game;

import com.lastsword.entities.Enemy;
import com.lastsword.entities.Player;
import com.lastsword.graphics.Animation;
import com.lastsword.graphics.ButtonRenderer;
import com.lastsword.input.KeyboardInputs;
import com.lastsword.utilities.GetFrames;
import com.lastsword.utilities.WordGenerator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.lastsword.utilities.GetFrames.scaleImage;
import static com.lastsword.utilities.GetFrames.scaleImages;


public class GamePanel extends JPanel {
    private int panelWidth = 1280;
    private int panelHeight = 720;

    private Animation attackAnimationPlayer,
            ultAnimationPlayer, arrowAnimationPlayer,
            walkAnimationPlayer, runAnimationPlayer,
            hurtAnimationPlayer, deadAnimationPlayer,
            idleAnimationPlayer;
    private static Animation attack1AnimationEnemy, attack2AnimationEnemy,
            ultAnimationEnemy, projectileAnimationEnemy,
            walkAnimationEnemy, runAnimationEnemy,
            hurtAnimationEnemy, deadAnimationEnemy,
            idleAnimationEnemy;
    private Timer animation_timer, arrow_timer;
    private static WordGenerator wordGenerator;
    private static ButtonRenderer buttonRenderer;
    private static KeyboardInputs keyboardInputs;
    private List<BufferedImage> attackFramesPlayer, ultFramesPlayer, walkFramesPlayer, hurtFramesPlayer, deadFramesPlayer, idleFramesPlayer;
    private static List<BufferedImage> attackFramesEnemy, walkFramesEnemy, hurtFramesEnemy, deadFramesEnemy, idleFramesEnemy;
    private static List<List<BufferedImage>> enemyUltCollection;
    private BufferedImage arrow;
    private static BufferedImage projectile;
    private static final int defaultAnimationSpeed = 100;
    private final int attackAnimation_delay = 100;
    private final int arrowAnimation_delay = 90;
    private static Player player;
    private static int[] letterValues;
    private Random random = new Random();
    private int randomEnemy;

    public static boolean   isPlayerAttack , isEnemyAttack,
                            isPlayerWalk , isEnemyWalk,
                            isPlayerHurt, isEnemyHurt,
                            isPlayerDead,isEnemyDead,
                            isPlayerIdle,isEnemyIdle,
                            isPlayerUlt,isEnemyUlt;
    //бг
    private int imageWidth = 1280;
    private int imageHeight = 720;
    private Image backgroundImage;
    private int x1 = 0;
    private int x2 = imageWidth;
    public static boolean isCarouselActive = false;
    private Timer timer;
    private int shiftX = 0;


    public GamePanel() {
        keyboardInputs = new KeyboardInputs();
        player = Game.getSelectedPlayer();
        System.out.println(player.getInfo());
        setBackground(Color.PINK);
        setSize(1280, 720);
        setFocusable(true);
        addKeyListener(keyboardInputs);
        CreateRandomEnemy();
        InitPlayerFrames();
        AddPlayerAnimations();
        RenderRandomBtns();
        AddBackground();
        CreateTimers();
        System.out.println("rdy");

    }
    public void AddBackground() {
        try {
            backgroundImage = ImageIO.read(new File("src/res/images/backgrounds/Battleground2/Battleground2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addCollection(List<BufferedImage> images) {
        enemyUltCollection.add(images);
    }
    public boolean isCollision(Rectangle rect1, Rectangle rect2) {
        return rect1.intersects(rect2);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //bg move
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, x1 - shiftX, 0, imageWidth, imageHeight, null);
            g.drawImage(backgroundImage, x2 - shiftX, 0, imageWidth, imageHeight, null);
        }

        buttonRenderer.draw(g, 500, 600);
    }


    public static void RenderRandomBtns() {
        wordGenerator = new WordGenerator(3);
        letterValues = wordGenerator.getLetterValues();
        buttonRenderer = new ButtonRenderer(letterValues);
        keyboardInputs.setCurrentIndex();
        keyboardInputs.setWordToMatch(wordGenerator.getWord());
        keyboardInputs.setButtonRenderer(buttonRenderer);
    }

    //player
    private void AddPlayerAttackFrames() {
        GetFrames getFrames1, getFrames2, getFrames3;
        List<BufferedImage> frames1 = null, frames2 = null, frames3 = null;
        switch (player.getPlayerId()) {
            case 1 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/fire_vizard/Attack_2.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
                getFrames2 = new GetFrames("src/res/images/sprites/player/fire_vizard/Attack_2.png",
                        null);
                frames2 = scaleImages(getFrames2.FramesToList(), 2);
                getFrames3 = new GetFrames("src/res/images/sprites/player/fire_vizard/Attack_2.png",
                        null);
                frames3 = scaleImages(getFrames3.FramesToList(), 2);
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
                getFrames3 = new GetFrames("src/res/images/sprites/player/samurai_commander/Attack_1.png",
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

    private void AddPlayerIdleFrames() {
        GetFrames getFrames1;
        List<BufferedImage> frames1 = null;
        switch (player.getPlayerId()) {
            case 1 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/fire_vizard/Idle.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 2 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_archer/Idle.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
            case 3 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_commander/Idle.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2);
            }
        }

        idleFramesPlayer = new ArrayList<>();
        if (frames1 != null) {
            idleFramesPlayer.addAll(frames1);
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
        AddPlayerIdleFrames();
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
    private static void AddEnemyAttackFrames() {
        GetFrames getFrames1, getFrames2;
        List<BufferedImage> frames1 = null, frames2 = null;
        switch (Enemy.getEnemyId()) {
            case 1 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/Attack1.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2.6);
            }
            case 2 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/not_bosses/juggernaut/Attack.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2.6);
            }
            case 3 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/not_bosses/hydra/Attack1.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2.6);
                getFrames2 = new GetFrames("src/res/images/sprites/enemy/not_bosses/hydra/Attack2.png",
                        null);
                frames2 = scaleImages(getFrames2.FramesToList(), 2.6);
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

    private static void AddEnemyUltFrames() {
        GetFrames getFrames1;
        getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/Summon_Juggernaut.png",
                null);
        addCollection(scaleImages(getFrames1.FramesToList(), 2.6));
        getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/MagorixUltAttack1.png",
                null);
        addCollection(scaleImages(getFrames1.FramesToList(), 2.6));
        getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/MagorixUltAttack2.png",
                null);
        addCollection(scaleImages(getFrames1.FramesToList(), 2.6));
        getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/MagorixUltWalk.png",
                null);
        addCollection(scaleImages(getFrames1.FramesToList(), 2.6));
        getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/MagorixUltHurt.png",
                null);
        addCollection(scaleImages(getFrames1.FramesToList(), 2.6));
        getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/MagorixUltIdle.png",
                null);
        addCollection(scaleImages(getFrames1.FramesToList(), 2.6));

    }

    private static void AddEnemyWalkFrames() {
        GetFrames getFrames1;
        List<BufferedImage> frames1 = null;
        switch (Enemy.getEnemyId()) {
            case 1 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/Walk.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2.6);
            }
            case 2 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/not_bosses/juggernaut/Walk.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2.6);
            }
            case 3 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/not_bosses/hydra/Walk.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2.6);
            }
        }

        walkFramesEnemy = new ArrayList<>();
        if (frames1 != null) {
            walkFramesEnemy.addAll(frames1);
        }
    }

    private static void AddEnemyHurtFrames() {
        GetFrames getFrames1;
        List<BufferedImage> frames1 = null;
        switch (Enemy.getEnemyId()) {
            case 1 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/Hurt.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2.6);
            }
            case 2 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/not_bosses/juggernaut/Hurt.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2.6);
            }
            case 3 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/not_bosses/hydra/Hurt.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2.6);
            }
        }

        hurtFramesEnemy = new ArrayList<>();
        if (frames1 != null) {
            hurtFramesEnemy.addAll(frames1);
        }
    }

    private static void AddEnemyDeadFrames() {
        GetFrames getFrames1;
        List<BufferedImage> frames1 = null;
        switch (Enemy.getEnemyId()) {
            case 1 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/Death.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2.6);
            }
            case 2 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/not_bosses/juggernaut/Death.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2.6);
            }
            case 3 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/not_bosses/hydra/Death.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2.6);
            }
        }

        deadFramesEnemy = new ArrayList<>();
        if (frames1 != null) {
            deadFramesEnemy.addAll(frames1);
        }
    }

    private static void AddEnemyIdleFrames() {
        GetFrames getFrames1;
        List<BufferedImage> frames1 = null;
        switch (Enemy.getEnemyId()) {
            case 1 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/bosses/magorix/Idle.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2.6);
            }
            case 2 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/not_bosses/juggernaut/Idle.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2.6);
            }
            case 3 -> {
                getFrames1 = new GetFrames("src/res/images/sprites/enemy/not_bosses/hydra/Idle.png",
                        null);
                frames1 = scaleImages(getFrames1.FramesToList(), 2.6);
            }
        }

        idleFramesEnemy = new ArrayList<>();
        if (frames1 != null) {
            idleFramesEnemy.addAll(frames1);
        }
    }

    private static void AddEnemyProjectileFrame() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/res/images/sprites/enemy/bosses/magorix/Projectile.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        projectile = scaleImage(img, 2.6);
    }

    public static void InitEnemyFrames() {
        AddEnemyAttackFrames();
        AddEnemyWalkFrames();
        AddEnemyHurtFrames();
        AddEnemyDeadFrames();
        AddEnemyIdleFrames();
        AddEnemyProjectileFrame();
        if(Enemy.isBossFight())AddEnemyUltFrames();
    }

    private void CreateTimers() {
        animation_timer = new Timer(20, e -> {
            repaint();
        });
        animation_timer.start();

        timer = new Timer(20, e -> {
            if (isCarouselActive) {
                if (shiftX < imageWidth) {
                    shiftX += 5; // Оновлюємо зміщення по осі X
                    this.removeKeyListener(keyboardInputs);
                } else {
                    this.addKeyListener(keyboardInputs);
                    shiftX=0;
                    x1=0;
                    x2=imageWidth;
                    isCarouselActive = false;
                }
                repaint(); // Викликаємо метод перерисування
            }
        });
        timer.start();
    }

    private void AddPlayerAnimations() {
        attackAnimationPlayer = new Animation(attackFramesPlayer, defaultAnimationSpeed+10, false);
        ultAnimationPlayer = new Animation(ultFramesPlayer, defaultAnimationSpeed, false);
        if (player.getPlayerId() == 2) arrowAnimationPlayer = new Animation(arrow);
        walkAnimationPlayer = new Animation(walkFramesPlayer, defaultAnimationSpeed, true);
        hurtAnimationPlayer = new Animation(hurtFramesPlayer, defaultAnimationSpeed, true);
        deadAnimationPlayer = new Animation(deadFramesPlayer, defaultAnimationSpeed, false);
        idleAnimationPlayer = new Animation(idleFramesPlayer, defaultAnimationSpeed, true);
    }

    public static void AddEnemyAnimation() {
        if (Enemy.isBossFight()) {
            attack1AnimationEnemy = new Animation(enemyUltCollection.get(1), defaultAnimationSpeed, false);
            attack2AnimationEnemy = new Animation(enemyUltCollection.get(2), defaultAnimationSpeed, false);
            projectileAnimationEnemy = new Animation(projectile);
            walkAnimationEnemy = new Animation(enemyUltCollection.get(3), defaultAnimationSpeed, true);
            hurtAnimationEnemy = new Animation(enemyUltCollection.get(4), defaultAnimationSpeed-49, true);
            idleAnimationEnemy = new Animation(enemyUltCollection.get(5), defaultAnimationSpeed, true);
        } else {
            attack1AnimationEnemy = new Animation(attackFramesEnemy, defaultAnimationSpeed+300, true);
            walkAnimationEnemy = new Animation(walkFramesEnemy, defaultAnimationSpeed, true);
            hurtAnimationEnemy = new Animation(hurtFramesEnemy, defaultAnimationSpeed-50, true);
            deadAnimationEnemy = new Animation(deadFramesEnemy, defaultAnimationSpeed, false);
            idleAnimationEnemy = new Animation(idleFramesEnemy, defaultAnimationSpeed, true);
            if (Enemy.getEnemyId() == 1) projectileAnimationEnemy = new Animation(projectile);
        }
    }

    private void CreateRandomEnemy(){
        randomEnemy = random.nextInt(0,3);
        Enemy.setEnemyId(2);
        InitEnemyFrames();
        AddEnemyAnimation();
    }

}

