package util;

import java.io.File;
import java.io.FileInputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

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
	    AudioInputStream stream;
	    AudioFormat format;
	    DataLine.Info info;
	    Clip clip;

	    stream = AudioSystem.getAudioInputStream(new File(file));
	    format = stream.getFormat();
	    info = new DataLine.Info(Clip.class, format);
	    clip = (Clip) AudioSystem.getLine(info);
	    clip.open(stream);
	    clip.start();
	} catch (Exception e) {
	    // whatevers
	    e.printStackTrace();
	}
    }

}
