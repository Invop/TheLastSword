package com.lastsword.menu;

import com.lastsword.graphics.Animation;
import com.lastsword.utilities.GetFrames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;

import static com.lastsword.utilities.GetFrames.scaleImages;

public class CharacterSelectionMenu extends JPanel{
    private List<BufferedImage> idleAnimationHero1;
    private List<BufferedImage> idleAnimationHero2;
    private List<BufferedImage> idleAnimationHero3;
    private static final int animationSpeed = 100;
    private static final  int animation_delay = 100;
    private Animation idleAnimation;
    private int currentHeroIndex = 3;
    private JTextArea heroInfoTextArea;
    private Timer idleAnimation_timer;
    private JButton previousButton;
    private JButton nextButton;
    private JButton selectButton;

    public CharacterSelectionMenu() {
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
            System.out.println(currentHeroIndex);
            if (currentHeroIndex > 1) {
                currentHeroIndex--;
            } else {
                currentHeroIndex = 3;
            }
            UpdateHeroInfo(currentHeroIndex);
        });

        nextButton.addActionListener(e -> {
            System.out.println(currentHeroIndex);
            if (currentHeroIndex < 3) {
                currentHeroIndex++;
            } else {
                currentHeroIndex = 1;
            }
            UpdateHeroInfo(currentHeroIndex);

        });

        selectButton.addActionListener(e -> {

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
        GetFrames getFrames1 = new GetFrames("src/res/images/sprites/player/samurai/Idle.png");
        idleAnimationHero1 = scaleImages(getFrames1.FramesToList(), 2);

        GetFrames getFrames2 = new GetFrames("src/res/images/sprites/player/samurai_archer/Idle.png");
        idleAnimationHero2 = scaleImages(getFrames2.FramesToList(), 2);

        GetFrames getFrames3 = new GetFrames("src/res/images/sprites/player/samurai_commander/Idle.png");
        idleAnimationHero3 = scaleImages(getFrames3.FramesToList(), 2);
    }
    private String getCurrentHeroInfo() {
        switch (currentHeroIndex) {
            case 1:
                return "Hero 1 Info";
            case 2:
                return "Hero 2 Info";
            case 3:
                return "Hero 3 Info";
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
