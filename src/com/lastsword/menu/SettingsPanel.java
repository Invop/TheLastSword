package com.lastsword.menu;

import com.lastsword.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class SettingsPanel extends JPanel {
    private Image backgroundImage;
    private static int value;

    public SettingsPanel() {
        setLayout(new BorderLayout());
        setSize(1280, 720);
        setFocusable(true);
        addBackground();
        addSlider();
        addButton();
    }

    private void addBackground() {
        try {
            backgroundImage = ImageIO.read(new File("src/res/images/backgrounds/menubackground/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addSlider() {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 2, 1);
        slider.setOpaque(false);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(1);
        slider.setPaintLabels(true);
        slider.setPreferredSize(new Dimension(640, slider.getPreferredSize().height));

        slider.setLabelTable(slider.createStandardLabels(1));
        slider.setLabelTable(slider.createStandardLabels(2));
        slider.setLabelTable(slider.createStandardLabels(3));

        slider.setLabelTable(createSliderLabels());
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                value = slider.getValue();
            }
        });
        JPanel sliderPanel = new JPanel(new BorderLayout());
        sliderPanel.setOpaque(false);
        sliderPanel.add(slider, BorderLayout.CENTER);

        add(sliderPanel, BorderLayout.CENTER);
    }

    private Hashtable<Integer, JLabel> createSliderLabels() {
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel("Легкий"));
        labelTable.put(1, new JLabel("Середній"));
        labelTable.put(2, new JLabel("Складний"));
        return labelTable;
    }
    private void addButton() {
        try {
            Image BackBtnImage = ImageIO.read(new File("src/res/images/buttons/back/72px/back01.png"));
            ImageIcon BackBtnIcon = new ImageIcon(BackBtnImage);
            JButton BackBtn = new JButton(BackBtnIcon);
            BackBtn.setBorderPainted(false);
            BackBtn.setContentAreaFilled(false);
            BackBtn.addActionListener(e -> {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                frame.requestFocus();
                frame.dispose();
                MainMenuPanel mainMenuPanel = new MainMenuPanel();
                mainMenuPanel.setDifficultyLevel(value);
                MainMenuWindow menuWindow = new MainMenuWindow(mainMenuPanel);
            });

            JPanel BackBtnPanel = new JPanel(new BorderLayout());
            BackBtnPanel.setOpaque(false);
            BackBtnPanel.add(BackBtn, BorderLayout.WEST);

            add(BackBtnPanel, BorderLayout.SOUTH);
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
}
