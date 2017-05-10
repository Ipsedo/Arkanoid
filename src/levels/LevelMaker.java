package levels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import game.MyJFrame;
import game.MyJPanel;
import object.Ball;
import object.Brick;

public class LevelMaker extends JFrame {

    private List<Brick> bricks;
    private MyJPanel jpanel;
    private String fileName;

    public LevelMaker() {
	super("Arkanoid - LevelMaker");
	super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	super.setLocation(300, 0);
	super.getContentPane().setLayout(new BorderLayout());

	this.bricks = Collections.synchronizedList(new ArrayList<Brick>());

	this.jpanel = new MyJPanel() {

	    @Override
	    public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.clearRect(0, 0, this.getWidth(), this.getHeight());
		g2.setColor(Color.GREEN);
		g2.drawRect(0, 0, this.getWidth(), (int) (0.5f * this.getHeight()));
		for (Brick b : LevelMaker.this.bricks)
		    b.draw(g2);
	    }
	};

	this.fileName = "myFile";

	this.jpanel.addMouseListener(new MouseListener() {

	    @Override
	    public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if ((float) arg0.getY() / (float) LevelMaker.this.jpanel.getHeight() < 0.5f) {
		    LevelMaker.this.bricks.add(Brick.makeSimpleBrick(new float[] { (float) arg0
			    .getX() / (float) LevelMaker.this.jpanel.getWidth(), (float) arg0.getY()
				    / (float) LevelMaker.this.jpanel.getHeight() }, new float[2],
			    new float[2], LevelMaker.this.jpanel, 1));
		    LevelMaker.this.repaint();
		}
	    }

	    @Override
	    public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	    }

	    @Override
	    public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	    }

	});

	JButton save = new JButton("Save");
	save.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		LevelMaker.this.toFile(LevelMaker.this.fileName + ".txt");
	    }
	});

	JButton undo = new JButton("Undo");
	undo.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		synchronized (LevelMaker.this.bricks) {
		    if (!LevelMaker.this.bricks.isEmpty())
			LevelMaker.this.bricks.remove(LevelMaker.this.bricks.size() - 1);
		    LevelMaker.this.repaint();
		}
	    }

	});

	JTextField jTextField = new JTextField();
	jTextField.setText("myFile");
	jTextField.setColumns(10);
	jTextField.addCaretListener(new CaretListener() {

	    @Override
	    public void caretUpdate(CaretEvent arg0) {
		// TODO Auto-generated method stub
		LevelMaker.this.fileName = ((JTextField) arg0.getSource()).getText();
	    }

	});

	JPanel jpanel = new JPanel();
	jpanel.setLayout(new GridLayout(3, 1));
	jpanel.add(save);
	jpanel.add(undo);
	jpanel.add(jTextField);
	save.setFocusable(false);
	undo.setFocusable(false);
	//jTextField.setFocusable(false);
	jpanel.setFocusable(false);
	this.jpanel.setFocusable(false);

	super.getContentPane().add(this.jpanel, BorderLayout.CENTER);
	super.getContentPane().add(jpanel, BorderLayout.SOUTH);
	super.setSize(MyJFrame.WIDTH, MyJFrame.HEIGHT);

	// super.pack();
	super.setFocusable(false);

	super.setVisible(true);
    }

    public void toFile(String fileName) {
	try {
	    PrintWriter writer = new PrintWriter(fileName, "UTF-8");
	    for (Brick b : this.bricks)
		writer.write(b + "\n");
	    writer.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public static List<Brick> createFromFile(String fileName, MyJPanel jpanel) {
	List<Brick> res = new ArrayList<Brick>();
	try {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    String line;
	    while ((line = br.readLine()) != null) {
		String[] tmp = line.split(" ");
		res.add(Brick.makeSimpleBrick(new float[] { Float.parseFloat(tmp[0]), Float
			.parseFloat(tmp[1]) }, new float[2], new float[2], jpanel, Integer.parseInt(
				tmp[2])));
	    }
	} catch (IOException ioe) {
	    ioe.printStackTrace();
	}
	return res;
    }

    /**
     * 
     * @param levelId
     * @param rand
     * @param jpanel
     * @return
     */
    public static List<Ball> getBallsFromLevelId(int levelId, Random rand, MyJPanel jpanel) {
	if (levelId == 0) {
	    int nbBall = 2;

	    List<Ball> res = Collections.synchronizedList(new ArrayList<Ball>());
	    for (int i = 0; i < nbBall; i++) {
		res.add(new Ball(rand, jpanel));
	    }
	    return res;
	} else if (levelId == 1) {
	    int nbBall = 60;
	    List<Ball> res = Collections.synchronizedList(new ArrayList<Ball>());
	    for (int i = 0; i < nbBall; i++) {
		res.add(new Ball(rand, jpanel));
	    }
	    return res;
	} else if (levelId == 2) {
	    int nbBall = 60;
	    List<Ball> res = Collections.synchronizedList(new ArrayList<Ball>());
	    for (int i = 0; i < nbBall; i++) {
		res.add(new Ball(rand, jpanel));
	    }
	    return res;
	} else {
	    int nbBall = 60;
	    List<Ball> res = Collections.synchronizedList(new ArrayList<Ball>());
	    for (int i = 0; i < nbBall; i++) {
		res.add(new Ball(rand, jpanel));
	    }
	    return res;
	}
    }

    /**
     * 
     * @param levelId
     * @param jpanel
     * @return
     */
    public static List<Brick> getBricksFromLevelID(int levelId, MyJPanel jpanel) {
	List<Brick> res = Collections.synchronizedList(new ArrayList<Brick>());
	float milieu = 0.5f;
	float[] speed = new float[2];
	float[] acc = new float[2];

	if (levelId == 0) {
	    for (int i = -3; i < -1; i++) {
		for (int j = 0; j < 9; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f
			    + (float) j * (Brick.height + 0.01f) };
		    res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
		}
	    }

	    for (int i = 1; i < 3; i++) {
		for (int j = 0; j < 9; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f
			    + (float) j * (Brick.height + 0.01f) };
		    res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
		}
	    }

	    for (int i = -2; i < 2; i++) {
		for (int j = -1; j < 0; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f
			    + (float) j * (Brick.height + 0.01f) };
		    res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
		}
	    }

	    for (int i = -2; i < 2; i++) {
		for (int j = 9; j < 10; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f
			    + (float) j * (Brick.height + 0.01f) };
		    res.add(Brick.makeBonusBrick(pos, speed, acc, jpanel));
		}
	    }
	} else if (levelId == 1) {
	    for (int i = -1; i < 2; i++) {
		for (int j = 0; j < 15; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f
			    + (float) j * (Brick.height + 0.01f) };
		    res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
		}
	    }

	    for (int i = -3; i < 4; i++) {
		for (int j = 15; j < 17; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f
			    + (float) j * (Brick.height + 0.01f) };
		    res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
		}
	    }

	    for (int i = -3; i < -1; i++) {
		for (int j = 1; j < 4; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), 0.1f
			    + (float) j * (Brick.height + 0.01f) };
		    res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
		}
	    }
	} else if (levelId == 2) {
	    double radius = 0.2d;
	    for (int i = 0; i < 24; i++) {
		double angle = (double) i * Math.PI * 2d / 24d;
		float[] pos = new float[] { milieu + (float) (radius * Math.cos(angle)), 0.25f
			+ (float) (radius * Math.sin(angle)) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
	    }
	    radius = 0.1d;
	    for (int i = 0; i < 12; i++) {
		double angle = (double) i * Math.PI * 2d / 12d;
		float[] pos = new float[] { milieu + (float) (radius * Math.cos(angle)), 0.25f
			+ (float) (radius * Math.sin(angle)) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 2));
	    }
	} else if (levelId == 3) {
	    for (int i = 0; i < 20; i++) {
		float[] pos = new float[] { (float) i / 20f, 0.1f + (float) Math.cos((double) i
			* Math.PI * 2d / 20d) / 10f };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
	    }

	    for (int i = 0; i < 20; i++) {
		float[] pos = new float[] { (float) i / 20f, 0.4f + (float) -Math.cos((double) i
			* Math.PI * 2d / 20d) / 10f };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
	    }

	    double radius = 0.15d;
	    for (int i = 0; i < 16; i++) {
		double angle = (double) i * Math.PI * 2d / 16d;
		float[] pos = new float[] { milieu + (float) (radius * Math.cos(angle)), 0.25f
			+ (float) (radius * Math.sin(angle)) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 2));
	    }
	} else if (levelId == 5) {
	    float h = 0.13f;
	    for (int i = -3; i < 4; i++) {
		for (int j = 0; j < 8; j++) {
		    if ((i == -2 && j == 2) || (i == -2 && j == 3) || (i == 2 && j == 2) || (i == 2
			    && j == 3)) {
			float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
				+ (float) j * (Brick.height + 0.01f) };
			res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 3));
		    } else {
			float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
				+ (float) j * (Brick.height + 0.01f) };
			res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
		    }
		}
	    }
	    for (int j = 2; j < 7; j++) {
		float[] pos = new float[] { milieu + (float) (-4) * (Brick.width + 0.01f), h
			+ (float) j * (Brick.height + 0.01f) };
		float[] pos1 = new float[] { milieu + (float) 4 * (Brick.width + 0.01f), h
			+ (float) j * (Brick.height + 0.01f) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
		res.add(Brick.makeSimpleBrick(pos1, speed, acc, jpanel, 1));
	    }
	    for (int j = 4; j < 10; j++) {
		float[] pos = new float[] { milieu + (float) (-5) * (Brick.width + 0.01f), h
			+ (float) j * (Brick.height + 0.01f) };
		float[] pos1 = new float[] { milieu + (float) 5 * (Brick.width + 0.01f), h
			+ (float) j * (Brick.height + 0.01f) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 2));
		res.add(Brick.makeSimpleBrick(pos1, speed, acc, jpanel, 2));
	    }
	    for (int j = 8; j < 10; j++) {
		float[] pos = new float[] { milieu + (float) (-3) * (Brick.width + 0.01f), h
			+ (float) j * (Brick.height + 0.01f) };
		float[] pos1 = new float[] { milieu + (float) 3 * (Brick.width + 0.01f), h
			+ (float) j * (Brick.height + 0.01f) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
		res.add(Brick.makeSimpleBrick(pos1, speed, acc, jpanel, 1));
	    }
	    for (int k = -2; k < 0; k++) {
		for (int j = 10; j < 12; j++) {
		    float[] pos = new float[] { milieu + (float) k * (Brick.width + 0.01f), h
			    + (float) j * (Brick.height + 0.01f) };
		    float[] pos1 = new float[] { milieu + (float) (-k) * (Brick.width + 0.01f), h
			    + (float) j * (Brick.height + 0.01f) };
		    res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 2));
		    res.add(Brick.makeSimpleBrick(pos1, speed, acc, jpanel, 2));
		}
	    }
	    for (int j = -2; j < 0; j++) {
		float[] pos = new float[] { milieu + (float) 2 * (Brick.width + 0.01f), h
			+ (float) j * (Brick.height + 0.01f) };
		float[] pos1 = new float[] { milieu + (float) (-2) * (Brick.width + 0.01f), h
			+ (float) j * (Brick.height + 0.01f) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 2));
		res.add(Brick.makeSimpleBrick(pos1, speed, acc, jpanel, 2));
	    }
	    for (int j = -4; j < -2; j++) {
		float[] pos = new float[] { milieu + (float) 3 * (Brick.width + 0.01f), h
			+ (float) j * (Brick.height + 0.01f) };
		float[] pos1 = new float[] { milieu + (float) (-3) * (Brick.width + 0.01f), h
			+ (float) j * (Brick.height + 0.01f) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 2));
		res.add(Brick.makeSimpleBrick(pos1, speed, acc, jpanel, 2));
	    }

	} else if (levelId == 4) {
	    float h = 0.13f;

	    for (int i = -3; i < 3; i++) {
		for (int j = 0; j < 8; j++) {
		    if ((i == -2 && j == 3) || (i == -2 && j == 4) || (i == 1 && j == 3) || (i == 1
			    && j == 4) || (i == -3 && j == 3) || (i == -3 && j == 4) || (i == 2
				    && j == 3) || (i == 2 && j == 4) || (i == -3 && j == 5)
			    || (i == 2 && j == 5)) {
			float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
				+ (float) j * (Brick.height + 0.01f) };
			res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 3));
		    } else {
			float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
				+ (float) j * (Brick.height + 0.01f) };
			res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
		    }
		}
	    }
	    for (int j = 1; j < 7; j++) {
		float[] pos = new float[] { milieu + (float) 3 * (Brick.width + 0.01f), h
			+ (float) j * (Brick.height + 0.01f) };
		float[] pos1 = new float[] { milieu + (float) (-4) * (Brick.width + 0.01f), h
			+ (float) j * (Brick.height + 0.01f) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
		res.add(Brick.makeSimpleBrick(pos1, speed, acc, jpanel, 1));
	    }
	    for (int i = -2; i < 2; i++) {
		float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
			+ (float) (-1) * (Brick.height + 0.01f) };
		float[] pos1 = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
			+ (float) (-1) * (Brick.height + 0.01f) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
		res.add(Brick.makeSimpleBrick(pos1, speed, acc, jpanel, 1));
	    }
	    for (int i = -2; i < 2; i++) {
		float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
			+ (float) 9 * (Brick.height + 0.01f) };
		float[] pos1 = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
			+ (float) 9 * (Brick.height + 0.01f) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
		res.add(Brick.makeSimpleBrick(pos1, speed, acc, jpanel, 1));
	    }
	    for (int i = -2; i < 2; i++) {
		for (int j = 11; j < 13; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
			    + (float) j * (Brick.height + 0.01f) };
		    res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
		}
	    }
	    float[] pos = new float[] { milieu + (float) (-2) * (Brick.width + 0.01f), h + (float) 8
		    * (Brick.height + 0.01f) };
	    float[] pos1 = new float[] { milieu + (float) 1 * (Brick.width + 0.01f), h + (float) 8
		    * (Brick.height + 0.01f) };
	    float[] pos2 = new float[] { milieu + (float) (-1) * (Brick.width + 0.01f), h
		    + (float) 8 * (Brick.height + 0.01f) };
	    float[] pos3 = new float[] { milieu + (float) 0 * (Brick.width + 0.01f), h + (float) 8
		    * (Brick.height + 0.01f) };
	    res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
	    res.add(Brick.makeSimpleBrick(pos1, speed, acc, jpanel, 1));
	    res.add(Brick.makeSimpleBrick(pos2, speed, acc, jpanel, 3));
	    res.add(Brick.makeSimpleBrick(pos3, speed, acc, jpanel, 3));

	} else if (levelId == 6) {
	    float h = 0.1f;

	    // bouche haut
	    for (int j = 0; j < 4; j++) {
		float[] pos = new float[] { milieu + (float) 2 * (Brick.width + 0.01f), h
			+ (float) j * (Brick.height + 0.01f) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 2));
	    }
	    for (int i = -3; i < 0; i++) {
		float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
			+ (float) 4 * (Brick.height + 0.01f) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 2));
	    }
	    // sourcil
	    float[] pos9 = new float[] { milieu + (float) 1 * (Brick.width + 0.01f), h + (float) 2
		    * (Brick.height + 0.01f) };
	    res.add(Brick.makeSimpleBrick(pos9, speed, acc, jpanel, 2));
	    // oeil
	    float[] pos8 = new float[] { milieu + (float) 1 * (Brick.width + 0.01f), h + (float) 3
		    * (Brick.height + 0.01f) };
	    res.add(Brick.makeSimpleBrick(pos8, speed, acc, jpanel, 3));
	    // langue
	    float[] pos10 = new float[] { milieu + (float) 3 * (Brick.width + 0.01f), h + (float) 3
		    * (Brick.height + 0.01f) };
	    res.add(Brick.makeSimpleBrick(pos10, speed, acc, jpanel, 3));
	    float[] pos11 = new float[] { milieu + (float) 4 * (Brick.width + 0.01f), h + (float) 2
		    * (Brick.height + 0.01f) };
	    res.add(Brick.makeSimpleBrick(pos11, speed, acc, jpanel, 3));

	    for (int j = 2; j < 5; j++) {
		float[] pos = new float[] { milieu + (float) 0 * (Brick.width + 0.01f), h
			+ (float) j * (Brick.height + 0.01f) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 2));
	    }
	    // bouche bas
	    for (int i = 1; i < 5; i++) {
		float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
			+ (float) 4 * (Brick.height + 0.01f) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
	    }
	    // cou haut
	    for (int i = -4; i < 3; i++) {
		float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
			+ (float) 5 * (Brick.height + 0.01f) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
	    }
	    for (int i = -5; i < 2; i++) {
		float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
			+ (float) 6 * (Brick.height + 0.01f) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
	    }
	    // carre gauche
	    for (int i = -5; i < -3; i++) {
		for (int j = 7; j < 9; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
			    + (float) j * (Brick.height + 0.01f) };
		    res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
		}
	    }
	    float[] pos12 = new float[] { milieu + (float) (-3) * (Brick.width + 0.01f), h
		    + (float) 7 * (Brick.height + 0.01f) };
	    res.add(Brick.makeSimpleBrick(pos12, speed, acc, jpanel, 2));

	    // cou milieu
	    for (int i = -3; i < 3; i++) {
		float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
			+ (float) 8 * (Brick.height + 0.01f) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 2));
	    }
	    for (int i = -4; i < 4; i++) {
		float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
			+ (float) 9 * (Brick.height + 0.01f) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
	    }
	    for (int i = -3; i < 5; i++) {
		float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
			+ (float) 10 * (Brick.height + 0.01f) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
	    }
	    // carre droit
	    for (int i = 3; i < 5; i++) {
		for (int j = 11; j < 13; j++) {
		    float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
			    + (float) j * (Brick.height + 0.01f) };
		    res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
		}
	    }
	    float[] pos13 = new float[] { milieu + (float) 2 * (Brick.width + 0.01f), h + (float) 11
		    * (Brick.height + 0.01f) };
	    res.add(Brick.makeSimpleBrick(pos13, speed, acc, jpanel, 2));

	    // cou bas
	    for (int i = -2; i < 3; i++) {
		float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
			+ (float) 12 * (Brick.height + 0.01f) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 2));
	    }
	    for (int i = -3; i < 4; i++) {
		float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
			+ (float) 13 * (Brick.height + 0.01f) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
	    }
	    for (int i = -5; i < 3; i++) {
		float[] pos = new float[] { milieu + (float) i * (Brick.width + 0.01f), h
			+ (float) 14 * (Brick.height + 0.01f) };
		res.add(Brick.makeSimpleBrick(pos, speed, acc, jpanel, 1));
	    }
	}
	return res;
    }
}
