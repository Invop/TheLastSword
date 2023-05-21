package com.lastsword.utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetFrames {
    private String filePath;

    public GetFrames(String filePath){
        this.filePath=filePath;
    }

    public List<BufferedImage> FramesToList(){
        try {
            BufferedImage spriteSheet = ImageIO.read(new File(filePath)); // Завантаження спрайт-аркуша

            List<BufferedImage> frames = extractFrames(spriteSheet);

            // Виведення отриманих кадрів
            for (int i = 0; i < frames.size(); i++) {
                BufferedImage frame = frames.get(i);
                System.out.println("Кадр " + (i + 1) + ": ширина=" + frame.getWidth() + ", висота=" + frame.getHeight());
            }

            return frames;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<BufferedImage> scaleImages(List<BufferedImage> images, double scale) {
        List<BufferedImage> scaledImages = new ArrayList<>();

        for (BufferedImage image : images) {
            double scaledWidth = image.getWidth() * scale;
            double scaledHeight = image.getHeight() * scale;

            BufferedImage scaledImage = new BufferedImage((int)scaledWidth, (int)scaledHeight, BufferedImage.TYPE_INT_ARGB);

            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            AffineTransform transform = AffineTransform.getScaleInstance(scale, scale);
            graphics2D.drawImage(image, transform, null);
            graphics2D.dispose();

            scaledImages.add(scaledImage);
        }

        return scaledImages;
    }
    private static List<BufferedImage> extractFrames(BufferedImage spriteSheet) {
        List<BufferedImage> frames = new ArrayList<>();

        int width = spriteSheet.getWidth();
        int height = spriteSheet.getHeight();

        boolean isTransparentBackground = hasTransparentBackground(spriteSheet);

        int startX = 0;
        int currentFrameWidth = 0;

        for (int x = 0; x < width; x++) {
            boolean isFrameEnd = isTransparentBackground ?
                    isTransparentColumn(spriteSheet, x) :
                    isSameColorColumn(spriteSheet, x, spriteSheet.getRGB(x, 0));

            if (isFrameEnd) {
                if (currentFrameWidth > 0) {
                    BufferedImage frame = spriteSheet.getSubimage(startX, 0, currentFrameWidth, height);
                    frames.add(frame);
                }

                startX = x + 1;
                currentFrameWidth = 0;
            } else {
                currentFrameWidth++;
            }
        }

        if (currentFrameWidth > 0) {
            BufferedImage frame = spriteSheet.getSubimage(startX, 0, currentFrameWidth, height);
            frames.add(frame);
        }

        return frames;
    }
    private static boolean hasTransparentBackground(BufferedImage image) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if ((image.getRGB(x, y) & 0xFF000000) != 0) {
                    return false;
                }
            }
        }
        return true;
    }
    private static boolean isTransparentColumn(BufferedImage image, int x) {
        for (int y = 0; y < image.getHeight(); y++) {
            if ((image.getRGB(x, y) & 0xFF000000) != 0) {
                return false;
            }
        }
        return true;
    }
    private static boolean isSameColorColumn(BufferedImage image, int x, int referenceColor) {
        for (int y = 0; y < image.getHeight(); y++) {
            if (image.getRGB(x, y) != referenceColor) {
                return false;
            }
        }
        return true;
    }
}
