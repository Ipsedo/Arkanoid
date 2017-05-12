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
	Sound.playSound("./res/paddle.wav");
    }

    public static void bonusSound() {
	Sound.playSound("./res/bonus.wav");
    }

    public static void brickSound() {
	Sound.playSound("./res/brick.wav");
    }
    
    public static void mainSound() {
	Sound.playSound("./res/loop_funky_sam.wav");
    }

    private static void playSound(String file) {
	try {
	    File soundFile = new File(file);
	    AudioInputStream stream = AudioSystem.getAudioInputStream(soundFile);
	    AudioFormat format = stream.getFormat();
	    DataLine.Info info = new DataLine.Info(Clip.class, format);
	    Clip clip = (Clip) AudioSystem.getLine(info);
	    
	    clip.start();
	} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}