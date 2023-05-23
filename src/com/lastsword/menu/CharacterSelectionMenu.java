package com.lastsword.menu;

import com.lastsword.graphics.Animation;
import com.lastsword.utilities.GetFrames;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static com.lastsword.utilities.GetFrames.scaleImages;

public class CharacterSelectionMenu extends JPanel {
    private List<BufferedImage> idleAnimationHero1;
    private List<BufferedImage> idleAnimationHero2;
    private List<BufferedImage> idleAnimationHero3;
    private static final int animationSpeed = 100;
    private static final  int animation_delay = 100;
    private Animation idleAnimation;
    private int currentHeroIndex = 0;
    private JTextArea heroInfoTextArea;
    private Timer idleAnimation_timer;

    public CharacterSelectionMenu() {
        setLayout(new BorderLayout());
        CreateTimer();
        AddIdleFrames();
        PlayAnimations();
        add(createSelectionPanel(), BorderLayout.EAST);
    }

    private JPanel createSelectionPanel() {
        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new BorderLayout());

        JButton previousButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        JButton selectButton = new JButton("Select");

        previousButton.addActionListener(e -> {

            repaint();
        });

        nextButton.addActionListener(e -> {

            repaint();
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

        selectionPanel.add(buttonsPanel, BorderLayout.NORTH);
        selectionPanel.add(new JScrollPane(heroInfoTextArea), BorderLayout.CENTER);

        return selectionPanel;
    }

    private void AddIdleFrames() {
        GetFrames getFrames1 = new GetFrames("src/res/images/sprites/player/samurai_archer/Idle.png");
        idleAnimationHero1 = scaleImages(getFrames1.FramesToList(), 2);

        GetFrames getFrames2 = new GetFrames("src/res/images/sprites/player/samurai_archer/Idle.png");
        idleAnimationHero2 = scaleImages(getFrames2.FramesToList(), 2);

        GetFrames getFrames3 = new GetFrames("src/res/images/sprites/player/samurai_archer/Idle.png");
        idleAnimationHero3 = scaleImages(getFrames3.FramesToList(), 2);
    }

    private void PlayAnimations() {
        idleAnimation = new Animation(idleAnimationHero1, animationSpeed, true);
    }


    private String getCurrentHeroInfo() {
        switch (currentHeroIndex) {
            case 0:
                return "Hero 1 Info";
            case 1:
                return "Hero 2 Info";
            case 2:
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

        int x = 100;
        int y = 100;
        idleAnimation.update();
        idleAnimation.draw(g, x, y);
    }
}
