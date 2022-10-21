package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    public static final int BG_MUSIC = 0;
    public static final int COIN = 1;
    public static final int POWER_UP = 2;
    public static final int UNLOCK = 3;
    public static final int FANFARE = 4;
    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound() {
        soundURL[BG_MUSIC] = getClass().getResource("/sound/BlueBoyAdventure.wav");
        soundURL[COIN] = getClass().getResource("/sound/coin.wav");
        soundURL[POWER_UP] = getClass().getResource("/sound/powerup.wav");
        soundURL[UNLOCK] = getClass().getResource("/sound/unlock.wav");
        soundURL[FANFARE] = getClass().getResource("/sound/fanfare.wav");
    }

    public void setFile(int index) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[index]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
