package thread;

import java.util.List;

import object.Particule;

public class PointsAction extends CancelableThread {

    private List<Particule> points;

    public PointsAction(List<Particule> points) {
	super("PointsAction");
	this.points = points;
    }

    public void run() {
	while (!super.canceled) {
	    for (int i = this.points.size() - 1; i >= 0; i--) {
		this.points.get(i).move();
		if (!this.points.get(i).isAlive()) {
		    this.points.remove(i);
		}
	    }
	    try {
		Thread.sleep((long) CancelableThread.TIME_TO_WAIT);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

}
