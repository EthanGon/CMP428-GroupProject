import java.awt.Graphics;

public class Player extends Mob {
	
	int w;
	int h;
	public boolean UP, DOWN, LEFT, RIGHT;
	public int moveSpeed = 5;
	public static Player playerInstance;
	// XP Progress
	public int level = 1;
	public int xp = 0;
	public int xpToNextLv = 10;
	
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
	
	public float getXPPercent() {
		return (float) xp / xpToNextLv;
	}
	
	public void addXP(int amount) {
		xp += amount;
		
		//handles if a lot of xp is gained from abundance of mobs (double/triple/etc) level-up
		while(xp>=xpToNextLv) {
			xp -= xpToNextLv;
			levelUp();
		}
		
	}
	
	private void levelUp() {
		level++;
		
		// Exponential increase for XP per level
		xpToNextLv = (int)(10 * Math.pow(1.25, level - 1));
		
		System.out.println("LEVEL UP! level: " + level);
	
}

}
