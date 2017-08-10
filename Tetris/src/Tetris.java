import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Tetris extends JFrame{

	
	private GraphicsGrid graphicsGrid;
	//private GraphicsGrid rightPanel;
	
	//private JSlider slider;
	
	
	public Tetris() {
		// TODO Auto-generated constructor stub
		super();
		setLocation(200, 100);
		
		graphicsGrid = new GraphicsGrid(50);
		Thread t1 = new Thread(graphicsGrid);
		t1.start();
		
		add(graphicsGrid,BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		validate();
		setVisible(true);
		
		
	}
	
	
	/**
	 * get right panel
	 * @return right panel
	 */
	/*private JPanel getTopPanel(){
		JPanel panel1 = new JPanel();
		panel1.setPreferredSize(new Dimension(20 * 5, 20 * 10));
		level = new JLabel("Level:     1");
		lines = new JLabel("Lines:     0");
		score = new JLabel("Score:     0");
		quit = new JButton("QUIT");
		quit.setBackground(Color.white);
		
		quit.setPreferredSize(new Dimension(80, 40));
		quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		rightPanel = new GraphicsGrid(50);
		Thread t2 = new Thread(rightPanel);
		t2.start();
		//add to panel
		panel1.setLayout(new GridLayout(3,1));
		
		panel1.add(level);
		panel1.add(lines);
		panel1.add(score);
		
		JPanel panel2 = new JPanel();
		panel2.add(quit,BorderLayout.SOUTH);
		//panel2.setPreferredSize(new Dimension(4000, 50));
		quit.setLocation(0, 0);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(40 * 5, 40 * 10));
		//panel.setLayout(new GridLayout(3,1));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(rightPanel);
		panel.add(panel1);
		panel.add(panel2);
		//panel1.add(highscore);
		
		
		
		
		return panel;
	}*/
	
	
	
	
	
	public static void main(String[] a) {
		// TODO Auto-generated method stub
		new Tetris();
		
	}

}
