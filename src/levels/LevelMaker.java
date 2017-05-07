package levels;

import java.util.ArrayList;
import java.util.Random;

import game.MyJPanel;
import object.Ball;
import object.Brick;
import util.BrickInitializator;

public class LevelMaker {
    
    private static int nbBall = 2;
    
    /**
     * 
     * @param levelId
     * @param rand
     * @param jpanel
     * @return
     */
    public static ArrayList<Ball> getBallsFromLevelId(int levelId, Random rand, MyJPanel jpanel) {
	if (levelId == 0) {
	    ArrayList<Ball> res = new ArrayList<Ball>();
	    for (int i = 0; i < nbBall; i++) {
		res.add(new Ball(rand, jpanel));
	    }
	    return res;
	} else {
	    ArrayList<Ball> res = new ArrayList<Ball>();
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
    public static ArrayList<Brick> getBricksFromLevelID(int levelId, MyJPanel jpanel) {
	if (levelId == 0) {
	    return BrickInitializator.initBrickRandom(jpanel, 10);
	} else if (levelId == 1) {
	    return BrickInitializator.initBrickRandom(jpanel, 100);
	} else {
	    return new ArrayList<>();
	}
    }

}
