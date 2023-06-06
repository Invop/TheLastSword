package com.lastsword.menu;

import com.lastsword.entities.Player;
import com.lastsword.game.Game;
import com.lastsword.graphics.Animation;
import com.lastsword.utilities.GetFrames;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.lastsword.utilities.GetFrames.scaleImages;

public class CharacterSelectionMenuPanel extends JPanel {
    private List<BufferedImage> idleAnimationHero1;
    private List<BufferedImage> idleAnimationHero2;
    private List<BufferedImage> idleAnimationHero3;
    private Image backgroundImage;
    private static final int animationSpeed = 120;
    private static final int animation_delay = 100;
    private Animation idleAnimation;
    private int currentHeroIndex = 1;
    private Timer idleAnimation_timer;
    private JButton backButton;
    private JButton nextButton;
    private JButton selectButton;
    private static int difficultyLevel;

    public CharacterSelectionMenuPanel() {
        setSize(1280,720);
        setLayout(new BorderLayout());
        CreateTimer();
        AddBackground();
        AddIdleFrames();
        UpdateHeroInfo(1);
        createBtns();
    }
    private void createBtns() {
        nextButton = new JButton();
        selectButton = new JButton();
        backButton = new JButton();
        setLayout(new FlowLayout());
        // Set button icons
        nextButton.setIcon(new ImageIcon("src/res/images/buttons/next/72px/next01.png"));
        selectButton.setIcon(new ImageIcon("src/res/images/buttons/play/72px/play01.png"));
        backButton.setIcon(new ImageIcon("src/res/images/buttons/back/72px/back01.png"));


        nextButton.setBorderPainted(false);
        nextButton.setContentAreaFilled(false);
        selectButton.setBorderPainted(false);
        selectButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);


        backButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.requestFocus();
            frame.dispose();
            MainMenuPanel mainMenuPanel = new MainMenuPanel();
            MainMenuWindow menuWindow = new MainMenuWindow(mainMenuPanel);
        });

        nextButton.addActionListener(e -> {
            if (currentHeroIndex < 3) {
                currentHeroIndex++;
            } else {
                currentHeroIndex = 1;
            }
            UpdateHeroInfo(currentHeroIndex);
        });

        selectButton.addActionListener(e -> {
            Game game = new Game();
            game.setSelectedPlayer(new Player(currentHeroIndex));
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(MainMenuPanel.getPanel());
            frame.requestFocus();
            game.StartGame();
            frame.dispose();

        });
        add(backButton);
        add(nextButton);
        add(selectButton);

    }

    private void UpdateHeroInfo(int heroId) {

        switch (heroId) {
            case 1 -> {
                idleAnimation = new Animation(idleAnimationHero1, animationSpeed, true);
            }
            case 2 -> {
                idleAnimation = new Animation(idleAnimationHero2, animationSpeed, true);
            }
            case 3 -> {
                idleAnimation = new Animation(idleAnimationHero3, animationSpeed, true);
            }
            default -> {
                idleAnimation = null;
            }
        }
        repaint();
    }

    private void AddIdleFrames() {
        GetFrames getFrames1 = new GetFrames("src/res/images/sprites/player/fire_vizard/Idle.png", null);
        idleAnimationHero1 = scaleImages(getFrames1.FramesToList(), 2.6);

        GetFrames getFrames2 = new GetFrames("src/res/images/sprites/player/samurai_archer/Idle.png", null);
        idleAnimationHero2 = scaleImages(getFrames2.FramesToList(), 2.6);

        GetFrames getFrames3 = new GetFrames("src/res/images/sprites/player/samurai_commander/Idle.png", null);
        idleAnimationHero3 = scaleImages(getFrames3.FramesToList(), 2.6);
    }
    private void AddBackground() {
        try {
            backgroundImage = ImageIO.read(new File("src/res/images/backgrounds/menubackground/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void CreateTimer() {
        idleAnimation_timer = new Timer(animation_delay, e -> {
            repaint();
        });
        idleAnimation_timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        int x = 570;
        int y = 100;
        idleAnimation.update();
        idleAnimation.draw(g, x, y);
    }

    public void setDifficultyLevel(int difficult) {
        difficultyLevel = difficult;
    }

    public static int getDifficultyLevel() {
        return difficultyLevel;
    }
}
