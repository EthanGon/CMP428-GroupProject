import java.awt.Graphics;

public class Monster extends Mob {
	
	
	int w;
	int h;
	public boolean UP, DOWN, LEFT, RIGHT;
	private int moveSpeed = 2;


	public Monster(int x, int y, int w, int h) {
		
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		System.out.printf("Player Position: x=%d, y=%d\n", x, y);
		
	}
		
	/* Every object that uses the draw method need to follow this format
	 * screenX/Y ensure they're located visually on the screen, but they still keep they're real X/Y values
	 * 
	 * screenX - objectWidth, ScreenY - objectHeight 
	 * */
	
	@Override
	public void draw(Graphics g) {
		int screenX = Camera.getInstance().projectX(x); 
		int screenY = Camera.getInstance().projectY(y);
		
		g.fillRect(screenX - w/2, screenY - h/2, w, h);
	}
	
	
	//chase another character
		// its gonna happen 60th time per second
		public void chase(Player r) {
			
			if(x > r.x) x-= moveSpeed;
			if(x < r.x) x+= moveSpeed;
			
			//wouldn't need this for platform game 
			//coz they go right to left
			if(y > r.y) y-= moveSpeed;
			if(y < r.y) y+= moveSpeed;
			
		}


}
