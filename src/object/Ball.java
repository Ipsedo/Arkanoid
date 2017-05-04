package object;

import java.util.Random;

public class Ball extends Item {

	public Ball(Random rand, int xMin, int xMax, int yMin, int yMax) {
		super(new float[]{xMin + rand.nextFloat() * xMax, yMin + rand.nextFloat() * yMin}, new float[]{rand.nextFloat(), rand.nextFloat()}, new float[]{0f, 0f}, 10, 10);
	}
}
