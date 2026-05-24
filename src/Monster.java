import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Monster extends Mob {
	
	
	//int w;
	//int h;
	public boolean UP, DOWN, LEFT, RIGHT;
	protected int moveSpeed = 2;
	private final int SIZE = 64;
	private boolean isDead;
	private static int ID = 0;
	private int monsterNum;
	private Image sprite = new ImageIcon(System.getProperty("user.dir") + "/images/Skeleton/Skeleton_L/Skeleton_L_0.png").getImage();
	protected int maxHp = 30;
	protected int currentHp = 30;
	protected int moveSpeed_base = 2;

		//Walk animation. WF
	final static String[] WALK_POSE = {
		    "",
		    "",
		    "",
		    ""
		};


    public void takeDamage(int amount) {
        currentHp -= amount;
        if (currentHp <= 0) killEnemy();
    }
    
	public Monster(int x, int y, int w, int h) {
		super("images/Skeleton", x, y, w, h, 1, WALK_POSE);
		isDead = false;
		monsterNum = ID++;

		//this.x = x;
		//this.y = y;
		//this.w = SIZE;
		//this.h = SIZE;
		System.out.println("Enemy spawned: #" + monsterNum);
	}
		
	/* Every object that uses the draw method need to follow this format
	 * screenX/Y ensure they're located visually on the screen, but they still keep they're real X/Y values
	 * 
	 * screenX - objectWidth, ScreenY - objectHeight 
	 * */
	
	@Override
	public void draw(Graphics g) {
		if(isDead) return;
		int screenX = Camera.getInstance().projectX(x);
		int screenY = Camera.getInstance().projectY(y);
		g.drawImage(sprite, screenX - w/2, screenY - h/2, w, h, null);
		int barX = screenX - w/2;
		int barY = screenY - h/2 - 10;
		float hpPercent = (float) currentHp / maxHp;
		g.setColor(Color.RED);
		g.fillRect(barX, barY, w, 6);
		g.setColor(Color.GREEN);
		g.fillRect(barX, barY, (int)(w * hpPercent), 6);
		g.setColor(Color.WHITE);
		g.drawRect(barX, barY, w, 6);
		/*removed to allow for image of monster to get drawn instead of square
		 * 
		//int screenX = Camera.getInstance().projectX(x); 
		//int screenY = Camera.getInstance().projectY(y);
		
		//g.fillRect(screenX - w/2, screenY - h/2, w, h);
		 *WF
		 */
		 
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
			if(isDead) return;
			if(x > r.x){
				moveLT(moveSpeed);
			} else if (x < r.x) {
				moveRT(moveSpeed);
			}
			if (y > r.y) {
				moveUP(moveSpeed);
			} else if (y < r.y) {
				moveDN(moveSpeed);
			}
			
			/* Removed to adjust for animation of movement 
			 * following movement from sprite class. 
			 * Monster now spawn with walking animation 
			 * WF.
			if(x > r.x) x-= moveSpeed;
			if(x < r.x) x+= moveSpeed;
			
			//wouldn't need this for platform game 
			//coz they go right to left
			if(y > r.y) y-= moveSpeed;
			if(y < r.y) y+= moveSpeed;
			*/
			
		}
		
		public Rect getRect() {
			return new Rect (x - w / 2 , y - h / 2, w , h);
		}

}
