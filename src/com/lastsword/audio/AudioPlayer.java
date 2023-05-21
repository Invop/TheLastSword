package com.lastsword.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {

    public AudioPlayer(String path) {
        play(path);
    }

    private void play(String audioFilePath) {
        File audioFile = new File(audioFilePath);

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);


            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);


            float initialVolume = 0.2f; // 50%
            setVolume(gainControl, initialVolume);

            clip.start();

            // Sleep while the audio is playing
            Thread.sleep(clip.getMicrosecondLength() / 1000);
            clip.stop();
            clip.close();
            audioStream.close();

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void setVolume(FloatControl gainControl, float volume) {
        float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
    }
}
