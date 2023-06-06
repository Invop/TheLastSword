package com.lastsword.game;

import com.lastsword.audio.AudioPlayer;
import com.lastsword.entities.Enemy;
import com.lastsword.entities.Player;
import com.lastsword.graphics.Animation;
import com.lastsword.graphics.ButtonRenderer;
import com.lastsword.input.KeyboardInputs;
import com.lastsword.menu.MainMenuPanel;
import com.lastsword.menu.MainMenuWindow;
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

import static com.lastsword.game.Game.resetFlagsEnemy;
import static com.lastsword.game.Game.resetFlagsPlayer;
import static com.lastsword.utilities.GetFrames.scaleImage;
import static com.lastsword.utilities.GetFrames.scaleImages;


public class GamePanel extends JPanel {
    private AudioPlayer audioPlayer;

    private Animation attackAnimationPlayer,
            ultAnimationPlayer, arrowAnimationPlayer,
            walkAnimationPlayer, runAnimationPlayer,
            hurtAnimationPlayer, deadAnimationPlayer,
            idleAnimationPlayer,currentPA;
    private static Animation attack1AnimationEnemy, attack2AnimationEnemy,
            ultAnimationEnemy, projectileAnimationEnemy,
            walkAnimationEnemy, hurtAnimationEnemy, deadAnimationEnemy,
            idleAnimationEnemy,currentEA;
    private Timer animation_timer;
    private static WordGenerator wordGenerator;
    private static ButtonRenderer buttonRenderer;
    private static KeyboardInputs keyboardInputs;
    private List<BufferedImage> attackFramesPlayer, ultFramesPlayer, walkFramesPlayer, hurtFramesPlayer, deadFramesPlayer, idleFramesPlayer;
    private static List<BufferedImage> attackFramesEnemy, walkFramesEnemy, hurtFramesEnemy, deadFramesEnemy, idleFramesEnemy;
    private BufferedImage arrow;
    private static BufferedImage projectile;
    private static final int defaultAnimationSpeed = 100;
    private static Player player;
    private static int[] letterValues;
    private final Random random = new Random();
    private int randomEnemy;

    public static boolean isPlayerAttack = false, isEnemyAttack1 = false,isEnemyAttack2= false,
            isPlayerWalk = false, isEnemyWalk = false,
            isPlayerHurt = false, isEnemyHurt = false,
            isPlayerDead = false, isEnemyDead = false,
            isPlayerIdle = false, isEnemyIdle = false,
            isPlayerUlt = false, isEnemyUlt = false,
            isPlayerRange = false, isEnemyRange = false;
    //бг
    private final int imageWidth = 1280;
    private final int imageHeight = 720;
    private Image backgroundImage;
    private int x1 = 0;
    private int x2 = imageWidth;
    public static boolean isCarouselActive = false;
    public static Timer screen_timer;
    private int shiftX = 0;
    private boolean playAudio;
    private int playerShiftX = 0,enemyShiftX=0;
    public static int playerPoint =200 ,enemyPoint =200;
    public static int currentPlayerPoint=-200;
    public static int currentEnemyPoint=1280;
    private Game game;
    private static int wordCnt=0;
    private boolean newWord;
    private static int difficultyLevel;
    private int previousHp = 4;
    private int killedCnt=0;
    private boolean shouldDraw = true;

    public GamePanel() {
        game = new Game();
        keyboardInputs = new KeyboardInputs();
        player = Game.getSelectedPlayer();
        setBackground(Color.PINK);
        setSize(1280, 720);
        setFocusable(true);
        addKeyListener(keyboardInputs);
        CreateRandomEnemy();
        InitPlayerFrames();
        AddPlayerAnimations();
        RenderRandomBtns();
        AddBackground();
        ScreenMove();
    }

    private Image loadImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        return icon.getImage();
    }
    public void AddBackground() {
        try {
            backgroundImage = ImageIO.read(new File("src/res/images/backgrounds/Battleground2/Battleground2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            if (isCarouselActive && player.getPlayerHp()>0) {
                currentPA = walkAnimationPlayer;
                currentPA.update();
                currentPA.draw(g, currentPlayerPoint, 280);
            }
        }
        if(!isCarouselActive) {
            if (isPlayerWalk) {
                currentPA = walkAnimationPlayer;
                currentPA.update();
                currentPA.draw(g, currentPlayerPoint , 280);
            }
            else if (isPlayerIdle) {
                currentPA = idleAnimationPlayer;
                currentPA.update();
                currentPA.draw(g, currentPlayerPoint, 280);
            }
            else if (isPlayerAttack) {
                currentPA = attackAnimationPlayer;
                currentPA.update();
                currentPA.draw(g, currentPlayerPoint, 280);
                Rectangle PlayerRect = currentPA.getBounds(currentPlayerPoint, 280);
                Rectangle EnemyRect = currentEA.getBounds(currentEnemyPoint, 280);
                if(currentPA.getCurrentFrameIndex() == attackFramesPlayer.size()-1){
                    isPlayerIdle=true;
                    isPlayerAttack=false;
                    isEnemyHurt=false;
                    isEnemyDead=true;
                    killedCnt++;
                }
                else{
                    if(isCollision(EnemyRect,PlayerRect)){
                    isEnemyIdle=false;
                    isEnemyHurt=true;
                    }else{
                        isEnemyHurt=false;
                        isEnemyIdle=true;
                    }
                }
            }
            else if (isPlayerDead) {
                currentPA = deadAnimationPlayer;
                currentPA.update();
                currentPA.draw(g, currentPlayerPoint, 280);
                if(currentPA.getCurrentFrameIndex()== deadFramesPlayer.size()-2){
                    resetFlagsPlayer();
                    resetFlagsEnemy();
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                    frame.requestFocus();
                    frame.dispose();

                    String message = "Ви винищили " + killedCnt + " ворогів";
                    JOptionPane.showMessageDialog(this, message);


                    MainMenuPanel mainMenuPanel = new MainMenuPanel();
                    MainMenuWindow menuWindow = new MainMenuWindow(mainMenuPanel);
                }
            }
            else if (isPlayerHurt) {
                currentPA = hurtAnimationPlayer;
                currentPA.update();
                currentPA.draw(g, currentPlayerPoint, 280);
            }
            else if (isPlayerUlt) {
                currentPA = ultAnimationPlayer;
                currentPA.update();
                currentPA.draw(g, currentPlayerPoint, 280);
            }

             if (isEnemyWalk) {
            currentEA = walkAnimationEnemy;
            currentEA.update();
            currentEA.draw(g, currentEnemyPoint, 280);
            }
             else if (isEnemyAttack1) {
             enemyPoint = currentEnemyPoint;
             currentEA = attack1AnimationEnemy;
             Rectangle PlayerRect = currentPA.getBounds(enemyPoint, 280);
             Rectangle EnemyRect = currentEA.getBounds(enemyPoint, 280);
             Rectangle EnemyAttack = currentEA.getBounds((int) (enemyPoint-EnemyRect.getWidth()+20), 280);

             currentEA.update();
             currentEA.draw(g, (int)(enemyPoint-EnemyRect.getWidth()+20), 280);
             if(currentEA.getCurrentFrameIndex() == attackFramesEnemy.size()-2){
                 isEnemyIdle=true;
                 isEnemyAttack1=false;
                 isPlayerAttack=true;
                 player.setPlayerHp(player.getPlayerHp()-1);
                 if(player.getPlayerHp()==0){
                     resetFlagsPlayer();
                     isPlayerDead=true;
                 }
             }
             else{
                 if(isCollision(PlayerRect,EnemyAttack)){
                 }else{
                     isPlayerHurt=false;
                     isPlayerIdle=true;
                 }}
            } else if (isEnemyIdle) {
                 if(newWord){RenderRandomBtns();newWord=false;}
                currentEA = idleAnimationEnemy;
                currentEA.update();
                currentEA.draw(g, currentEnemyPoint, 280);
            }
             else if (isEnemyDead) {
                currentEA = deadAnimationEnemy;
                currentEA.update();
                currentEA.draw(g, currentEnemyPoint, 280);
                 if(currentPA.getCurrentFrameIndex() == deadFramesEnemy.size()-2){
                     isEnemyDead=false;
                     resetFlagsPlayer();
                     resetFlagsEnemy();
                     GamePanel.isCarouselActive=true;
                     currentEA=null;
                 }
            } else if (isEnemyHurt) {
                currentEA = hurtAnimationEnemy;
                currentEA.update();
                currentEA.draw(g, currentEnemyPoint, 280);
            } else if (isEnemyUlt) {
                currentEA = ultAnimationEnemy;
                currentEA.update();
                currentEA.draw(g, currentEnemyPoint, 280);
            }
        }
        buttonRenderer.draw(g, 500, 600);

        int hp = player.getPlayerHp();
        int imageWidth = 32;
        int imageHeight = 32;
        int spacing = 5;

        for (int i = 0; i < hp; i++) {
            int x = i * (imageWidth + spacing);
            int y = 0;
            Image hpBarImage = loadImage("src/res/images/hpbar/hpBar.png");
            g.drawImage(hpBarImage, x, y, imageWidth, imageHeight, null);
        }
    }


    public static void RenderRandomBtns() {
        if(wordCnt==0){
            letterValues = new int[]{24, 17, 23,16,17};
            buttonRenderer = new ButtonRenderer(letterValues);
            KeyboardInputs.setCurrentIndex();
            KeyboardInputs.setButtonRenderer(buttonRenderer);
            KeyboardInputs.setWordToMatch("START");
            wordCnt++;
        }
        else {

            switch (difficultyLevel) {
                case 0 -> wordGenerator = new WordGenerator(3);
                case 1 -> wordGenerator = new WordGenerator(4);
                case 2 -> {
                    wordGenerator = new WordGenerator(4);
                    player.setPlayerHp(2);
                }
            }
            letterValues = wordGenerator.getLetterValues();
            buttonRenderer = new ButtonRenderer(letterValues);
            KeyboardInputs.setCurrentIndex();
            KeyboardInputs.setButtonRenderer(buttonRenderer);
            KeyboardInputs.setWordToMatch(wordGenerator.getWord());
            KeyboardInputs.UpdateTimer(difficultyLevel);
        }
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
    }

    public void PlayerMove(int point){
        animation_timer = new Timer(20, e -> {
            if (isPlayerWalk) {
                if (currentPlayerPoint < point) {
                    currentPlayerPoint += 7;
                } else {
                    resetFlagsPlayer();
                    isPlayerIdle=true;
                    animation_timer.restart();
                }
                // Викликаємо метод перерисування
            }repaint();
        });
        animation_timer.start();
    }
    public void EnemyMove(int point){
        animation_timer = new Timer(20, e -> {
            if (isEnemyWalk) {
                if (currentEnemyPoint > point) {
                    currentEnemyPoint -= 5;
                } else {
                    resetFlagsEnemy();
                    isEnemyIdle=true;
                    this.addKeyListener(keyboardInputs);
                    animation_timer.restart();
                }
                // Викликаємо метод перерисування
            }repaint();
        });
        animation_timer.start();
    }
    private void ScreenMove(){
        screen_timer = new Timer(40, e -> {
            if (isCarouselActive) {
                if (shiftX < imageWidth) {
                    shiftX += 15; // Оновлюємо зміщення по осі X
                    this.removeKeyListener(keyboardInputs);
                    playAudio=true;
                } else {
                    System.gc();
                    isCarouselActive = false;
                    CreateRandomEnemy();

                    currentEnemyPoint=1280;
                    Game.PlayerMoveToThePoint(670);
                    Game.EnemyMoveToThePoint(800);
                    newWord = true;
                    shiftX = 0;
                    x1 = 0;
                    x2 = imageWidth;
                    screen_timer.restart();
                }
                // Викликаємо метод перерисування
            }
            repaint();
        });

        screen_timer.start();
    }

    private void AddPlayerAnimations() {
        attackAnimationPlayer = new Animation(attackFramesPlayer, defaultAnimationSpeed + 10, true);
        ultAnimationPlayer = new Animation(ultFramesPlayer, defaultAnimationSpeed, false);
        if (player.getPlayerId() == 2) arrowAnimationPlayer = new Animation(arrow);
        walkAnimationPlayer = new Animation(walkFramesPlayer, defaultAnimationSpeed, true);
        hurtAnimationPlayer = new Animation(hurtFramesPlayer, defaultAnimationSpeed, true);
        deadAnimationPlayer = new Animation(deadFramesPlayer, defaultAnimationSpeed, false);
        idleAnimationPlayer = new Animation(idleFramesPlayer, defaultAnimationSpeed, true);
    }

    public static void AddEnemyAnimation() {
            attack1AnimationEnemy = new Animation(attackFramesEnemy, defaultAnimationSpeed+200, true);
            walkAnimationEnemy = new Animation(walkFramesEnemy, defaultAnimationSpeed, true);
            hurtAnimationEnemy = new Animation(hurtFramesEnemy, defaultAnimationSpeed - 50, true);
            deadAnimationEnemy = new Animation(deadFramesEnemy, defaultAnimationSpeed, false);
            idleAnimationEnemy = new Animation(idleFramesEnemy, defaultAnimationSpeed, true);
            if (Enemy.getEnemyId() == 1) projectileAnimationEnemy = new Animation(projectile);
    }

    private void CreateRandomEnemy() {
        randomEnemy = random.nextInt(2) + 2;
        Enemy.setEnemyId(randomEnemy);
        InitEnemyFrames();
        AddEnemyAnimation();
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}

