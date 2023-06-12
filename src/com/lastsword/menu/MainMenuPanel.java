package com.lastsword.menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainMenuPanel extends JPanel {
    private static CharacterSelectionMenuPanel selectionMenuPanel;
    private static SettingsPanel settingsPanel;
    private Image backgroundImage;
    private int difficultyLevel;

    public MainMenuPanel() {
        setLayout(new BorderLayout());
        setSize(1280, 720);
        setFocusable(true);
        AddBackground();
        addButtons();

    }

    public static JPanel getPanel() {
        return selectionMenuPanel;
    }

    private void AddBackground() {
        try {
            backgroundImage = ImageIO.read(new File("src/res/images/backgrounds/menubackground/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addButtons() {
        try {
            // Button 1
            Image PlayBtnImage = ImageIO.read(new File("src/res/images/buttons/play/72px/play01.png"));
            ImageIcon PlayBtnIcon = new ImageIcon(PlayBtnImage);
            JButton PlayBtn = new JButton(PlayBtnIcon);
            PlayBtn.setBorderPainted(false);
            PlayBtn.setContentAreaFilled(false);
            PlayBtn.addActionListener(e -> {
                //action on click
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.requestFocus();
                frame.dispose();
                selectionMenuPanel = new CharacterSelectionMenuPanel();
                selectionMenuPanel.setDifficultyLevel(difficultyLevel);
                System.out.println(difficultyLevel);
                CharaterSelectionMenuWindow selectionMenuWindow = new CharaterSelectionMenuWindow(selectionMenuPanel);
            });
            // Button 2
            Image OptionsBtnImage = ImageIO.read(new File("src/res/images/buttons/option/72px/option01.png"));
            ImageIcon OptionsBtnIcon = new ImageIcon(OptionsBtnImage);
            JButton OptionsBtn = new JButton(OptionsBtnIcon);
            OptionsBtn.setBorderPainted(false);
            OptionsBtn.setContentAreaFilled(false);
            OptionsBtn.addActionListener(e -> {
                //action on click
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.requestFocus();
                frame.dispose();
                settingsPanel = new SettingsPanel();
                SettingsWindow settingsWindow = new SettingsWindow(settingsPanel);
            });

            JPanel buttonPanel = new JPanel(new GridBagLayout());
            buttonPanel.setOpaque(false);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(0, 0, 20, 0); // Adjust top margin of button 1

            buttonPanel.add(PlayBtn, gbc);

            gbc.gridy = 1;
            gbc.insets = new Insets(20, 0, 0, 0); // Adjust top margin of button 2

            buttonPanel.add(OptionsBtn, gbc);

            add(buttonPanel, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }
}
