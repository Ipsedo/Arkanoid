package util;

import java.util.ArrayList;
import java.util.Random;

import game.MyJPanel;
import object.Brick;

public class BrickInitializator {

    public static ArrayList<Brick> initBrickFromFile() {
	return new ArrayList<>();
    }

    public static ArrayList<Brick> initBrickRandom(MyJPanel jpanel, int nb) {
	Random rand = new Random(System.currentTimeMillis());
	ArrayList<Brick> res = new ArrayList<>();

	for (int i = 0; i < nb; i++) {
	    float[] pos = new float[] { rand.nextFloat(), rand.nextFloat() * 0.5f };
	    float[] speed = new float[2];
	    float[] acc = new float[2];
	    res.add(new Brick(pos, speed, acc, jpanel));
	}

	return res;
    }

}
