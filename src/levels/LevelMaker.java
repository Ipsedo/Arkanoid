package levels;

import game.MyJPanel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    public static List<Ball> getBallsFromLevelId(int levelId, Random rand, MyJPanel jpanel) {
	if (levelId == 0) {
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
	return BrickInitializator.initBrickRandom(levelId, jpanel);
    }

}
