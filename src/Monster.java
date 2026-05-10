import java.awt.Graphics;

public class Monster extends Mob {
	
	
	int w;
	int h;
	public boolean UP, DOWN, LEFT, RIGHT;
	private int moveSpeed = 2;
	private final int SIZE = 64;
	private boolean isDead;
	private static int ID = 0;
	private int monsterNum;


	public Monster(int x, int y) {
		isDead = false;
		monsterNum = ID;
		ID++;
		
		
		this.x = x;
		this.y = y;
		this.w = SIZE;
		this.h = SIZE;
		
		System.out.println("Enemy spawned: #" + monsterNum);
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
	
	public boolean isDead() {
		return isDead;
	}
	
	public void toggleDeadState(boolean val) {
		this.isDead = val;
	}
	
	public void killEnemy() {
		this.isDead = true;
		// TODO: When collision check is added, make sure to ignore that when enemy is dead;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    
	    if (!(o instanceof Monster)) return false;
	    Monster m = (Monster)o;
	    
	    
	    return this.monsterNum == m.monsterNum;
	}

	@Override
	public int hashCode() {
	    int result = Integer.hashCode(this.monsterNum);
	    result = 31 * result + Integer.hashCode(this.monsterNum);
	    return result;
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
		
		public Rect getRect() {
			return new Rect (x - w / 2 , y - h / 2, w , h);
		}

}
