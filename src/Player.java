import java.awt.Graphics;

public class Player extends Mob {
	
	int w;
	int h;
	public boolean UP, DOWN, LEFT, RIGHT;
	public int moveSpeed = 5;
	int[] dir = new int[2];
	public static Player playerInstance;
	
	public Player(int x, int y, int w, int h) {
		this.playerInstance = this;
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		System.out.printf("Player Position: x=%d, y=%d\n", x, y);
		
	}
	
	public static Player getPlayer() {
		return playerInstance;
	}

	@Override
	public void draw(Graphics g) {
		int screenX = Camera.getInstance().projectX(x);
		int screenY = Camera.getInstance().projectY(y);
		
		g.fillRect(screenX - w, screenY - h, w, h);
	}
	
	

}
