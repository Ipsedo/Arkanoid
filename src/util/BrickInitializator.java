package util;

import game.MyJPanel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import object.Brick;

public class BrickInitializator {

    public static List<Brick> initBrickFromFile() {
	List<Brick> res = Collections.synchronizedList(new ArrayList<Brick>());
	return res;
    }

    public static List<Brick> initBrickRandom(int levelId, MyJPanel jpanel) {
	List<Brick> res = Collections.synchronizedList(new ArrayList<Brick>());

	if (levelId == 0) {
	    float nb = 10;
	    /*for (float i = 1f / (float) nb; i < 0.7f; i += 1f / (float) nb) {
		for (float j = 1f / (float) nb; j < 1f - (1f / (float) nb); j += 1f / (float) nb) {
		    float[] pos = new float[] { j, i };
		    float[] speed = new float[2];
		    float[] acc = new float[2];
		    res.add(new Brick(pos, speed, acc, jpanel));
		}
	    }*/
	    float decalageWidth = 1f / (float) (nb * nb);
	    for(int i = 0; i < nb; i++) {
		for(int j = 0; j < nb; j++) {
		    float[] pos = new float[] { (float) i / (float) nb + decalageWidth, (float) j / (float) nb};
		    float[] speed = new float[2];
		    float[] acc = new float[2];
		    res.add(new Brick(pos, speed, acc, jpanel));
		}
	    }
	} else if (levelId == 1) {
	    float nb = 10;
	    for (float i = 1f / ((float) nb * 2f); i < 0.7f; i += 1f / ((float) nb * 2f)) {
		for (float j = (1f / 2f) - (1f / ((float) nb)); j < (1f / 2f) - (1f / ((float) nb)); j += 1f / ((float) nb)) {
		    float[] pos = new float[] { j, i };
		    float[] speed = new float[2];
		    float[] acc = new float[2];
		    res.add(new Brick(pos, speed, acc, jpanel));
		}
	    }
	}

	return res;
    }

}
