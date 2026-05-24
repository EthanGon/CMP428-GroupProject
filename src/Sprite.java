/** This is the sprite class used for loading images. 
 * 
 */
import java.awt.Graphics;

public class Sprite extends Rect
{
	String name;
	boolean moving = false;
	boolean physics = false;
	
	public static final int UP = 0;
	public static final int DN = 1;
	public static final int LT = 2;
	public static final int RT = 3;

	int direction;
	double vx = 0;
	double vy = 0;
	Animation[] animation = new Animation[4];
	
	public Sprite(String name, int x, int y, int w, int h, int direction, String[] pose)
	{
		super(x, y, w, h);
		this.name = name;
		
		for(int i = 0; i < animation.length; i++)
		{
			animation[i] = new Animation(name + "/" + pose[i], 5, 10, "png");
		}		
		
		this.direction = direction;
	}
	
	public void move()
	{
		x += vx;		
		y += vy;
	}
	
	
	public void goUP(int dy)
	{
		vy = -dy;
		
		direction = UP;
		
		moving = true;
	}
	
	public void goDN(int dy)
	{
		vy = dy;

		direction = DN;

		moving = true;
	}
	
	public void goLT(int dx)
	{
		vx = -dx;

		direction = LT;
		
		moving = true;
	}
	
	public void goRT(int dx)
	{
		vx = dx;
		
		direction = RT;

		moving = true;

	}
		
	public void moveUP(int dy)
	{
		y -= dy;
		
		direction = UP;
		
		moving = true;
	}
	
	public void moveDN(int dy)
	{
		y += dy;

		direction = DN;

		moving = true;
	}
	
	public void moveLT(int dx)
	{
		x -= dx;

		direction = LT;
		
		moving = true;
	}
	
	public void moveRT(int dx)
	{
		x += dx;
		
		direction = RT;

		moving = true;

	}
		
	
	public void draw(Graphics g) {
	    // Get the instance of the camera
	    Camera cam = Camera.getInstance();
	    
	    // Project the world coordinates to screen coordinates
	    int screenX = cam.projectX(this.x);
	    int screenY = cam.projectY(this.y);

	    if (moving) {
	        g.drawImage(animation[direction].nextImage(), screenX - w/2, screenY - h/2, w, h, null);
	    } else {
	        g.drawImage(animation[direction].stillImage(), screenX - w/2, screenY - h/2, w, h, null);
	    }       
	    
	    // Reset moving flag for the next frame
	    moving = false;
	}
	
}
