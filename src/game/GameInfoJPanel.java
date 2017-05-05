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
		
		pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameInfoJPanel.this.jframe.killThreads();
			}			
		});	
		
		pause.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getKeyChar() == 'p') {
					GameInfoJPanel.this.jframe.killThreads();
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
				
		resume.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameInfoJPanel.this.jframe.resumeGame();
			}		
		});	
		
		resume.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getKeyChar() == 'o') {
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
		
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameInfoJPanel.this.jframe.resetGame();
			}			
		});	
		
		reset.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getKeyChar() == 'r') {
					GameInfoJPanel.this.jframe.resetGame();
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
		
	}

}