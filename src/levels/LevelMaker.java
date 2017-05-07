package levels;

import game.MyJPanel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import object.Ball;
import object.Brick;

public class LevelMaker {

    /**
     * 
     * @param levelId
     * @param rand
     * @param jpanel
     * @return
     */
    public static List<Ball> getBallsFromLevelId(int levelId, Random rand, MyJPanel jpanel) {
	if (levelId == 0) {
	    int nbBall = 2;
	    List<Ball> res = Collections.synchronizedList(new ArrayList<Ball>());
	    for (int i = 0; i < nbBall; i++) {
		res.add(new Ball(rand, jpanel));
	    }
	    return res;
	} else {
	    List<Ball> res = Collections.synchronizedList(new ArrayList<Ball>());
	    res.add(new Ball(rand, jpanel));
	    res.add(new Ball(rand, jpanel));
	    return res;
	}
    }

    /**
     * 
     * @param levelId
     * @param jpanel
     * @return
     */
    public static List<Brick> getBricksFromLevelID(int levelId, MyJPanel jpanel) {
	List<Brick> res = Collections.synchronizedList(new ArrayList<Brick>());
	float milieu = 0.5f;
	if (levelId == 3) {
	    for (int i = -3; i < -1; i++) {
		for (int j = 0; j < 9; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f + (float) j * (Brick.height + 0.01f) };
		    float[] speed = new float[2];
		    float[] acc = new float[2];
		    res.add(new Brick(pos, speed, acc, jpanel, 1));
		}
	    }

	    for (int i = 1; i < 3; i++) {
		for (int j = 0; j < 9; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f + (float) j * (Brick.height + 0.01f) };
		    float[] speed = new float[2];
		    float[] acc = new float[2];
		    res.add(new Brick(pos, speed, acc, jpanel, 1));
		}
	    }

	    for (int i = -2; i < 2; i++) {
		for (int j = -1; j < 0; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f + (float) j * (Brick.height + 0.01f) };
		    float[] speed = new float[2];
		    float[] acc = new float[2];
		    res.add(new Brick(pos, speed, acc, jpanel, 1));
		}
	    }

	    for (int i = -2; i < 2; i++) {
		for (int j = 9; j < 10; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f + (float) j * (Brick.height + 0.01f) };
		    float[] speed = new float[2];
		    float[] acc = new float[2];
		    res.add(new Brick(pos, speed, acc, jpanel, 1));
		}
	    }
	} else if (levelId == 1) {
	    for (int i = -1; i < 2; i++) {
		for (int j = 0; j < 15; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f + (float) j * (Brick.height + 0.01f) };
		    float[] speed = new float[2];
		    float[] acc = new float[2];
		    res.add(new Brick(pos, speed, acc, jpanel, 2));
		}
	    }

	    for (int i = -3; i < 4; i++) {
		for (int j = 15; j < 17; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f + (float) j * (Brick.height + 0.01f) };
		    float[] speed = new float[2];
		    float[] acc = new float[2];
		    res.add(new Brick(pos, speed, acc, jpanel, 2));
		}
	    }

	    for (int i = -3; i < -1; i++) {
		for (int j = 1; j < 4; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f + (float) j * (Brick.height + 0.01f) };
		    float[] speed = new float[2];
		    float[] acc = new float[2];
		    res.add(new Brick(pos, speed, acc, jpanel, 2));
		}
	    }
	} else if (levelId == 2) {
	    double radius = 0.2d;
	    for (int i = 0; i < 24; i++) {
		double angle = (double) i * Math.PI * 2d / 24d;
		float[] pos = new float[] { milieu + (float) (radius * Math.cos(angle)), 0.25f + (float) (radius * Math.sin(angle)) };
		float[] speed = new float[2];
		float[] acc = new float[2];
		res.add(new Brick(pos, speed, acc, jpanel, 1));
	    }
	    radius = 0.1d;
	    for (int i = 0; i < 12; i++) {
		double angle = (double) i * Math.PI * 2d / 12d;
		float[] pos = new float[] { milieu + (float) (radius * Math.cos(angle)), 0.25f + (float) (radius * Math.sin(angle)) };
		float[] speed = new float[2];
		float[] acc = new float[2];
		res.add(new Brick(pos, speed, acc, jpanel, 2));
	    }
	} else if(levelId == 0) {
	    for (int i = 0; i < 20; i++) {
		float[] pos = new float[] { (float) i / 20f, 0.1f + (float) Math.cos((double) i * Math.PI * 2d / 20d) / 10f};
		float[] speed = new float[2];
		float[] acc = new float[2];
		res.add(new Brick(pos, speed, acc, jpanel, 2));
	    }
	    
	    for (int i = 0; i < 20; i++) {
		float[] pos = new float[] { (float) i / 20f, 0.4f + (float) -Math.cos((double) i * Math.PI * 2d / 20d) / 10f};
		float[] speed = new float[2];
		float[] acc = new float[2];
		res.add(new Brick(pos, speed, acc, jpanel, 2));
	    }
	    
	    double radius = 0.15d;
	    for (int i = 0; i < 16; i++) {
		double angle = (double) i * Math.PI * 2d / 16d;
		float[] pos = new float[] { milieu + (float) (radius * Math.cos(angle)), 0.25f + (float) (radius * Math.sin(angle)) };
		float[] speed = new float[2];
		float[] acc = new float[2];
		res.add(new Brick(pos, speed, acc, jpanel, 3));
	    }
	} else if (levelId == -1) {
	    float nb = 10;
	    float decalageWidth = 1f / (float) (nb * nb);

	    for (int i = 0; i < nb; i++) {
		for (int j = 0; j < nb; j++) {
		    float[] pos = new float[] { (float) i / nb + decalageWidth, 0.6f * (float) j / nb };
		    float[] speed = new float[2];
		    float[] acc = new float[2];
		    res.add(new Brick(pos, speed, acc, jpanel, 1));
		}
	    }
	}

	return res;
    }

}
