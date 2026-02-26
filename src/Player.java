import java.awt.Graphics;

public class Player extends Mob {
	
	int w;
	int h;
	public boolean UP, DOWN, LEFT, RIGHT;
	public int moveSpeed = 5;
	int[] dir = new int[2];
	
	public Player(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		System.out.printf("Player Position: x=%d, y=%d\n", x, y);
		
	}

	@Override
	public void draw(Graphics g) {
		int screenX = x - Camera.getInstance().x + 1280/2;
		int screenY = y - Camera.getInstance().y + 720/2;
		
		g.fillRect(screenX - w, screenY - h, w, h);
	}
	
	/* As of right now diagonal movement is faster than normal
	 * this is due to the vectors magnitude being a bit more 
	 * not a big deal but could normalize the direction
	 * */
	public void setDir(int x, int y) {
		dir[0] = x;
		dir[y] = y;
	}

}
