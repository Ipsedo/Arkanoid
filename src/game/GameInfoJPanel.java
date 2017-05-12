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
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import levels.LevelMaker;

@SuppressWarnings("serial")
public class GameInfoJPanel extends JPanel {

    private MyJFrame jframe;
    private int idLevel;
    private boolean editMode;

    private static int NB_LEVEL = 7;
    private static String Level_0 = "Level 0 - Zero";
    private static String Level_1 = "Level 1 - One";
    private static String Level_2 = "Level 2 - Circle";
    private static String Level_3 = "Level 3 - Eye";
    private static String Level_4 = "Level 4 - Skull";
    private static String Level_5 = "Level 5 - Space";
    private static String Level_6 = "Level 6 - Snake";

    @SuppressWarnings("unused")
    private static String File_0, File_1, File_2, File_3, File_4, File_5, File_6, File_7, File_8,
	    File_9;

    private static String Arcade = "Arcade Mode";
    private static String Story = "Story Mode";
    private static String Edit = "Edit Mode";

    private JComboBox<String> comboBox;

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
	this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

	final JButton back = new JButton("Back");
	final JButton next = new JButton("Next");
	final JCheckBox sound = new JCheckBox("Sound");
	final JButton nFile = new JButton("Edit");
	final JButton refresh = new JButton("Load");

	this.editMode = false;

	JButton start = new JButton(
		"<HTML><BODY align='center'>Start /<BR>Resume</BR><BR>'S'</BR></BODY></HTML>");
	JButton retry = new JButton("Retry 'T'");
	JButton pause = new JButton("Pause 'P'");
	JButton reset = new JButton("Reset 'R'");

	final JPanel backNext = new JPanel();
	backNext.setLayout(new GridLayout(0, 2));

	final JPanel newRefresh = new JPanel();
	newRefresh.setLayout(new GridLayout(0, 2));

	JPanel infoPanel = new JPanel();
	infoPanel.setLayout(new GridLayout(0, 1));

	final JPanel centerPanel = new JPanel();
	centerPanel.setLayout(new BorderLayout());

	final String[] levelList = { Level_0, Level_1, Level_2, Level_3, Level_4, Level_5, Level_6 };
	this.comboBox = new JComboBox<String>(levelList);
	this.comboBox.setEditable(false);
	this.comboBox.setBackground(new Color(189, 195, 199));

	final String[] modeList = { Arcade, Story, Edit };
	final JComboBox<String> modeBox = new JComboBox<String>(modeList);
	modeBox.setEditable(false);

	modeBox.setBackground(new Color(189, 195, 199));
	nFile.setBackground(new Color(189, 195, 199));
	refresh.setBackground(new Color(189, 195, 199));
	sound.setBackground(new Color(189, 195, 199));
	back.setBackground(new Color(189, 195, 199));
	next.setBackground(new Color(189, 195, 199));
	start.setBackground(new Color(189, 195, 199));
	retry.setBackground(new Color(189, 195, 199));
	pause.setBackground(new Color(189, 195, 199));
	reset.setBackground(new Color(189, 195, 199));

	sound.setHorizontalAlignment(JCheckBox.CENTER);
	sound.setFocusable(false);

	centerPanel.add(modeBox, BorderLayout.NORTH);

	this.add(comboBox, BorderLayout.NORTH);

	infoPanel.add(start);
	infoPanel.add(pause);
	infoPanel.add(retry);
	infoPanel.add(reset);
	centerPanel.add(infoPanel, BorderLayout.CENTER);
	centerPanel.add(sound, BorderLayout.SOUTH);
	this.add(centerPanel, BorderLayout.CENTER);

	backNext.add(back);
	backNext.add(next);
	this.add(backNext, BorderLayout.SOUTH);

	newRefresh.add(nFile);
	newRefresh.add(refresh);

	this.comboBox.addItemListener(new ItemListener() {
	    @Override
	    public void itemStateChanged(ItemEvent ie) {
		if (!GameInfoJPanel.this.editMode && ie.getStateChange() == ItemEvent.SELECTED) {
		    GameInfoJPanel.this.idLevel = GameInfoJPanel.this.comboBox.getSelectedIndex();
		    GameInfoJPanel.this.jframe.level(GameInfoJPanel.this.idLevel);
		} else if (ie.getStateChange() == ItemEvent.SELECTED) {
		    GameInfoJPanel.this.jframe.startLevelFromFile((String) ie.getItem());
		}
	    }

	});
	this.comboBox.setFocusable(false);

	modeBox.addItemListener(new ItemListener() {
	    @SuppressWarnings({ "unchecked", "rawtypes" })
	    @Override
	    public void itemStateChanged(ItemEvent ie) {
		// GameInfoJPanel.this.jframe.resetScore();

		if (ie.getItem().equals(Arcade) && ie.getStateChange() == ItemEvent.SELECTED) {
		    GameInfoJPanel.this.editMode = false;
		    // comboBx
		    GameInfoJPanel.this.jframe.clearScore();
		    GameInfoJPanel.this.comboBox.setModel(new DefaultComboBoxModel(levelList));
		    GameInfoJPanel.this.idLevel = 0;
		    GameInfoJPanel.this.pause();
		    GameInfoJPanel.this.jframe.level(0);
		    GameInfoJPanel.this.comboBox.setSelectedIndex(GameInfoJPanel.this.idLevel);
		    GameInfoJPanel.this.comboBox.setEnabled(true);
		    // new file
		    GameInfoJPanel.this.remove(newRefresh);
		    GameInfoJPanel.this.add(backNext, BorderLayout.SOUTH);
		    back.setEnabled(true);
		    next.setEnabled(true);
		} else if (ie.getItem().equals(Story) && ie.getStateChange() == ItemEvent.SELECTED) {
		    GameInfoJPanel.this.editMode = false;
		    // comboBox
		    GameInfoJPanel.this.jframe.clearScore();
		    GameInfoJPanel.this.comboBox.setModel(new DefaultComboBoxModel(levelList));
		    GameInfoJPanel.this.idLevel = 0;
		    GameInfoJPanel.this.pause();
		    GameInfoJPanel.this.jframe.level(0);
		    GameInfoJPanel.this.comboBox.setSelectedIndex(GameInfoJPanel.this.idLevel);
		    GameInfoJPanel.this.comboBox.setEnabled(false);
		    // new file
		    GameInfoJPanel.this.remove(newRefresh);
		    GameInfoJPanel.this.add(backNext, BorderLayout.SOUTH);
		    back.setEnabled(false);
		    next.setEnabled(false);
		} else if (ie.getItem().equals(Edit) && ie.getStateChange() == ItemEvent.SELECTED) {
		    GameInfoJPanel.this.editMode = true;
		    // comboBox
		    GameInfoJPanel.this.comboBox.removeAllItems();
		    GameInfoJPanel.this.comboBox.setModel(new DefaultComboBoxModel(
			    GameInfoJPanel.this.listFile().toArray()));
		    GameInfoJPanel.this.comboBox.setEnabled(true);
		    if (!GameInfoJPanel.this.listFile().isEmpty()) {
			GameInfoJPanel.this.pause();
			GameInfoJPanel.this.comboBox.setSelectedIndex(0);
			GameInfoJPanel.this.idLevel = 0;
			GameInfoJPanel.this.jframe.startLevelFromFile(GameInfoJPanel.this.listFile().get(0));
		    }
		    // new file
		    GameInfoJPanel.this.remove(backNext);
		    GameInfoJPanel.this.add(newRefresh, BorderLayout.SOUTH);
		}
	    }

	});
	modeBox.setFocusable(false);

	this.jframe.addKeyListener(new KeyListener() {
	    @Override
	    public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_P) {
		    GameInfoJPanel.this.pause();
		} else if (arg0.getKeyCode() == KeyEvent.VK_R) {
		    GameInfoJPanel.this.reset();
		} else if (arg0.getKeyCode() == KeyEvent.VK_S) {
		    GameInfoJPanel.this.start();
		} else if (arg0.getKeyCode() == KeyEvent.VK_T) {
		    GameInfoJPanel.this.retry();
		} else if (arg0.getKeyCode() == KeyEvent.VK_B) {
		    GameInfoJPanel.this.back();
		} else if (arg0.getKeyCode() == KeyEvent.VK_N) {
		    GameInfoJPanel.this.next();
		} else if (arg0.getKeyChar() >= '0' && arg0.getKeyChar() <= '6') {
		    GameInfoJPanel.this.idLevel = arg0.getKeyChar() - '0';
		    GameInfoJPanel.this.comboBox.setSelectedIndex(GameInfoJPanel.this.idLevel);
		}
	    }

	    @Override
	    public void keyReleased(KeyEvent arg0) {
	    }

	    @Override
	    public void keyTyped(KeyEvent arg0) {
	    }
	});
	this.jframe.setFocusable(true);

	nFile.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		new LevelMaker();
	    }

	});
	nFile.setFocusable(false);

	refresh.addActionListener(new ActionListener() {
	    @SuppressWarnings({ "unchecked", "rawtypes" })
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		GameInfoJPanel.this.listFile();
		GameInfoJPanel.this.comboBox.setModel(new DefaultComboBoxModel(GameInfoJPanel.this
			.listFile().toArray()));
	    }

	});
	refresh.setFocusable(false);

	back.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		GameInfoJPanel.this.back();
	    }

	});
	back.setFocusable(false);

	next.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		GameInfoJPanel.this.next();
	    }

	});
	next.setFocusable(false);

	start.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		GameInfoJPanel.this.start();
	    }
	});
	start.setFocusable(false);

	retry.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		GameInfoJPanel.this.retry();
	    }
	});
	retry.setFocusable(false);

	pause.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		GameInfoJPanel.this.pause();
	    }
	});
	pause.setFocusable(false);

	reset.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		GameInfoJPanel.this.reset();
	    }
	});
	reset.setFocusable(false);

    }

    private void start() {
	this.jframe.resumeGame();
    }

    private void pause() {
	this.jframe.pauseGame();
    }

    private void reset() {
	if (!this.editMode) {
	    this.jframe.clearScore();
	    if (this.idLevel != 0) {
		this.idLevel = 0;
		this.jframe.pauseGame();
		this.comboBox.setSelectedIndex(this.idLevel);
	    } else {
		this.jframe.pauseGame();
		this.jframe.level(0);
	    }
	} else {
	    this.jframe.clearScore();
	    this.jframe.pauseGame();
	    this.jframe.startLevelFromFile(this.listFile().get(this.idLevel));
	}
    }

    private void retry() {
	if (!this.editMode) {
	    this.jframe.divScore();
	    this.jframe.pauseGame();
	    this.jframe.level(this.idLevel);
	} else {
	    this.jframe.divScore();
	    this.jframe.pauseGame();
	    this.jframe.startLevelFromFile(this.listFile().get(this.idLevel));
	}
    }

    private void next() {
	if (this.idLevel < NB_LEVEL - 1) {
	    this.idLevel++;
	    this.jframe.pauseGame();
	    this.comboBox.setSelectedIndex(this.idLevel);
	}
    }

    private void back() {
	if (this.idLevel > 0) {
	    this.idLevel--;
	    this.jframe.pauseGame();
	    this.comboBox.setSelectedIndex(this.idLevel);
	}
    }

    public ArrayList<String> listFile() {
	ArrayList<String> res = new ArrayList<>();
	File directory = new File("./res/");
	File[] fList = directory.listFiles();

	for (File file : fList) {
	    if (file.isFile() && file.getName().endsWith(".txt")) {
		res.add(file.getName());
	    }
	}
	return res;
    }

    public void levelDone() {
	if (!this.editMode) {
	    if (this.idLevel < 6) {
		this.idLevel++;
	    }
	    this.comboBox.setSelectedIndex(this.idLevel);
	}
    }
}