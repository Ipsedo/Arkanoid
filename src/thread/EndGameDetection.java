package thread;

import java.util.ArrayList;

import game.MyJPanel;
import object.Ball;
import object.Brick;

public class EndGameDetection extends CancelableThread {

	private ArrayList<Ball> balls;
	private ArrayList<Brick> bricks;
	private MyJPanel jpanel;
	
	public EndGameDetection(ArrayList<Ball> balls, ArrayList<Brick> bricks, MyJPanel jpanel){
		super("EndGameDetection");
		this.balls = balls;
		this.jpanel = jpanel;
	}
	
	public void run() {
		while(!this.canceled) {
			if(this.balls.isEmpty()) {
				synchronized (this.jpanel) {
					this.jpanel.setDead(true);
				}
			} else if(this.bricks.isEmpty()) {
				synchronized (this.jpanel) {
					this.jpanel.setWinner(true);
				}
			}
			try {
				Thread.sleep(10L);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}
