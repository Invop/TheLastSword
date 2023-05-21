package com.lastsword.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    private Clip clip;
    private boolean isPlaying;

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

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            float initialVolume = 0.3f; // 20%
            setVolume(gainControl, initialVolume);

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    if (isPlaying) {
                        clip.setMicrosecondPosition(0);
                        clip.start();
                    }
                }
            });

            isPlaying = true;
            clip.start();

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void loop() {
        if (clip != null) {
            if (!isPlaying) {
                isPlaying = true;
                clip.setMicrosecondPosition(0);
                clip.start();
            }
        }
    }

    public void stop() {
        if (clip != null) {
            isPlaying = false;
            clip.stop();
            clip.close();
        }
    }

    private void setVolume(FloatControl gainControl, float volume) {
        float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
    }
}
