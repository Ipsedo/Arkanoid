package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import object.Ball;

public class GameInfoJPanel extends JPanel {
	
	private MyJFrame jframe;
		
	/**
	 * Mettre des info de jeux en argument
	 */
	public GameInfoJPanel(MyJFrame f) {
		super();
		
		this.jframe = f;
		JButton start = new JButton("Start");
		JButton pause = new JButton("Pause");
		JButton reset = new JButton("Reset");
		
		this.add(start);;
		this.add(new JSeparator(SwingConstants.HORIZONTAL));
		this.add(pause);
		this.add(new JSeparator(SwingConstants.HORIZONTAL));
		this.add(reset);
		this.add(new JSeparator(SwingConstants.HORIZONTAL));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				GameInfoJPanel.this.jframe.resetGame();
			}
			
		});
		
		
	}

}
