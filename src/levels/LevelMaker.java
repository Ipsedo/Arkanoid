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

	if (levelId == 1) {
	    float nb = 10;
	    float decalageWidth = 1f / (float) (nb * nb);

	    for (int i = 0; i < nb; i++) {
		for (int j = 0; j < nb; j++) {
		    float[] pos = new float[] { (float) i / nb + decalageWidth, 0.6f * (float) j / nb };
		    float[] speed = new float[2];
		    float[] acc = new float[2];
		    res.add(new Brick(pos, speed, acc, jpanel));
		}
	    }
	} else if (levelId == 0) {
	    /*float nb = 20;
	    float decalageWidth = 1f / (float) (nb * nb);

	    for (int i = 0; i < nb; i++) {
		for (int j = 0; j < nb; j++) {
		    float[] pos = new float[] { (float) i / nb + decalageWidth, 0.6f * (float) j / nb };
		    float[] speed = new float[2];
		    float[] acc = new float[2];
		    res.add(new Brick(pos, speed, acc, jpanel));
		}
	    }*/
	    
	    float milieu = 0.5f;
	    for(int i = -1; i < 2; i++) {
		for (int j = 0; j < 15; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f) , 0.1f + (float) j * (Brick.height + 0.01f) };
		    float[] speed = new float[2];
		    float[] acc = new float[2];
		    res.add(new Brick(pos, speed, acc, jpanel));
		}
	    }
	}

	return res;
    }

}
