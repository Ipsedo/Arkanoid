package util;

import java.util.ArrayList;
import java.util.Random;

import game.MyJPanel;
import object.Brick;

public class BrickInitializator {

    public static ArrayList<Brick> initBrickFromFile() {
	return new ArrayList<>();
    }

    public static ArrayList<Brick> initBrickRandom(int levelID, MyJPanel jpanel) {
	ArrayList<Brick> res = new ArrayList<>();

	float nb = 10;
	for (float i = 1f / ((float) nb*2f + 1f); i < 0.7f; i+= 1f / ((float) nb*2f + 1f)) {
	    for (float j = 1f / ((float) nb + 1f); j < 1f - (1f / ((float) nb + 1f)); j+= 1f / ((float) nb + 1f)) {
		float[] pos = new float[] { j, i };
		float[] speed = new float[2];
		float[] acc = new float[2];
		res.add(new Brick(pos, speed, acc, jpanel));
	    }
	}

	return res;
    }

}
