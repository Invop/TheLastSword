package com.lastsword.menu;

import com.lastsword.entities.Player;
import com.lastsword.game.Game;
import com.lastsword.graphics.Animation;
import com.lastsword.utilities.GetFrames;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static com.lastsword.utilities.GetFrames.scaleImages;

public class CharacterSelectionMenuPanel extends JPanel{
    private List<BufferedImage> idleAnimationHero1;
    private List<BufferedImage> idleAnimationHero2;
    private List<BufferedImage> idleAnimationHero3;
    private static final int animationSpeed = 120;
    private static final  int animation_delay = 100;
    private Animation idleAnimation;
    private int currentHeroIndex = 1;
    private JTextArea heroInfoTextArea;
    private Timer idleAnimation_timer;
    private JButton previousButton;
    private JButton nextButton;
    private JButton selectButton;

    public CharacterSelectionMenuPanel() {
        setLayout(new BorderLayout());
        CreateTimer();
        AddIdleFrames();

        add(createTextPanel(), BorderLayout.EAST);
        UpdateHeroInfo(1);
    }
    private JPanel createTextPanel() {
        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new BorderLayout());

        previousButton = new JButton("Previous");
        nextButton = new JButton("Next");
        selectButton = new JButton("Select");

        previousButton.addActionListener(e -> {
            if (currentHeroIndex > 1) {
                currentHeroIndex--;
            } else {
                currentHeroIndex = 3;
            }
            UpdateHeroInfo(currentHeroIndex);
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
            JFrame frame = (JFrame) getTopLevelAncestor();
            frame.requestFocus();
            frame.dispose();
            game.StartGame();
        });

        heroInfoTextArea = new JTextArea();
        heroInfoTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        heroInfoTextArea.setEditable(false);
        heroInfoTextArea.setText(getCurrentHeroInfo());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 1));
        buttonsPanel.add(previousButton);
        buttonsPanel.add(nextButton);
        buttonsPanel.add(selectButton);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(0.75);
        splitPane.setEnabled(false);
        splitPane.setTopComponent(new JScrollPane(heroInfoTextArea));
        splitPane.setBottomComponent(buttonsPanel);

        selectionPanel.add(splitPane, BorderLayout.CENTER);

        // Set preferred size with width 400
        selectionPanel.setPreferredSize(new Dimension(400, selectionPanel.getPreferredSize().height));

        return selectionPanel;
    }
    private void UpdateHeroInfo(int heroId) {
        String heroInfo;

        switch (heroId) {
            case 1 -> {
                heroInfo = getCurrentHeroInfo();
                idleAnimation = new Animation(idleAnimationHero1, animationSpeed, true);
            }
            case 2 -> {
                heroInfo = getCurrentHeroInfo();
                idleAnimation = new Animation(idleAnimationHero2, animationSpeed, true);
            }
            case 3 -> {
                heroInfo = getCurrentHeroInfo();
                idleAnimation = new Animation(idleAnimationHero3, animationSpeed, true);
            }
            default -> {
                heroInfo = "";
                idleAnimation = null;
            }
        }

        heroInfoTextArea.setText(heroInfo);
        repaint();
    }
    private void AddIdleFrames() {
        GetFrames getFrames1 = new GetFrames("src/res/images/sprites/player/fire_vizard/Idle.png",null);
        idleAnimationHero1 = scaleImages(getFrames1.FramesToList(), 2);

        GetFrames getFrames2 = new GetFrames("src/res/images/sprites/player/samurai_archer/Idle.png",null);
        idleAnimationHero2 = scaleImages(getFrames2.FramesToList(), 2);

        GetFrames getFrames3 = new GetFrames("src/res/images/sprites/player/samurai_commander/Idle.png",null);
        idleAnimationHero3 = scaleImages(getFrames3.FramesToList(), 2);
    }
    private String getCurrentHeroInfo() {
        switch (currentHeroIndex) {
            case 1:
                return new Player(1).getInfo();
            case 2:
                return new Player(2).getInfo();
            case 3:
                return new Player(3).getInfo();
            default:
                return "";
        }
    }
    private void CreateTimer(){
        idleAnimation_timer = new Timer(animation_delay, e -> {
            repaint();
        });
        idleAnimation_timer.start();
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        int x = 350;
        int y = 100;
        idleAnimation.update();
        idleAnimation.draw(g, x, y);
    }




}
