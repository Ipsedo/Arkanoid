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
	    int nbBall = 3;
	    List<Ball> res = Collections.synchronizedList(new ArrayList<Ball>());
	    for (int i = 0; i < nbBall; i++) {
		res.add(new Ball(rand, jpanel));
	    }
	    return res;
	} else if (levelId == 1) {
	    int nbBall = 2;
	    List<Ball> res = Collections.synchronizedList(new ArrayList<Ball>());
	    for (int i = 0; i < nbBall; i++) {
		res.add(new Ball(rand, jpanel));
	    }
	    return res;
	} else if (levelId == 2) {
	    int nbBall = 1;
	    List<Ball> res = Collections.synchronizedList(new ArrayList<Ball>());
	    for (int i = 0; i < nbBall; i++) {
		res.add(new Ball(rand, jpanel));
	    }
	    return res;
	} else {
	    int nbBall = 1;
	    List<Ball> res = Collections.synchronizedList(new ArrayList<Ball>());
	    for (int i = 0; i < nbBall; i++) {
		res.add(new Ball(rand, jpanel));
	    }
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
	float[] speed = new float[2];
	float[] acc = new float[2];

	if (levelId == 0) {
	    for (int i = -3; i < -1; i++) {
		for (int j = 0; j < 9; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f + (float) j * (Brick.height + 0.01f) };
		    res.add(new Brick(pos, speed, acc, jpanel, 1));
		}
	    }

	    for (int i = 1; i < 3; i++) {
		for (int j = 0; j < 9; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f + (float) j * (Brick.height + 0.01f) };
		    res.add(new Brick(pos, speed, acc, jpanel, 1));
		}
	    }

	    for (int i = -2; i < 2; i++) {
		for (int j = -1; j < 0; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f + (float) j * (Brick.height + 0.01f) };
		    res.add(new Brick(pos, speed, acc, jpanel, 1));
		}
	    }

	    for (int i = -2; i < 2; i++) {
		for (int j = 9; j < 10; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f + (float) j * (Brick.height + 0.01f) };
		    res.add(new Brick(pos, speed, acc, jpanel, 1));
		}
	    }
	} else if (levelId == 1) {
	    for (int i = -1; i < 2; i++) {
		for (int j = 0; j < 15; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f + (float) j * (Brick.height + 0.01f) };
		    res.add(new Brick(pos, speed, acc, jpanel, 2));
		}
	    }

	    for (int i = -3; i < 4; i++) {
		for (int j = 15; j < 17; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f + (float) j * (Brick.height + 0.01f) };
		    res.add(new Brick(pos, speed, acc, jpanel, 2));
		}
	    }

	    for (int i = -3; i < -1; i++) {
		for (int j = 1; j < 4; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f + (float) j * (Brick.height + 0.01f) };
		    res.add(new Brick(pos, speed, acc, jpanel, 2));
		}
	    }
	} else if (levelId == 2) {
	    double radius = 0.2d;
	    for (int i = 0; i < 24; i++) {
		double angle = (double) i * Math.PI * 2d / 24d;
		float[] pos = new float[] { milieu + (float) (radius * Math.cos(angle)), 0.25f + (float) (radius * Math.sin(angle)) };
		res.add(new Brick(pos, speed, acc, jpanel, 1));
	    }
	    radius = 0.1d;
	    for (int i = 0; i < 12; i++) {
		double angle = (double) i * Math.PI * 2d / 12d;
		float[] pos = new float[] { milieu + (float) (radius * Math.cos(angle)), 0.25f + (float) (radius * Math.sin(angle)) };
		res.add(new Brick(pos, speed, acc, jpanel, 2));
	    }
	} else if (levelId == 3) {
	    for (int i = 0; i < 20; i++) {
		float[] pos = new float[] { (float) i / 20f, 0.1f + (float) Math.cos((double) i * Math.PI * 2d / 20d) / 10f };
		res.add(new Brick(pos, speed, acc, jpanel, 2));
	    }

	    for (int i = 0; i < 20; i++) {
		float[] pos = new float[] { (float) i / 20f, 0.4f + (float) -Math.cos((double) i * Math.PI * 2d / 20d) / 10f };
		res.add(new Brick(pos, speed, acc, jpanel, 2));
	    }

	    double radius = 0.15d;
	    for (int i = 0; i < 16; i++) {
		double angle = (double) i * Math.PI * 2d / 16d;
		float[] pos = new float[] { milieu + (float) (radius * Math.cos(angle)), 0.25f + (float) (radius * Math.sin(angle)) };
		res.add(new Brick(pos, speed, acc, jpanel, 3));
	    }
	} else if (levelId == 5) {
	    float h = 0.13f;
	    for (int i = -3; i < 4; i++) {
		for (int j = 0; j < 8; j++) {
		    if ((i == -2 && j == 2) || (i == -2 && j == 3) || (i == 2 && j == 2) || (i == 2 && j == 3)) {
			float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h + (float) j * (Brick.height + 0.01f) };
			res.add(new Brick(pos, speed, acc, jpanel, 3));
		    } else {
			float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h + (float) j * (Brick.height + 0.01f) };
			res.add(new Brick(pos, speed, acc, jpanel, 1));
		    }
		}
	    }
	    for (int j = 2; j < 7; j++) {
		float[] pos = new float[] { milieu + (float) (-4) * (Brick.width + 0.01f), h + (float) j * (Brick.height + 0.01f) };
		float[] pos1 = new float[] { milieu + (float) 4 * (Brick.width + 0.01f), h + (float) j * (Brick.height + 0.01f) };
		res.add(new Brick(pos, speed, acc, jpanel, 1));
		res.add(new Brick(pos1, speed, acc, jpanel, 1));
	    }
	    for (int j = 4; j < 10; j++) {
		float[] pos = new float[] { milieu + (float) (-5) * (Brick.width + 0.01f), h + (float) j * (Brick.height + 0.01f) };
		float[] pos1 = new float[] { milieu + (float) 5 * (Brick.width + 0.01f), h + (float) j * (Brick.height + 0.01f) };
		res.add(new Brick(pos, speed, acc, jpanel, 2));
		res.add(new Brick(pos1, speed, acc, jpanel, 2));
	    }
	    for (int j = 8; j < 10; j++) {
		float[] pos = new float[] { milieu + (float) (-3) * (Brick.width + 0.01f), h + (float) j * (Brick.height + 0.01f) };
		float[] pos1 = new float[] { milieu + (float) 3 * (Brick.width + 0.01f), h + (float) j * (Brick.height + 0.01f) };
		res.add(new Brick(pos, speed, acc, jpanel, 1));
		res.add(new Brick(pos1, speed, acc, jpanel, 1));
	    }
	    for (int k = -2; k < 0; k++) {
		for (int j = 10; j < 12; j++) {
		    float[] pos = new float[] { milieu + (float) k * (Brick.width + 0.01f), h + (float) j * (Brick.height + 0.01f) };
		    float[] pos1 = new float[] { milieu + (float) (-k) * (Brick.width + 0.01f), h + (float) j * (Brick.height + 0.01f) };
		    res.add(new Brick(pos, speed, acc, jpanel, 2));
		    res.add(new Brick(pos1, speed, acc, jpanel, 2));
		}
	    }
	    for (int j = -2; j < 0; j++) {
		float[] pos = new float[] { milieu + (float) 2 * (Brick.width + 0.01f), h + (float) j * (Brick.height + 0.01f) };
		float[] pos1 = new float[] { milieu + (float) (-2) * (Brick.width + 0.01f), h + (float) j * (Brick.height + 0.01f) };
		res.add(new Brick(pos, speed, acc, jpanel, 2));
		res.add(new Brick(pos1, speed, acc, jpanel, 2));
	    }
	    for (int j = -4; j < -2; j++) {
		float[] pos = new float[] { milieu + (float) 3 * (Brick.width + 0.01f), h + (float) j * (Brick.height + 0.01f) };
		float[] pos1 = new float[] { milieu + (float) (-3) * (Brick.width + 0.01f), h + (float) j * (Brick.height + 0.01f) };
		res.add(new Brick(pos, speed, acc, jpanel, 2));
		res.add(new Brick(pos1, speed, acc, jpanel, 2));
	    }

	} else if (levelId == 4) {
	    float h = 0.13f;

	    for (int i = -3; i < 3; i++) {
		for (int j = 0; j < 8; j++) {
		    if ((i == -2 && j == 3) || (i == -2 && j == 4) || (i == 1 && j == 3) || (i == 1 && j == 4) || (i == -3 && j == 3) || (i == -3 && j == 4) || (i == 2 && j == 3) || (i == 2 && j == 4) || (i == -3 && j == 5) || (i == 2 && j == 5)) {
			float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h + (float) j * (Brick.height + 0.01f) };
			res.add(new Brick(pos, speed, acc, jpanel, 3));
		    } else {
			float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h + (float) j * (Brick.height + 0.01f) };
			res.add(new Brick(pos, speed, acc, jpanel, 1));
		    }
		}
	    }
	    for (int j = 1; j < 7; j++) {
		float[] pos = new float[] { milieu + (float) 3 * (Brick.width + 0.01f), h + (float) j * (Brick.height + 0.01f) };
		float[] pos1 = new float[] { milieu + (float) (-4) * (Brick.width + 0.01f), h + (float) j * (Brick.height + 0.01f) };
		res.add(new Brick(pos, speed, acc, jpanel, 1));
		res.add(new Brick(pos1, speed, acc, jpanel, 1));
	    }
	    for (int i = -2; i < 2; i++) {
		float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h + (float) (-1) * (Brick.height + 0.01f) };
		float[] pos1 = new float[] { milieu + (float) i * (Brick.width + 0.01f), h + (float) (-1) * (Brick.height + 0.01f) };
		res.add(new Brick(pos, speed, acc, jpanel, 1));
		res.add(new Brick(pos1, speed, acc, jpanel, 1));
	    }
	    for (int i = -2; i < 2; i++) {
		float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h + (float) 9 * (Brick.height + 0.01f) };
		float[] pos1 = new float[] { milieu + (float) i * (Brick.width + 0.01f), h + (float) 9 * (Brick.height + 0.01f) };
		res.add(new Brick(pos, speed, acc, jpanel, 1));
		res.add(new Brick(pos1, speed, acc, jpanel, 1));
	    }
	    for (int i = -2; i < 2; i++) {
		for (int j = 11; j < 13; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h + (float) j * (Brick.height + 0.01f) };
		    res.add(new Brick(pos, speed, acc, jpanel, 1));
		}
	    }
	    float[] pos = new float[] { milieu + (float) (-2) * (Brick.width + 0.01f), h + (float) 8 * (Brick.height + 0.01f) };
	    float[] pos1 = new float[] { milieu + (float) 1 * (Brick.width + 0.01f), h + (float) 8 * (Brick.height + 0.01f) };
	    float[] pos2 = new float[] { milieu + (float) (-1) * (Brick.width + 0.01f), h + (float) 8 * (Brick.height + 0.01f) };
	    float[] pos3 = new float[] { milieu + (float) 0 * (Brick.width + 0.01f), h + (float) 8 * (Brick.height + 0.01f) };
	    res.add(new Brick(pos, speed, acc, jpanel, 1));
	    res.add(new Brick(pos1, speed, acc, jpanel, 1));
	    res.add(new Brick(pos2, speed, acc, jpanel, 3));
	    res.add(new Brick(pos3, speed, acc, jpanel, 3));

	} else if (levelId > 4) {
	    float nb = 10;
	    float decalageWidth = 1f / (float) (nb * nb);

	    for (int i = 0; i < nb; i++) {
		for (int j = 0; j < nb; j++) {
		    float[] pos = new float[] { (float) i / nb + decalageWidth, 0.6f * (float) j / nb };
		    res.add(new Brick(pos, speed, acc, jpanel, 1));
		}
	    }
	}

	return res;
    }

}
