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
		
	/**
	 * Mettre des info de jeux en argument
	 */
	public GameInfoJPanel(MyJFrame f) {
		super();
		
		JButton start = new JButton("Start");
		JButton pause = new JButton("Start");
		JButton reset = new JButton("Start");
		
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
				
			}
			
		});
		
		
	}

}
