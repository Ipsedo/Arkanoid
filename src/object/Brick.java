package object;

import game.MyJPanel;

import java.awt.Color;
import java.awt.Graphics2D;

public class Brick extends Item {
    
    public static float width = 1f / 20f;
    public static float height = 1f / 50f;
    
    private int life;

    /**
     * 
     * @param mPosition
     * @param mSpeed
     * @param mAcceleration
     * @param jpanel
     * @param life
     */
    public Brick(float[] mPosition, float[] mSpeed, float[] mAcceleration, MyJPanel jpanel, int life) {
	super(mPosition, mSpeed, mAcceleration, width, height, jpanel);
	this.life = life;
    }
    
    public boolean intersect(Item other) {
	if(this.rect.intersects(other.rect)) {
	    this.life--;
	    return true;
	}
	return false;
    }
    
    public int getScore() {
	return (this.life + 1) * (this.life + 1) * 5;
    }
    
    public boolean isAlive(){
	return this.life > 0;
    }
    
    public void draw(Graphics2D g2) {
	if(this.life == 3) {
	    g2.setColor(new Color(192, 57, 43));
	} else if(this.life == 2) {
	    g2.setColor(new Color(211, 84, 0));
	} else if (this.life == 1) {
	    g2.setColor(new Color(243, 156, 18));
	}
	super.draw(g2);
    }
}
