import java.awt.Color;
import java.util.Random;
public class Shape {

	private int shape[][];
	private Color color;
	int res;
	public Color getColor() {
		return color;
	}

	public int[][] getShape() {
		return shape;
	}

	public Shape(){
		Random random = new Random();

		this.shape = new int[2][2];
		res = random.nextInt(5) % 5;
		//res = 4;
		switch(res){
		case 0:
			this.color = Color.yellow;
			this.shape[0][0] = 0;
			this.shape[0][1] = 4;
			this.shape[1][0] = 0;
			this.shape[1][1] = 0;
			break;
		case 1:
			this.color = Color.yellow;
			this.shape[0][0] = 10;
			this.shape[0][1] = 0;
			this.shape[1][0] = 0;
			this.shape[1][1] = 0;
			break;
		case 2:
			this.color = Color.yellow;
			this.shape[0][0] = 3;
			this.shape[0][1] = 4;
			this.shape[1][0] = 0;
			this.shape[1][1] = 0;
			break;
		case 3:
			this.color = Color.yellow;
			this.shape[0][0] = 2;
			this.shape[0][1] = 4;
			this.shape[1][0] = 0;
			this.shape[1][1] = 0;
			break;
		case 4:
			this.color = Color.yellow;
			this.shape[0][0] = 3;
			this.shape[0][1] = 1;
			this.shape[1][0] = 0;
			this.shape[1][1] = 0;
			break;
		default:
			
		}
	}
	
	// rotate  the  shape  clockwise
	public void rotateclockwise(){
		for(int i = 0; i < 2; i ++){
			for(int j = 0; j < 2; j ++){
				if(this.shape[i][j] != 0){
					this.shape[i][j] = rotate(true,this.shape[i][j]);
					
				}
			}
		}
		recon(true);
		
	}
	
	// rotate  the  shape  counter-­clockwise
	public void rotatecounter_clockwise(){
		//System.out.println("counter-­clockwise");
		for(int i = 0; i < 2; i ++){
			for(int j = 0; j < 2; j ++){
				if(this.shape[i][j] != 0){
					this.shape[i][j] = rotate(false,this.shape[i][j]);
				}
			}
		}
		
		recon(false);
	}
	
	private void recon(boolean clock){
		if(clock){
			if(this.shape[0][0] != 0 && this.shape[0][1] != 0){
				this.shape[1][0] = this.shape[0][1];
				this.shape[0][1] = 0;
			}else if(this.shape[0][0] != 0 && this.shape[1][0] != 0){
				this.shape[0][1] = this.shape[0][0];
				this.shape[0][0] = this.shape[1][0];
				this.shape[1][0] = 0;
			}
		}else{
			if(this.shape[0][0] != 0 && this.shape[1][0] != 0){
				this.shape[0][1] = this.shape[1][0];
				this.shape[1][0] = 0;
			}else if(this.shape[0][0] != 0 && this.shape[0][1] != 0){
				this.shape[1][0] = this.shape[0][0];
				this.shape[0][0] = this.shape[0][1];
				this.shape[0][1] = 0;
			}
		}
	}
	private int rotate(boolean clockwise, int m){
		if(m == 10) return 10;
		if(clockwise){
			return (m) % 4 + 1;
		}else{
			if(m == 1)	return 4;
			else return m - 1;
			
		}
	}
}
