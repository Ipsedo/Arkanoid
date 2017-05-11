package util;

import java.io.IOException;
import java.net.URL;

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
	/*try {
	        DataLine.Info daInfo = new DataLine.Info(Clip.class, null);
	        try {
	            URL sounURL = Sound.class.getResource("/home/samuel/Documents/L3/S6/Projet_IHM_Concurentielle/Arkanoid/" + file);

	            AudioInputStream inputStream = AudioSystem.getAudioInputStream(sounURL);
	            DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
	            Clip clip = (Clip) AudioSystem.getLine(info);
	            clip.open(inputStream);
	            clip.start();
	        } catch (LineUnavailableException e) {
	            e.printStackTrace();
	        } catch (UnsupportedAudioFileException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }*/

    }
}
