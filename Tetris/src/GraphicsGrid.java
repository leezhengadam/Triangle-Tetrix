import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class GraphicsGrid extends JPanel implements Runnable{

	private JLabel level;
	private JLabel score;
	private JLabel lines;
	private JButton quit;
	private int pixel;
	private int width;
	private int height;
	private int mouseIn = 0;
	private int row,col;
	private int map[][];
	private int nextShape[][];
	private boolean gameover;
	private Shape currentSp;
	private Shape nextSp;
	private int down;
	private int left;
	public GraphicsGrid(int pix) {
		// TODO Auto-generated constructor stub
		this.currentSp = new Shape();
		this.nextSp = new Shape();
		this.down = 0;
		this.left = 2;
		this.gameover = false;
		this.pixel = pix;
		this.row = 10;
		this.col = 5;
		this.width = 5 * this.pixel;
		this.height = 10 * this.pixel;
		
		this.map = new int[this.row + 1][this.col];
		nextShape = new int[1][2];
		for(int i = 0; i < this.row; i ++){
			for(int j = 0; j < this.col; j ++){
				this.map[i][j] = 0;
			}
		}
		for(int j = 0; j < this.col; j ++){
			this.map[this.row][j] = 10;
		}
		for(int i = 0; i < 1; i ++){
			for(int j = 0; j < 2; j ++){
				this.nextShape[i][j] = 0;
			}
		}
		//this.add(new JButton());
		
		level = new JLabel("Level:     1");
		lines = new JLabel("Lines:     0");
		score = new JLabel("Score:     0");
		quit = new JButton("QUIT");
		quit.setBackground(Color.white);
		setLayout(null); 
		level.setBounds(this.width / 2 + 200, this.height / 2 - 100,this.width/2,10);
		lines.setBounds(this.width / 2 + 200, this.height / 2  - 20,this.width/2,10);
		score.setBounds(this.width / 2 + 200, this.height / 2 + 60,this.width/2,10);
		
		quit.setBounds(this.width / 2 + 200, this.height - 100,this.width/2,50);
		
		add(level);
		add(lines);
		add(score);
		add(quit);
		
		quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		setFocusable(true);
		this.addMouseWheelListener(new MouseWheelListener(){

			@Override
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getWheelRotation() > 0){//down
					GraphicsGrid.this.currentSp.rotatecounter_clockwise();
				}else if(arg0.getWheelRotation() < 0){
					GraphicsGrid.this.currentSp.rotateclockwise();
				}
			}
			
		});
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(GraphicsGrid.this.mouseIn == 0){
				if(arg0.getButton() == MouseEvent.BUTTON1){
					//System.out.println("left Button");
					if(left > 0)
						GraphicsGrid.this.left --;
					
				}else if(arg0.getButton() == MouseEvent.BUTTON3){
					//System.out.println("right Button");
					if(left < 3)
						GraphicsGrid.this.left ++;
					
				}
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
		this.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getX() < GraphicsGrid.this.width && e.getX() < GraphicsGrid.this.height ){
					mouseIn = 1;
				}else{
					mouseIn = 0;
				}
					
				repaint();
			}
			
		});
	}
		
	
	@Override
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return new Dimension(width * 2 +4,height + 1);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//draw border
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width, height);
		
		if(mouseIn == 1){
			g.setColor(Color.BLUE);
			g.setFont(new Font("Helvetica",Font.PLAIN,20));
			g.drawString("PAUSE", width / 2 - 30, height / 2 - 20); 
		}
		
		
		//draw left game map
		/*for(int i = 0; i < this.row; i ++){
			for(int j = 0; j < 2; j ++){
				//g.drawRect(0, 0, width, height);
				if(this.currentSp.getShape()[i][j] == 1){
					g.setColor(this.currentSp.getColor());
					int xPoints[] = {this.pixel * (left + j),this.pixel * (left+1+j), this.pixel * (left + j)};
					int yPoints[] = {this.pixel * (down+i), this.pixel * (down+i), this.pixel * ((down+i) + 1)};
					g.fillPolygon(xPoints, yPoints, 3);
					g.setColor(Color.black);
					g.drawPolygon(xPoints, yPoints, 3);
				}else if(this.currentSp.getShape()[i][j] == 2){
					g.setColor(this.currentSp.getColor());
					int xPoints[] = {this.pixel * (left + j),this.pixel * (left+1+j), this.pixel * (left+1+j)};
					int yPoints[] = {this.pixel * (down+i), this.pixel * (down+i), this.pixel * ((down+i) + 1)};
					g.fillPolygon(xPoints, yPoints, 3);
					g.setColor(Color.black);
					g.drawPolygon(xPoints, yPoints, 3);
				}else if(this.currentSp.getShape()[i][j] == 4){
					g.setColor(this.currentSp.getColor());
					int xPoints[] = {this.pixel * (left + j),this.pixel * (left+j), this.pixel * (left + 1+j)};
					int yPoints[] = {this.pixel * (down+i), this.pixel * ((down+i)+1), this.pixel * ((down+i) + 1)};
					g.fillPolygon(xPoints, yPoints, 3);
					g.setColor(Color.black);
					g.drawPolygon(xPoints, yPoints, 3);
				}else if(this.currentSp.getShape()[i][j] == 3){
					g.setColor(this.currentSp.getColor());
					int xPoints[] = {this.pixel * (left+j),this.pixel * (left+1+j), this.pixel * (left + 1+j)};;
					int yPoints[] = {this.pixel * ((down+i)+1), this.pixel * (down+i), this.pixel * ((down+i) + 1)};
					g.fillPolygon(xPoints, yPoints, 3);
					g.setColor(Color.black);
					g.drawPolygon(xPoints, yPoints, 3);
				}else if(this.currentSp.getShape()[i][j] == 10){
					g.setColor(this.currentSp.getColor());
					g.fillRect(this.pixel * left, this.pixel * (down+i), this.pixel, this.pixel);
					g.setColor(Color.black);
					g.drawRect(this.pixel * left, this.pixel * (down+i), this.pixel, this.pixel);
				}
				
				
			}
		}*/
		//System.out.println(this.currentSp.getShape()[0] + "--" + this.currentSp.getShape()[1]);
		for(int i = 0; i < 2; i ++){
			for(int j = 0; j < 2; j ++){
				//g.drawRect(0, 0, width, height);
				if(this.currentSp.getShape()[i][j] == 1){
					g.setColor(this.currentSp.getColor());
					int xPoints[] = {this.pixel * (left + j),this.pixel * (left+1+j), this.pixel * (left + j)};
					int yPoints[] = {this.pixel * (down+i), this.pixel * (down+i), this.pixel * ((down+i) + 1)};
					g.fillPolygon(xPoints, yPoints, 3);
					g.setColor(Color.black);
					g.drawPolygon(xPoints, yPoints, 3);
				}else if(this.currentSp.getShape()[i][j] == 2){
					g.setColor(this.currentSp.getColor());
					int xPoints[] = {this.pixel * (left + j),this.pixel * (left+1+j), this.pixel * (left+1+j)};
					int yPoints[] = {this.pixel * (down+i), this.pixel * (down+i), this.pixel * ((down+i) + 1)};
					g.fillPolygon(xPoints, yPoints, 3);
					g.setColor(Color.black);
					g.drawPolygon(xPoints, yPoints, 3);
				}else if(this.currentSp.getShape()[i][j] == 4){
					g.setColor(this.currentSp.getColor());
					int xPoints[] = {this.pixel * (left + j),this.pixel * (left+j), this.pixel * (left + 1+j)};
					int yPoints[] = {this.pixel * (down+i), this.pixel * ((down+i)+1), this.pixel * ((down+i) + 1)};
					g.fillPolygon(xPoints, yPoints, 3);
					g.setColor(Color.black);
					g.drawPolygon(xPoints, yPoints, 3);
				}else if(this.currentSp.getShape()[i][j] == 3){
					g.setColor(this.currentSp.getColor());
					int xPoints[] = {this.pixel * (left+j),this.pixel * (left+1+j), this.pixel * (left + 1+j)};;
					int yPoints[] = {this.pixel * ((down+i)+1), this.pixel * (down+i), this.pixel * ((down+i) + 1)};
					g.fillPolygon(xPoints, yPoints, 3);
					g.setColor(Color.black);
					g.drawPolygon(xPoints, yPoints, 3);
				}else if(this.currentSp.getShape()[i][j] == 10){
					g.setColor(this.currentSp.getColor());
					g.fillRect(this.pixel * left, this.pixel * (down+i), this.pixel, this.pixel);
					g.setColor(Color.black);
					g.drawRect(this.pixel * left, this.pixel * (down+i), this.pixel, this.pixel);
				}
				
				
			}
		}
		
		/*g.setColor(Color.yellow);
		int xPoints[] = {this.pixel * (j+1),this.pixel * (j+1), this.pixel * j};;
		int yPoints[] = {this.pixel * (i+1), this.pixel * i, this.pixel * (i + 1)};
		g.fillPolygon(xPoints, yPoints, 3);
		g.setColor(Color.black);
		g.drawPolygon(xPoints, yPoints, 3);*/
		
		
		//draw next shape
		g.setColor(Color.BLACK);
		g.drawRect(width / 2 + 200, 10, this.pixel * 2, this.pixel);
		
		int x = width / 2 + 200;
		int y = 10;
		
		for(int j = 0; j < 2; j ++){
			//g.drawRect(0, 0, width, height);
			if(this.nextSp.getShape()[0][j] == 1){
				g.setColor(this.nextSp.getColor());
				int xPoints[] = {x+this.pixel * (j),x+this.pixel * (1+j), x+this.pixel * (j)};
				int yPoints[] = {y+this.pixel * 0, y+this.pixel * 0, y+this.pixel * (0 + 1)};
				g.fillPolygon(xPoints, yPoints, 3);
				g.setColor(Color.black);
				g.drawPolygon(xPoints, yPoints, 3);
			}else if(this.nextSp.getShape()[0][j] == 2){
				g.setColor(this.nextSp.getColor());
				int xPoints[] = {x+this.pixel * (0 + j),x+this.pixel * (0+1+j), x+this.pixel * (0+1+j)};
				int yPoints[] = {y+this.pixel * 0, y+this.pixel * 0, y+this.pixel * (0 + 1)};
				g.fillPolygon(xPoints, yPoints, 3);
				g.setColor(Color.black);
				g.drawPolygon(xPoints, yPoints, 3);
			}else if(this.nextSp.getShape()[0][j] == 4){
				g.setColor(this.nextSp.getColor());
				int xPoints[] = {x+this.pixel * (0 + j),x+this.pixel * (0+j), x+this.pixel * (0 + 1+j)};
				int yPoints[] = {y+this.pixel * 0, y+this.pixel * (0+1), y+this.pixel * (0 + 1)};
				g.fillPolygon(xPoints, yPoints, 3);
				g.setColor(Color.black);
				g.drawPolygon(xPoints, yPoints, 3);
			}else if(this.nextSp.getShape()[0][j] == 3){
				g.setColor(this.nextSp.getColor());
				int xPoints[] = {x+this.pixel * (0+j),x+this.pixel * (0+1+j), x+this.pixel * (0 + 1+j)};;
				int yPoints[] = {y+this.pixel * (0+1), y+this.pixel * 0, y+this.pixel * (0 + 1)};
				g.fillPolygon(xPoints, yPoints, 3);
				g.setColor(Color.black);
				g.drawPolygon(xPoints, yPoints, 3);
			}else if(this.nextSp.getShape()[0][j] == 10){
				g.setColor(this.nextSp.getColor());
				g.fillRect(x+this.pixel * 0, y+this.pixel * 0, this.pixel, this.pixel);
				g.setColor(Color.black);
				g.drawRect(x+this.pixel * 0, y+this.pixel * 0, this.pixel, this.pixel);
			}
			
			
		}
		
		x = y = 0;
		for(int i = 0; i < this.row; i ++){
			for(int j = 0; j < this.col; j ++){
				//g.drawRect(0, 0, width, height);
				g.setColor(this.currentSp.getColor());
				//System.out.print(this.map[i][j] + "  ");
				//System.out.println(i + "--" + j);
				if(this.map[i][j] == 1){
					//g.setColor(this.currentSp.getColor());
					int xPoints[] = {x + this.pixel * j,x + this.pixel * (j+1), x + this.pixel * j};;
					int yPoints[] = {y + this.pixel * i, y + this.pixel * i, y + this.pixel * (i + 1)};
					g.fillPolygon(xPoints, yPoints, 3);
					g.setColor(Color.black);
					g.drawPolygon(xPoints, yPoints, 3);
				}else if(this.map[i][j] == 2){
					//g.setColor(Color.yellow);
					int xPoints[] = {x + this.pixel * j,x + this.pixel * (j+1), x + this.pixel * (j+1)};;
					int yPoints[] = {y + this.pixel * i, y + this.pixel * i, y + this.pixel * (i + 1)};
					g.fillPolygon(xPoints, yPoints, 3);
					g.setColor(Color.black);
					g.drawPolygon(xPoints, yPoints, 3);
				}else if(this.map[i][j] == 4){
					//g.setColor(Color.yellow);
					int xPoints[] = {x + this.pixel * j,x + this.pixel * (j+1), x + this.pixel * j};;
					int yPoints[] = {y + this.pixel * i, y + this.pixel * (i+1),y +  this.pixel * (i + 1)};
					g.fillPolygon(xPoints, yPoints, 3);
					g.setColor(Color.black);
					g.drawPolygon(xPoints, yPoints, 3);
				}else if(this.map[i][j] == 3){
					//g.setColor(Color.yellow);
					int xPoints[] = {x + this.pixel * (j+1),x + this.pixel * (j+1), x + this.pixel * j};;
					int yPoints[] = {y + this.pixel * (i+1),y +  this.pixel * i, y + this.pixel * (i + 1)};
					g.fillPolygon(xPoints, yPoints, 3);
					g.setColor(Color.black);
					g.drawPolygon(xPoints, yPoints, 3);
				}else if(this.map[i][j] == 10){
					//g.setColor(Color.yellow);
					g.fillRect(x + this.pixel * j, y + this.pixel * i, this.pixel, this.pixel);
					g.setColor(Color.black);
					g.drawRect(x + this.pixel * j, y + this.pixel * i, this.pixel, this.pixel);
				}
				
				
			}
			
			//System.out.println();
		}
		
		//System.out.println();System.out.println();
		
	}

	private int abs(int a){
		if(a < 0){
			return -1*a;
		}else{
			return a;
		}
	}
	private boolean judge(int a,int b){
		return (a!=0) && (b!=0) && (abs(a-b)!= 2);
	}
	public boolean canDown(){
		if(down + 2 > this.row) return false;
		if(judge(this.map[down + 2][left],this.currentSp.getShape()[1][0])
				||judge(this.map[down + 1][left],this.currentSp.getShape()[0][0])
				||judge(this.map[down + 2][left+1],this.currentSp.getShape()[1][1])
				||judge(this.map[down + 1][left+1],this.currentSp.getShape()[0][1])){
			return false;
		}else{
			return true;
		}
	}
	
	private void addToMap(){
		
		//System.out.println(down + "---"+left);
		if(this.currentSp.getShape()[0][0] != 0){
		if(this.map[down][left]!=0 && abs(this.map[down][left] - this.currentSp.getShape()[0][0]) == 2){
			this.map[down][left] = 10;
		}else{
			this.map[down][left] = this.currentSp.getShape()[0][0];
		}
		}
		
		if(this.currentSp.getShape()[1][0] != 0){
		if(this.map[down+1][left]!=0 && abs(this.map[down + 1][left] - this.currentSp.getShape()[1][0]) == 2){
			this.map[down + 1][left] = 10;
		}else{
			this.map[down+1][left] = this.currentSp.getShape()[1][0];
		}
		}
		
		if(this.currentSp.getShape()[0][1] != 0){
		if(this.map[down][left+1]!=0 && abs(this.map[down][left + 1] - this.currentSp.getShape()[0][1]) == 2){
			this.map[down][left+1] = 10;
		}else{
			this.map[down][left+1] = this.currentSp.getShape()[0][1];
		}}
		
		if(this.currentSp.getShape()[1][1] != 0){
		if(this.map[down+1][left+1]!=0 && abs(this.map[down+1][left+1] - this.currentSp.getShape()[1][1]) == 2){
			this.map[down+1][left+1] = 10;
		}else{
			this.map[down+1][left+1] = this.currentSp.getShape()[1][1];
		}
		}
		/*this.map[down + 1][left] = this.currentSp.getShape()[1][0];
		this.map[down][left + 1] = this.currentSp.getShape()[0][1];
		this.map[down + 1][left+ 1] = this.currentSp.getShape()[1][1];*/
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!this.gameover){
			//if(mouseIn == 0){
			if(this.mouseIn == 0)
				if(canDown()){
					this.down ++;
				}else{
					if(down == 0)	this.gameover = true;
					addToMap();
					this.currentSp = this.nextSp;
					this.nextSp = new Shape();
					this.down = 0;
					this.left = 2;
					//repaint();
				}
				
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//}
		}
		
	}
	
	

	
	
}
