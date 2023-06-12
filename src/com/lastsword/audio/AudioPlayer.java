package com.lastsword.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * The type Audio player.
 */
public class AudioPlayer {
    private Clip clip;

    /**
     * Instantiates a new Audio player.
     *
     * @param path the path
     */
    public AudioPlayer(String path) {
        play(path);
    }

    private void play(String audioFilePath) {
        File audioFile = new File(audioFilePath);

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);

            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets loop.
     *
     * @param loop the loop
     */
    public void setLoop(boolean loop) {
        if (clip != null) {
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.loop(0);
            }
        }
    }

    /**
     * Play.
     */
    public void play() {
        if (clip != null && !clip.isRunning()) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    /**
     * Stop.
     */
    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
            clip = null;
        }
    }
}
