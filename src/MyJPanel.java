import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MyJPanel extends JPanel {

	public MyJPanel(){
		super();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.clearRect(0, 0, super.getWidth(), super.getHeight());
	}
}
