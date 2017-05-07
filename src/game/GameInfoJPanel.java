package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
	JButton start = new JButton("Start 'S'");
	JButton pause = new JButton("Pause 'P'");
	JButton resume = new JButton("Resume 'O'");
	JButton reset = new JButton("Reset 'R'");

	JPanel infoPanel = new JPanel();
	infoPanel.setLayout(new GridLayout(5, 1, 0, 0));
	setLayout(new BorderLayout());

	String[] levelList = { "Level 0", "Level 1", "Level 2", "Level 3", "Level 4", "Level 5" };
	JComboBox<String> comboBox = new JComboBox<String>(levelList);
	comboBox.setEditable(false);
	comboBox.setBackground(new Color(189, 195, 199));

	start.setBackground(new Color(189, 195, 199));
	pause.setBackground(new Color(189, 195, 199));
	resume.setBackground(new Color(189, 195, 199));
	reset.setBackground(new Color(189, 195, 199));
	
	comboBox.addItemListener(new ItemListener() {

	    @Override
	    public void itemStateChanged(ItemEvent ie) {
		// TODO Auto-generated method stub
		if (ie.getItem().equals("Level 0")) {
		    jframe.level(0);
		} else if (ie.getItem().equals("Level 1")) {
		    jframe.level(1);
		} else if (ie.getItem().equals("Level 2")) {
		    jframe.level(2);
		} else if (ie.getItem().equals("Level 3")) {
		    jframe.level(3);
		}
	    }
	    
	});

	infoPanel.add(comboBox);
	this.add(comboBox, BorderLayout.NORTH);

	infoPanel.add(start);
	infoPanel.add(pause);
	infoPanel.add(resume);
	infoPanel.add(reset);
	add(infoPanel, BorderLayout.CENTER);

	// this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

	this.jframe.addKeyListener(new KeyListener() {
	    @Override
	    public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == KeyEvent.VK_P) {
		    GameInfoJPanel.this.jframe.pauseGame();
		} else if (arg0.getKeyCode() == KeyEvent.VK_R) {
		    GameInfoJPanel.this.jframe.level(0);
		} else if (arg0.getKeyCode() == KeyEvent.VK_O) {
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
	
	start.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		GameInfoJPanel.this.jframe.startBall();
	    }
	});

	pause.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		GameInfoJPanel.this.jframe.pauseGame();
	    }
	});
	pause.setFocusable(false);

	resume.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		GameInfoJPanel.this.jframe.resumeGame();
	    }
	});
	resume.setFocusable(false);

	reset.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		GameInfoJPanel.this.jframe.level(0);
	    }
	});
	reset.setFocusable(false);

    }

}