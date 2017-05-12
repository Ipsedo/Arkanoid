package thread;

import java.util.List;

import object.Particule;

public class ExplosionAction extends CancelableThread {

    private List<Particule> points;

    /**
     * ExplosionAction : Thread permettant l'animation des explosions ainsi que
     * leur suppression
     * 
     * @param points
     *            La liste de particules
     */
    public ExplosionAction(List<Particule> points) {
	super("PointsAction");
	this.points = points;
    }

    /**
     * On fait bouger les particules et si elles sont "mortes", on les supprime
     */
    public void run() {
	while (!super.canceled) {
	    synchronized (this.points) {
		for (int i = this.points.size() - 1; i >= 0; i--) {
		    this.points.get(i).move();
		    if (!this.points.get(i).isAlive()) {
			this.points.remove(i);
		    }
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
