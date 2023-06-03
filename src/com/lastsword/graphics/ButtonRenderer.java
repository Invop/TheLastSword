package com.lastsword.graphics;

import javax.swing.*;
import java.awt.*;

public class ButtonRenderer {

    private final int[] letterValues;
    private final Image[] images;

    public ButtonRenderer(int[] letterValues) {
        this.letterValues = letterValues;
        this.images = loadImages();
    }

    private Image[] loadImages() {
        Image[] images = new Image[letterValues.length];
        for (int i = 0; i < letterValues.length; i++) {
            String imageName = "src/res/images/buttons/individual_icons/keyboard_" + (letterValues[i]) + ".png";
            images[i] = new ImageIcon(imageName).getImage();
        }
        return images;
    }

    public void draw(Graphics g, int x, int y) {
        for (int i = 0; i < letterValues.length; i++) {
            Image image = images[i];
            g.drawImage(image, x, y, null);

            x += image.getWidth(null);
        }
    }

    public void updateImage(int index) {
        if (index >= 0 && index < images.length) {
            String imageName = "src/res/images/buttons/individual_icons/keyboard_" + (letterValues[index] + 101) + ".png";
            images[index] = new ImageIcon(imageName).getImage();
        }
    }
}