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

    private static String Level_0 = "Level 0 - Zero";
    private static String Level_1 = "Level 1 - One";
    private static String Level_2 = "Level 2 - Circle";
    private static String Level_3 = "Level 3 - Eye";
    private static String Level_4 = "Level 4 - Skull";
    private static String Level_5 = "Level 5 - Space";
    private static String Level_6 = "Level 6 - Snake";

    private static String Arcade = "Arcade Mode";
    private static String Story = "Story Mode";

    /**
     * 
     * @param f
     * @param idLevel
     */
    public GameInfoJPanel(MyJFrame f, final int idLevel) {
	super();
	this.jframe = f;
	this.idLevel = idLevel;
	this.setBackground(new Color(236, 240, 241));
	setLayout(new BorderLayout());

	final JButton back = new JButton("Back");
	final JButton next = new JButton("Next");

	JButton start = new JButton("<HTML><BODY align='center'>Start /<BR>Resume</BR><BR>'S'</BR></BODY></HTML>");
	JButton retry = new JButton("Retry 'T'");
	JButton pause = new JButton("Pause 'P'");
	JButton reset = new JButton("Reset 'R'");

	JPanel backNext = new JPanel();
	backNext.setLayout(new GridLayout(0, 2));

	JPanel infoPanel = new JPanel();
	infoPanel.setLayout(new GridLayout(0, 1));

	JPanel centerPanel = new JPanel();
	centerPanel.setLayout(new BorderLayout());

	final String[] levelList = { Level_0, Level_1, Level_2, Level_3, Level_4, Level_5, Level_6 };
	final JComboBox<String> comboBox = new JComboBox<String>(levelList);
	comboBox.setEditable(false);
	comboBox.setBackground(new Color(189, 195, 199));

	final String[] modeList = { Arcade, Story };
	final JComboBox<String> modeBox = new JComboBox<String>(modeList);
	modeBox.setEditable(false);
	modeBox.setBackground(new Color(189, 195, 199));

	back.setBackground(new Color(189, 195, 199));
	next.setBackground(new Color(189, 195, 199));
	start.setBackground(new Color(189, 195, 199));
	retry.setBackground(new Color(189, 195, 199));
	pause.setBackground(new Color(189, 195, 199));
	reset.setBackground(new Color(189, 195, 199));

	centerPanel.add(modeBox, BorderLayout.NORTH);

	this.add(comboBox, BorderLayout.NORTH);

	infoPanel.add(back);
	infoPanel.add(next);
	infoPanel.add(start);
	infoPanel.add(pause);
	infoPanel.add(retry);
	infoPanel.add(reset);
	centerPanel.add(infoPanel, BorderLayout.CENTER);
	this.add(centerPanel, BorderLayout.CENTER);

	backNext.add(back);
	backNext.add(next);
	this.add(backNext, BorderLayout.SOUTH);

	comboBox.addItemListener(new ItemListener() {

	    @Override
	    public void itemStateChanged(ItemEvent ie) {
		if (ie.getItem().equals(Level_0)) {
		    GameInfoJPanel.this.jframe.level(0);
		    GameInfoJPanel.this.idLevel = 0;
		} else if (ie.getItem().equals(Level_1)) {
		    GameInfoJPanel.this.jframe.level(1);
		    GameInfoJPanel.this.idLevel = 1;
		} else if (ie.getItem().equals(Level_2)) {
		    GameInfoJPanel.this.jframe.level(2);
		    GameInfoJPanel.this.idLevel = 2;
		} else if (ie.getItem().equals(Level_3)) {
		    GameInfoJPanel.this.jframe.level(3);
		    GameInfoJPanel.this.idLevel = 3;
		} else if (ie.getItem().equals(Level_4)) {
		    GameInfoJPanel.this.jframe.level(4);
		    GameInfoJPanel.this.idLevel = 4;
		} else if (ie.getItem().equals(Level_5)) {
		    GameInfoJPanel.this.jframe.level(5);
		    GameInfoJPanel.this.idLevel = 5;
		} else if (ie.getItem().equals(Level_6)) {
		    GameInfoJPanel.this.jframe.level(6);
		    GameInfoJPanel.this.idLevel = 6;
		}
	    }

	});
	comboBox.setFocusable(false);

	modeBox.addItemListener(new ItemListener() {

	    @Override
	    public void itemStateChanged(ItemEvent ie) {
		 //GameInfoJPanel.this.jframe.resetScore();
		if (ie.getItem().equals(Arcade)) {
		    comboBox.setEnabled(true);
		    back.setEnabled(true);
		    next.setEnabled(true);

		} else if (ie.getItem().equals(Story)) {
		    comboBox.setEnabled(false);
		    back.setEnabled(false);
		    next.setEnabled(false);
		}
	    }

	});

	this.jframe.addKeyListener(new KeyListener() {
	    @Override
	    public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_P) {
		    GameInfoJPanel.this.jframe.pauseGame();
		} else if (arg0.getKeyCode() == KeyEvent.VK_R) {
		    GameInfoJPanel.this.jframe.level(0);
		} else if (arg0.getKeyCode() == KeyEvent.VK_S) {
		    GameInfoJPanel.this.jframe.resumeGame();
		} else if (arg0.getKeyCode() == KeyEvent.VK_T) {
		    GameInfoJPanel.this.jframe.level(GameInfoJPanel.this.idLevel);
		} else if (arg0.getKeyCode() == KeyEvent.VK_B) {
		    if (GameInfoJPanel.this.idLevel > 0) {
			GameInfoJPanel.this.idLevel--;
		    }
		} else if (arg0.getKeyCode() == KeyEvent.VK_N) {
		    if (GameInfoJPanel.this.idLevel < levelList.length - 1) {
			GameInfoJPanel.this.idLevel++;
		    }
		} else if (arg0.getKeyChar() == '0') {
		    GameInfoJPanel.this.jframe.level(0);
		    GameInfoJPanel.this.idLevel = 0;
		} else if (arg0.getKeyChar() == '1') {
		    GameInfoJPanel.this.jframe.level(1);
		    GameInfoJPanel.this.idLevel = 1;
		} else if (arg0.getKeyChar() == '2') {
		    GameInfoJPanel.this.jframe.level(2);
		    GameInfoJPanel.this.idLevel = 2;
		} else if (arg0.getKeyChar() == '3') {
		    GameInfoJPanel.this.jframe.level(3);
		    GameInfoJPanel.this.idLevel = 3;
		} else if (arg0.getKeyChar() == '4') {
		    GameInfoJPanel.this.jframe.level(4);
		    GameInfoJPanel.this.idLevel = 4;
		} else if (arg0.getKeyChar() == '5') {
		    GameInfoJPanel.this.jframe.level(5);
		    GameInfoJPanel.this.idLevel = 5;
		} else if (arg0.getKeyChar() == '6') {
		    GameInfoJPanel.this.jframe.level(6);
		    GameInfoJPanel.this.idLevel = 6;
		}
		comboBox.setSelectedIndex(GameInfoJPanel.this.idLevel);
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
		if (GameInfoJPanel.this.idLevel > 0) {
		    GameInfoJPanel.this.idLevel--;
		    comboBox.setSelectedIndex(GameInfoJPanel.this.idLevel);
		    GameInfoJPanel.this.jframe.level(GameInfoJPanel.this.idLevel);
		}
	    }

	});
	back.setFocusable(false);

	next.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		if (GameInfoJPanel.this.idLevel < levelList.length - 1) {
		    GameInfoJPanel.this.idLevel++;
		    comboBox.setSelectedIndex(GameInfoJPanel.this.idLevel);
		    GameInfoJPanel.this.jframe.level(GameInfoJPanel.this.idLevel);
		}
	    }

	});
	next.setFocusable(false);

	start.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		GameInfoJPanel.this.jframe.resumeGame();
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