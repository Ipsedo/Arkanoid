import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MyJFrame extends JFrame {

	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	
	public MyJFrame(){
		super("Arkanoid");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setSize(MyJFrame.WIDTH, MyJFrame.HEIGHT);
		super.setLocation(200, 200);
		super.getContentPane().setLayout(new BorderLayout());
		
		super.setVisible(true);
	}
	
	public static void main(String[] args) {
		MyJFrame frame = new MyJFrame();
	}
}
