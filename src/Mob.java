import java.awt.Graphics;

public abstract class Mob extends Sprite {
	//int x;
	//int y;
	
	
	public Mob(String name, int x, int y, int w, int h, int direction, String[] pose) {
        super(name, x, y, w, h, direction, pose);
    }
	
	/*  With how this is designed this needs to be written before g.draw() is called
	 * 
	 * 	int screenX = x - Camera.getInstance().x + SCREEN WIDTH /2;
		int screenY = y - Camera.getInstance().y + SCREEN HIGHT /2;
		
		This makes sure that the object's x/y will be correctly offset by camera
		TODO (not needed): Could update this code so this line isn't repeated
		
	 * */
	//public abstract void draw(Graphics g);

}


/* Extended Mob class to Sprite class.
* With this we can load images of player and enemies.
* Player position is now loaded into Sprite class which extends Rect class. 
* WF
*/
