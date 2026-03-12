import java.awt.Graphics;

public class Monsters extends Mob {
	
	
	int w;
	int h;
	public boolean UP, DOWN, LEFT, RIGHT;
	public int moveSpeed = 15;
	public static Monsters mostersInstance;
	// XP Progress
	public int level = 1;
	public int xp = 0;
	public int xpToNextLv = 10;
	
	public Monsters(int x, int y, int w, int h) {
		Monsters.mostersInstance = this;
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		System.out.printf("Player Position: x=%d, y=%d\n", x, y);
		
	}
	
	public static Monsters getPlayer() {
		return mostersInstance;
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
	
	//chase another character
		// its gonna happen 60th time per second
		public void chase(Player r) {
			
			if(x > r.x) x-= 2;
			if(x < r.x) x+= 2;
			
			//wouldn't need this for platform game 
			//coz they go right to left
			if(y > r.y) y-= 2;
			if(y < r.y) y+= 2;
			
		}
	
//	public void addXP(int amount) {
//		xp += amount;
//		
//		//handles if a lot of xp is gained from abundance of mobs (double/triple/etc) level-up
//		while(xp>=xpToNextLv) {
//			xp -= xpToNextLv;
//			levelUp();
//		}
//		
//	}
	
//	private void levelUp() {
//		level++;
//		
//		// Exponential increase for XP per level
//		xpToNextLv = (int)(10 * Math.pow(1.25, level - 1));
//		
//		System.out.println("LEVEL UP! level: " + level);
//	
//   }

}
