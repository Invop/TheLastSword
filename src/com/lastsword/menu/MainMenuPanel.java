package com.lastsword.menu;

import com.lastsword.Main;
import com.lastsword.game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainMenuPanel extends JPanel{
    private final int imageWidth = 1280;
    private final int imageHeight = 720;
    private Image backgroundImage;
    private JButton settingsButton;
    private JButton startButton;
    private JButton exitButton;
    private ImageIcon startIcon;
    private ImageIcon settingsIcon;
    private ImageIcon exitIcon;
    /*public MainMenuPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.PINK);
        setSize(1280, 720);
        setFocusable(true);
        AddBackground();
    }

     */
    private MainMenuPanel(){
        AddBackground();
        startIcon = new ImageIcon("src/res/images/buttons/menuButtons/startButton.png");
        startButton = new JButton(startIcon);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.addActionListener(e ->{
                Game game = new Game();
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Main.getPanel());
                frame.requestFocus();
                frame.dispose();

                game.StartGame();
        });
        add(startButton);
        settingsIcon = new ImageIcon("src/res/images/buttons/settingsbutton/settings.png");
        settingsButton = new JButton(settingsIcon);
        settingsButton.setBorderPainted(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setFocusPainted(false);
        add(settingsButton);
        exitIcon = new ImageIcon("src/res/images/buttons/exitButton/exitbutton.png");
        exitButton = new JButton(exitIcon);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        add(exitButton);
    }
    public void AddBackground() {
        try {
            backgroundImage = ImageIO.read(new File("src/res/images/backgrounds/menubackground/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
