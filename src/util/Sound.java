package util;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

    public static void paddleSound() {
	Sound.playSound("paddle.wav");
    }

    public static void bonusSound() {
	Sound.playSound("bonus.wav");
    }

    public static void brickSound() {
	Sound.playSound("brick.wav");
    }

    private static void playSound(String file) {
	try {
	    AudioInputStream ais = AudioSystem.getAudioInputStream(new File(file));
            Clip test = AudioSystem.getClip();
            test.open(ais);
            test.start();
            test.drain();
            test.close();
	} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

}
