package object;

import game.MyJPanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import util.Vector;

public class Point {
    
    private float[] mPosition;
    private float[] mSpeed;
    private MyJPanel jpanel;
    
    public Point(float[] mPosition, Random rand, MyJPanel jpanel) {
	this.mPosition = mPosition;
	double angle = rand.nextDouble();
	this.mSpeed = new float[]{0.03f * (float) Math.cos(angle), 0.03f * (float) Math.sin(angle)};
	this.jpanel = jpanel;
    }
    
    public void move() {
	this.mPosition[0] += this.mSpeed[0];
	this.mPosition[1] += this.mSpeed[1];
	this.mSpeed[0] /= 2f;
	this.mSpeed[1] /= 2f;
    }
    
    public boolean isAlive() {
	return Vector.length2f(this.mPosition) > 0.01f;
    }
    
    public void draw(Graphics2D g2) {
	g2.setColor(Color.RED);
	g2.fillOval((int) (this.mPosition[0] * this.jpanel.getWidth()), (int) (this.mPosition[1] * this.jpanel.getHeight()), (int) (0.005f * this.jpanel.getWidth()),  (int) (0.005f * this.jpanel.getHeight()));	
    }

}
