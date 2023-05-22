package com.lastsword.graphics;

import javax.swing.*;
import java.awt.*;

public class ButtonRenderer {

    private int[] letterValues;

    public ButtonRenderer(int[] letterValues) {
        this.letterValues = letterValues;
    }

    public void draw(Graphics g, int x, int y) {
        for (int value : letterValues) {
            String imageName = "src/res/images/buttons/individual_icons/keyboard_" + value + ".png";
            Image image = new ImageIcon(imageName).getImage();
            g.drawImage(image, x, y, null);

            x += image.getWidth(null);
        }
    }
}
