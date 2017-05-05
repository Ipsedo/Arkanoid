package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class GameInfoJPanel extends JPanel {
	
	private MyJFrame jframe;
		
	/**
	 * Mettre des info de jeux en argument
	 */
	public GameInfoJPanel(MyJFrame f) {
		super();
		this.setBackground(new Color(236, 240, 241));
		
		this.jframe = f;
		JButton pause = new JButton("Pause 'P'");
		JButton resume = new JButton("Resume 'O'");
		JButton reset = new JButton("Reset 'R'");
		
		String[] levelList = {"Level 0", "Level 1", "Level 3", "Level 4", "Level 5"};
		JComboBox comboBox = new JComboBox(levelList);
		comboBox.setEditable(false);
		comboBox.setBackground(new Color(189, 195, 199));
		
		pause.setBackground(new Color(189, 195, 199));
		resume.setBackground(new Color(189, 195, 199));
		reset.setBackground(new Color(189, 195, 199));
		
		this.add(comboBox);
		this.add(new JSeparator(SwingConstants.HORIZONTAL));
		
		this.add(pause);
		this.add(new JSeparator(SwingConstants.HORIZONTAL));
		this.add(resume);
		this.add(new JSeparator(SwingConstants.HORIZONTAL));
		this.add(reset);
		this.add(new JSeparator(SwingConstants.HORIZONTAL));
			
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.jframe.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getKeyCode() == KeyEvent.VK_P) {
					GameInfoJPanel.this.jframe.killThreads();
				}
				else if (arg0.getKeyCode() == KeyEvent.VK_R) {
					GameInfoJPanel.this.jframe.resetGame();
				}
				else if (arg0.getKeyCode() == KeyEvent.VK_O) {
					GameInfoJPanel.this.jframe.resumeGame();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
			
		});
		
		this.jframe.setFocusable(true);
		
		pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameInfoJPanel.this.jframe.killThreads();
			}			
		});	
				
		resume.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameInfoJPanel.this.jframe.resumeGame();
			}		
		});
		
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameInfoJPanel.this.jframe.resetGame();
			}			
		});	
		
	}

}