package object;

import java.util.Random;

import util.Vector;

public class Ball extends Item {

	public Ball(Random rand, int xMin, int xMax, int yMin, int yMax, int screenW, int screenH) {
		super(new float[]{xMin + rand.nextFloat() * (xMax - 10) + 10,
						yMin + rand.nextFloat() * (yMax - 10) + 10},
						Vector.normalize2f(new float[]{rand.nextFloat(), rand.nextFloat()}),
						new float[]{0f, 0f},
						10,
						10,
						screenW, 
						screenH);
		System.out.println(screenW + ", " + screenH);
	}
	
	public Ball(float[] mPosition, float[] mSpeed, float[] mAcceleration, int screenW, int screenH) {
		super(mPosition.clone(), mSpeed.clone(), mAcceleration.clone(), 10, 10, screenW, screenH);
	}
	
	public void bounding(){
		if(super.mPosition[0] <= 0) {
			super.mSpeed[0] = Math.abs(super.mSpeed[0]);
		}
		if(super.mPosition[1] <= 0) {
			super.mSpeed[1] = Math.abs(super.mSpeed[1]);
		}
		if(super.mPosition[0] + super.width >= super.screenWidth) {
			super.mSpeed[0] = -Math.abs(super.mSpeed[0]);
		}
		if(super.mPosition[1] >= super.screenHeight){
			super.mSpeed[1] = -Math.abs(super.mSpeed[1]);
		}
	}
}
