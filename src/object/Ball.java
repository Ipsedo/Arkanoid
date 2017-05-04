package object;

import java.util.Random;

public class Ball extends Item {

	public Ball(Random rand, int xMin, int xMax, int yMin, int yMax) {
		super(new float[]{xMin + rand.nextFloat() * xMax, yMin + rand.nextFloat() * yMin}, new float[]{rand.nextFloat(), rand.nextFloat()}, new float[]{0f, 0f}, 10, 10);
	}
	
	public Ball(float[] mPosition, float[] mSpeed, float[] mAcceleration) {
		super(mPosition.clone(), mSpeed.clone(), mAcceleration.clone(), 10, 10);
	}
	
	public void bounding(){
		if(super.mPosition[0] <= 0) {
			super.mSpeed[0] = -super.mSpeed[0];
			super.move(super.mAcceleration);
		}
		if(super.mPosition[1] <= 0) {
			super.mSpeed[1] = -super.mSpeed[1];
			super.move(super.mAcceleration);
		}
		if(super.mPosition[0] + super.width >= super.screenWidth) {
			super.mSpeed[0] = -super.mSpeed[0];
			super.move(super.mAcceleration);
		}
		if(super.mPosition[1] + super.height >= super.screenHeight){
			super.mSpeed[1] = -super.mSpeed[1];
			super.move(super.mAcceleration);
		}
	}
}
