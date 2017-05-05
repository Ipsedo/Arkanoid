package levels;

import java.util.ArrayList;
import java.util.Random;

import game.MyJPanel;
import object.Ball;
import object.Brick;
import util.BrickInitializator;

public class LevelMaker {
	
	public static ArrayList<Ball> getBallsFromLevelId(int levelId, Random rand, MyJPanel jpanel){
		if(levelId == 0) {
			ArrayList<Ball> res = new ArrayList<Ball>();
			res.add(new Ball(rand, jpanel));
			return res;
		} else {
			ArrayList<Ball> res = new ArrayList<Ball>();
			res.add(new Ball(rand, jpanel));
			res.add(new Ball(rand, jpanel));
			return res;
		}
	}
	
	public static ArrayList<Brick> getBricksFromLevelID(int levelId, MyJPanel jpanel) {
		if(levelId == 0) {
			return BrickInitializator.initBrickRandom(jpanel, 50);
		} else if(levelId == 1){
			return BrickInitializator.initBrickRandom(jpanel, 100);
		} else {
			return new ArrayList<>();
		}
	}

}
