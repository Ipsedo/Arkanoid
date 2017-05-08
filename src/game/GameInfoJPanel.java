package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class GameInfoJPanel extends JPanel {

    private MyJFrame jframe;
    private int idLevel;

    /**
     * Mettre des info de jeux en argument
     */
    public GameInfoJPanel(MyJFrame f, final int idLevel) {
	super();
	this.jframe = f;
	this.idLevel = idLevel;
	this.setBackground(new Color(236, 240, 241));
	setLayout(new BorderLayout());

	JButton back = new JButton("Back");
	JButton next = new JButton("Next");

	JButton start = new JButton("<HTML><BODY align='center'>Start /<BR>Resume</BR><BR>'S'</BR></BODY></HTML>");
	JButton retry = new JButton("Retry 'T'");
	JButton pause = new JButton("Pause 'P'");
	//JButton resume = new JButton("Resume 'O'");
	JButton reset = new JButton("Reset 'R'");

	JPanel backNext = new JPanel();
	backNext.setLayout(new GridLayout(0, 2));

	JPanel infoPanel = new JPanel();
	infoPanel.setLayout(new GridLayout(0, 1));

	String[] levelList = { "Level 0", "Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6" };
	final JComboBox<String> comboBox = new JComboBox<String>(levelList);
	comboBox.setEditable(false);
	comboBox.setBackground(new Color(189, 195, 199));

	back.setBackground(new Color(189, 195, 199));
	next.setBackground(new Color(189, 195, 199));
	start.setBackground(new Color(189, 195, 199));
	retry.setBackground(new Color(189, 195, 199));
	pause.setBackground(new Color(189, 195, 199));
	//resume.setBackground(new Color(189, 195, 199));
	reset.setBackground(new Color(189, 195, 199));

	this.add(comboBox, BorderLayout.NORTH);

	infoPanel.add(back);
	infoPanel.add(next);
	infoPanel.add(start);
	infoPanel.add(retry);
	infoPanel.add(pause);
	//infoPanel.add(resume);
	infoPanel.add(reset);
	this.add(infoPanel, BorderLayout.CENTER);

	backNext.add(back);
	backNext.add(next);
	this.add(backNext, BorderLayout.SOUTH);

	comboBox.addItemListener(new ItemListener() {

	    @Override
	    public void itemStateChanged(ItemEvent ie) {
		if (ie.getItem().equals("Level 0")) {
		    GameInfoJPanel.this.jframe.level(0);
		    GameInfoJPanel.this.jframe.pauseGame();
		    GameInfoJPanel.this.idLevel = 0;
		} else if (ie.getItem().equals("Level 1")) {
		    GameInfoJPanel.this.jframe.level(1);
		    GameInfoJPanel.this.jframe.pauseGame();
		    GameInfoJPanel.this.idLevel = 1;
		} else if (ie.getItem().equals("Level 2")) {
		    GameInfoJPanel.this.jframe.level(2);
		    GameInfoJPanel.this.jframe.pauseGame();
		    GameInfoJPanel.this.idLevel = 2;
		} else if (ie.getItem().equals("Level 3")) {
		    GameInfoJPanel.this.jframe.level(3);
		    GameInfoJPanel.this.jframe.pauseGame();
		    GameInfoJPanel.this.idLevel = 3;
		} else if (ie.getItem().equals("Level 4")) {
		    GameInfoJPanel.this.jframe.level(4);
		    GameInfoJPanel.this.jframe.pauseGame();
		    GameInfoJPanel.this.idLevel = 4;
		} else if (ie.getItem().equals("Level 5")) {
		    GameInfoJPanel.this.jframe.level(5);
		    GameInfoJPanel.this.jframe.pauseGame();
		    GameInfoJPanel.this.idLevel = 5;
		} else if (ie.getItem().equals("Level 6")) {
		    GameInfoJPanel.this.jframe.level(6);
		    GameInfoJPanel.this.jframe.pauseGame();
		    GameInfoJPanel.this.idLevel = 6;
		}
	    }

	});
	comboBox.setFocusable(false);

	this.jframe.addKeyListener(new KeyListener() {
	    @Override
	    public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_P) {
		    GameInfoJPanel.this.jframe.pauseGame();
		} else if (arg0.getKeyCode() == KeyEvent.VK_R) {
		    GameInfoJPanel.this.jframe.level(0);
		} else if (arg0.getKeyCode() == KeyEvent.VK_O) {
		    GameInfoJPanel.this.jframe.resumeGame();
		} else if (arg0.getKeyCode() == KeyEvent.VK_T) {
		    GameInfoJPanel.this.jframe.level(GameInfoJPanel.this.idLevel);
		} /*else if (arg0.getKeyCode() == KeyEvent.VK_S) {
		    GameInfoJPanel.this.jframe.startGame();
		}*/
	    }

	    @Override
	    public void keyReleased(KeyEvent arg0) {
	    }

	    @Override
	    public void keyTyped(KeyEvent arg0) {
	    }
	});
	this.jframe.setFocusable(true);

	back.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		System.out.println("back");
		if (GameInfoJPanel.this.idLevel > 0) {
		    GameInfoJPanel.this.idLevel--;
		    comboBox.setSelectedIndex(GameInfoJPanel.this.idLevel);
		    jframe.level(GameInfoJPanel.this.idLevel);
		    GameInfoJPanel.this.jframe.pauseGame();
		}
	    }

	});
	back.setFocusable(false);

	next.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		GameInfoJPanel.this.idLevel++;
		comboBox.setSelectedIndex(GameInfoJPanel.this.idLevel);
		jframe.level(GameInfoJPanel.this.idLevel);
		GameInfoJPanel.this.jframe.pauseGame();
	    }

	});
	next.setFocusable(false);

	start.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		GameInfoJPanel.this.jframe.startGame();
	    }
	});
	start.setFocusable(false);

	retry.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		GameInfoJPanel.this.jframe.level(GameInfoJPanel.this.idLevel);
	    }
	});
	retry.setFocusable(false);

	pause.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		GameInfoJPanel.this.jframe.pauseGame();
	    }
	});
	pause.setFocusable(false);

	/*resume.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		GameInfoJPanel.this.jframe.resumeGame();
	    }
	});
	resume.setFocusable(false);*/

	reset.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		comboBox.setSelectedIndex(0);
		GameInfoJPanel.this.jframe.level(0);
	    }
	});
	reset.setFocusable(false);

    }
}